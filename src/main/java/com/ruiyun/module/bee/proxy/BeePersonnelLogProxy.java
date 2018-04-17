package com.ruiyun.module.bee.proxy;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.module.bee.entity.BeePersonnelLogEntity;

@Repository("beePersonnelLogProxy")
public interface BeePersonnelLogProxy {

	/**
	 * 添加日志
	 * @param entity
	 * @return
	 */
	public Long insertSelective(BeePersonnelLogEntity entity);
	
	/**
	 * 根据beePersonnelId查询日志
	 * @param beePersonnelId
	 * @return
	 */
	public List<BeePersonnelLogEntity> selectByBeePersonnelId(@Param("beePersonnelId")Integer beePersonnelId);
	
	/**
	 * 根据beeTeamId查询日志
	 * @param selectByBeeTeamId
	 * @return
	 */
	public List<BeePersonnelLogEntity> selectByBeeTeamId(@Param("beeTeamId")Integer beeTeamId);
	
	

}
