package io.github.kuyer.model;

import java.io.Serializable;
import java.util.Date;

public class LogInfoMdl implements Serializable {

	private static final long serialVersionUID = -5558327920226484353L;
	
	/** 编号 **/
	private String id;
	/** 日志位置 **/
	private String filePath;
	/** 是否正在运行 **/
	private boolean running;
	/** 位置位置 **/
	private long lastPosition;
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
		return "LogInfoMdl [id=" + id + ", filePath=" + filePath + ", running=" + running + ", lastPosition="
				+ lastPosition + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
