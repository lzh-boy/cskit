package com.bizvane.utils.mongoutils;

import com.bizvane.utils.responseinfo.PageInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Micro
 * @Title: MongoDB 工具类
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/2 10:25
 */
@NoRepositoryBean
public interface MongoTemplateService<T, ID extends Serializable> extends MongoRepository<T, ID> {

    /**
    　　* @Description: 单个模型添加
    　　* @param ${tags} 
    　　* @return ${return_type} 
    　　* @throws
    　　* @author Micro
    　　* @date 2018/7/4 20:01
    　　*/
    boolean singleSave(T t);

    /**
     * 　　* @Description: 批量添加
     * 　　* @param list S列表
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author Micro
     * 　　* @date 2018/7/2 15:16
     */
    <S extends T> int batchSave(List<S> list);

    /**
    　　* @Description: 单条删除
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @author Micro
    　　* @date 2018/7/4 20:06
    　　*/
    boolean singleDelete(ID id);

    /**
     * 　　* @Description: 批量删除
     * 　　* @param list ID列表
     * 　　* @return boolean
     * 　　* @throws
     * 　　* @author Micro
     * 　　* @date 2018/7/2 15:15
     */
    boolean batchDelete(List<ID> list);

    /**
     * 　　* @Description: ${todo}
     * 　　* @param id 更新主键
     *
     * @param updateMap key:需要更新的属性  value:对应的属性值
     *                  　　* @return ${return_type}
     *                  　　* @throws
     *                  　　* @author Micro
     *                  　　* @date 2018/7/2 15:11
     */
    boolean update(ID id, Map<String, Object> updateMap);

    /**
     * 　　* @Description: 批量更新
     * 　　* @param queryParam 查询参数
     *
     * @param updateField 更新参数
     *                    　　* @return boolean
     *                    　　* @throws
     *                    　　* @author Micro
     *                    　　* @date 2018/7/2 15:18
     */
    boolean update(Map<String, Object> queryParam, Map<String, Object> updateField);

    /**
     * 　　* @Description: 分页查询列表
     * 　　* @param pageIndex
     *
     * @param pageSize
     * @param sort     排序 key:排序字段 value:升序0或降序1
     *                 　　* @return ${return_type}
     *                 　　* @throws
     *                 　　* @author Micro
     *                 　　* @date 2018/7/2 15:20
     */
    PageInfo<T> findPage(int pageNum, int pageSize, Map<String, Integer> sort);


    /**
     * 　　* @Description: 带条件的查询分页
     * 　　* @param queryParam
     *
     * @param pageNum
     * @param pageSize
     * @param sortParam 　　* @return ${return_type}
     *                  　　* @throws
     *                  　　* @author Micro
     *                  　　* @date 2018/7/2 15:20
     */
    PageInfo<T> findPageWithParam(Map<String, Object> queryParam, Integer pageNum, Integer pageSize, Map<String, Integer> sortParam);
}
