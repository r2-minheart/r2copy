package com.r2comms.r2copy.service;

import com.r2comms.r2copy.dto.JobResponseDto;
import com.r2comms.r2copy.entity.Job;
import com.r2comms.r2copy.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

	public List<JobResponseDto> getList() {
		return jobRepository.findAll(Sort.by(Sort.Direction.DESC, "jobId"))
				.stream()
				.map(entity -> new JobResponseDto(entity))
				.collect(Collectors.toList());	
	}
	
	public List<JobResponseDto> getListByWorkerId(int workId) {	 
		return jobRepository.findByWorkerIdAndStatusAndIsActive(workId, 0, true)
				.stream()
				.map(entity -> new JobResponseDto(entity))
				.collect(Collectors.toList());	
	}

	public void updateStatus(Long jobId, int status) {
		jobRepository.findById(jobId).ifPresent(job -> {
			job.updateStatus(status);
			jobRepository.save(job);
		});
	}

	public JobResponseDto getOne(Long jobId) {
		Optional<Job> result =	jobRepository.findById(jobId);		
		return result.isPresent() ? new JobResponseDto(result.get()) : null;
	}
}
