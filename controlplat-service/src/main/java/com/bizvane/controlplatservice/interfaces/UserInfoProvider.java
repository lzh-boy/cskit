package com.bizvane.controlplatservice.interfaces;

import com.bizvane.controlplatservice.models.CtrlAccountModel;
import com.bizvane.controlplatservice.models.UserInfo;
import com.bizvane.utils.responseinfo.PageInfo;
import com.bizvane.utils.responseinfo.ResponseData;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
@Api()
public interface UserInfoProvider {

    @ApiOperation(value = "无内容返回", notes = "无内容", tags = {"示例"})
    @GetMapping("/nocontent")
    ResponseData<String> noContent();

    @ApiOperation(value = "Mongo添加", notes = "演示Mongo示例", tags = {"Mongo"})
    @GetMapping("/saveuserinfotomongo")
    ResponseData<String> saveUserInfoToMongo();

    @ApiOperation(value = "获取Mongo数据", notes = "获取单条Mongo数据", tags = {"Mongo"})
    @GetMapping("/getsingleuserinfo")
    ResponseData<UserInfo> getSingleUserInfo(@RequestParam(value = "id", defaultValue = "10000000000", required = true) Long id);

    @ApiOperation(value = "获取列表Mongo数据", notes = "获取列表Mongo数据", tags = {"Mongo"})
    @GetMapping("/getlistuserinfo")
    ResponseData<List<UserInfo>> getListUserInfo();

    @ApiOperation(value = "Mongo删除", notes = "演示Mongo示例", tags = {"Mongo"})
    @GetMapping("/deleteuserinfotomongo")
    ResponseData<String> deleteUserInfoToMongo(@RequestParam(value = "id", defaultValue = "10000000000", required = true) Long id);

    @ApiOperation(value = "获取分页数据", notes = "演示Mongo示例", tags = {"Mongo"})
    @GetMapping("/getpageuserinfomongo")
    PageInfo<UserInfo> getPageUserInfoMongo(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2")  int pageSize);

    @ApiOperation(value = "触发异常", notes = "触发自定义异常", tags = {"自定义异常"})
    @GetMapping("/touchexception")
    ResponseData<String> touchException();

    @ApiOperation(value = "缓存数据", notes = "缓存测试", tags = {"Redis"})
    @GetMapping("/addcachedata")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "缓存Key", required = true, example = "random_key"),
            @ApiImplicitParam(name = "value", value = "缓存Value", required = true, example = "XXXOOOO")
    })
    ResponseData<UserInfo> addCacheData(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "value", required = true) String value);

    @ApiOperation(value = "获取单个信息", notes = "单个信息", tags = {"示例"})
    @GetMapping("/getdatetime")
    ResponseData<String> getDateTime(@ApiParam(name = "iscurrenttime", value = "是否获取当前时间", required = true) @RequestParam(value = "iscurrenttime",required = true) boolean iscurrenttime);

    @ApiOperation(value = "列表返回", notes = "列表数据返回", tags = {"示例"})
    @GetMapping("/getUserList")
    ResponseData<List<UserInfo>> getUserList() throws Exception;

    @ApiOperation(value = "获取用户分页信息", notes = "用户分页信息", tags = {"示例"})
    @GetMapping("/getuserinfobypage")
    ResponseData<PageInfo<UserInfo>> getUserInfoByPage(@RequestParam(value = "pageno", defaultValue = "1", required = false) int pageno, @RequestParam(value = "pagesize", defaultValue = "5") int pagesize);

    @ApiOperation(value = "ES获取单个信息", notes = "ES获取单个信息", tags = {"ElasticSearch"})
    @GetMapping("/getaccountfromes")
    ResponseData<CtrlAccountModel> getAccountFromES(@RequestParam(value = "ctrlaccountid", defaultValue = "7", required = true) Long ctrlaccountid);

    @ApiOperation(value = "获取ES分页数据", notes = "演示ES分页示例", tags = {"ElasticSearch"})
    @GetMapping("/getpageaccountfromes")
    PageInfo<CtrlAccountModel> getPageAccountFromES(@RequestParam(value = "pagenum", defaultValue = "0") int pagenum, @RequestParam(value = "pagesize", defaultValue = "2")  int pagesize);

    @ApiOperation(value = "接收内容", notes = "接收内容", tags = {"kafka"})
    @GetMapping("/kafkareceive")
    ResponseData<String> kafkaReceive();

    @ApiOperation(value = "发送内容", notes = "发送内容", tags = {"kafka"})
    @GetMapping("/kafkasend")
    ResponseData<String> kafkaSend(@RequestParam(value = "content", defaultValue = "kafka发送的内容") String content);
}
