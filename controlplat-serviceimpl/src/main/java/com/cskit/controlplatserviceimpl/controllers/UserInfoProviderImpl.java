package com.cskit.controlplatserviceimpl.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

import com.cskit.basecontroller.BaseController;
import com.cskit.controlplatservice.interfaces.UserInfoProvider;
//import com.cskit.controlplatservice.models.CtrlAccountModel;
import com.cskit.controlplatservice.models.UserInfo;
import com.cskit.controlplatserviceimpl.config.SwaggerConfig;
//import com.cskit.controlplatserviceimpl.es.CtrlAccountESRepository;
import com.cskit.controlplatserviceimpl.mappers.UserInfoMapper;
import com.cskit.controlplatserviceimpl.mongo.UserInfoMongoRepository;
//import com.cskit.utils.esutils.EsTemplateService;
import com.cskit.utils.exception.BizException;
import com.cskit.utils.kafkautils.KafkaConsumerClient;
import com.cskit.utils.kafkautils.KafkaProducerClient;
import com.cskit.utils.redisutils.RedisTemplateServiceImpl;
import com.cskit.utils.responseinfo.PageInfo;
import com.cskit.utils.responseinfo.ResponseData;
import com.cskit.utils.tenant.TenantNotInterceptor;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api(value = "公共工具示例", tags = {"公共示例List"})
@RestController
public class UserInfoProviderImpl extends BaseController implements UserInfoProvider {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoProviderImpl.class);

    @Autowired
    SwaggerConfig swaggerConfig;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoMongoRepository userInfoMongoRepository;

    @Autowired
    private RedisTemplateServiceImpl<String, UserInfo> redisTemplateService;

/*    @Autowired
    private CtrlAccountESRepository ctrlAccountESRepository;*/

    @Autowired
    private KafkaProducerClient<String> kafkaProducerClient;


    @Autowired
    private KafkaConsumerClient<String> kafkaConsumerClient;

    /**
     * 　　* @Description: ${todo}
     * 　　* @param ${tags}
     * 　　* @return void
     * 　　* @throws
     * 　　* @author Micro
     * 　　* @date 2018/6/12 17:25
     */
    public UserInfoProviderImpl() {
    }

    @Override
    public ResponseData<String> noContent() {
        return new ResponseData<>("提示消息");
    }

    @Override
    public ResponseData<String> saveUserInfoToMongo() {
        logger.error(String.valueOf(swaggerConfig.getShowinfo()) + "消息");
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(10000000000000000L + new Random().nextLong() * 10000);
        userInfo.setUsername(String.valueOf(new Random().nextInt(1000)));
        userInfo.setAdddatetime(new Timestamp(LocalDateTime.now().getSecond()));
        userInfoList.add(userInfo);
        userInfo = new UserInfo();
        userInfo.setId(10000000000000000L + new Random().nextLong() * 10000);
        userInfo.setUsername(String.valueOf(new Random().nextInt(1000)));
        userInfo.setAdddatetime(new Timestamp(LocalDateTime.now().getSecond()));
        userInfoList.add(userInfo);
        if (userInfoMongoRepository.batchSave(userInfoList) > 0) {
            return new ResponseData<>("添加成功");
        } else
            return new ResponseData<>("添加失败");
    }

    @Override
    public ResponseData<List<UserInfo>> getListUserInfo() {
        return new ResponseData<List<UserInfo>>(userInfoMongoRepository.findAll());
    }

    @Override
    public ResponseData<UserInfo> getSingleUserInfo(Long id) {
        return new ResponseData<UserInfo>(userInfoMongoRepository.findById(id).orElse(new UserInfo()));
    }

    @Override
    public ResponseData<String> deleteUserInfoToMongo(@RequestParam(value = "id", defaultValue = "10000000000L", required = true) Long id) {
        if (0 > id) {
            throw new BizException(100, "id异常", "id不能小于0", Level.FINER);
        } else {
            try {
                userInfoMongoRepository.singleDelete(id);
                return new ResponseData<String>("删除成功");
            } catch (Exception ex) {
                return new ResponseData<String>("删除失败");
            }
        }
    }

    @Override
    public PageInfo<UserInfo> getPageUserInfoMongo(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        return userInfoMongoRepository.findPage(pageNum, pageSize, null);
    }


    @Override
    public ResponseData<String> touchException() {
        throw new BizException("自定义异常", "异常详细信息", Level.FINE);
    }

    @Override
    public ResponseData<UserInfo> addCacheData(String key, String value) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(10000000000000000L);
        userInfo.setUsername(value.concat("-").concat(String.valueOf(new Random().nextInt(1000))));
        userInfo.setAdddatetime(new Timestamp(LocalDateTime.now().getSecond()));
        redisTemplateService.hashPushHashMap("name", key, userInfo);
        return new ResponseData<UserInfo>(200, "缓存数据", redisTemplateService.hashGet("name", key));
    }

    /**
     * 　　* @description: ${todo}
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author micro
     * 　　* @date 2018/6/12 17:24
     */
    @Override
    public ResponseData<String> getDateTime(@ApiParam(name = "iscurrenttime", value = "是否获取当前时间", required = true) @RequestParam(name = "iscurrenttime", value = "iscurrenttime", required = true) boolean iscurrenttime) {
        return new ResponseData<String>("提示消息", String.format("{\"datetime-services\":\"%s\",\"datetime\":\"%s\"}", "datetime-services", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    @Override
    public ResponseData<List<UserInfo>> getUserList() throws Exception {
        List<UserInfo> userInfoList = userInfoMapper.getAll();
        logger.error("异常测试");
        return new ResponseData<List<UserInfo>>("提示消息", userInfoList);
    }

    @Override
    public ResponseData<PageInfo<UserInfo>> getUserInfoByPage(@RequestParam(value = "pageno", defaultValue = "1", required = false) int pageno, @RequestParam(value = "pagesize", defaultValue = "5") int pagesize) {
        PageHelper.startPage(pageno, pagesize);
        List<UserInfo> dbUserList = userInfoMapper.getAll();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(dbUserList);
        return new ResponseData<PageInfo<UserInfo>>("提示消息", pageInfo);
    }

    /*@Override
    public ResponseData<CtrlAccountModel> getAccountFromES(Long ctrlaccountid) {
        CtrlAccountModel ctrlAccountModel = null;

      *//*  //  查询组合开始
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = QueryBuilders.termQuery("position", "企业管理员");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("ctrl_account_id").from(0, true).to(1000, true);
        RangeQueryBuilder dateRangeQueryBuilder = QueryBuilders.rangeQuery("create_date").gt("2018-07-12T18:34:31.000Z").lt("2018-07-23T19:53:39.000Z").includeLower(true).includeUpper(true);
        boolQueryBuilder.must(queryBuilder).must(dateRangeQueryBuilder).must(rangeQueryBuilder);
        Iterable<CtrlAccountModel> accountModels = ctrlAccountESRepository.search(boolQueryBuilder);
        //  开发组合结束

        ctrlAccountModel = ctrlAccountESRepository.findById(ctrlaccountid).orElse(new CtrlAccountModel());*//*
        return new ResponseData<CtrlAccountModel>("提示消息", ctrlAccountModel);
    }*/

    /*@Override
    public PageInfo<CtrlAccountModel> getPageAccountFromES(int pagenum, int pagesize) {
        PageInfo<CtrlAccountModel> pageInfo = null;
*//*        Pageable pageable = PageRequest.of(pagenum, pagesize, Sort.Direction.DESC, "create_date");
        org.springframework.data.domain.Page<CtrlAccountModel> page = ctrlAccountESRepository.findAll(pageable);
        if (StringUtils.isEmpty(page))
            pageInfo = new PageInfo<>();
        else {
            pageInfo = new PageInfo<>();
            pageInfo.setList(page.getContent());
            pageInfo.setTotal((int) page.getTotalElements());
            pageInfo.setPageNum(pagenum);
            pageInfo.setPageSize(pagesize);
            pageInfo.setSize(page.getContent().size());
        }*//*
        return pageInfo;
    }*/

    @Override
    public ResponseData<String> kafkaReceive() {
        new Thread(() -> {
            kafkaConsumerClient.receive(Collections.singletonList("etl-test-2"), 1000L, x -> x.forEach(y -> System.out.println(y.key() + ":" + y.value())));
        }).start();
        return new ResponseData<String>("接收端已开启");
    }

    @Override
    public ResponseData<String> kafkaSend(String content) {
        kafkaProducerClient.send("etl-test-2", "etl-".concat(String.valueOf(LocalDateTime.now().getNano())), content);
        return new ResponseData<String>("已发送内容:".concat(content));
    }
}


