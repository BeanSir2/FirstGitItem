/**
 * 
 */
package com.ruiyun.module.bee.entity;

import java.math.BigDecimal;

/**
 * @className:SignEntity
 * @author:   wurong
 * @date:     2017年7月6日 上午11:11:30
 * @version:
 */
public class SignEntity extends SubscriptionEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519294143349930910L;
	
	private BigDecimal onlineSignAmount;
	private String onlineSignTime;
	public BigDecimal getOnlineSignAmount() {
		return onlineSignAmount;
	}
	public void setOnlineSignAmount(BigDecimal onlineSignAmount) {
		this.onlineSignAmount = onlineSignAmount;
	}
	public String getOnlineSignTime() {
		return onlineSignTime;
	}
	public void setOnlineSignTime(String onlineSignTime) {
		this.onlineSignTime = onlineSignTime;
	}
	
}
