package org.ltq.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Application类与Controller类要有共同的父包
 * @author 罗天齐
 *
 */
@SpringBootApplication
@ComponentScan("org.ltq")	//扫描org.ltq包下的Component
@MapperScan("org.ltq.mapper")	//扫描Mapper接口
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
