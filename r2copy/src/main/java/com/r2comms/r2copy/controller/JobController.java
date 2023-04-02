package com.r2comms.r2copy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.r2comms.r2copy.dto.JobResponseDto;
import com.r2comms.r2copy.dto.UserResponseDto;
import com.r2comms.r2copy.service.JobService;
import com.r2comms.r2copy.service.UserService;

@Log4j2
@Controller
@RequestMapping({"/job"})
@RequiredArgsConstructor
public class JobController {
	final private JobService jobService;

    @GetMapping({"list"})
    public String list(Model model) {
        return "job/list";
    }
    
    @GetMapping({"detail"})
    public String detail(Model model) {
        return "job/detail";
    }
   
	@ResponseBody
    @PostMapping("data.json")
    public  List<JobResponseDto> data(String requestURL) {
		log.info("order json2 " + requestURL);
		List<JobResponseDto> data  = null;
		
		data = jobService.getList();
		return data;
    }
}
