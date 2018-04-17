package com.ruiyun.module.bee.proxy;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.module.bee.entity.BeeCommonEntity;

@Repository("beeAttendProxy")
public interface BeeAttendProxy {
	
	/**
	 * 拓客数
	 * @param beePersonnelId
	 * @param personnelPermission
	 * @return
	 */
	public Integer getAllowanceCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 报备数
	 * @param beePersonnelId
	 * @return
	 */
	public Integer getReportCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 到访数
	 * @param beePersonnelId
	 * @return
	 */
	public Integer getVisitCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 认购数
	 * @param beePersonnelId
	 * @return
	 */
	public Integer getSubscripteCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 成交数
	 * @param beePersonnelId
	 * @return
	 */
	public Integer getTurnoverCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	

}
