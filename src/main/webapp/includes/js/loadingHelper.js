/**
 * 
 */
function LoadingHelper ( parentDomId,infoDomId) {
	if( parentDomId == null) this.createDialog();
	this.loadingDom = $("#" + (parentDomId || "loadingImgId"));
	this.loadingInfoDom =$("#" + (infoDomId || "loadingInfoId"));
	
	this.loadingDom.dialog({
		autoOpen : false,
		modal: true,
		minHeight : "50px",
		minWidth : "400px"
	});
	this.loadingDom.parent().children(".ui-dialog-titlebar").hide();
	//$(".ui-dialog-titlebar").hide();
	this.interval = null;
	this.time = 0;
	this.info ="";
}

LoadingHelper.prototype = {
		createDialog : function () {
			var bodyDom = $("body");
			var dialogDom = $('<div id="loadingImgId" style="float: left;padding : 5px;min-width:500px;width :100%;display:none"></div>').appendTo(bodyDom);
			var imgDom =$('<div style="float:left"><img alt="" src="img/ajax-loader.gif" /></div>').appendTo(dialogDom);
			var textDom = $('<div style="float:left;padding:12px;" id="loadingInfoId">服务器正在运行中..</div>').appendTo(dialogDom);
		},
		showLoading : function ( info ) {
			
			this.setInfo( info );
			this.setInterval();
			this.loadingDom.dialog("open");
		},
		setInterval : function () {
			this.interval = window.setInterval($.hitch(this, this.update), 1000);
		},
		clearInterval : function () {
			if( this.interval != null) clearInterval( this.interval );
		},
		update : function () {
			this.loadingInfoDom.text( this.info +"  "+(++this.time)+"S" );
		},
		setInfo : function ( value ) {
			if( value == null) value ="服务器忙,请稍后..";
			this.info = value;
			this.loadingInfoDom.text( value );
		},
		hideLoading : function () {
			this.clearInterval();
			this.loadingDom.dialog("close");
		}
}