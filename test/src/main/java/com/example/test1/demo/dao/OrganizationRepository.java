package com.example.test1.demo.dao;

import com.example.test1.demo.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

}
