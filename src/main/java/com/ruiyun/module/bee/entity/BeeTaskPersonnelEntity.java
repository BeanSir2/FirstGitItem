package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 行销管理_任务参与人
 * @author fzy
 * @createTime 2017年6月23日
 */
public class BeeTaskPersonnelEntity implements Serializable{

	private static final long serialVersionUID = -5985552443707035195L;
	
	private Integer beeTaskPersonnelId;
	
	private Integer beeTaskId;
	
	private Integer beePersonnelId;
	
	private Integer createBeePersonnelId;
	
	private String createTime;

	public Integer getBeeTaskPersonnelId() {
		return beeTaskPersonnelId;
	}

	public void setBeeTaskPersonnelId(Integer beeTaskPersonnelId) {
		this.beeTaskPersonnelId = beeTaskPersonnelId;
	}

	public Integer getBeeTaskId() {
		return beeTaskId;
	}

	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
	}

	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}

	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}

	public Integer getCreateBeePersonnelId() {
		return createBeePersonnelId;
	}

	public void setCreateBeePersonnelId(Integer createBeePersonnelId) {
		this.createBeePersonnelId = createBeePersonnelId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
