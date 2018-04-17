package com.ruiyun.common.constant;

/**
 * 业务类型常量
 * @类名称 BusiConstants
 * @author HKCHEN
 */
public class BusiConstants {
	
	/*************************** 业务常量配置 ****************************/
	
	/**
	  * 项目管理-审批状态<br>
	  * 0.未审 1.通过 2.拒绝 3.上架申请 4.下架申请
	  */
	 public static class PROJ_AUDIT {
		 /** 0-未审 */
		 public static final int WS=0;
		 /** 1-通过 */
		 public static final int TG=1;
		 /** 2-拒绝 */
		 public static final int JJ=2;
		 /** 3-上架申请 */
		 public static final int SJSQ=3;
		 /** 4-下架申请 */
		 public static final int XJSQ=4;
	 }
	 
	 /**
	  * 项目管理-上架状态<br>
	  * 0.未上架 1.已上架 2-已下架
	  */
	 public static class PROJ_ISSTART {
		 /** 0-未上架 */
		 public static final int WSJ=0;
		 /** 1-已上架 */
		 public static final int YSJ=1;
		 /** 2-已下架 */
		 public static final int YXJ=2;
	 }
	 
	 /**
	  * 项目管理-推荐状态<br>
	  * 0.未推荐 1.已推荐
	  */
	 public static class PROJ_ISCOMMEND {
		 /** 0-未推荐 */
		 public static final int WTJ=0;
		 /** 1-已推荐 */
		 public static final int YTJ=1;
	 }
	 
	 /**
	  * 套餐管理-套餐类型<br>
	  * 1.非电商 2.电商 3.非电商+电商
	  */
	 public static class PROJ_PACKAGETYPE {
		 /** 1-分销(非电商) */
		 public static final int FX=1;
		 /** 2-电商 */
		 public static final int DS=2;
		 /** 3-分销(非电商)+电商 */
		 public static final int FXDS=3;
	 }
	 /**
	  * 套餐管理-认筹类型<br>
	  * 1.折扣 2.抵扣 3.折扣+抵扣
	  */
	 public static class PROJ_GROUPTYPE {
		 /** 1-折扣 */
		 public static final int ZK=1;
		 /** 2-抵扣 */
		 public static final int DK=2;
		 /** 3-折扣+抵扣 */
		 public static final int ZKDK=3;
	 }
	 
	 /**
	  * 订单管理-渠道类型<br>
	  * 1.自然到访 2.中介
	  */
	 public static class ORDER_CHANNEL {
		 /** 1-自然到访 */
		 public static final int ZRDF=1;
		 /** 2-中介 */
		 public static final int ZJ=2;
	 }
	 
	 /**
	  * 订单管理-订单状态<br>
	  * 1.到访 2.认筹 3.认购 4.签约待审 5.成交有效 6.退款 7.退房 8.退房且退款  -1.废弃订单
	  */
	 public static class ORDER_STATUS {
		 /** 1-到访 */
		 public static final short DF=1;
		 /** 2-认筹 */
		 public static final short TG=2;
		 /** 3-认购 */
		 public static final short RG=3;
		 /** 4-签约待审 */
		 public static final short QYDS=4;
		 /** 5-成交有效 */
		 public static final short CJYX=5;
		 /** 6-退款 */
		 public static final short TK=6;
		 /** 7-退房 */
		 public static final short TF=7;
		 /** 8-退房且退款 */
		 public static final short TFQTK=8;
		 /** 1--废弃订单 */
		 public static final short FQDD=-1;
	 }
	 
	 /**
	  * 订单管理-订单审核状态<br>
	  * 0.未审 1.通过 2.拒绝 
	  */
	 public static class ORDER_AUDIT {
		 /** 0-未审 */
		 public static final int WS=0;
		 /** 1-通过 */
		 public static final int TG=1;
		 /** 2-拒绝 */
		 public static final int JJ=2;
	 }
	 
	 /**
	  * 订单管理-报备状态<br>
	  * 1.报备待确认  2.报备无效  3.报备有效  4.报备保护过期  5.到访无效  6.到访有效  7.到访保护过期
	  */
	 public static class RECORD_STATUS {
		 /** 1-报备待确认 */
		 public static final int S1=1;
		 /** 2-报备无效 */
		 public static final int S2=2;
		 /** 3-报备有效 */
		 public static final int S3=3;
		 /** 4-报备保护过期 */
		 public static final int S4=4;
		 /** 5-到访无效 */
		 public static final int S5=5;
		 /** 6-到访有效 */
		 public static final int S6=6;
		 /** 7-到访保护过期 */
		 public static final int S7=7;
	 }
	 
	 /**
	  * 二维码-二维码类型<br>
	  * 1.楼盘二维码  2.置业顾问二维码  3.楼盘广告二维码
	  */
	 public static class QRCODE_BUSITYPE {
		 /** 1-楼盘二维码 */
		 public static final int Q1=1;
		 /** 2-置业顾问二维码 */
		 public static final int Q2=2;
		 /** 3-活动二维码 */
		 public static final int Q3=3;
		 /** 4-经纪人-投资小秘书二维码 */
		 public static final int Q4=4;
	 }
	 
	 /**
	 * 广告来源渠道类别
	 * @author Ajian
	 * @time 2017-9-5 下午4:50:10
	 */
	 public static enum ADVERTISE_CHANNEL_CATEGORY_ENUM {
		传统媒体("传统媒体","AdChannelCategory_1"), 网络媒体("网络媒体","AdChannelCategory_2"), 
		户外广告("户外广告","AdChannelCategory_3"), 植入广告("植入广告","AdChannelCategory_4"), 
		其它("其它","AdChannelCategory_5");
		private String name;
		private String code;

		public final String getName() {
			return name;
		}

		public final String getCode() {
			return code;
		}

		private ADVERTISE_CHANNEL_CATEGORY_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	 }
	 
	 /**
	 * 广告渠道数据来源枚举
	 * @author Ajian
	 * @time 2017-9-5 下午5:59:57
	 */
	public static enum ADVERTISE_CHANNEL_SOURCE_ENUM {
		 客户,置业顾问;
	 }
	
	/**
	 * 审核状态枚举
	 * @author t440s12
	 *
	 */
	public static enum AUDIT_STATUS_ENUM {
		未审核("未审核", "0"), 通过("通过", "1"), 未通过("未通过", "2");
		
		private String name;
		private String code;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private AUDIT_STATUS_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 通盘通客转介审核状态枚举
	 */
	public static enum REFERRAL_AUDIT_STATUS_ENUM {
		未审核("未审核", "0"), 通过("允许", "1"), 未通过("拒绝", "2");
		
		private String name;
		private String code;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private REFERRAL_AUDIT_STATUS_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 保险订单状态枚举
	 * @author xlin
	 */
	public static enum INS_ORDER_STATUS_ENUM { 
		待付款("待付款", "0"), 已付款("已付款", "1"), 已关闭("已关闭", "2");
		
		private String name;
		private String code;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private INS_ORDER_STATUS_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 保险订单保障状态枚举
	 * @author xlin
	 */
	public static enum INS_AUDIT_STATUS_ENUM {
		已失效("已失效", "0"), 保障中("保障中", "1"), 待保障("待保障", "2"), 承保审核中("承保审核中", "3"), 承保失败("承保失败", "4");
		
		private String name;
		private String code;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private INS_AUDIT_STATUS_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 保险产品套餐的保障期限类型状态枚举
	 * @author xlin
	 */
	public static enum INS_PRODUCT_PACKAGE_PERIOD_TYPE_ENUM {
		年("年", "1"), 月份("月份", "2"), 天("天", "3");
		
		private String name;
		private String code;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private INS_PRODUCT_PACKAGE_PERIOD_TYPE_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 保险产品套餐类型的枚举
	 * @author xl
	 *
	 */
	public static enum INS_PACKAGE_PROJECT_TYPE_ENUM {
		基本保障("基本保障", "1"), 赠送服务("赠送服务", "2");
		
		private String name;
		private String code;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		private INS_PACKAGE_PROJECT_TYPE_ENUM(String name, String code) {
			this.name = name;
			this.code = code;
		}
	}
	
	/**
	 * 平安保险结佣订单审核状态枚举
	 * @ClassName: INS_COMMISSION_SERIAL_AUDIT_STATUS 
	 * @Description: INS_COMMISSION_SERIAL_AUDIT_STATUS 
	 * @author xialin
	 * @date 2017年11月16日 下午3:32:51 
	 *
	 */
	public static enum INS_COMMISSION_SERIAL_AUDIT_STATUS {
		
		未审核("审核中", "0"), 通过("通过", "1"), 拒绝("拒绝", "2");
		
		private String name;
		private String code;
		
		private INS_COMMISSION_SERIAL_AUDIT_STATUS(String name, String code) {
			this.name = name;
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	}
	
	/**
	 * 平安保险结佣订单结佣对象枚举
	 * @ClassName: INS_COMMISSION_SERIAL_RECOMMEND_SOURCE 
	 * @Description: INS_COMMISSION_SERIAL_RECOMMEND_SOURCE 
	 * @author xialin
	 * @date 2017年11月16日 下午3:40:53 
	 *
	 */
	public static enum INS_COMMISSION_SERIAL_RECOMMEND_SOURCE{
		销售用户("销售用户", "1"), 关注用户("关注用户", "2");
		
		private String name;
		private String code;
		
		
		private INS_COMMISSION_SERIAL_RECOMMEND_SOURCE(String name, String code) {
			this.name = name;
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	}
}
