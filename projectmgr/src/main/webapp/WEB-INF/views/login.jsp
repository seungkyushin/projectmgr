<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
	<head>
<title>KYU - LOGIN</title>
	<meta charset="utf-8" />
	<meta name="viewport"content="width=device-width, initial-scale=1, user-scalable=no" />		<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/action.css" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<link rel="stylesheet" href="assets/css/popup.css"/>
</head>
	<body>
		<div id="page-wrapper">
			<%@ include file="/common/header.jsp"%>

			<!-- Main -->
				<div id="main" class="wrapper style1">
					<div class="container">
						<header class="major">
							<h2>로그인</h2>
                            <p>계정이 없으시다면   <a href="./join">여기</a>에서 생성해주시길 바랍니다.</p>
                         	<p id="errorMsg" style="color:#e44c65">${errorMessage}</p>
                         	<p id="checkmsg"></p>
						</header>
					<!-- Form -->
							<section>
				    				<form id="formData" method="post" action="./logincheck">
									<div class="row gtr-uniform gtr-50">

   
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="email" name="email" id="email" value="" placeholder="이메일"  maxlength="50"/>
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                        
                                        
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="password" name="password" id="password" value="" placeholder="비밀번호"  maxlength="20"/>
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
									

                                    <div class="col-5 col-4-medium col-12-xsmall"></div>
                                    <div class="col-2 col-4-medium col-12-xsmall">
										<ul class="actions stacked">
											<li><input id="sendBtn" type="button" class="button primary fit" value="로그인"></li>
											</ul>
            						</div>
            						</div>
								</form>
							</section>
					</div>
				</div>
				<%@ include file="/common/footer.jsp" %>
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
	startAnimation("#formData","bounceIn");

			$("#sendBtn").on("click",function(event){
				 
				 event.preventDefault();
					
				 login();
				 
			 });
			
			$("#formData").keypress(function (e) {
		        if (e.which == 13){
		        	login();
		        }
		    });
				
			//< 애니매이션을 다시 설정해주기위해 스크립트를 불러온다.
			 callScript("assets/js/main.js");					 
});
function login(){
	
	if( isCheckForm() == true )
	 {
		$("#formData").submit();
	 }
	else{
		$("#errorMsg").text("");
	}
	
}
function isCheckForm(){
				
				var password =  $("#password").val();
				var email = $("#email").val();
				var checkMsgHTML = "";
				var result = true;

				$("#checkmsg").html("");
						
	
				if( email.length <= 0 || email.match(/\w@\w.\w/) == null)
				{
					checkMsgHTML += " [<strong style='color:#e44c65'>이메일</strong>] ";
					startAnimation("#email","shake");
					result = false;
				}
				
				
				if( password.length <= 0 ){
					checkMsgHTML += " [<strong style='color:#e44c65'>비밀번호</strong>] ";
					startAnimation("#password","shake");
					result = false;
				}
				
				if( result == false){
					checkMsgHTML += "를 확인해 주세요!";
					$("#checkmsg").html(checkMsgHTML);
				}
				
				return result;
}
		
</script>

<%@ include file="/common/popup.jsp" %>
</body>
</html>

