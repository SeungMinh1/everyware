package com.yedam.app.attend.attend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yedam.app.attend.attend.mapper.AttendMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfiguration {
	@Autowired
	AttendMapper attendMapper;
	
	@Scheduled(cron = "0 30 23 * * *")
	public void autoOut() {
		log.info("Scheduler 실행");
		attendMapper.autoUpdate();
		
	}
}
