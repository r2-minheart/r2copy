package com.r2comms.r2copy.service;

import com.r2comms.r2copy.repository.JobHistoryRepository;
import com.r2comms.r2copy.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class JobHistoryService {
    private final JobHistoryRepository jobHistoryRepository;
}
