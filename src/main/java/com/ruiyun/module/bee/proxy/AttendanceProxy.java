/**
 * 
 */
package com.ruiyun.module.bee.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.ace.jqgrid.JQGridPageParams;

/**
 * @className:AttendanceProxy
 * @author:   wurong
 * @date:     2017年6月27日 下午4:22:00
 * @version:
 */
@Repository
public interface AttendanceProxy {
	
	
	/**
	 * 考勤列表
	 * @param pageParams
	 * @param beeTeamId
	 * @param personnelName
	 * @param beeTaskId
	 * @return
	 */
	public List<HashMap<String, Object>> getAtt(
			@Param("pageParams") JQGridPageParams pageParams,
			@Param("beeTeamId") Integer beeTeamId,
			@Param("personnelName") String personnelName,
			@Param("beeTaskId") Integer beeTaskId
			);


	/**
	 * 获取json日期
	 * @param beeTeamId
	 * @param beeTaskId
	 * @return
	 */
	public List<HashMap<String, Object>> getAttTaskDate(@Param("beeTeamId") Integer beeTeamId,@Param("beeTaskId") Integer beeTaskId);

	/**
	 *  获取考勤列表总条数
	 * @param beeTeamId
	 * @param personnelName
	 * @param beeTaskId
	 * @return
	 */
	public Long getGridDataCount(
			@Param("beeTeamId") Integer beeTeamId,
			@Param("personnelName") String personnelName,
			@Param("beeTaskId")Integer beeTaskId);
	
	
	/**
	 * 根据团队beeTeamId和beeTaskId查询所有团队信息
	 * @param beeTeamId
	 * @param beeTaskId
	 * @param epersonnelName
	 * @return
	 */
	public List<Map<String, Object>> teamId(@Param("beeTeamId") Integer beeTeamId,@Param("beeTaskId") Integer beeTaskId,@Param("epersonnelName")String epersonnelName);
	
	
	/**
	 * 查询任务名称
	 * @param beeTeamId
	 * @param beeTaskId
	 * @return
	 */
	public Map<String, Object> taskName(@Param("beeTeamId") Integer beeTeamId,@Param("beeTaskId") Integer beeTaskId);
	
	/**
	 * 导出行销活动管理中的考勤明细
	 * @param beeTeamId
	 * @param personnelName
	 * @param beeTaskId
	 * @return
	 */
	public List<Map<String, Object>> downloadAttendanceDetailsExcel(
			@Param("beeTeamId") Integer beeTeamId, @Param("personnelName") String personnelName, @Param("beeTaskId") Integer beeTaskId);

	/**
	 * 导出
	 * @param beeTeamId
	 * @param beeTaskId
	 * @param epersonnelName
	 * @return
	 */
	public List<Map<String, Object>> extenDetailExport(@Param("beeTeamId") Integer beeTeamId,@Param("beeTaskId") Integer beeTaskId);
	
}
