$(function($) {
	/** **************************************构造jqGrid start *************************************** */
	var gridSelector = "#grid-table";
	var pagerSelector = "#grid-pager";

	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : pagerSelector,
		gridSelector : gridSelector,
		parentDOMClass : "col-sm-6",
		title : "角色列表",
		url : "getgriddata",
		postData : {},
		height : "500px",
		multiSelect : false,
		colNamesArray : [ '角色ID', '角色编号', '角色名称', '终端类型', '状态', '描述'],
		colModel : [ {
			name : "roleId",
			index : "roleId",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "roleCode",
			index : "roleCode",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "roleName",
			index : "roleName",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "terminalType",
			index : "terminalType",
			width : 60,
			sorttype : "string",
			editable : false,
			formatter : ysyTerminalType
		}, {
			name : "status",
			index : "status",
			width : 60,
			sorttype : "int",
			editable : false,
			formatter : statusFormatter
		}, {
			name : "descript",
			index : "descript",
			width : 60,
			sorttype : "int",
			editable : false
		} ]
	});
	
	// 选中行事件
	jqGridOption.onSelectRow = function(rowIndex, status) {
		var rowData = $(gridSelector).getRowData(rowIndex);

		// 请求该角色的权限
		$.ajax({
			url : "getpermission",
			type : "post",
			dataType : "json",
			data : {
				roleId : rowData.roleId
			},
			success : function(json) {
				setPermissionByJson(json);
			},
			error : function() {
				Common.messageBox("提示", "服务器繁忙，请稍候再试！", false);
			}
		});
	};

	// 渲染表格
	$(gridSelector).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end *************************************** */

	/** **************************************按钮事件 start *************************************** */
	/**
	 * 新增角色
	 */
	$("#add").click(function() {
		clear();
		saveRoleCondition("add");
	});

	/**
	 * 修改角色
	 */
	$("#edit").click(function() {
		clear();
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想修改的数据！", false);
			return false;
		}
		var rowData = Common.jqGridGetRowData(gridSelector, selectedRowsId);
		$.ajax({
			url:"getinfo",
			type:"post",
			data:{
				roleId:rowData.roleId
			},
			dataType:"json",
			success:function(json){
				var role = json.result;
				$("#roleId").val(role.roleId);
				$("#roleName").val(role.roleName);
				$("#terminalType").val(role.terminalType);
				$("#descript").val(role.descript);
			},
			error:function(){
				Common.messageBox("提示", "获取角色信息失败！", false);
			}
		});
		saveRoleCondition("edit");
	});

	function saveRoleCondition(url){
		var title = url == "edit" ? "修改角色" : "添加角色";
		var style = url == "edit" ? "fa fa-pencil-square-o" : "glyphicon glyphicon-plus";
		var dialog = $("#role_condition").removeClass('hide').dialog(
			{
				modal : true,
				title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon "+style+"'></i>"+title+"</h4></div>",
				title_html : true,
				width : "500px",
				buttons : [ {
					text : "取消",
					"class" : "btn btn-xs",
					click : function() {
						$(this).dialog("close");
					}
				} ,{
					text : "重置",
					"class" : "btn btn-primary btn-xs",
					click : function() {
						$("#roleName").val("");
						$("#terminalType").val("");
						$("#descript").val("");
					}
				}, {
					text : "保存",
					"class" : "btn btn-primary btn-xs",
					click : function() {
						var roleId = $("#roleId").val();
						var roleName = $("#roleName").val();
						var terminalType = $("#terminalType").val();
						var descript = $("#descript").val();
						if (Common.isEmpty(roleName)) {
							Common.messageBox("提示", "角色名称不能为空！", false);
							return false;
						}
						if (Common.isEmpty(terminalType)) {
							Common.messageBox("提示", "终端类型不能为空！", false);
							return false;
						}
						$.ajax({
							url : url,
							type : "post",
							data : {
								roleId : roleId,
								roleName : roleName,
								terminalType : terminalType,
								descript : descript
							},
							dataType : "json",
							success : function(json) {
								if (json.isSuccess) {
									Common.messageBox("提示", json.msg, true);
									clear();
									$("#role_condition").dialog("close");
									Common.jqGridReload(gridSelector);
								} else {
									Common.messageBox("提示", json.msg, false);
								}
							},
							error : function() {
								Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
							}
						});
					}
				}]
		});
	}
	
	function clear(){
		$("#roleId").val("");
		$("#roleName").val("");
		$("#terminalType").val("");
		$("#descript").val("");
	}
	
	/**
	 * 启用禁用角色
	 */
	$("#lock").click(function() {
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想禁用/启用的数据！", false);
			return false;
		}
		
		var rowData = Common.jqGridGetRowData(gridSelector, selectedRowsId);

		$.ajax({
			url : "lock",
			type : "post",
			data : {
				roleId : rowData.roleId
			},
			dataType : "json",
			success : function(json) {
				if (json.isSuccess) {
					Common.messageBox("提示", "禁用/启用角色成功！", true);
					Common.jqGridReload(gridSelector);
				} else {
					Common.messageBox("提示", json.msg, false);
				}
			},
			error : function() {
				Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
			}
		});
	});

	/**
	 * 保存角色权限
	 */
	$("#save").click(function() {
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想保存权限的数据！", false);
			return false;
		}
		
		Common.showMask();
		
		var rowData = Common.jqGridGetRowData(gridSelector, selectedRowsId);
		
		var rolePermissions = getPermission();
		
		$.ajax({
			url : "savepermission",
			type : "post",
			data : {
				rolePermissions : rolePermissions,
				roleId : rowData.roleId
			},
			dataType : "json",
			success : function(json) {
				Common.hideMask();
				
				if (json.isSuccess) {
					Common.messageBox("提示", "保存角色权限成功！", true);
				} else {
					Common.messageBox("提示", json.msg, false);
				}
			},
			error : function() {
				Common.hideMask();
				Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
			}
		});
	});
	/** **************************************按钮事件 end *************************************** */
});