$(function() {
	/** ***********************************************界面初使化********************************************************* */
	Common.initSelect2("province", {
		width : "200px"
	});

	Common.initSelect2("city", {
		width : "200px"
	});

	Common.initSelect2("buildingProject", {
		width : "200px"
	});
	
	Common.initSelect2("agentId", {
		width : "200px"
	});

	$("#datePeriod").daterangepicker({
		applyClass : 'btn-sm btn-success',
		cancelClass : 'btn-sm btn-default',
		locale : {
			applyLabel : '确认',
			cancelLabel : '取消',
			fromLabel : '起始时间',
			toLabel : '结束时间',
			customRangeLabel : '自定义',
			firstDay : 1
		},
		ranges : {
			// '最近1小时': [moment().subtract('hours',1), moment()],
			'今日' : [ moment().startOf('day'), moment() ],
			'昨日' : [ moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day') ],
			'最近7日' : [ moment().subtract('days', 6), moment() ],
			'最近30日' : [ moment().subtract('days', 29), moment() ],
			'本月' : [ moment().startOf("month"), moment().endOf("month") ],
			'上个月' : [ moment().subtract(1, "month").startOf("month"), moment().subtract(1, "month").endOf("month") ]
		},
		opens : 'right', // 日期选择框的弹出位置
		separator : ' 至 ',
		showWeekNumbers : true, // 是否显示第几周

		// timePicker: true,
		// timePickerIncrement : 10, // 时间的增量，单位为分钟
		// timePicker12Hour : false, // 是否使用12小时制来显示时间

		// maxDate : moment(), // 最大时间
		format : 'YYYY-MM-DD'

	}, function(start, end, label) { // 格式化日期显示框
		$('#beginTime').val(start.format('YYYY-MM-DD'));
		$('#endTime').val(end.format('YYYY-MM-DD'));
	}).next().on('click', function() {
		$(this).prev().focus();
	});
	
	$("#datePeriod").val(new Date().dateAdd("d", -29).format("yyyy-MM-dd") + "至" + new Date().format("yyyy-MM-dd"));

	$("#query").click(function() {
		query(satisfactionDetailGridTable);
	});

	function query(gridId) {
		var dialog = $("#condition")
				.removeClass('hide')
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
									var provinceId = $("#province").val();
									var cityId = $("#city").val();
									var buildingProjectId = $("#buildingProject").val();
									var datePeriod = $("#datePeriod").val();
									var agentOperatorName = $("#agentOperatorName").val();
                                	var agentOperatorAccount = $("#agentOperatorAccount").val();
                                	var archivesTel1 = $("#archivesTel1").val();
                                	var archivesName = $("#archivesName").val();
                                	var appraiseScore = $("#appraiseScore").val();
                                	var agentId = $("#agentId").val();
                                	
									Common.jqGridReload(gridId, {
										agentOperatorName : agentOperatorName,
										agentOperatorAccount : agentOperatorAccount,
										archivesTel1 : archivesTel1,
										archivesName : archivesName,
										appraiseScore : appraiseScore,
										provinceId : provinceId,
										cityId : cityId,
										buildingProjectId : buildingProjectId,
										datePeriod : datePeriod,
										agentId : agentId
									});

									$(this).dialog("close");
								}
							} ,{
                                text : "重置",
                                "class" : "btn btn-primary btn-xs",
                                click : function() {
                                	$("#agentOperatorName").val("");
                                	$("#agentOperatorAccount").val("");
                                	$("#archivesTel1").val("");
                                	$("#archivesName").val("");
                                	$("#appraiseScore").val("");
                                    $('#province').select2("val", "");
                                    $('#province').trigger("change");
                                    $('#city').select2("val", "");
                                    $('#city').trigger("change");
                                    $('#buildingProject').select2("val", "");
                                    $('#buildingProject').trigger("change");
                                    $('#datePeriod').val("");
                                    $('#agentId').select2("val", "");
                                }
                            } ]
						});
	}
	
	$("#export").click(function() {
		Common.showMask("正在生成Excel数据！");
		
		var provinceId = $("#province").val();
		var cityId = $("#city").val();
		var buildingProjectId = $("#buildingProject").val();
		var datePeriod = $("#datePeriod").val();
		var agentOperatorName = $("#agentOperatorName").val();
    	var agentOperatorAccount = $("#agentOperatorAccount").val();
    	var archivesTel1 = $("#archivesTel1").val();
    	var archivesName = $("#archivesName").val();
    	var appraiseScore = $("#appraiseScore").val();
    	var agentId = $("#agentId").val();
		
		$.ajax({
			url : "exportsellsmsappraisedetailgriddata",
			type : "post",
			dataType : "json",
			data : {
				agentOperatorName : agentOperatorName,
				agentOperatorAccount : agentOperatorAccount,
				archivesTel1 : archivesTel1,
				archivesName : archivesName,
				appraiseScore : appraiseScore,
				provinceId : provinceId,
				cityId : cityId,
				buildingProjectId : buildingProjectId,
				datePeriod : datePeriod,
				agentId : agentId
			},
			success : function(json) {
				Common.hideMask();
				if (json.isSuccess) {
					var url = "../../common/download/" + json.result + "/" + encodeURI(encodeURI("客户评分明细统计"));
					$("<iframe width=0px height=0px id='download' name='download' style='display:none' src='" + url + "'></iframe>").prependTo('body');
				} else {
					Common.messageBox("提示", "数据文件生成失败！", false);
				}
			},
			error : function() {
				Common.hideMask();
				Common.messageBox("提示", "数据文件生成失败！！", false);
			}
		});
	});
	/** ***********************************************界面初使化********************************************************* */

	/** **************************************构造jqGrid start *************************************** */
	var satisfactionDetailGridTable = "#satisfactionDetailGridTable";
	var satisfactionDetailGridPager = "#satisfactionDetailGridPager";

	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : satisfactionDetailGridPager,
		gridSelector : satisfactionDetailGridTable,
		parentDOMClass : "col-sm-12",
		title : "客户评分明细统计",
		url : "getsellsmsappraisedetailgriddata",
		postData : {
			datePeriod : new Date().dateAdd("d", -29).format("yyyy-MM-dd") + "至" + new Date().format("yyyy-MM-dd")
		},
		multiSelect : false,
		height : "500px",
		sortName : "send_time",
		sortOrder : "desc",
		colNamesArray : [ '评价ID', '公司', '省份', '城市', '楼盘', '代理公司', '置业顾问', '客户电话', '客户姓名', '评分', '回复内容', '短信发送时间', '客户回复时间', '客管回访记录', '客管回访时间'],
		colModel : [ {
			name : "sellSmsAppraiseId", 
			index : "sell_sms_appraise_id",
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
			name : "provinceName",
			index : "province_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "cityName",
			index : "cityName",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "buildingProjectName",
			index : "building_project_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "agentName",
			index : "agent_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "agentOperatorName", 
			index : "agent_operator_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "archivesTel1", 
			index : "archives_tel1",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "archivesName", 
			index : "archives_name",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "appraiseScore", 
			index : "appraise_score",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "recvContent", 
			index : "recv_content",
			width : 60,
			sortable : false,
			editable : false
		}, {
			name : "sendTime", 
			index : "send_time",
			width : 60,
			sortable : false,
			editable : false
		}, {
			name : "recvTime",
			index : "recv_time", 
			width : 60,
			sortable : false,
			editable : false
		}, {
			name : "replyContent",
			index : "reply_content", 
			width : 60,
			sortable : false,
			editable : false
		}, {
			name : "replyTime",
			index : "reply_time", 
			width : 60,
			sortable : false,
			editable : false,
			formatter:dateFormatter
		}]
	});

	// 选中行事件
	jqGridOption.onSelectRow = function(rowIndex, status) {
		var rowData = $(satisfactionDetailGridTable).getRowData(rowIndex);
	};

	// 渲染表格
	$(satisfactionDetailGridTable).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变
	/** **************************************构造jqGrid end *************************************** */
	
	/*************************************LY 2017-10-10 添加跟进操作 start********************************************/
	$("#runComplain").click(function(){
		var selectedRowsId = Common.jqGridGetSelectedRow(satisfactionDetailGridTable);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想跟进的数据！", false);
			return false;
		}
		var rowData = Common.jqGridGetRowData(satisfactionDetailGridTable, selectedRowsId);
		
		if(rowData.replyContent!='' && rowData.replyContent != null){
			Common.messageBox("提示", "该条信息已跟进！", false);
			return false;
		}
		
		window.location="followupappraisescore?sellSmsAppraiseId="+rowData.sellSmsAppraiseId;
	});
	
	$("#follow_view").click(function(){
		var selectedRowsId = Common.jqGridGetSelectedRow(satisfactionDetailGridTable);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想查看的数据！", false);
			return false;
		}
		var rowData = Common.jqGridGetRowData(satisfactionDetailGridTable, selectedRowsId);
		
		window.location="view?sellSmsAppraiseId="+rowData.sellSmsAppraiseId;
	});
	/**************************************LY 2017-10-10 添加跟进操作 end*******************************************/

	$("#modifyScore").click(function() {
		var selectedRowsId = Common.jqGridGetSelectedRow(satisfactionDetailGridTable);
		if (Common.isEmpty(selectedRowsId) || selectedRowsId.length <= 0) {
			Common.messageBox("提示", "请选择一条你想修改评分的数据！", false);
			return false;
		}
		
		var rowData = Common.jqGridGetRowData(satisfactionDetailGridTable, selectedRowsId);
		
		var dialog = $("#modifyScorePanel")
		.removeClass('hide')
		.dialog({
			modal : true,
			title : "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-pencil-square-o'></i> 重新评分</h4></div>",
			title_html : true,
			width : "500px",
			buttons : [ {
				text : "取消",
				"class" : "btn btn-xs",
				click : function() {
					$(this).dialog("close");
				}
			}, {
				text : "确定",
				"class" : "btn btn-primary btn-xs",
				click : function() {
					var score = $("#score").val();
					var reason = $("#reason").val();
					var sellSmsAppraiseId = rowData.sellSmsAppraiseId;
					
					$.ajax({
						url : "modifyappraisescore",
						type : "post",
						data : {
							score : score,
							reason : reason,
							sellSmsAppraiseId : sellSmsAppraiseId
						},
						dataType : "json",
						success : function(json) {
							if (json.isSuccess) {
								Common.messageBox("提示", json.msg, true);
								$.extend(rowData, {appraiseScore : score});
								Common.jqGridSetRowData(satisfactionDetailGridTable, selectedRowsId, rowData);
								$("#reason").val("");
								$("#score").val(5);
								dialog.dialog("close");
							} else {
								Common.messageBox("提示", json.msg, false);
							}
						},
						error : function() {
							Common.messageBox("提示", Common.SERVER_EXCEPTION, false);
							dialog.dialog("close");
						}
					});
				}
			} ]
		});
	});
});