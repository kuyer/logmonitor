package io.github.kuyer.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.github.kuyer.model.LogInfoMdl;

/**
 * 日志分析Service
 * @author rory.zhang
 */
@Service
public class LogAnalyzerService {

	@Async
	public void analyzer(LogInfoMdl logInfo, String content) {
		// TODO Auto-generated method stub
		
	}

}
