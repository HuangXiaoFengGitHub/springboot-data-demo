package com.example.test1.demo.dao;

import com.example.test1.demo.DemoApplication;
import com.example.test1.demo.Model.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ActivityRepositoryTest {
    @Autowired
    ActivityRepository activityRepository ;
    @Test
    public void testSelect()
    {
        Activity activity=activityRepository.findByActivityId(1L);
        log.info(activity.toString());
        Assert.assertEquals(1L,activity.getActivityId());
    }
}
