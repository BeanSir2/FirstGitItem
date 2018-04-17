package com.ruiyun.common.constant;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.ruiyun.common.service.constant.TerminalTypeEnum;

public class GetTerminalTypeNameByCodeHandler implements TypeHandler<TerminalTypeEnum>{

	@Override
	public void setParameter(PreparedStatement ps, int i, TerminalTypeEnum parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TerminalTypeEnum getResult(ResultSet rs, String columnName) throws SQLException {
		if(null != rs.getObject(columnName)) {
			TerminalTypeEnum flags[] = TerminalTypeEnum.values();
			for (TerminalTypeEnum e : flags) {
				if (e.getTerminalTypeCode() == Integer.valueOf(rs.getObject(columnName).toString())) {
					return e;
				}
			}
		}
		return null;
	}

	@Override
	public TerminalTypeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
		if(null != rs.getObject(columnIndex)) {
			TerminalTypeEnum flags[] = TerminalTypeEnum.values();
			for (TerminalTypeEnum e : flags) {
				if (e.getTerminalTypeCode() == Integer.valueOf(rs.getObject(columnIndex).toString())) {
					return e;
				}
			}
		}
		return null;  
	}

	@Override
	public TerminalTypeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
		if(null != cs.getObject(columnIndex)) {
			TerminalTypeEnum flags[] = TerminalTypeEnum.values();
			for (TerminalTypeEnum e : flags) {
				if (e.getTerminalTypeCode() == Integer.valueOf(cs.getObject(columnIndex).toString())) {
					return e;
				}
			}
		}
		return null;
	}

}
