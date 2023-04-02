package com.r2comms.r2copy.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="job")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "jobList")
public class Job extends BaseEntity {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long jobId;
    
	@Column(length = 50, unique=true, nullable = false)
	private String jobName;
	
	@Column(columnDefinition = "integer default 1")
	private int workerId;
	
	@Column(columnDefinition = "integer default 0")
	private int status;
	
	@Column(columnDefinition = "boolean default true")
	private boolean isActive;
	
	@Column(length = 100)
	private String rclonePath;
	
	@Column(length = 10)
	private String rcloneCmd;
	
	@Column(length = 100)
	private String rcloneOption;
	
	@Column(length = 100)
	private String srcPath;
	
	@Column(length = 100)
	private String targetPath;
	
	@Column(length = 100)
	private String logPath;
	
	@Column(length = 1000)
	private String description;
	
	// one to many
	// relation : role --> user
	@Builder.Default
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<JobHistory> jobList = new ArrayList<JobHistory>();


	public void updateStatus(int status2) {
		// TODO Auto-generated method stub
		this.status = status2;
	}

}
