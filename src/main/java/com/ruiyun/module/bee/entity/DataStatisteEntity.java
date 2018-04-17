package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 行销团队管理的数据统计实体
 * @author fzy
 * @createTime 2017年6月24日
 */
public class DataStatisteEntity implements Serializable {
	private static final long serialVersionUID = -6705520163812968103L;
	
	private Double attendanceCount;//出勤人数', 
	
	private Integer allowanceCount;//拓客数
	
	private Integer reportCount;//报备数
	
	private Integer visitCount;//到访数
	
	private Integer subscriptionCount;//认购数
	
	private Integer turnoverCount;//成交数
	
	public DataStatisteEntity(){
		
	}
	public DataStatisteEntity(Double attendanceCount,Integer allowanceCount,
			Integer reportCount,Integer visitCount,Integer subscriptionCount,Integer turnoverCount){
		this.attendanceCount = attendanceCount;
		this.allowanceCount = allowanceCount;
		this.reportCount = reportCount;
		this.visitCount = visitCount;
		this.subscriptionCount = subscriptionCount;
		this.turnoverCount = turnoverCount;	
	}

	public Double getAttendanceCount() {
		return attendanceCount;
	}

	public void setAttendanceCount(Double attendanceCount) {
		this.attendanceCount = attendanceCount;
	}

	public Integer getAllowanceCount() {
		return allowanceCount;
	}

	public void setAllowanceCount(Integer allowanceCount) {
		this.allowanceCount = allowanceCount;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getSubscriptionCount() {
		return subscriptionCount;
	}

	public void setSubscriptionCount(Integer subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}

	public Integer getTurnoverCount() {
		return turnoverCount;
	}

	public void setTurnoverCount(Integer turnoverCount) {
		this.turnoverCount = turnoverCount;
	}
	
	
	

}
