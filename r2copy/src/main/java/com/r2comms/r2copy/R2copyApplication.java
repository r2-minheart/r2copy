package com.r2comms.r2copy;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class R2copyApplication {

	public static void main(String[] args) {
		SpringApplication.run(R2copyApplication.class, args);
	}
	
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
