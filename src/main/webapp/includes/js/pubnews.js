function PubNews() {
	this.textAreaId ="contentId";
	this.titleDom=$("#titleId");
	this.tagDom=$("#tagId");
	this.postUrl="data/news.createNews";
	this.btnDom=$("#btnPubId");
	
}
PubNews.prototype= {
    init : function () {
    	this.initEdit();
    	this.titleDom.val('');
    	this.tagDom.val('');
    	this.setEditContent('');
    	this.btnDom.unbind("click");
    	this.btnDom.bind("click",$.hitch(this,this.create));
    },
	initEdit :function () {
    	if ( typeof CKEDITOR == 'undefined' )
    	{
    		document.write(
    			'<strong><span style="color: #ff0000">Error</span>: CKEditor not found</strong>.' +
    			'This sample assumes that CKEditor (not included with CKFinder) is installed in' +
    			'the "/ckeditor/" path. If you have it installed in a different place, just edit' +
    			'this file, changing the wrong paths in the &lt;head&gt; (line 5) and the "BasePath"' +
    			'value (line 32).' ) ;
    	}
    	else
    	{	
    		this.editor = CKEDITOR.replace( this.textAreaId,
    				         { coreStyles_bold	: { element : 'b' },
    			               dcoreStyles_italic : { element : 'i' },
    			               fontSize_style : {element : 'font',attributes : { 'size' : '#(size)' }}
    			             }
    					  );
    		this.editor.setData( "" );
    		CKFinder.setupCKEditor( this.editor, '../' ) ;
    	}
    },
	setEditContent : function ( content ) {
		if( this.editor )  this.editor.setData( content || "" );
	},
	getEditContent : function () {
		if( this.editor ) return this.editor.getData();
		return "";
	},
	getValues　: function () {
		return {
			title :this.titleDom.val(),
			content :this.getEditContent(),
			tag :this.tagDom.val()
		};
	},
	create : function() {
		var url = this.postUrl;
		var values =this.getValues();
		var loadingHelper = new LoadingHelper();
		loadingHelper.showLoading("正在发布新闻，请稍等...");
		$.ajax({
	        "type" : "post",
	        "url" : url,
	        "dataType" : "json",
	        "data" :values,
	        "success" : function (data) {
	        	loadingHelper.hideLoading();
	            if( data.s == 1) {
	            	 noty({text :  data.i  });
	            } else {
	                 noty({text :  data.i ,type :"error"});
	            }
	        },
	        "error" : function (data) {
	        	loadingHelper.hideLoading();
	            console.info("error");
	        }
		});
	}	
}

$(function() {
	var pubnews = new PubNews ();
	pubnews.init();
});