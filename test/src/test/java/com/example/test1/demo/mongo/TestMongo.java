package com.example.test1.demo.mongo;

import com.example.test1.demo.DemoApplication;
import com.example.test1.demo.Model.mongo.MgUser;
import com.example.test1.demo.dao.mongo.MongoUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

/**
 * mongodb测试
 *
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestMongo {
    @Autowired
    private MongoUserRepository userRepository;

    @Test
    public void testMonogo() {
        MgUser mgUser = MgUser.builder().mgid(1L).nickname("py2").phone("1234").build();
        mgUser.setMgid(1L);
        //mgUser.setNickname("p");
        mgUser.setPhone("18650140605");
        //插入成功后_id属性有值(mongo数据库生成的)
        userRepository.insert(mgUser);
//        MgUser mgUsers=userRepository.findByNickname("py2");
  //      log.info(mgUsers.toString());
        //该方法使用的是mongodb中ObjectId为条件的
//        MgUser selectMgUser = userRepository.findOne(1L);
//        MgUser selectMgUser = userRepository.findByNickname("pysasuke");
        MgUser selectMgUser = userRepository.findByMgid(1L); //转换为对象
        log.info(selectMgUser.toString());
        Assert.assertEquals(mgUser.getMgid(), selectMgUser.getMgid());
    }
}
