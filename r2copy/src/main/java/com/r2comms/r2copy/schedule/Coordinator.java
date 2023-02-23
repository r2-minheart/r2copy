package com.r2comms.r2copy.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping
@Service
public class Coordinator {
	
	@Scheduled(cron = "0 */1 * * * *")
	public void checkJob() {
		log.info("checkJob----" + Thread.currentThread().getName());
	}
	
}
