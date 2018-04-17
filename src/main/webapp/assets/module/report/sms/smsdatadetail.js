$(function() {
	/** ***********************************************界面初使化********************************************************* */
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
		query(smsInfoGridTable);
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
									var smsTel = $("#smsTel").val();
									var datePeriod = $("#datePeriod").val();

									Common.jqGridReload(gridId, {
										smsTel : smsTel,
										datePeriod : datePeriod
									});

									$(this).dialog("close");
								}
							} ,{
                                text : "重置",
                                "class" : "btn btn-primary btn-xs",
                                click : function() {
                                    $("#smsTel").val("");
                                    $('#datePeriod').val("");
                                }
                            } ]
						});
	}
	
	/** ***********************************************界面初使化********************************************************* */

	/** **************************************构造jqGrid start *************************************** */
	var smsInfoGridTable = "#smsInfoGridTable";
	var smsInfoGridPager = "#smsInfoGridPager";

	// 构造jqGrid配置信息
	var jqGridOption = new Common().createGridOption({
		pagerSelector : smsInfoGridPager,
		gridSelector : smsInfoGridTable,
//		widthOffset : "25px",
		parentDOMClass : "col-sm-12",
		title : "短信数据查询",
		url : "query",
		postData : {
			datePeriod : new Date().dateAdd("d", -29).format("yyyy-MM-dd") + "至" + new Date().format("yyyy-MM-dd")
		},
		multiSelect : false,
		height : "500px",
		sortName : "log_sms_id",
		sortOrder : "desc",
		colNamesArray : ['短信ID', '接收电话', '短信内容', '发送人', '发送时间', '发送状态', '短信通道', '子系统'],
		colModel : [ {
			name : "logSmsId",
			index : "log_sms_id",
			width : 60,
			sorttype : "string",
			editable : false,
			hidden : true
		}, {
			name : "smsTel",
			index : "sms_tel",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "smsContent",
			index : "sms_content",
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
			name : "sendTime",
			index : "send_time",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "sendStatus",
			index : "send_status",
			width : 60,
			sorttype : "string",
			editable : false,
			formatter : smsStatus
		}, {
			name : "smsChannel",
			index : "sms_channel",
			width : 60,
			sorttype : "string",
			editable : false
		}, {
			name : "subSystemName",
			index : "sub_system_name",
			width : 60,
			sorttype : "string",
			editable : false
		}]
	});

	// 选中行事件
	jqGridOption.onSelectRow = function(rowIndex, status) {
		var rowData = $(smsInfoGridTable).getRowData(rowIndex);
	};

	// 渲染表格
	$(smsInfoGridTable).jqGrid(jqGridOption);

	$(window).triggerHandler('resize.jqGrid');// 浏览器大小发生改变时，GRID跟着一起变

	/** **************************************构造jqGrid end *************************************** */
});