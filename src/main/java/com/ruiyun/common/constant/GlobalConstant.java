package com.ruiyun.common.constant;

import com.ruiyun.module.system.entity.SystemOperatorEntity;

public class GlobalConstant {
	// 系统内置用户属性
	public static SystemOperatorEntity systemDefaultOperator = new SystemOperatorEntity() {
		private static final long serialVersionUID = 1L;

		{
			setOperatorId(99999999);
			setOperatorAccount("admin");
			setStatus(1);
			setOperatorName("平台初使化管理员");
			setOperatorPwd("");
			setCreateTime("1970-01-01");
			setMerchantId(0);
			setCompanyId(0);
			setMerchantType("SUPER");
		}
	};
}
