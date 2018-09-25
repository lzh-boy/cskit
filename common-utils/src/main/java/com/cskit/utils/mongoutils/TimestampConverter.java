package com.cskit.utils.mongoutils;

import org.springframework.core.convert.converter.Converter;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/5 12:47
 */
public class TimestampConverter implements Converter<Date,Timestamp> {
    @Override
    public Timestamp convert(Date date) {
        if(date != null){
            return new Timestamp(date.getTime());
        }
        return null;
    }
}
