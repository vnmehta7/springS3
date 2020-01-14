package com.samples.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class S3Application {
	public static void main(String[] args) {
		SpringApplication.run(S3Application.class, args);
	}
}
