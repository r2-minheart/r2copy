package com.r2comms.r2copy.dto;

import com.r2comms.r2copy.entity.Job;
import com.r2comms.r2copy.entity.User;
import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JobResponseDto {

	private Long jobId;

	private String jobName;
	

	private int workerId;

	private int status;
	
	private boolean isActive;

	private String rclonePath;
	
	private String rcloneCmd;
	
	private String rcloneOption;
	
	private String srcPath;
	
	private String targetPath;
	
	private String logPath;
	
	private String description;

    private void toJob(Job entity) {
    	this.jobId = entity.getJobId();
    
    	this.status = entity.getStatus();
    	this.workerId = entity.getWorkerId();
    	this.jobName = entity.getJobName();
    	this.isActive = entity.isActive();
    	
    	this.rcloneCmd = entity.getRcloneCmd();
    	this.rcloneOption = entity.getRcloneOption();
    	this.rclonePath = entity.getRclonePath();
    	
    	this.srcPath = entity.getSrcPath();
    	this.targetPath = entity.getTargetPath();
    
    	this.logPath = entity.getLogPath();
    	
    	this.description = entity.getDescription();
    }

    public JobResponseDto(Job entity) {
        this.toJob(entity);
    }
}
