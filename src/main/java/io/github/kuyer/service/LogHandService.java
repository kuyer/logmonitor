package io.github.kuyer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.github.kuyer.model.LogInfoMdl;

@Service
public class LogHandService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Async
	public void handler(LogInfoMdl logInfo) {
		if(logInfo.isRunning()) {
			logger.warn("logInfo: {} is running", logInfo);
			return;
		}
		long fileLen = 0l;
		try {
			fileLen = logInfo.getRaFile().length();
		} catch (Exception e) {
			logger.error("read "+logInfo.toString()+" file len error.", e);
		}
		if(fileLen <= 0l) {
			logger.warn("file len is 0, do not read.");
			return;
		}
		if(fileLen == logInfo.getLastPosition()) {
			logger.warn("file len eq last position, do not read.");
			return;
		}
		//TODO
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
			// TODO: handle exception
		}
		String content = sb.toString();
		if(!"".equals(content)) {
			//TODO
		}
	}

}
