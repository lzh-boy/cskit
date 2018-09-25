package com.bizvane.utils.mongoutils;

import com.bizvane.utils.exception.BizException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/2 17:12
 */
@Configuration
//@EnableMongoRepositories(basePackages = "com.bizvane.**.mongo",mongoTemplateRef = "diseaseMongoTemplate",repositoryBaseClass = MongoTemplateServiceImpl.class)
@ConditionalOnProperty(name = "spring.data.mongodb.database")
@EnableMongoRepositories(basePackages = "com.bizvane.**.mongo",repositoryBaseClass = MongoTemplateServiceImpl.class)
public class MongoConfig {
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        mappingConverter.setCustomConversions(customConversions());
        return mappingConverter;
    }
    @Bean
    public CustomConversions customConversions() {
        List list = new ArrayList();
        list.add(new TimestampConverter());
        CustomConversions.StoreConversions storeConversions = CustomConversions.StoreConversions.NONE;
        return new CustomConversions(storeConversions,list);
    }
   /* @Bean(name = "diseaseMongoTemplate")
    public MongoTemplate diseaseMongoTemplate() throws Exception {
        MappingMongoConverter converter = new MappingMongoConverter(
                new DefaultDbRefResolver(mongodbFactory()),
                new MongoMappingContext());

        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(mongodbFactory(), converter);
    }
    @Bean
    public MongoDbFactory mongodbFactory() throws Exception {
        MongoClientURI mongoClientUri = new MongoClientURI("");
        return new SimpleMongoDbFactory(new MongoClient(mongoClientUri), "0");
    }*/
}
