<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 
Other dependency is in commonJs.jsp
<script src="js/jquery-1.11.1.js"></script>
<script src="includes/js/dom.help.bigknow.js"></script>
 -->

<link href='includes/css/tableCss.css' rel='stylesheet'>
<!-- <link href="css/jquery.dataTables.min.css" rel='stylesheet'></link> -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script src="includes/js/bigknow.ajax.extend.js" type="text/javascript"></script>
<script src="includes/js/bigknow.table.extend.js" type="text/javascript"></script>

<%--
	includes : <jsp:include page = "newTableDependency.jsp"/>
	
$(function () {
	this.tableID ="tableList";
	tableJs = new TableJs(this.tableID);
	tableJs.pushHead("拼音", "name");
	tableJs.pushHead("汉字", "value");
	
	var configHelper = new TableJsConfigHelper();
	configHelper.setFenYe(true);
	configHelper.setCheckBox(true, "checkbox", true);
	configHelper.setOperation( "myoperation", true, "操作")
	
	var replaceHelper = new TableJsValueHelper();
	replaceHelper.pushValue("日本", "日本帝国");
	replaceHelper.pushValue("上海", "上海的海上");
	
	configHelper.pushReplace("value", replaceHelper.getData());
	
	var relationHelper = new TableJsValueHelper();
	relationHelper.pushValue("北京", "BEI JING City");

	configHelper.pushRelation("name", "value", relationHelper.getData());
	
	var relationHelper = new TableJsValueHelper();
	relationHelper.pushValue("bei jing", "Today")

	configHelper.pushRelation("value", "name", relationHelper.getData());
	
	
	var btnHelper = new TableJsKeyValueHelper();
	btnHelper.pushValue("name", ["bei jing","zhong guo"]);
	
	configHelper.pushBtn("myoperation", "删除", deleteFunc, "btn-success btn-sm", btnHelper.getData());
	
	tableJs.setConfig( configHelper.getConfig() );

	
	var data = [
	            {name : "tian jin", value: "天津" },
	            {name : "bei jing", value: "北京" },
	            {name : "shang hai", value: "上海" },
	            {name : "ri ben", value: "日本" },
	            {name : "zhong guo", value: "中国" },
	            {name : "zhong guo", value: "中国1" }
	    ];
	tableJs.setData(data, "name", "value");
	
	tableJs.show();
});
function deleteFunc ( evt) {
	console.info( evt );
	console.info("deleteFuncData",this.getValuesOn( $(evt.target)));
	console.info("checked",this.getValues(true));
}
	

 --%>