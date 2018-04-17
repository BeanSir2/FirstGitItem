package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 行销管理_人员异动日志
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeePersonnelLogEntity implements Serializable{

	private static final long serialVersionUID = 6513259629555710793L;
	
	private Integer beePersonnelLogId;
	
	private Integer beePersonnelId;
	
	private String createTime;
	
	private String logContent;
	
	public BeePersonnelLogEntity(){
		
	}
	
	public BeePersonnelLogEntity(Integer beePersonnelId,String logContent){
		this.beePersonnelId = beePersonnelId;
		this.logContent = logContent;
	} 

	public Integer getBeePersonnelLogId() {
		return beePersonnelLogId;
	}

	public void setBeePersonnelLogId(Integer beePersonnelLogId) {
		this.beePersonnelLogId = beePersonnelLogId;
	}

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	
}
