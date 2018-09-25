package com.bizvane.utils.mongoutils;

import com.bizvane.utils.responseinfo.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Micro
 * @Title: MongoDB
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/2 11:25
 */
public class MongoTemplateServiceImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements MongoTemplateService<T, ID> {
    private final static Logger logger = LoggerFactory.getLogger(MongoTemplateServiceImpl.class);

    protected final MongoOperations mongoTemplate;

    @Autowired
    protected final MongoEntityInformation<T, ID> entityInformation;

    private Class<T> clazz;
    public MongoTemplateServiceImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoTemplate = mongoOperations;
        entityInformation = metadata;
        this.clazz = entityInformation.getJavaType();
    }

    @Override
    public boolean singleSave(T t) {
        if (null != t) {
            try {
                save(t);
                return true;
            } catch (Exception ex) {
                logger.error("单条添加MongoDB失败：入参:{}", t, ex);
                return false;
            }
        }
        return false;
    }

    @Override
    public <S extends T> int batchSave(List<S> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            list = list.stream().filter(o -> Objects.nonNull(o)).collect(Collectors.toList());
            if (list.size() > 0)
                try {
                    return saveAll(list).size();
                } catch (Exception ex) {
                    logger.error("批量添加MongoDB失败：入参:{}", list, ex);
                    return 0;
                }
        }
        return 0;
    }

    @Override
    public boolean singleDelete(ID id) {
        if (id != null) {
            try {
                deleteById(id);
                return true;
            } catch (Exception ex) {
                logger.error("单条删除MongoDB失败：入参:{}", id, ex);
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean batchDelete(List<ID> list) {
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(list)) {
            Query query = new Query(Criteria.where("_id").in(list));
            try {
                mongoTemplate.findAllAndRemove(query, clazz);
                return true;
            } catch (Exception ex) {
                logger.error("批量删除MongoDB失败：入参:{}", list, ex);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(ID id, Map<String, Object> updateMap) {
        if (updateMap != null || !updateMap.isEmpty()) {
            Criteria criteria = new Criteria("_id").is(id);
            Update update = new Update();
            updateMap.entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
            try {
                mongoTemplate.findAndModify(new Query(criteria), update, clazz);
                return true;
            } finally {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(Map<String, Object> queryParam, Map<String, Object> updateField) {
        if (queryParam != null || !queryParam.isEmpty()) {
            List<Criteria> criteriaList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
                criteriaList.add(Criteria.where(entry.getKey()).is(entry.getValue()));
            }

            int size = criteriaList.size();
            Criteria[] criterias = new Criteria[size];
            for (int i = 0; i < size; i++) {
                criterias[i] = criteriaList.get(i);
            }
            Criteria criteria = new Criteria().andOperator(criterias);

            if (updateField != null || !updateField.isEmpty()) {
                Update update = new Update();
                updateField.entrySet().forEach(entry -> update.set(entry.getKey(), entry.getValue()));
                try {
                    mongoTemplate.findAndModify(new Query(criteria), update, clazz);
                    return true;
                } finally {
                    return false;
                }

            }

        }
        return false;
    }

    @Override
    public PageInfo<T> findPage(int pageIndex, int pageSize, Map<String, Integer> sort) {
        List<Sort.Order> orders = new ArrayList<>();
        Pageable pageable = null;
        if (sort != null && !sort.isEmpty()) {
            sort.entrySet().forEach(entry -> orders.add(new Sort.Order(entry.getValue() == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, entry.getKey())));
            pageable = PageRequest.of(pageIndex, pageSize, new Sort(orders));
        } else {
            pageable = PageRequest.of(pageIndex, pageSize);
        }
        Page<T> page = findAll(pageable);
        PageInfo<T> pageInfo = new PageInfo<T>(pageIndex, pageSize);
        pageInfo.setList(page.getContent());
        pageInfo.setTotal((int) page.getTotalElements());
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setSize(page.getContent().size());
        return pageInfo;
    }

    @Override
    public PageInfo<T> findPageWithParam(Map<String, Object> queryParam, Integer pageIndex, Integer pageSize, Map<String, Integer> sortParam) {
        if (queryParam == null || queryParam.isEmpty()) {
            return findPage(pageIndex, pageSize, sortParam);
        }

        List<Criteria> criteriaList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : queryParam.entrySet()) {
            criteriaList.add(Criteria.where(entry.getKey()).is(entry.getValue()));
        }

        Criteria[] criterias = new Criteria[criteriaList.size()];
        criterias = criteriaList.toArray(criterias);
        Criteria criteria = new Criteria().andOperator(criterias);

        Query query = new Query(criteria);

        if (sortParam != null && !sortParam.isEmpty()) {
            List<Sort.Order> orders = new ArrayList<>();
            sortParam.entrySet().forEach(entry -> orders.add(new Sort.Order(entry.getValue() == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, entry.getKey())));
            Sort sort = Sort.by(orders);
            query.with(sort);
        }
        long total = this.mongoTemplate.count(query, clazz);
        query.skip(pageIndex * pageSize);
        query.limit(pageSize);

        List<T> data = this.mongoTemplate.find(query, clazz);

        PageInfo<T> pageInfo = new PageInfo<>(pageIndex, pageSize);
        pageInfo.setTotal((int) total);
        pageInfo.setList(data);
        pageInfo.setSize(data.size());
        return pageInfo;
    }
}
