/**
 * 
 */
package com.ruiyun.module.bee.entity;

/**
 * @className:ExtenEntity
 * @author:   wurong
 * @date:     2017年6月28日 下午3:15:41
 * @version:
 */
public class ExtenEntity extends DataStatisteEntity{
	
	public ExtenEntity() {
		super();
	}
	private static final long serialVersionUID = 1626180820176226692L;
	private String createTime;
	private String personnelName;
	private String taskName;
	private Integer beeTaskId;
	private Integer beePersonnelId;
	
	
	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}
	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}
	public Integer getBeeTaskId() {
		return beeTaskId;
	}
	public void setBeeTaskId(Integer beeTaskId) {
		this.beeTaskId = beeTaskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public ExtenEntity(String createTime, String personnelName, String taskName,Double attendanceCount,Integer allowanceCount,
			Integer reportCount,Integer visitCount,Integer subscriptionCount,Integer turnoverCount) {
		super(attendanceCount,allowanceCount,reportCount, visitCount,subscriptionCount, turnoverCount);
		this.createTime = createTime;
		this.personnelName = personnelName;
		this.taskName = taskName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
}
