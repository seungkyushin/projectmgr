<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
<head>
<title>KYU - COMMENT</title>
	<meta charset="utf-8" />
	<meta name="viewport"content="width=device-width, initial-scale=1, user-scalable=no" />	
	<link rel="stylesheet" href="assets/css/action.css" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/popup.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
 <body>
	<div id="page-wrapper">
		<%@ include file="/common/header.jsp"%>
		<!-- Main -->
		<div id="main" class="wrapper style1">
			<div class="container">
				<header class="major">
					<h2>${requestScope.name}</h2>
					<p>덧글은 개발자에게 큰 힘이 됩니다!!</p>
					<p id="checkmsg"></p>
				</header>
				<!-- FORM -->
				<section>
					<h3>평점과 하고싶은 말을 남겨주세요!</h3>
					<form id="formData" method="POST" action="./addcomment">
						<input type="hidden" id="projectId" name="projectId" value="${requestScope.projectId}">
						<div class="row gtr-uniform gtr-50">
							<div class="col-12">
								<div id="score">
									<i class="fas fa-thumbs-up fa-2x"> <input type="checkbox"
										id="score1" name="score" value="1" title="1점">
									</i> <i class="fas fa-thumbs-up fa-2x"> <input type="checkbox"
										id="score2" name="score" value="2" title="2점">
									</i> <i class="fas fa-thumbs-up fa-2x"> <input type="checkbox"
										id="score3" name="score" value="3" title="3점">
									</i> <i class="fas fa-thumbs-up fa-2x"> <input type="checkbox"
										id="score4" name="score" value="4" title="4점">
									</i> <i class="fas fa-thumbs-up fa-2x"> <input type="checkbox"
										id="score5" name="score" value="5" title="5점">
									</i>
								</div>


							</div>

							<div class="col-5">
								<select name="type" id="type">
									<option value="0">- 덧글의 분류 -</option>
									<option value="응원">응원</option>
									<option value="칭찬">칭찬</option>
									<option value="비난">비난</option>
									<option value="충고">충고</option>
									<option value="버그">버그</option>
								</select>
							</div>

							<div class="col-12">
								<textarea name="content" id="content" placeholder="내용을 입력해주세요"
									rows="6"></textarea>
							</div>
							<div class="col-6 col-12-medium">
								<input type="checkbox" id="showCheck" name="showCheck">
								<label for="showCheck">개발자에게만 보이게 하기</label>
							</div>

							<div class="col-8"></div>
							<div class="col-4">
								<ul class="actions">
									<li><input type="button" id="sendBtn" value="확인"
										class="primary" /></li>
									<li><input type="reset" value="초기화" /></li>
								</ul>
							</div>
						</div>
					</form>
				</section>

			</div>
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
	
<script>
$(document).ready(function(){

	//< 점수 
	$("#score").on("click",function(event){
		var clickedValue = event.target.firstElementChild.value;
		for (var index = 0; index < $("#score").children().length; index++) {
			
			if (index + 1 <= clickedValue) {
				$(this).children().eq(index).css('color',"#e44c65");
				continue;
			}

			$(this).children().eq(index).css('color',"rgba(255, 255, 255, 0.75)");
		}

		$(this).children().eq(clickedValue - 1).children().first().prop("checked", true);
	});
	
	


	//< 데이터 전송
	$("#sendBtn").on("click",function(event) {
		event.preventDefault();

		if( isCheckForm() == true )
		 {
			$("#formData").submit();
		 }

		}); 
	
	//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
	callScript("assets/js/main.js");
	
});


function isCheckForm() {
	var content = $("#content").val();
	var checkMsgHTML = "";
	var result = true;
	
	$("#checkmsg").html("");
	
	if( content.length <= 0 ){
		checkMsgHTML += " [<strong style='color:#e44c65'>내용</strong>] "
		startAnimation("#content","shake");
		result = false;
	}
	
	if( result == false){
		checkMsgHTML += "를 확인해 주세요!";
		$("#checkmsg").html(checkMsgHTML);
	}
	
	return result;
}
</script>

	<%@ include file="/common/popup.jsp"%>
	
</body>
</html>

