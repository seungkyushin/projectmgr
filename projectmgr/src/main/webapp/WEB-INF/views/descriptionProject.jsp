<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
<title>KYU - DESCRIPTION PROJECT</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/action.css" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/popup.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
	
	
</head>
<body class="is-preload landing">
	<div id="page-wrapper">
		<%@ include file="/common/header.jsp"%>

		<!-- Main -->
		<div id="main" class="wrapper style1">
			<div class="container">
				<header class="major">
					<h2>${project.name}</h2>
					<p>${project.subdescription}</p>
					<p>${project.description}</p>
				</header>

				<span class="image fit main"> <img src="${project.image}" alt="${project.name}" />
				</span>
				<div class="col-2 col-4-medium col-12-xsmall">
					<c:choose>
						<c:when test="${empty sessionScope.email }">
							<a href="./login"><input type="button"
								class="button primary fit" value="프로젝트 열기"></a>
						</c:when>
						<c:otherwise>
							<a href="${project.url}" target="_blank"><input type="button"
								id="sendBtn" class="button primary fit" value="프로젝트 열기"></a>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>
		<hr>
		<div class="container">
			<div class="review_box">
				<div>
					<h3>
						* 방문자 한줄평 *<a title="덧글달기"
							href="./comment?projectId=${project.id}"><i
							style="float: right; clear: both"
							class="far fa-comment-alt fa-2x"></i></a>
					</h3>

					<span style="float: right" class="join_count"><strong><em
							id="avgScore">0/5.0</em></strong> | <em id="maxCount">0</em>건 등록</span>
				
				<div class="row htr-uniform htr-50">
					<div class="col-4 col-12-xsmall">
						<select id="searchType">
							<option value="none">- 검색 종류 -</option>
							<option value="email">이메일</option>
							<option value="content">내용</option>
							<option value="score">점수</option>
							<option value="commentType">덧글종류</option>
							<option value="registerDate">등록날짜/option>
						</select>
					</div>
					<div class="col-4 col-12-xsmall">
						<input type="text" id="keyword" value="" placeholder="입력" maxlength="10">
					</div>
					<div class="col-1 col-12-xsmall">
						<input type="button" id="searchBtn" value="찾기">
					</div>
					
				</div>
				<br>
				</div>
		
				<ul id="list_review" class="alt">
					<!-- project 덧글 -->
				</ul>

				<p class="guide">

					<span> <i class="fas fa-child fa-2x"></i> 실제 방문한 사용자가 남긴
						평가입니다 <i class="fas fa-child fa-2x"></i></span>
				</p>
			</div>
		</div>
		<hr>
	
  		<div style="text-align:center;" id="page-navigate" data-crtGroupPageNum=0 data-max=0>
			<ul style="display:inline-block;" id="page-count"></ul>
		</div>
		
		<%@ include file="/common/footer.jsp"%>	
	</div>


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

	<script>
		$(document).ready(function(){
			ajaxComment(1);
			
			$("#searchBtn").on("click",function(){
				ajaxComment(1);
			});
			
			
			
			//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
			callScript("assets/js/main.js");
		});

		function ajaxComment(pageNum) {
			var projectId = "${project.id}";
			if(projectId == ""){
				var param = getURLParameter(window.location.search);
				projectId = param['id'];
			}
			
			var sendData = {};
			sendData['reqPageNum'] = pageNum;
			sendData['projectId'] = projectId;
			sendData['type'] = $("#searchType").val();
			sendData['keyWord'] = $("#keyword").val(); 
			
			
			$.ajax({
				type : "POST",
				url : "api/comment",
				data : sendData,
				success : function(response) {
					setCommentHTML(response);
					setPageCount(response);

					window.setTimeout(function() {
						$("body").removeClass('is-preload');
					}, 1000);

				},
				error : function(request, status, error) {
					alert(request.responseText);
				}

			});

		}
		function setProjectHTML(responseData) {
			var data = {};
			data['name'] = projectInfo.name;
			data['subdescription'] = projectInfo.subdescription;
			data['description'] = projectInfo.description;
			data['image'] = projectInfo.image;
			data['url'] = projectInfo.url;

			templateParserAfter("#template-project-infomation", data, "#main");
		}

	
		//< 덧글을 수정합니다.
		function setCommentHTML(responseData) {
			$("#list_review").empty();
						
			var avgScore = (Math.floor(responseData.avgScore * 10) / 10);
			$("#avgScore").text(avgScore + "/5.0");
			$("#maxCount").text(responseData.comment.allCount);
			
			var comments = responseData.comment.items;
			if (responseData.comment.allCount == 0 || comments.length == 0)
				return;
		
			comments.forEach(function(v) {
				var data = {};
				data['visiterEmail'] = v.visiterEmail;
				data['content'] = v.content;
				data['score'] = v.score;
				data['type'] = v.type;
				data['typeIcon'] = changTypeClassName(v.type);
				data['date'] = v.date;

				templateParserAfter("#list_item", data, "#list_review");
			});

		}

		
		//< 덧글 패이지 수를 설정합니다.
		function setPageCount(responseData) {
			var comment = responseData.comment;
			var page = responseData.page;
			var groupPage = responseData.groupPage;
			var avgScore = responseData.avgScore;
			
			$("#page-count").empty();

			if( comment.items.length == 0 )
				return;
			
			//< 보여주는 페이지 수를 제한한다.
			$("#page-navigate").data("crtGroupPageNum", groupPage.crtNum);
			$("#page-navigate").data("max", groupPage.maxLimitCount);
			
			if( groupPage.crtNum != 1){
				$("#page-count").append(function() {
					var html = '<li style="list-style-type:none;float:left;">';
					return  html + '<a href="javascript:void(0)" id="groupPage-pre" class="fas fa-angle-double-left"></a></li>';
				});
			}
			
			for (var index = page.startNum; index <= page.limitNum; index++) {
				$("#page-count").append(function() {
							var html = '<li style="list-style-type:none;float:left;">';
							
							if (page.crtNum  == index ) {
								return html += '<a href="javascript:void(0)" class="icon fa-github" data-index='+ index + '>'+ index +'</a></li>';
							}

							return html += '<a href="javascript:void(0)" class="icon alt fa-github" data-index='+ index + '>'+ index +'</a></li>';
				});
			}
			
			if( groupPage.crtNum != groupPage.maxCount){
				$("#page-count").append(function() {
					var html = '<li style="list-style-type:none;float:left;">';
					return html + '<a href="javascript:void(0)" id="groupPage-next" class="fas fa-angle-double-right"></a></li>';
				});
			}
	
		}
		
		
		
		$("#page-count").on("click", function(event) {
			var pageNum = 0;
			var grouppage = $("#page-navigate").data("crtGroupPageNum");
			var maxGroupPage = $("#page-navigate").data("max");
			
			if( event.target.id == "groupPage-pre"){
				pageNum = ((grouppage-1)*maxGroupPage);
				
			}else if(event.target.id == "groupPage-next"){
				pageNum = (grouppage*maxGroupPage)+1;
			}else{
				 pageNum = event.target.dataset.index ;
			}

			if( !pageNum || pageNum == 0){
				return;
			}
		
			ajaxComment(pageNum);
		});
		
		function changTypeClassName(type) {
			//< 응원, 칭찬, 비난, 충고, 버그
			var iconName = [ {
				key : "응원",
				className : "far fa-laugh-beam"
			}, {
				key : "칭찬",
				className : "far fa-smile"
			}, {
				key : "비난",
				className : "far fa-angry"
			}, {
				key : "충고",
				className : "far fa-frown-open"
			}, {
				key : "버그",
				className : "far fa-dizzy"
			} ]

			var result = iconName.filter(function(v) {
				return v.key == type;
			});

			return result[0].className;
		}
		
	</script>

	<%@ include file="/common/popup.jsp"%>
</body>
</html>

