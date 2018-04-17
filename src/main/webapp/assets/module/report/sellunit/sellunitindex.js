$(function($) {
	/** **************************************构造jqGrid start *************************************** */
	var gridSelector = "#sellUnitGrid";
	var pagerSelector = "#sellUnitGridPager";
	// 初始化下拉框
    Common.initSelect2("city_name", {
        allowClear: true,
        minimumResultsForSearch: Infinity,
        width: "250px"
    });
    Common.initSelect2("building_project_name", {
        width: "250px"
    });
    Common.initSelect2("province_name", {
    	width: "250px"
    });
    
    Common.initSelect2("city_name_import", {
    	allowClear: true,
    	minimumResultsForSearch: Infinity,
    	width: "250px"
    });
    Common.initSelect2("building_project_name_import", {
    	width: "250px"
    });
    Common.initSelect2("province_name_import", {
    	width: "250px"
    });
    
	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : pagerSelector,
		gridSelector : gridSelector,
		parentDOMClass : "col-sm-12",
		title : "产品列表",
		url : "sellunitData",
		postData : {},
//		multiSelect : true,
		height : "500px",
		colNamesArray : [ '销售单位id','所属城市id', '项目id','项目名称','所属城市','分期','产品类型','楼栋名称','单元号','楼层','房号','预售建筑面积','预售套内面积','实测建筑面积','实测套内面积','实测补差金额','销售状态','推货日期','认购日期','认购证号','客户名称','电话','约定签约日期','是否逾期签约','签约日期','标准总价','房间总价','成交总价','合同创建人','置业顾问','销售代理公司','折扣说明','成交面积状态','认购总价','预售证日期','竣工备案日期'],
		colModel : [ {
			name : "id",
			index : "id",
			sorttype : "int",
			editable : false,
			hidden : true
		}, {
			name : "cityId",
			index : "cityId",
			sorttype : "int",
			editable : false,
			hidden : true
		}, {
			name : "buildingProjectId",
			index : "buildingProjectId",
			sorttype : "int",
			editable : false,
			hidden : true
		}, {
			name : "buildingProjectName",
			index : "buildingProjectName",
			fixed:true,
			width : 130,
			sorttype : "string",
			editable : false
		}, {
			name : "cityName",
			index : "cityName",
			fixed:true,
			width : 70,
			sorttype : "string",
			editable : false
		}, {
			name : "excelBuildingArea",
			index : "excelBuildingArea",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "buildingType",
			index : "buildingType",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelDongName",
			index : "excelDongName",
			fixed:true,
			width : 80,
			sorttype : "string",
			editable : false
		},{
			name : "excelUnitNumber",
			index : "excelUnitNumber",
			fixed:true,
			width : 60,
			sorttype : "string",
			editable : false
		},{
			name : "excelFloorNumber",
			index : "excelFloorNumber",
			fixed:true,
			width : 60,
			sorttype : "string",
			editable : false
		},{
			name : "excelRoomNumber",
			index : "excelRoomNumber",
			fixed:true,
			width : 60,
			sorttype : "string",
			editable : false
		},{
			name : "excelPresellBuildingArea",
			index : "excelPresellBuildingArea",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelPresellBuildingInnerArea",
			index : "excelPresellBuildingInnerArea",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelMeasurementBuildingArea",
			index : "excelMeasurementBuildingArea",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelMeasurementBuildingInnerArea",
			index : "excelMeasurementBuildingInnerArea",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelRealDifferencePrice",
			index : "excelRealDifferencePrice",
			fixed:true,
			width : 100,
			sorttype : "string",
			editable : false
		},{
			name : "excelSellStatus",
			index : "excelSellStatus",
			fixed:true,
			width : 80,
			sorttype : "string",
			editable : false
		},{
			name : "excelPublicityDate",
			index : "excelPublicityDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelSurebuyDate",
			index : "excelSurebuyDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelSurebuyNumber",
			index : "excelSurebuyNumber",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelCustomerName",
			index : "excelCustomerName",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelCustomerTel",
			index : "excelCustomerTel",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelAppointSignDate",
			index : "excelAppointSignDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelIsOverdueSign",
			index : "excelIsOverdueSign",
			fixed:true,
			width : 80,
			sorttype : "string",
			editable : false
		},{
			name : "excelSignDate",
			index : "excelSignDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelStandardTotalPrice",
			index : "excelStandardTotalPrice",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelRoomTotalPrice",
			index : "excelRoomTotalPrice",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelDealTotalPrice",
			index : "excelDealTotalPrice",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelContractCreateName",
			index : "excelContractCreateName",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelOperatorName",
			index : "excelOperatorName",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelSellCompanyName",
			index : "excelSellCompanyName",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelDiscountDesc",
			index : "excelDiscountDesc",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelDealAreaStatus",
			index : "excelDealAreaStatus",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelSurebuyTotalPrice",
			index : "excelSurebuyTotalPrice",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelPresellCardDate",
			index : "excelPresellCardDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		},{
			name : "excelCompletedFilingDate",
			index : "excelCompletedFilingDate",
			fixed:true,
			width : 120,
			sorttype : "string",
			editable : false
		}]
	});
	
	//认购时间的查询选择条件方
	$('#sureBuyStartTime').datepicker({ 
		todayBtn : "linked", 
		autoclose : true, 
		todayHighlight : true, 
		endDate : new Date() 
		}).on('changeDate',function(e){
			if(e.date){
				$('#sureBuyEndTime').val("");
				var startTime = e.date;
				$('#sureBuyEndTime').datepicker('setStartDate',startTime); 
				var time = DateAdd("y",1,e.date);
				$("#sureBuyEndTime").datepicker('setEndDate',time);
            }else{
				$('#sureBuyEndTime').datepicker('setStartDate',null); 
                $("#sureBuyEndTime").datepicker('setEndDate',null);
            }
		});
	$('#sureBuyEndTime').datepicker({
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,  
	    endDate : new Date()
	}).on('changeDate',function(e){
	    var endTime = e.date;
	});
	
	//签约时间的查询选择条件方法
	$('#signStartTime').datepicker({ 
		todayBtn : "linked", 
		autoclose : true, 
		todayHighlight : true, 
		endDate : new Date() 
		}).on('changeDate',function(e){
			if(e.date){
				$('#signEndTime').val("");
				var startTime = e.date;
				$('#signEndTime').datepicker('setStartDate',startTime); 
				var time = DateAdd("y",1,e.date);
				$("#signEndTime").datepicker('setEndDate',time);
            }else{
				$('#signEndTime').datepicker('setStartDate',null); 
                $("#signEndTime").datepicker('setEndDate',null);
            }
		});
	$('#signEndTime').datepicker({
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,  
	    endDate : new Date()
	}).on('changeDate',function(e){  
		if(!e.date){
			$('#signStartTime').datepicker('setStartDate',null); 
            $("#signStartTime").datepicker('setStartDate',null);
        }
	});
	
	//加减年份的方法
	function DateAdd(interval, number, date) {
		var dateTime = new Date();
		switch (interval) {
			case "y": {
				//加number年
				dateTime.setFullYear(date.getFullYear() + number, date.getMonth(), date.getDate());
				return dateTime;
				break;
			}
			case "-y": {
				//减number年
				dateTime.setFullYear(date.getFullYear() - number, date.getMonth(), date.getDate());
				return dateTime;
				break;
			}
		}
	}

	//时间格式
	function formartDateTime(value){
		if(value==undefined || value==null){
			return "";
		}
		var datetime = new Date(value);
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1;
		var date = datetime.getDate();
		var hour = datetime.getHours();
		var minutes = datetime.getMinutes();
		var second = datetime.getSeconds();
		// 月，日，时，分，秒 小于10时，补0
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (second < 10) {
			second = "0" + second;
		}
		return year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second;
	}
	
	// 渲染表格
	$(gridSelector).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变
	
	// 选中行事件
	jqGridOption.onSelectRow = function(rowIndex, status) {
		var rowData = $(gridSelector).getRowData(rowIndex);

	};

	/** **************************************构造jqGrid end *************************************** */
	
	
	/** **************************************按钮事件 start *************************************** */
	//搜索功能
	$('#searchbox').click(function () {
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
//                        	//判断只选择了省 未选择市的情况
//                        	var province_name = $("#province_name").val();
//                        	var cityId = $("#city_name").val();
//                        	console.log(province_name+"-------"+cityId);
//                        	if(province_name!=""){
//                        		if(cityId==""){
//                        			Common.messageBox("提示", "请选择所属城市名称！", false);
//                        			return false;
//                        		}
//                        	}
                            var params = {
                            		//各查询条件的值
                            		cityId:$("#city_name").val(),
                            		buildingProjectId:$("#building_project_name").val(),
                            		sureBuyStartTime:$("#sureBuyStartTime").val(),
                            		sureBuyEndTime:$("#sureBuyEndTime").val(),
                            		signStartTime:$("#signStartTime").val(),
                            		signEndTime:$("#signEndTime").val(),
                            		provinceId:$("#province_name").val()
                            };
                            Common.jqGridReload("sellUnitGrid", params);
                            $(this).dialog("close");
                        }
                    }  ,{
                    	text : "重置",
                        "class" : "btn btn-primary btn-xs",
                        click : function() {
                        	$('#signEndTime').datepicker('setStartDate',null); 
                        	$('#signStartTime').datepicker('setEndDate',null); 
                        	$('#sureBuyEndTime').datepicker('setStartDate',null); 
                        	$('#sureBuyStartTime').datepicker('setEndDate',null); 
                        	//各查询条件重置
                        	document.getElementById("searchForm").reset();
                        	$("#province_name").select2("val","");
                        	$("#city_name").select2("val","");
                        	$("#building_project_name").select2("val","");
                        	$("#sureBuyStartTime").val("");
                        	$("#sureBuyEndTime").val("");
                        	$("#signStartTime").val("");
                        	$("#signEndTime").val("");
                        }
                    }]
                });
    });
	
	
	//导出Excel
	$("#export").click(function(){
		location.href="export?cityId="+$("#city_name").val()
		+"&buildingProjectId="+$("#building_project_name").val()
		+"&sureBuyStartTime="+$("#sureBuyStartTime").val()
		+"&sureBuyEndTime="+$("#sureBuyEndTime").val()
		+"&signStartTime="+$("#signStartTime").val()
		+"&signEndTime="+$("#signEndTime").val()
		+"&provinceId="+$("#province_name").val();
	});
	
	//导入excel文件
	var path;
	$("#import").click(function() {
		var dialog = $("#selFile").removeClass('hide').dialog({
			modal : true,
			title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-search'></i> 选择导入项目</h4></div>",
			title_html : true,
			width : "400px",
			buttons : [ {
				text : "取消",
				"class" : "btn btn-xs",
				click : function() {
					$(this).dialog("close");
				}
			}, {
				text : "导入",
				"class" : "btn btn-primary btn-xs",
				click : function() {
					var cityId = $("#city_name_import").val();
					var buildingProjectId = $("#building_project_name_import").val();
					if(cityId==undefined || cityId==null || cityId==""){
						Common.messageBox("提示", "请选择所属城市名称！", false);	
						return false;
					}
					if(buildingProjectId==undefined || buildingProjectId==null || buildingProjectId==""){
						Common.messageBox("提示", "请选择项目名称！", false);	
						return false;
					}
					if(Common.isEmpty(path)){
						Common.messageBox("提示", "请选择导入excel文件！", false);	
						return false;
					};
					Common.showMask("请稍候......");
					$.ajax({
						url : "import",
						data : {
							diskPath : path,
							cityId : cityId,
							buildingProjectId : buildingProjectId
						},
						type : "post",
						dataType : "json",
						success : function(json) {
							$("#selFile").dialog("close");
							Common.hideMask();
							if(json.isSuccess){
								Common.messageBox("提示", json.msg, true);
								Common.jqGridReload("#sellUnitGrid");
							}else{
								Common.messageBox("提示",json.msg, false);
							}
						},
						error : function() {
							$(this).dialog("close");
							Common.hideMask();
							Common.messageBox("提示", "服务器繁忙,请稍后在重试！！", false);
						}
					});
					$(this).dialog("close");
				}
			} ]
		});
	});
	
	//得到导入文件进行导入
	$("#uploadFile").uploadifive({
		buttonText : "导入数据",
		swf      : '${path}/assert/plugins/uploadify/uploadify.swf',
		uploadScript : "../../common/upload/excel",
		fileType : ".xls,.xlsx",
		queueSizeLimit : 1,
		removeCompleted : false,
		onUploadComplete : function(file, data) {
			var jsonData = eval("(" + data + ")");
			path=jsonData[0].diskPath;
		}
	});
	
	
	/** **************************************按钮事件 end *************************************** */
});
