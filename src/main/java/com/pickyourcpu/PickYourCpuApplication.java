package com.pickyourcpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class PickYourCpuApplication {

	public static void main(String[] args) {
		SpringApplication.run( PickYourCpuApplication.class, args);
	}
}
