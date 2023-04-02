package com.r2comms.r2copy.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.r2comms.r2copy.dto.JobResponseDto;
import com.r2comms.r2copy.rclone.Cmd;
import com.r2comms.r2copy.rclone.CommandLineExecutor;
import com.r2comms.r2copy.service.JobService;
import com.r2comms.r2copy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping
@Service
@RequiredArgsConstructor
public class Coordinator {
//	@Value("${file.encoding}")
//	String encoding;
//	
//	@Value("${workerId}")
//	private String workerId;
	
	private final Cmd cmd;
	
	private final CommandLineExecutor comm2;
	
	private final JobService jobService;
	
	private final int workId = 1;
	
	// String command = cmd.inputCommand(strCommand);		
	// log.info(cmd.execCommand(command));	
	
	@Scheduled(cron = "0 */10 * * * *")
	public void checkJob() {
		log.info("checkJob----" + Thread.currentThread().getName() );
	
		
		List<JobResponseDto> jobDtoList = jobService.getListByWorkerId(workId) ;
		
		
		// String strCommand = "d:\\rclone-v1.61.1-windows-amd64\\rclone copy d:\\rclone-v1.61.1-windows-amd64\\A goo:/A/TARGET --bwlimit 10M -v --progress --log-file=d:\\rclone-v1.61.1-windows-amd64\\rclone.log";
		// comm2.execute(strCommand);
		
		for (JobResponseDto jobDto : jobDtoList) {
			
			jobService.updateStatus(jobDto.getJobId(), 1);

			String strCommand = jobDto.getRclonePath() + " " + jobDto.getRcloneCmd()  + " " + jobDto.getSrcPath() + " " + jobDto.getTargetPath() + " " + jobDto.getRcloneOption() + " --log-file " + jobDto.getLogPath();
			log.info(strCommand);
			comm2.execute(strCommand);
			
			jobService.updateStatus(jobDto.getJobId(), 0);
		}
	}
	
}
