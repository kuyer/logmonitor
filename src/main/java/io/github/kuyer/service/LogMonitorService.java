package io.github.kuyer.service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.kuyer.model.LogInfoMdl;
import io.github.kuyer.util.SequenceUtil;

@Service
public class LogMonitorService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private volatile boolean isInit = false;
	
	private Map<String, LogInfoMdl> logInfoMap;
	
	@Value("${logmonitor.logfile.paths}")
	private String logFilePaths;
	@Autowired
	private LogHandService logHandService;
	
	@PostConstruct
	public void init() {
		logger.info("init logmonitor start.");
		if(null==logFilePaths || logFilePaths.trim().equals("")) {
			logger.warn("`logFilePaths` is empty, init fail.");
			return;
		}
		logInfoMap = new HashMap<>();
		Date date = new Date();
		String[] filePaths = logFilePaths.split(";");
		for(String filePath : filePaths) {
			filePath = filePath.trim();
			if("".equals(filePath)) {
				continue;
			}
			try {
				RandomAccessFile raFile = new RandomAccessFile(filePath, "r");
				String id = SequenceUtil.getSequence();
				LogInfoMdl logInfo = new LogInfoMdl();
				logInfo.setId(id);
				logInfo.setFilePath(filePath);
				logInfo.setRaFile(raFile);
				logInfo.setRunning(false);
				logInfo.setLastPosition(0);
				logInfo.setVersion(0);
				logInfo.setCreateTime(date);
				logInfo.setUpdateTime(date);
				logInfoMap.put(id, logInfo);
			} catch (Exception e) {
				logger.error("open {} error.", filePath);
			}
		}
		if(logInfoMap.size()<=0) {
			logger.info("init no loginfos, retry after 5s");
			try {
				Thread.sleep(5000);
				init();
			} catch (InterruptedException e) {
				logger.error("retry error", e);
			}
			return;
		}
		isInit = true;
		logger.info("init logmonitor finish.");
	}

	public void monitor() {
		if(!isInit) {
			logger.warn("logmonitor is not init.");
			return;
		}
		Iterator<Entry<String, LogInfoMdl>> iterator = logInfoMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, LogInfoMdl> entry = iterator.next();
			if(null!=entry && null!=entry.getValue()) {
				logHandService.handler(entry.getValue());
			}
		}
	}
	
	@PreDestroy
	public void destory() {
		logger.info("destory logmonitor start.");
		isInit = false;
		while(logInfoMap.size()>0) {
			Iterator<Entry<String, LogInfoMdl>> iterator = logInfoMap.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, LogInfoMdl> entry = iterator.next();
				LogInfoMdl logInfo = iterator.next().getValue();
				if(null != logInfo) {
					if(!logInfo.isRunning()) {
						try {
							logInfo.getRaFile().close();
						} catch (IOException e) {
							logger.error("close {} error.", logInfo);
							logger.error("close RandomAccessFile error.", e);
						}
						logInfoMap.remove(entry.getKey());
					}
				} else {
					logInfoMap.remove(entry.getKey());
				}
			}
		}
		logger.info("destory logmonitor finish.");
	}

	public Map<String, LogInfoMdl> getLogInfoMap() {
		return logInfoMap;
	}

}
