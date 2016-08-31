$(function () {
	var news = new NewsView();
	news.getMessageInfo();
});
function NewsView () {
	this.url = "data/news.getNewsById"
	this.titleDom = $("#msgTitleID");
	this.kindDom = $("#msgKindID");
	this.dateDom = $("#msgDateID");
	this.contentDom = $("#msgContentID");
}
NewsView.prototype =  {
		getQueryString : function (name)
		{
			 if( name == null || name.length <=1 ) return null;
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		},
		getMessageInfo : function () {
			var id = this.getQueryString("id");
			if ( id == null ) return;
			var params = { _id : id};
			var _this = this;
			$.ajax({
		        "type" : "post",
		        "url" : _this.url ,
		        "dataType" : "json",
		        "data" : params,
		        "success" : function (data) {
		               console.info("success");
					   if(data.s == 1) {
						   _this.showMessage( data);
					   } else {
					   	noty({ text: data.i , type:"error"});
					   }
		        },
		        "error" : function (data) {
		               console.info("error");
					   noty({ text: "System Error,Please Contact your administrator" , type:"error"});
		        }
			});
		},
		showMessage : function ( info ) {
			this.titleDom.empty();
			this.kindDom.empty();
			this.dateDom.empty();
			this.contentDom.empty();
			if ( info == null ) return;
			this.titleDom.text ( info.title ==null ? "": info.title);
			this.kindDom.text ( info.tag ==null ? "": info.tag);
			this.dateDom.text ( info.date ==null ? "": info.date);
			this.contentDom.html ( info.content ==null ? "": info.content);
		}
}

