$(function($) {
	
	 // 初始化下拉框
    Common.initSelect2("provinceId", {
        width: "200px"
    });
    Common.initSelect2("cityId", {
        width: "200px"
    });
    Common.initSelect2("status", {
    	width: "200px"
    });
    
    Common.initSelect2("provinceIdT", {
        width: "200px"
    });
    Common.initSelect2("cityIdT", {
        width: "200px"
    });
    Common.initSelect2("statusT", {
    	width: "200px"
    });
	
    /**指定操作员**/
    var opreatorId = $("#selectOperatorId").val();
	/** **************************************构造jqGrid start *************************************** */
	var gridSelector = "#operatorGrid";
	var pagerSelector = "#operatorGridPager";

	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : pagerSelector,
		gridSelector : gridSelector,
		parentDOMClass : "col-sm-6",
		title : "未关联楼盘",
		url : "notbindbuildingproject?opreatorId="+opreatorId,
		postData : {},
		multiSelect : true,
		height : "500px",
		colNamesArray : [ '楼盘ID', '楼盘名称', '所属省', '所属市', '状态' ],
		colModel : [ {
			name : "building_project_id",
			index : "building_project_id",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "building_project_name",
			index : "building_project_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "province_name",
			index : "province_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "city_name",
			index : "city_name",
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

	// 渲染表格
	$(gridSelector).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end *************************************** */
	
	
	/** **************************************构造jqGrid start（已关联楼盘） *************************************** */
	var buildingGridSelector = "#buildingGrid";
	var buildingPagerSelector = "#buildingGridPager";

	// 构造jqGrid配置信息
	var buildingJQGridOption = new Common().createGridOption({
		pagerSelector : buildingPagerSelector,
		gridSelector : buildingGridSelector,
		parentDOMClass : "col-sm-6",
		multiboxonly: true,
		title : "已关联楼盘",
		url : "bindbuildingproject?opreatorId="+opreatorId,
		postData : {},
		multiSelect : true,
		height : "500px",
		colNamesArray : [ '楼盘ID', '楼盘名称', '所属省', '所属市', '状态' ],
		colModel : [ {
			name : "building_project_id",
			index : "building_project_id",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "building_project_name",
			index : "building_project_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "province_name",
			index : "province_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "city_name",
			index : "city_name",
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

	// 渲染表格
	$(buildingGridSelector).jqGrid(buildingJQGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end (关联楼盘)*************************************** */

	
	/** **************************************按钮事件 start *************************************** */
	
	/**
	 * 搜索 未关联楼盘
	 */
	$("#searchBtnO").click(
			function() {
				var dialog = $("#condition").removeClass('hide')
						.dialog(
								{
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
											$('#provinceId').select2("val", "");
											$('#cityId').select2("val", "");
											$('#status').select2("val", "");
											$('#saleStatus').select2("val", "");
											$("#buildingProjectName").val("");
										}
									}]
								});
			});
	/**
	 * 获得页面用户数据方法 未关联楼盘
	 */
	function getGrid(){
		var params = {
				selectOperatorId:$("#selectOperatorId").val(),
				buildingProjectName : $("#buildingProjectName").val(),
                provinceId:$('#provinceId').val(),
                cityId :$('#cityId').val(),
                status: $('#status').val(),
                saleStatus : $('#saleStatus').val()
		};
		Common.jqGridReload(gridSelector, params);
	}
	
	/**
	 * 搜索 已关联楼盘
	 */
	$("#searchBtnT").click(
			function() {
				var dialog = $("#conditionT").removeClass('hide')
						.dialog(
								{
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
											getGridT();
											$(this).dialog("close");
										}
									} ,{
										text : "重置",
										"class" : "btn btn-primary btn-xs",
										click : function() {
											$('#provinceIdT').select2("val", "");
											$('#cityIdT').select2("val", "");
											$('#statusT').select2("val", "");
											$('#saleStatusT').select2("val", "");
											$("#buildingProjectNameT").val("");
										}
									}]
								});
			});
	/**
	 * 获得页面用户数据方法 已关联楼盘
	 */
	function getGridT(){
		var params = {
				selectOperatorId:$("#selectOperatorIdT").val(),
				buildingProjectName : $("#buildingProjectNameT").val(),
                provinceId:$('#provinceIdT').val(),
                cityId :$('#cityIdT').val(),
                status: $('#statusT').val(),
                saleStatus : $('#saleStatusT').val()
		};
		Common.jqGridReload(buildingGridSelector, params);
	}
	
	/**
	 * 添加
	 */
	$("#add").click(function(){
		var selectedRowsId = Common.jqGridGetSelectedRow(gridSelector);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择你想添加的楼盘！", false);
			return false;
		}
		
		// 获取选中的未添加楼盘
		var buildingIds = "";
		buildingRows = Common.jqGridGetSelectedRows(gridSelector);
		for (var i = 0; i < buildingRows.length; i++) {
			if (buildingRows[i] == "undefined") continue;
			var buildingId = Common.jqGridGetRowData(gridSelector, buildingRows[i]).building_project_id;
			if (Common.isEmpty(buildingId)) continue;
			buildingIds += buildingId + ",";
		}
		var selectOperatorId = $("#selectOperatorId").val(); //指定操作员id

		$.ajax({
			type:"POST",
			url:"saveoperatorbuilding",
			data:{
				operatorId:selectOperatorId,
				buildingProjectScopeStr:buildingIds
			},
			dataType : "json",
			success: function(json){
				if (json.isSuccess) {
                    Common.messageBox("提示", "添加成功！", true);
                    Common.jqGridReload(gridSelector);
                    Common.jqGridReload(buildingGridSelector);
                } else {
                    Common.messageBox("提示", json.msg, false);
                }
		    },
			error: function() {
				Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
			}
		});
	});
	
	//删除关联楼盘
	$("#delete").click(function(){
		// 获取选中的已添加楼盘
		var buildingProjectScopeStr = "";
		var buildingRows = Common.jqGridGetSelectedRows(buildingGridSelector);
		for (var i = 0; i < buildingRows.length; i++) {
			if (buildingRows[i] == "undefined") continue;
			//绑定销售团队ID
			var buildingId = Common.jqGridGetRowData(buildingGridSelector, buildingRows[i]).building_project_id;
			if (Common.isEmpty(buildingId)) continue;
			buildingProjectScopeStr +=buildingId+ ",";
		}
		if(buildingProjectScopeStr!=""){
			buildingProjectScopeStr=buildingProjectScopeStr.substring(0,buildingProjectScopeStr.length-1);
		}else{
			Common.messageBox("提示", "请选择您要删除关联的楼盘！", false);
			return false;
		}
		var selectOperatorId = $("#selectOperatorId").val(); //指定操作员id
		Common.confirm("请确认是删除此关联楼盘？", function(){
			$.ajax({
				type:"POST",
				url:"deleteoperatorbuilding",
				data:{
					operatorId:selectOperatorId,
					buildingProjectScopeStr:buildingProjectScopeStr
				},
				success: function(json){
					if (json.isSuccess) {
                        Common.messageBox("提示", "删除成功！", true);
                        Common.jqGridReload(gridSelector);
                        Common.jqGridReload(buildingGridSelector);
                    } else {
                        Common.messageBox("提示", json.msg, false);
                    }
			    },
				error: function() {
					Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
				}
			});
		});
	});
	
	/**
	 * 返回上一页
	 */
	$("#operatorindex_backBtn").click(function(){
	    window.history.go(-1);
	});
	/** **************************************按钮事件 end *************************************** */
});