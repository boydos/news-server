<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="ckeditorDependency.jsp"></jsp:include>
<jsp:include page="ckfinderDependency.jsp"></jsp:include>
<script src="includes/js/pubnews.js"></script>
</head>
<body>
<jsp:include page="banner.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-12">
		
			 <div class="formTitle">标题 *</div>
			 <div class="formInput form-group">
				  <input type="text" id="titleId" class="form-control" value="" placeholder="新闻标题" />
			 </div>
			 <div class="formTitle">标签 *</div>
			 <div class="formInput form-group">
				  <input type="text" id="tagId" class="form-control" value="" placeholder="新闻类型" />
			 </div>
			 <div class="formTitle">内容 *</div>
			 <div class="formInput form-group">
				  <textarea id="contentId" name="contentId"
				  rows="19" class="form-control" placeholder="新闻内容"></textarea>
			 </div>
			 <button id="btnPubId" class="btn btn-lg btn-primary btn-block" type="submit">发布</button>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>