$(function($) {
	/** **************************************构造jqGrid start *************************************** */
	var gridSelector = "#operatorGrid";
	var pagerSelector = "#operatorGridPager";

	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : pagerSelector,
		gridSelector : gridSelector,
		parentDOMClass : "col-sm-8",
		title : "操作员列表",
		url : "getgriddata",
		postData : {},
		multiSelect : false,
		height : "500px",
		sortName : "operator_id",
		sortOrder : "desc",
		colNamesArray : [ '操作员ID', '公司ID', '公司名称', '操作员编号', '操作员姓名', '关联电话', '登录账号', '创建时间', '状态' ],
		colModel : [ {
			name : "operatorId",
			index : "operator_id",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "companyId",
			index : "company_id",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "companyName",
			index : "company_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "operatorCode",
			index : "operator_code",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "operatorName",
			index : "operator_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "operatorTel",
			index : "operator_tel",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "operatorAccount",
			index : "operator_account",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "createTime",
			index : "create_time",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "status",
			index : "status",
			width : 60,
			sorttype : "int",
			editable : false,
			formatter : statusFormatter
		} ]
	});

	// 选中行事件
	jqGridOption.onSelectRow = function(rowIndex, status) {
		var rowData = $(gridSelector).getRowData(rowIndex);
		
		$.ajax({
			url : "getoperatorrole",
			type : "post",
			dateType : "josn",
			data : {
				operatorId : rowData.operatorId
			},
			success : function(json) {
				Common.jqGridSetAllRowUnSelect(roleGridSelector);
				
				if (Common.isEmpty(json.result)) return;
				
				// 循环所有角色
				var obj = $(roleGridSelector).jqGrid("getRowData");
				
			    var roleArray = json.result.split(",");
				
				for (var i = 0; i < obj.length; i++) {
					if ($.inArray(obj[i].roleId, roleArray) >= 0) {
						$(roleGridSelector).setSelection(i + 1, false); // rowIndex从1开始
					}
				}
			}
		});
		
		$(roleGridSelector).setSelection(3, false);
	};

	// 渲染表格
	$(gridSelector).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end *************************************** */
	
	/** **************************************构造jqGrid start *************************************** */
	var roleGridSelector = "#roleGrid";
	var rolePagerSelector = "#roleGridPager";

	// 构造jqGrid配置信息
	var roleJQGridOption = new Common().createGridOption({
		pagerSelector : rolePagerSelector,
		gridSelector : roleGridSelector,
		parentDOMClass : "col-sm-4",
		multiboxonly: true,
		title : "可选角色列表",
		url : "../role/getgriddata",
		postData : {},
		multiSelect : true,
		height : "500px",
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
		} ],
		rowNum : 100,
		rowList : [ 100, 200, 300 ]
	});

	// 渲染表格
	$(roleGridSelector).jqGrid(roleJQGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end *************************************** */
	
	/** **************************************按钮事件 start *************************************** */
	
	/**
	 * 搜索
	 */
	$("#searchBtn").click(function() {
				var dialog = $("#condition").removeClass('hide').dialog({
					modal : true,
					title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-search'></i> 请选择查询条件</h4></div>",
					title_html : true,
					width : "500px",
					buttons : [ {
						text : "取消",
						"class" : "btn btn-xs",
						click : function() {
							$(this).dialog("close");
						}
					}, {
						text : "查询",
						"class" : "btn btn-primary btn-xs",
						click : function() {
							getGrid();
							$(this).dialog("close");
						}
					} ,{
						text : "重置",
						"class" : "btn btn-primary btn-xs",
						click : function() {
							Common.initSelect2("auditFlag", {
								width : "50%"
							});
							document.getElementById("searchForm").reset();
						}
					}]
				});
			});
	/**
	 * 获得页面用户数据方法
	 */
	function getGrid(){
		var params = {
				operatorName : $("#operatorName").val(),
				operatorTel : $("#operatorTel").val(),
				operatorAccount : $("#operatorAccount").val(),
				companyId : null
		};
		Common.jqGridReload(gridSelector, params);
	}
	
	$("#goOperatorBuilding").click(function(){
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一个操作员！", false);
			return false;
		}

		Common.showMask();
		
		var rowData = Common.jqGridGetRowData(gridSelector, selectedRowsId);
		var operatorId = rowData.operatorId;
		window.location="operatorbuilding?operatorId="+operatorId;
	});
	
	$("#save").click(function() {
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一个操作员！", false);
			return false;
		}

		Common.showMask();
		
		var rowData = Common.jqGridGetRowData(gridSelector, selectedRowsId);

		// 获取选中的角色
		var roleIds = "";
		roleRows = Common.jqGridGetSelectedRows(roleGridSelector);
		for (var i = 0; i < roleRows.length; i++) {
			if (roleRows[i] == "undefined") continue;
			var roleId = Common.jqGridGetRowData(roleGridSelector, roleRows[i]).roleId;
			if (Common.isEmpty(roleId)) continue;
			roleIds += roleId + ",";
		}
		
		$.ajax({
			url : "saveoperatorrole",
			type : "post",
			dataType : "json",
			data : {
				operatorId : rowData.operatorId,
				operatorAccount : rowData.operatorAccount,
				roleIds : roleIds
			},
			success : function(json) {
				Common.hideMask();
				
				if (json.isSuccess) {
					Common.messageBox("提示", "保存操作员角色关联关系成功！", true);
				} else {
					Common.messageBox("提示", json.msg, false);
				}
			},
			error : function(){
				Common.hideMask();
				Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
			}
		});
	});
	
	/** **************************************按钮事件 end *************************************** */
});