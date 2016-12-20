package io.github.kuyer.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.kuyer.model.LogInfoMdl;

@Service
public class LogMonitorService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private volatile boolean isInit = false;
	
	private Map<String, LogInfoMdl> logInfoMap;
	
	@Value("${logmonitor.logfile.paths}")
	private String logFilePaths;
	
	@PostConstruct
	public void init() {
		logger.info("init logmonitor start.");
		logInfoMap = new ConcurrentHashMap<>();
		logger.info("init logmonitor finish.");
	}

	public void monitor() {
		if(!isInit) {
			logger.warn("logmonitor is not init.");
			return;
		}
		// TODO Auto-generated method stub
	}

}
