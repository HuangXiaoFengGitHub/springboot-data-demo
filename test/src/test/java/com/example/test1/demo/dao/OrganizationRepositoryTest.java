package com.example.test1.demo.dao;

import com.example.test1.demo.BaseTest;
import com.example.test1.demo.Model.Organization;
import com.example.test1.demo.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.rowset.BaseRowSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class OrganizationRepositoryTest  extends BaseTest {
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    UserRepostory userRepostory;
    @Test
    public void test()
    {
        //User user=User.builder().nickName("黄钢").phone("13538861957").build();
        User user=new User();
        user.setNickName("黄钢");
        //Organization organization=Organization.builder().organizationName("青年志愿者协会").email("2018hxf@gamil.com").build();
        Organization organization=new Organization();
        organization.setOrganizationName("青年志愿者协会！");
        organization.setEmail("2018hxf@gamil.com");

        Set<Organization> organizations=user.getLikeOrganizations();
        log.info(organizations.toString());
        user.getLikeOrganizations().add(organization);
        userRepostory.save(user);
        log.info(user.toString());
        User selectUser=userRepostory.findByUserId(user.getUserId());
        Set<Organization> organizationList=selectUser.getLikeOrganizations();
        log.info(selectUser.toString());
        log.info(selectUser.getLikeOrganizations().toString());
        Assert.assertTrue(organizationList.contains(organization));
    }
}
