package com.r2comms.r2copy.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="job_history")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JobHistory {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long jobHistoryId;
	
	// history
	private LocalDateTime startDate;
	private LocalDateTime endDate;
		
	private int result;	
	
	private String errCode;
	private String msg;
	
	// many to one
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="jobId", nullable = false)
	private Job job;

	public void changeRole(Job job) {
		if (this.job != null) {
			this.job.getJobList().remove(this);
		}
		this.job = job;
		this.job.getJobList().add(this);
	}

}
