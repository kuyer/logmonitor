package io.github.kuyer.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.github.kuyer.service.LogMonitorService;

@Component
public class LogMonitorTask {
	
	@Autowired
	private LogMonitorService logMonitorService;
	
	@Scheduled(cron="${logmonitor.execute.cron}")
	public void execute() {
		logMonitorService.monitor();
	}

}
