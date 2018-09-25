package com.cskit.utils.jobutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * @author chen.li
 * @date on 2018/7/12 17:44
 * @description
 * @Copyright (c) 2018 上海商帆信息科技有限公司-版权所有
 */
@Component
public class JobClient {

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${xxl.job.admin.addresses}")
    private String[] jobAdminUrl;

    private static String add ="/jobinfo/add";
    private static String update ="/jobinfo/update";
    private static String remove ="/jobinfo/remove";
    private static String removeByBiz ="/jobinfo/removeByBiz";
    private static String pause ="/jobinfo/pause";
    private static String resume ="/jobinfo/resume";
    private static String getJobInfoByBiz ="/jobinfo/getJobInfoByBiz";

    public  ResponseEntity<String> addJob(XxlJobInfo xxlJobInfo) {
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(xxlJobInfo);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(add), request , String.class );
        return response;
    }

    public  ResponseEntity<String> updateJob(XxlJobInfo xxlJobInfo) {
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(xxlJobInfo);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(update), request , String.class );
        return response;
    }

    public  ResponseEntity<String> removeJob(Integer xxlJobInfoId) {
        HttpEntity<Integer> request = getIntegerHttpEntity(xxlJobInfoId);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(remove), request , String.class );
        return response;
    }

    public  ResponseEntity<String> removeByBiz(XxlJobInfo xxlJobInfo) {
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(xxlJobInfo);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(removeByBiz), request , String.class );
        return response;
    }

    public  ResponseEntity<String> pauseJob(int xxlJobInfoId) {
        HttpEntity<Integer> request = getIntegerHttpEntity(xxlJobInfoId);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(pause), request , String.class );
        return response;
    }

    public  ResponseEntity<String> resumeJob(int xxlJobInfoId) {
        HttpEntity<Integer> request = getIntegerHttpEntity(xxlJobInfoId);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(resume), request , String.class );
        return response;
    }

    public  ResponseEntity<String> getJobInfoByBizJob(XxlJobInfo xxlJobInfo) {
        HttpEntity<MultiValueMap<String, String>> request = getMultiValueMapHttpEntity(xxlJobInfo);
        ResponseEntity<String> response = restTemplate.postForEntity( getLoadUrl(getJobInfoByBiz), request , String.class );
        return response;
    }

    /**
     * 获取负载的job调度url
     * @param method
     * @return
     */
    public String getLoadUrl(String method){
        int length = jobAdminUrl.length;
        Random random = new Random();
        int i = random.nextInt(length);
        String url = jobAdminUrl[i]+method;
        return url;
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(XxlJobInfo xxlJobInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> xxlJobInfoMap = MapUtil.obj2Map(xxlJobInfo);
        return new HttpEntity<>(xxlJobInfoMap, headers);
    }

    private HttpEntity<Integer> getIntegerHttpEntity(Integer xxlJobInfoId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(xxlJobInfoId, headers);
    }
}
