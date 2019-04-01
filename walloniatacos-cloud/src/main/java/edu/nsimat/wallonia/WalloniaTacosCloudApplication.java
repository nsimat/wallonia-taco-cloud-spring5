package edu.nsimat.wallonia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WalloniaTacosCloudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(WalloniaTacosCloudApplication.class, args);
		for(String name: context.getBeanDefinitionNames()) {
			log.info("Bean: " + name);
		}
	}

}
