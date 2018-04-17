package com.ruiyun.common.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruiyun.ace.jqgrid.JQGridPageParams;
import com.ruiyun.ace.jqgrid.JQGridResultEntity;
import com.ruiyun.module.platform.proxy.PlatformProxy;
import com.ruiyun.utils.DateUtil;
import com.ruiyun.utils.StringUtil;
import com.ruiyun.utils.cache.redis.RedisUtil;

/**
 * Service基类
 * 
 * @author Jiangbo
 * 
 */
public class BaseService extends Base {
	@Autowired
	private PlatformProxy platformProxy;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 计算总页数
	 * 
	 * @param totalRows
	 *            总行数
	 * @param pageRows
	 *            单页行数
	 * @return
	 */
	protected long calcTotalPage(Long totalRows, Long pageRows) {
		return totalRows / pageRows + (totalRows % pageRows > 0 ? 1 : 0);
	}

	/**
	 * 填充数据
	 * 
	 * @param list
	 * @param totalRecords
	 * @param pageParams
	 * @return
	 */
	protected <T> JQGridResultEntity<T> fillJQGrid(List<T> list, Long totalRecords, JQGridPageParams pageParams) {
		return fillJQGrid(null, list, totalRecords, pageParams);
	}

	/**
	 * 填充数据
	 * 
	 * @param result
	 * @param list
	 * @param totalRecords
	 * @param page
	 * @param rows
	 * @return
	 */
	protected <T> JQGridResultEntity<T> fillJQGrid(JQGridResultEntity<T> result, List<T> list, Long totalRecords, JQGridPageParams pageParams) {
		if (null == result) {
			result = new JQGridResultEntity<T>();
		}
		
		if(pageParams!=null) {
			result.setCurrentPage(pageParams.getPage());
			result.setTotalPages(calcTotalPage(totalRecords, pageParams.getRows()));
		} else {
			result.setCurrentPage(1);
			result.setTotalPages(0);
		}

//		result.setCurrentPage(pageParams.getPage()); //上面66 行 已经判断了
		result.setRows(list);
		result.setTotalRecords(totalRecords);
		
//		for (T t : list) {
//			Field[] fds = t.getClass().getDeclaredFields();
//			for (Field field : fds) {
//				String str = field.getName();
//				System.out.println(str);
//			}
//		}

		return result;
	}

	/**
	 * 生产Id
	 * modify by xialin 迁移到Base类中
	 * @param tableName
	 *            需要查询的表名
	 * @param fieldName
	 *            需要查询的字段名
	 * @return Integer
	 * @author chentianjin
	 * @date 2017年3月15日
	 */
	/*protected Integer generateId() {
		// String yyMMdd = DateUtil.currentTime("yyyyMMddHHmmssSSS");
		String yyMMdd = DateUtil.currentTime("mmssSSS");
		return Integer.parseInt((new Random().nextInt(9)+1)+yyMMdd);
	}*/

	/**
	 * 生成订单号
	 * 
	 * @param phone
	 * @return
	 */
	protected synchronized String generateOrderSerial(String phone) {
		String date = DateUtil.currentTime("yyyyMMdd");
		String lastNum = null;
		if (StringUtil.isEmpty(phone)) {
			lastNum = DateUtil.currentTime("HHmm");
		} else {
			lastNum = phone.substring(phone.length() - 4);
		}
		String sequence = padLeft(redisUtil.incr("REDIS_ORDERCODE_KEY"), 6, '0');
		return date + lastNum + sequence;
	}
	
	/**
	 * 转换成隐藏的号码
	 * @param tel 13389898989
	 * @return 133****8989
	 */
	public String convertHideTel(String tel) {
		if (StringUtil.isNotEmpty(tel)) {
			return tel.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		} else {
			return tel;
		}
		
	}

	/**
	 * 左补位，右对齐
	 * 
	 * @param oriStr
	 *            原字符串
	 * @param len
	 *            目标字符串长度
	 * @param alexin
	 *            补位字符
	 * @return 目标字符串
	 */
	private String padLeft(String oriStr, int len, char alexin) {
		int strlen = oriStr.length();
		String str = "";
		if (strlen < len) {
			for (int i = 0; i < len - strlen; i++) {
				str = str + alexin;
			}
		}
		str += oriStr;
		return str;
	}
	/**
	 * 生成返回结果
	 * 
	 * @param msgCode
	 * @param msg
	 * @return
	 */
	protected Map<String, String> generateResultMap(String msgCode, String msg) {
		Map<String, String> result = new HashMap<String, String>();

		result.put("msgcode", msgCode);
		result.put("msg", msg);

		return result;
	}
}
