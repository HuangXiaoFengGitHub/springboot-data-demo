package com.example.test1.demo.service;

import com.example.test1.demo.Model.User;

public interface IRegService {
    boolean regUser(String userId,String pwd);
    User findUser(String userId);
}
