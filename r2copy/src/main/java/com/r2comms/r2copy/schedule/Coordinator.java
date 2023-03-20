package com.r2comms.r2copy.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.r2comms.r2copy.rclone.Cmd;
import com.r2comms.r2copy.rclone.CommandLineExecutor;
import com.r2comms.r2copy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping
@Service
@RequiredArgsConstructor
public class Coordinator {
	
	private final Cmd cmd;
	
	private final CommandLineExecutor comm2;
	
	@Scheduled(cron = "0 */1 * * * *")
	public void checkJob() {
		log.info("checkJob----" + Thread.currentThread().getName());
		
		String strCommand = "d:\\rclone-v1.61.1-windows-amd64\\rclone copy d:\\rclone-v1.61.1-windows-amd64\\A goo:/A/W-A --bwlimit 10M -v --progress --log-file=d:\\rclone-v1.61.1-windows-amd64\\rclone.log";
		
		// String command = cmd.inputCommand(strCommand);		
		// log.info(cmd.execCommand(command));		
		
		comm2.execute(strCommand);
	}
	
}
