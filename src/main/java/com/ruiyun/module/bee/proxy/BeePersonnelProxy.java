package com.ruiyun.module.bee.proxy;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.module.bee.entity.BeeCommonEntity;
import com.ruiyun.module.bee.entity.BeePersonnelEntity;

@Repository("beePersonnelProxy")
public interface BeePersonnelProxy {
	
	public int updateByPrimaryKeySelective(BeePersonnelEntity entity);
	
	public Long getByTelOrLoingAccount(@Param("personnelTel")String personnelTel,@Param("beeLoginAccount")String beeLoginAccount);
	
	public Long getByTel(@Param("personnelTel")String personnelTel);
	
	public Long getByLoingAccount(@Param("beeLoginAccount")String beeLoginAccount);

	public BeePersonnelEntity selectByPrimaryKeySelective(@Param("beePersonnelId")Integer beePersonnelId);
	
	public BeePersonnelEntity ByPrimaryKeySelective(@Param("beePersonnelId")Integer beePersonnelId);
	
	/**
	 * 行销人员管理的列表
	 * @param pageParams
	 * @param entity
	 * @return
	 */
	public List<BeePersonnelEntity> getGridDataPersonnel
					(@Param("pageParams")JQGridPageParams pageParams, @Param("entity")BeePersonnelEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 行销人员管理的所有行数
	 * @param pageParams
	 * @param entity
	 * @return
	 */
	public Long getGridDataPersonnelCount
					(@Param("pageParams")JQGridPageParams pageParams, @Param("entity")BeePersonnelEntity entity, @Param("operatorId")Integer operatorId);
	/**
	 * 添加行销管理信息
	 * @param entity
	 * @return
	 */
	public Long insertSelective(BeePersonnelEntity entity);
	
	/**
	 * 根据用户名与密码查询实体
	 * @param beeLoginAccount
	 * @param beeLoginPwd
	 * @return
	 */
	public BeePersonnelEntity getBeePersonnelEntity(@Param("beeLoginAccount")String beeLoginAccount,@Param("beeLoginPwd")String beeLoginPwd);
	
	
	/**
	 * 根据beeteamid来查询团队人员信息
	 * @param pageParams
	 * @param beeTeamId
	 * @return
	 */
	public List<BeePersonnelEntity> getpersonlist
			(@Param("pageParams")JQGridPageParams pageParams, @Param("beeTeamId")Integer beeTeamId, @Param("operatorId")Integer operatorId);
	
	/**
	 * 根据beeteamid来查询团队人员数量
	 * @param pageParams
	 * @param beeTeamId
	 * @return
	 */
	public Long getpersonlistCount(@Param("pageParams")JQGridPageParams pageParams, @Param("beeTeamId")Integer beeTeamId, @Param("operatorId")Integer operatorId);
	
	/**
	 * 查询可以添加团队的人员信息
	 * @param pageParams
	 * @return
	 */
	public List<BeePersonnelEntity> getteamlist
				(@Param("pageParams")JQGridPageParams pageParams,@Param("entity")BeePersonnelEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 查询可以添加团队的人员信息数量
	 * @param pageParams
	 * @return
	 */
	public Long getteamlistCount
				(@Param("pageParams")JQGridPageParams pageParams,@Param("entity")BeePersonnelEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 根据条件查询所有小蜜蜂
	 * @param entity
	 * @return
	 */
	public List<BeePersonnelEntity> getAllBee(@Param("pageParams")JQGridPageParams pageParams, @Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	public Long getAllBeeCount(@Param("entity")BeeCommonEntity entity, @Param("operatorId")Integer operatorId);
	
	public List<BeePersonnelEntity> getBee(@Param("entity")BeePersonnelEntity entity, @Param("operatorId")Integer operatorId);
	
	/**
	 * 根据团队id查询信息
	 * @param beeTeamId
	 * @return
	 */
	public BeePersonnelEntity selectByTeamId(@Param("beeTeamId")Integer beeTeamId); 
	
	public List<BeePersonnelEntity> selectAllByTeamId(@Param("beeTeamId")Integer beeTeamId); 
}
