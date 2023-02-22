package com.r2comms.rclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class RcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RcloneApplication.class, args);
	}
	
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
