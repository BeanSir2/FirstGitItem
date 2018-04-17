package com.ruiyun.module.bee.proxy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.module.bee.entity.BeeCommonEntity;

@Repository("beeSignProxy")
public interface BeeSignProxy {
	
	//查询所有数据(签到数据默认今天)
	public Double getAllCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 查询签到所有数据(默认今天)
	 * @param entity
	 * @return
	 */
	public List<Map<String, Object>>getSignData(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	

}
