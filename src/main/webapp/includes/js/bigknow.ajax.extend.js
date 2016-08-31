/**
 * 
 * @param $
 */
(function($){
	$.sendbigknow =  function (url , callback, params) {
		return $.ajax({
	        "type" : "post",
	        "url" : url ,
	        "dataType" : "json",
	        "data" : params,
	        "success" : function (data) {
	               console.info("success");
	               if(callback!=null) {
	            	   callback( data);
	               }
				   if(data.s == 1) {
				   
				   } else {
				  /* 	noty({ text: data.i , type:"error"});*/
				   	if(data.i != null )showError(data.i);
				   	else {
				   		showError("Error, Please Try Again");
				   	}
				   }
	        },
	        "error" : function (data) {
	               console.info("error");
	           	   showError("System Error,Please Contact your administrator");
				   /*noty({ text: "System Error,Please Contact your administrator" , type:"error"});*/
	        }
		});
	};
	 $.hitch1 = function(context, func) {
	        var args = Array.prototype.slice.call(arguments, 
	                2/*Remove context, and func*/);
	        
	        return function() {
	            return func.apply(context, 
	                    Array.prototype.concat.apply(args, arguments));
	        };
	    };
})(jQuery);