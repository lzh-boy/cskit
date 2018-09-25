package com.bizvane.utils.esutils;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.io.Serializable;

/**
 * @author Micro
 * @Title: ES操作
 * @Package com.bizvane.utils.esutils
 * @Description: ES操作
 * @date 2018/7/20 16:47
 */
public interface EsTemplateService<T extends  Serializable, ID extends Serializable> extends ElasticsearchRepository<T, ID> {
}
