package com.example.test1.demo;

import com.example.test1.demo.Model.Login;
import com.example.test1.demo.Model.LoginExample;
import com.example.test1.demo.mapper.LoginMapper;
import com.example.test1.demo.service.RegService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.test1.demo.mapper")
public class DemoApplication  implements ApplicationRunner {
    @Autowired
    private LoginMapper loginMapper;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        String sb="hxf";
        log.info("日志框架："+sb);
       // String id= RegService
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("begin:");
	  //generateArtifacts(); //生成
 //      playWithArtifacts(); //演示
    }

    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void playWithArtifacts() {
        Login login=Login.builder().name("hxf").phone("135388e1957").build();
        Login login2=Login.builder().name("hxf").phone("13538d61957").build();
        log.info(login.toString());
        log.info(login2.toString());
        loginMapper.insert(login2);//插入
        loginMapper.insert(login);//插入
        Login s = loginMapper.selectByPrimaryKey(1L);//查询
        log.info("logio {}", s);
        LoginExample example = new LoginExample();
        example.createCriteria().andNameEqualTo("hxf");

        example.setOrderByClause("id desc");
        List<Login> list = loginMapper.selectByExample(example);//查询
        list.forEach(e -> log.info("selectByExample: {}", e));
    }

}

