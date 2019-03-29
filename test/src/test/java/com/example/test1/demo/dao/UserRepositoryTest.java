package com.example.test1.demo.dao;

import com.example.test1.demo.BaseTest;
import com.example.test1.demo.Model.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseTest {
    @Autowired
    UserRepostory userRepostory;
    @Autowired
    ActivityRepository activityRepository;
    @Test
    public void test()
    {
        userRepostory.deleteAll();
        User user= User.builder().phone("13538861967").build();
        userRepostory.save(user);
        User select=userRepostory.findByUserId(1L);

        Assert.assertEquals(user.getPhone(),select.getPhone());
    }
}
