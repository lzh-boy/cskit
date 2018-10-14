package com.cskit.controlplatserviceimpl.controllers;

import com.cskit.controlplatserviceimpl.ControlPlatServiceImplApplication;
import com.cskit.controlplatserviceimpl.mappers.UserInfoMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @Title: UserInfoProviderImplTest
 * @Description: TODO
 * @date 2018/10/137:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class UserInfoProviderImplTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void noContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/nocontent")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void saveUserInfoToMongo() {
    }

    @Test
    public void getListUserInfo() {
    }

    @Test
    public void getSingleUserInfo() {
    }

    @Test
    public void deleteUserInfoToMongo() {
    }

    @Test
    public void getPageUserInfoMongo() {
    }

    @Test
    public void touchException() {
    }

    @Test
    public void addCacheData() {
    }

    @Test
    public void getDateTime() {
    }

    @Test
    public void getUserList() {
    }

    @Test
    public void getUserInfoByPage() {
    }

    @Test
    public void kafkaReceive() {
    }

    @Test
    public void kafkaSend() {
    }
}