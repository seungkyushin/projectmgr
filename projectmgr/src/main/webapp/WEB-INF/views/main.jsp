<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
 <head>
	<title>KYU - MAIN</title>
	<meta charset="utf-8" />
	<meta name="viewport"content="width=device-width, initial-scale=1, user-scalable=no" />		<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/action.css" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/popup.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
 </head>
 
<body class="is-preload landing">
	<div id="page-wrapper"> 

		<%@ include file="/common/header.jsp"%>
		<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<h2>WEB DEVELOPER</h2>
							<p>웹 개발자 취업 가즈아! <br>
								개인적으로 공부하면서 만들어본 웹 프로젝트 입니다.<br />
							 </p>
						</header>
						<span class="image animated pulse infinite"><img src="images/pic01.jpg" alt="" /></span>
					</div>
					<a href="#info" class="goto-next scrolly">Next</a>
				</section>
				
					<!-- One -->
		<section id="info" class="spotlight style1 bottom">
					<span class="image fit main"><img src="images/pic02.jpg" alt="" /></span>
					<div class="content">
						<div class="container">
							<div class="row">
								<div class="col-4 col-12-medium">
									<header>
										<h2>제작 의도</h2>
										<p>'내가 만든 웹페이지를 어떻게 하면 간단하고 빠르게 보여줄수 있을까?'</p>
									</header>
								</div>
								<div class="col-2 col-12-medium">
									<p>포트폴리오에 사용된 언어 및 라이브러리 : HTML, JQUERY, JSP, MYSQL, SPRING</p>
								</div>
								<div class="col-4 col-12-medium">
									<p>공부를 하면서 작업한 프로젝트라 문제가 많을 수있습니다.
										버그나 수정이 필요한 부분이 있다면 덧글로 남겨주시길 바랍니다.
										(욕은 예비 개발자를 슬프게 합니다ㅜㅜ)
									</p>
								</div>
							</div>
						</div>
					</div>
					<a href="#skill" class="goto-next scrolly">Next</a>
				</section>
				
				<!-- 프로젝트 리스트 -->
				<div id="projectList-wrapper">
				</div>
				
				<section id="skill" class="wrapper style1 special fade-up" >
					<div class="container">
						<header class="major">
							<h2>Skill Stack</h2>
							<p>프로젝트에 사용한 기술 및 언어</p>
						</header>
						<div class="box alt">
							<div class="row gtr-uniform">
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-area-chart"></span>
									<h3>MYSQL</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-comment"></span>
									<h3>JSP</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-flask"></span>
									<h3>SPRING</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-paper-plane"></span>
									<h3>Non semper interdum</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-file"></span>
									<h3>Odio laoreet accumsan</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
								<section class="col-4 col-6-medium col-12-xsmall">
									<span class="icon alt major fa-lock"></span>
									<h3>Massa arcu accumsan</h3>
									<p>Feugiat accumsan lorem eu ac lorem amet accumsan donec. Blandit orci porttitor.</p>
								</section>
							</div>
						</div>
						<footer class="major">
							<ul class="actions special">
								<li><a href="#" class="button">버튼 ^^*</a></li>
							</ul>
						</footer>
					</div>
				</section>
					
		
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
		
		<script type="template" id="template-project">
  <section id="project-{{id}}" class="spotlight {{style}} {{direction}}">
	<span class="image fit main"><img src="{{image}}" alt="{{name}}" /></span>
	<div class="content">
		<header>
			<h2>{{name}}</h2>
			<p>{{subdescription}}</p>
		</header>
		<p>{{description}}</p>
		<ul class="actions">
	       <li><a href="./descriptionProject?id={{id}}" class="button">자세히 보기</a></li>
		</ul>
	</div>
		<a href="{{next}}" class="goto-next scrolly">Next</a>
 </section>
</script>
<script>
  $(document).ready(function(){
	
	  //< 프로젝트 정보를 갱신한다.
		 $.ajax({
			type : "GET",
			url : "./api/project/0",
			success : function(response){
					setProjectInfomation(response);
					
					window.setTimeout(function() {
						$("body").removeClass('is-preload');
					}, 1000);
					
				},
			error : function(request,status,error){
						alert(request.responseText);
						//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
						 callScript("assets/js/main.js");
					}
			}); 
		
}); 
	
function setProjectInfomation(responseData){
	responseData.projectList.forEach(function(v,i){
			setProjectHTML(v,i);
			}); 
	
	//< 인트로 페이지 이후 최신 프로젝트로 이동 할 수 있게 변경
	$("#info").children(".goto-next.scrolly").attr("href","#project-" + responseData.projectList.length);
	
	//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
	 callScript("assets/js/main.js");
}

//< 프로젝트의 HTML을 설정한다.
function setProjectHTML(responseData,childNum){
		var projectInfo = responseData; 
		var data = {};
		
		data['id'] = projectInfo.id;
		data['name'] = projectInfo.name;
		data['subdescription'] = projectInfo.subdescription;
		data['description']  = projectInfo.description;
		data['image'] = projectInfo.image;
		data['url'] = projectInfo.url;
			
		data['style'] = "style" + makeRandom(1,4);
		var direction = ['right','left'];
		data['direction'] = direction[ makeRandom(0,2)];
		
		data['next'] = data['id']-1 > 0 ? "#project-"+(data['id']-1) : "#skill";
		
		var resultHTML = templateParserAfter("#template-project", data,"#projectList-wrapper");
		
		$("#nav-projectList").append(function(){
			var html = "<li><a href='#project-" + projectInfo.id + "' class='scrolly'>" + projectInfo.name +"</a></li>"; 
			return  html;
		});
}
</script>
	<%@ include file="/common/popup.jsp"%>		
</body>
</html>


