<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
 <head>
	<title>KYU - DESCRIPTION PROJECT</title>
	<meta charset="utf-8" />
	<meta name="viewport"content="width=device-width, initial-scale=1, user-scalable=no" />		
	<link rel="stylesheet" href="assets/css/action.css" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/popup.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	
		<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/template.js"></script>
	<script src="assets/js/handlebars.min.js"></script>
 </head>
	<body class="is-preload">
		<div id="page-wrapper">
			<%@ include file="/common/header.jsp" %>

	   			<!-- Main -->
				<div id="main" class="wrapper style1">
					<!-- project 내용  -->
				</div>		
				
					<div class="container">
						<div class="review_box">
							<div>
								
									<h3>방문자 한줄평
								     		<a title="덧글달기" href="./comment?projectId=${requestScope.projectId}"><i style="float:right;clear:both" class="fas fa-comment-alt fa-2x"></i></a>				
									</h3>
									
								<span style="float:right"class="join_count"><strong><em id="avgScore">0/5.0</em></strong> | <em id="maxCount">0</em>건 등록</span> 
							</div>
							
							<ul id="list_review" class="alt">
							<!-- project 덧글 -->
							</ul>
							
							<p class="guide">
							<span> <i class="fas fa-child fa-2x"></i> 실제 방문한 사용자가 남긴 평가입니다 <i class="fas fa-child fa-2x"></i></span>
						</p>
						
						
						<ul id="page-count">
						</ul>
						
						</div>
					</div>
				</div>

<script type="template" id="list_item">

<li class="list_item" style="list-style-type: none" >

			<strong>{{visiterEmail}}</strong> 님의 한마디 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<p style="margin:0px 0em"class="review">
				{{content}}
			<span style="float:right">
				<i class="{{typeIcon}}" title="{{type}}"></i></sapn> | <span class="grade">{{score}}점</span> | <span class="date">{{date}}</span> 등록  | <a title="덧글달기" href="./comment?projectId=${requestScope.projectId}"><i class="far fa-edit"></i></a>
			</span> 
			</p>
</li>

</script>
<script type="template" id="template-project-infomation" >
<div class="container">
<header class="major">
	<h2>{{name}}</h2>
    <p>{{subdescription}}</p>
	<p>{{description}}</p>
</header>

	<span class="image fit main">
		<img src="{{image}}" alt="{{name}}" />
	</span>
			            <div class="col-2 col-4-medium col-12-xsmall">
								<c:choose>
								    <c:when test="${empty sessionScope.email }">
								        <a href="./login"><input type="button" class="button primary fit" value="프로젝트 열기"></a>
								    </c:when>
								    <c:otherwise>
								         <a href="{{url}}" target="_blank"><input type="button" id="sendBtn" class="button primary fit" value="프로젝트 열기"></a>
								    </c:otherwise>
								</c:choose>   
						</div>
			
</div>

</script>
<script>
$(document).ready(function(){

	var projectId = ${projectId};
	
	$.ajax({
		type : "GET",
		url : "./api/project/" + projectId,
		success : setHTML,
		error : function(request,status,error){
				alert(request.responseText);
			
		}
		
	});
	
	ajaxComment(projectId,0);
	
	//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
	 callScript("assets/js/main.js");
	
	 var checkMsg = "${ResultMessage}";
	 if( checkMsg != ""){
		 $(".popup_booking_wrapper").css("display","block");
	 } 
});

function ajaxComment(projectId,start){
	$.ajax({
		type : "GET",
		url : "./api/comment/" + projectId +"/" + start,
		success : function(response){
			setCommentHTML(response);
			setPageCount(response);
		},
		error : function(request,status,error){
			alert(request.responseText);
		}
		
	}); 
	
}
function setHTML(responseData){
	var projectInfo = responseData.projectList; 
	var data = {};
	data['name'] = projectInfo.name;
	data['subdescription'] = projectInfo.subdescription;
	data['description']  = projectInfo.description;
	data['image'] = projectInfo.image;
	data['url'] = projectInfo.url;
	
	templateParserAfter("#template-project-infomation",data, "#main");
}

//< 덧글을 수정합니다.
function setCommentHTML(responseData){
	
	if(responseData.allCount == 0) return;
	
	$("#list_review").empty();
	var comments = responseData.comments; 

	comments.forEach(function(v){	
			var data = {};
			data['visiterEmail'] = v.visiterEmail.substr(0,4) + "****";
			data['content'] = v.content;
			data['score'] = v.score;
			data['type'] = v.type;
			data['typeIcon'] = changTypeClassName(v.type);
			data['date']  = v.date;
		
			templateParserAfter("#list_item",data, "#list_review");
		});
	
	var avgScore = (Math.floor(responseData.avgScore * 10)/10);
	$("#avgScore").text( avgScore + "/5.0");
	$("#maxCount").text(responseData.allCount);
	
}

//< 덧글 패이지 수를 설정합니다.
function setPageCount(responseData){
	$("#page-count").empty();
	
	var pageCount = responseData.allCount / 4;
	pageCount = Math.floor(pageCount);
	
	if(responseData.allCount % 4 > 0)
		pageCount++;
	
	for( var index = 1; index <= pageCount; index++){
		$("#page-count").append(function(){

				var html = '<li style="list-style-type:none;float:left;">';			
				if((responseData.currentPage/4) == index-1){
					return html += '<a style="color:rgb(228, 76, 101)" href="javascript:void(0)">' + index + '</a></li>';		
				}
				
				return html += '<a style="color:rgb(255, 255, 255)" href="javascript:void(0)">' + index + '</a></li>';
	
		});
	}
	

	$("#page-count").on("click",function(event){
		var startNum = (event.target.innerText-1) * 4;
		var projectId = ${requestScope.projectId};
		ajaxComment(projectId,startNum);
	});	
}
function changTypeClassName(type){
	//< 응원, 칭찬, 비난, 충고, 버그
	var iconName = [ 
			{key:"응원", className :"far fa-laugh-beam"},
			{key:"칭찬", className :"far fa-smile"},
			{key:"비난", className :"far fa-angry"},
			{key:"충고", className :"far fa-frown-open"},
			{key:"버그", className :"far fa-dizzy"}
			]
	
	var result = iconName.filter(function (v) {
	    return v.key == type;
	});
	
	return result[0].className;
}
</script>

<%@ include file="/common/footer.jsp" %>
	</body>
</html>

