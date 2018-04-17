package com.ruiyun.module.bee.entity;

import java.io.Serializable;
/**
 * 这个实体只适用于返回报备数、拓客数等数据结果的返回
 * @author fzy
 * @createTime 2017年6月30日
 */
public class DataCommonEntity implements Serializable {
	private static final long serialVersionUID = -538242469125986150L;
	
	private Double signCount;//签到数
	
	private Integer tokerCount;//拓客数
	
	private Integer reportCount;//报备数
	
	private Integer visitCount;//到访数

	private Integer subscripteCount;//认购数
	
	private Integer turnoverCount;//成交数/签约数
	
	
	public DataCommonEntity(){
		
	}
	public DataCommonEntity(Double signCount,
			Integer tokerCount,Integer reportCount,Integer visitCount,Integer subscripteCount,Integer turnoverCount){
		this.signCount = signCount;
		this.tokerCount = tokerCount;
		this.reportCount = reportCount;
		this.visitCount = visitCount;
		this.subscripteCount = subscripteCount;
		this.turnoverCount = turnoverCount;
	}
	
	public Double getSignCount() {
		return signCount == null ? 0 :signCount;
	}

	public void setSignCount(Double signCount) {
		this.signCount = signCount;
	}

	public Integer getTokerCount() {
		return tokerCount;
	}

	public void setTokerCount(Integer tokerCount) {
		this.tokerCount = tokerCount;
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

	public Integer getSubscripteCount() {
		return subscripteCount;
	}

	public void setSubscripteCount(Integer subscripteCount) {
		this.subscripteCount = subscripteCount;
	}

	public Integer getTurnoverCount() {
		return turnoverCount;
	}

	public void setTurnoverCount(Integer turnoverCount) {
		this.turnoverCount = turnoverCount;
	}
	
	
}
