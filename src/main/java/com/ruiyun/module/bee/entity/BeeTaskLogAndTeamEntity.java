package com.ruiyun.module.bee.entity;


/**
 * @className:BeeTaskLogAndTeamEntity
 * @author:   wurong
 * @date:     2017年6月26日 上午10:17:50
 * @version:
 */
public class BeeTaskLogAndTeamEntity extends BeeTaskLogEntity{

	private static final long serialVersionUID = 6517142714291365733L;
	
	private Integer beePersonnelId;
	private Integer parentBeePersonnelId;
	private Integer companyId;
	private String companyName;
	private String parentBeePersonnelName;
	private String personnelName;
	
	
	
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getParentBeePersonnelName() {
		return parentBeePersonnelName;
	}
	public void setParentBeePersonnelName(String parentBeePersonnelName) {
		this.parentBeePersonnelName = parentBeePersonnelName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getBeePersonnelId() {
		return beePersonnelId;
	}
	public void setBeePersonnelId(Integer beePersonnelId) {
		this.beePersonnelId = beePersonnelId;
	}
	public Integer getParentBeePersonnelId() {
		return parentBeePersonnelId;
	}
	public void setParentBeePersonnelId(Integer parentBeePersonnelId) {
		this.parentBeePersonnelId = parentBeePersonnelId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
