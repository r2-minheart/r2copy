package com.r2comms.r2copy;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class R2copyApplication implements ApplicationRunner  {

	public static void main(String[] args) {
		
		
		for (int i=0; i < args.length; i++) {
			System.out.println("------------------:" + args[i]);
		}
				
		SpringApplication.run(R2copyApplication.class, args);
		
	}
	
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
	    for (String name : args.getOptionNames()){
	    	System.out.println(name + "=" + args.getOptionValues(name).get(0));
	    }
	}

}
