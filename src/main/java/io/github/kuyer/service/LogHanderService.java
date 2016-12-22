package io.github.kuyer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.github.kuyer.model.LogInfoMdl;

/**
 * 日志处理服务
 * @author rory.zhang
 */
@Service
public class LogHanderService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LogAnalyzerService logAnalyzerService;

	@Async
	public void handler(LogInfoMdl logInfo) {
		long fileLen = 0l;
		try {
			fileLen = logInfo.getRaFile().length();
		} catch (Exception e) {
			logger.error("read "+logInfo.toString()+" file len error.", e);
		}
		if(fileLen <= 0l) {
			logger.warn("file len is 0. logInfo: {}", logInfo);
			return;
		}
		if(fileLen == logInfo.getLastPosition()) {
			logger.warn("file len eq last position. logInfo: {}", logInfo);
			return;
		}
		//FIXME running的安全性
		logInfo.setRunning(true);
		if(fileLen < logInfo.getLastPosition()) {
			logInfo.setLastPosition(0);
		}
		StringBuilder sb = new StringBuilder("");
		try {
			logInfo.getRaFile().seek(logInfo.getLastPosition());
			String str = null;
			while(null != (str=logInfo.getRaFile().readLine())) {
				str = new String(str.getBytes("ISO8859-1"), "UTF-8");
				if(null != str) {
					sb.append(str.trim());
				}
			}
		} catch (Exception e) {
			logger.error("read file log error. logInfo: "+logInfo, e);
		}
		String content = sb.toString().trim();
		if(!"".equals(content)) {
			logAnalyzerService.analyzer(logInfo, content);
		}
		logInfo.setRunning(false);
	}

}
