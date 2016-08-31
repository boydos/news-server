
function NewsList() {
	this.tablejs = new TableJs("newslistId")
	this.tablejs.pushHead("UID", "_id"); 
	this.tablejs.pushHead("标题", "title"); 
	this.tablejs.pushHead("标签", "tag");// 显示的标题：名称，对应的key：为 name
	this.tablejs.pushHead("日期", "date");
	this.tablejs.setSortType("desc", 3);
	var configHelper = new TableJsConfigHelper();
	configHelper.setFenYe(true);
	configHelper.setCheckBox(false, "radio");
	configHelper.setOperation( "myoperation", true, "操作")
	var btnHelper = new TableJsKeyValueHelper();
	btnHelper.pushValue("_id", [],false);
	configHelper.pushBtn("myoperation", '<i  title="查看详情" class="glyphicon glyphicon-zoom-in"></i>', $.hitch(this.tablejs,this.ViewData), "btn btn-sm btn-link", btnHelper.getData());
	this.tablejs.setConfig( configHelper.getConfig() );
	
	this.url="data/news.getNewsAll";
	
}
NewsList.prototype= {
		load : function () {
			this.getNewsWithoutServer(this.url);
		},
		ViewData : function (evt) {
			var target = $(evt.target);
			var data =this.getValuesOn(target);
			window.location ="/news/page/newsView?id="+data._id;
		},
		getNewsWithoutServer : function(url) {
			var _this = this;
			var loadingHelper = new LoadingHelper();
			loadingHelper.showLoading("正在从服务器加载数据，请稍等..");
			$.ajax({
		        "type" : "post",
		        "url" : url ,
		        "dataType" : "json",
		        "data" : {},
		        "success" : function (data) {
		               console.info("success",data);
		               loadingHelper.hideLoading();
		               if(data.s == "1") {
		            	   _this.show( data.list );
		               } else {
		   			   	noty({ text: data.i , type:"error"});
					   }
		        },
		        "error" : function (data) {
		               console.info("error");
		               loadingHelper.hideLoading();
		        }
			});
		},
		show : function ( data) {
			this.tablejs.setData(data);
			this.tablejs.show(false);
		}
}

$(function () {
	  var newslist = new NewsList();
	  newslist.load();
});