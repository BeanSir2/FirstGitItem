/**
 * 
 */
package com.ruiyun.module.bee.proxy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.module.bee.entity.AttenDetailEntity;
import com.ruiyun.module.bee.entity.SignEntity;

/**
 * @className:AttenDetailProxy
 * @author:   wurong
 * @date:     2017年7月11日 下午3:30:01
 * @version:
 */
@Repository
public interface AttenDetailProxy {
	
	/**
	 * 列表
	 * @param pageParams
	 * @param beePersonnelId
	 * @param attendDate
	 * @return
	 */
	public List<AttenDetailEntity> attenList(
			@Param("pageParams") JQGridPageParams pageParams,
			@Param("beePersonnelId") Integer beePersonnelId,
			@Param("attendDate") String attendDate);

	
	/**
	 * 总条数
	 * @param beePersonnelId
	 * @param attendDate
	 * @return
	 */
	public Long getattenCount(@Param("beePersonnelId") Integer beePersonnelId,@Param("attendDate") String attendDate);
	
	
	
	/**
	 * 根据beePersonnelId和 attendDate查询personnel_name
	 * @param beePersonnelId
	 * @param attendDate
	 * @return
	 */
	public Map<String, Object> findPersonnelName(@Param("beePersonnelId") Integer beePersonnelId,@Param("attendDate") String attendDate);
	

	
	/**
	 * 导出列表
	 * @param beePersonnelId
	 * @param attendDate
	 * @return
	 */
    public List<Map<String, Object>> attDetailData(@Param("beePersonnelId") Integer beePersonnelId,@Param("attendDate") String attendDate);

}
