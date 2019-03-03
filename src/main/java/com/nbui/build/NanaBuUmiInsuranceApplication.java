package com.nbui.build;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages= {"com.nbui"})
@MapperScan(basePackages= {"com.nbui.employee.dao","com.nbui.policy.dao","com.nbui.user.dao","com.nbui.provinceCity.dao","com.nbui.role.dao"})
public class NanaBuUmiInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NanaBuUmiInsuranceApplication.class, args);
	}

}