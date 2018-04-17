/**
 * 
 */
package com.ruiyun.module.bee.entity;


/**
 * @className:VisitEntity
 * @author:   wurong
 * @date:     2017年7月5日 下午5:44:30
 * @version:
 */
public class VisitEntity extends ReportEntity{

	
	private static final long serialVersionUID = 2722763995884186109L;
	private String confirmVisitTime;
	
	public String getConfirmVisitTime() {
		return confirmVisitTime;
	}
	public void setConfirmVisitTime(String confirmVisitTime) {
		this.confirmVisitTime = confirmVisitTime;
	}
	
}
