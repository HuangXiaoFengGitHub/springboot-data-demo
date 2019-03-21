package com.example.test1.demo.util;

import com.example.test1.demo.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
public class JacksonTest  {
    ObjectMapper mapper =new ObjectMapper();
    @Test
    public void test() throws Exception {
        User friend = User.builder().id("1").pwd("19960704").userId("1996").build();
        //写为字符串
        String text = mapper.writeValueAsString(friend);
        // 写为文件
        mapper.writeValue(new File("friend.json"), friend);
        // 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(friend);
        System.out.println(text);
        // 从字符串中读取
        User newFriend = mapper.readValue(text, User.class);
        // 从字节流中读取
        newFriend = mapper.readValue(bytes, User.class);
        // 从文件中读取
        newFriend = mapper.readValue(new File("friend.json"), User.class);
        System.out.println(newFriend);
    }
}
