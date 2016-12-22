package io.github.kuyer.model;

import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 日志信息
 * @author rory.zhang
 */
public class LogInfoMdl implements Serializable {

	private static final long serialVersionUID = -5558327920226484353L;
	
	/** 编号 **/
	private String id;
	/** 日志位置 **/
	private String filePath;
	/** 读取的文件 **/
	private RandomAccessFile raFile;
	/** 是否正在运行 **/
	private boolean running;
	/** 位置位置 **/
	private long lastPosition;
	/** 变更版本 **/
	private long version;
	/** 告警词 **/
	private List<String> warnWords;
	/** 过滤词 **/
	private List<String> filterWords;
	/** 屏蔽词 **/
	private List<String> shieldWords;
	/** 创建时间 **/
	private Date createTime;
	/** 修改时间 **/
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public RandomAccessFile getRaFile() {
		return raFile;
	}
	public void setRaFile(RandomAccessFile raFile) {
		this.raFile = raFile;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public long getLastPosition() {
		return lastPosition;
	}
	public void setLastPosition(long lastPosition) {
		this.lastPosition = lastPosition;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public List<String> getWarnWords() {
		return warnWords;
	}
	public void setWarnWords(List<String> warnWords) {
		this.warnWords = warnWords;
	}
	public List<String> getFilterWords() {
		return filterWords;
	}
	public void setFilterWords(List<String> filterWords) {
		this.filterWords = filterWords;
	}
	public List<String> getShieldWords() {
		return shieldWords;
	}
	public void setShieldWords(List<String> shieldWords) {
		this.shieldWords = shieldWords;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "LogInfoMdl [id=" + id + ", filePath=" + filePath + ", raFile=" + raFile + ", running=" + running
				+ ", lastPosition=" + lastPosition + ", version=" + version + ", warnWords=" + warnWords
				+ ", filterWords=" + filterWords + ", shieldWords=" + shieldWords + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
