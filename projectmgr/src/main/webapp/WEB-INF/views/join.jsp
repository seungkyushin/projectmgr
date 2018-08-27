<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<!DOCTYPE HTML>
<html>
	<head>
		<title>KYU - VISITER</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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
					<div class="container">
						<header class="major">
							<h2>가입 하기</h2>
                            <p>홈페이지 관리자는 해당 정보를 악용하지 않습니다.</p>
                            <p>형식 및 빈칸없이 모두 알맞게 작성해주면 감사합니다.</p>
                            <p id="checkmsg"></p>
						</header>

						<!-- Form -->
							<section>
				    				<form id="formData" method="post" action="./addvisiter">
									<div class="row gtr-uniform gtr-50"></div>

                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="text" name="name" id="name" value="" placeholder="성함" maxlength="10"/>
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                     


                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="email" name="email" id="email" value="" placeholder="이메일" maxlength="50" />
                                        	
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                        
                                        
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="password" name="password" id="password" value="" placeholder="비밀번호"  maxlength="21"/>
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
									
									  <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="password" name="passwordCheck" id="passwordCheck" value="" placeholder="비밀번호 확인"  maxlength="21"/>
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                        
                                        <div class="col-4 col-12-xsmall"></div> 
										<div class="col-4 col-12-xsmall">
											<input type="text" name="organization" id="organization" value="" placeholder="소속"  maxlength="20" />
                                        </div>
                                        <div class="col-4 col-12-xsmall"></div> 
                                                                

                                    <div class="col-5 col-4-medium col-12-xsmall"></div>
                                    <div class="col-2 col-4-medium col-12-xsmall">
										<ul class="actions stacked">
											<li><input type="button" id="sendBtn" class="button primary fit" value="가입"></li>
											</ul>
            						</div>
								</form>
							</section>
					</div>
				</div>
				<%@ include file="/common/footer.jsp" %>
		</div>
		
<script>
		$(document).ready(function(){
			//< 폼태그 애니메이션 설정
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
		}
		function isCheckForm(){
						
			var name = $("#name").val();
			var password =  $("#password").val();
			var passwordCheck =  $("#passwordCheck").val();
			var email = $("#email").val();
			var checkMsgHTML = "";
			var result = true;
			
			$("#checkmsg").html("");
			
			if( name.length <= 0 ){
				checkMsgHTML += " [<strong style='color:#e44c65'>이름</strong>] "
				startAnimation("#name","shake");
				result = false;
			}
			
			if( password.length <= 0 ){
				checkMsgHTML += " [<strong style='color:#e44c65'>비밀번호</strong>] ";
				startAnimation("#password","shake");
				result = false;
			}
			
			if( passwordCheck.length <= 0 || password != passwordCheck){
				checkMsgHTML += " [<strong style='color:#e44c65'>비밀번호 확인</strong>] ";
				startAnimation("#passwordCheck","shake");
				result = false;
			}
			
			
					
			if( email.length <= 0 || email.match(/\w@\w.\w/) == null)
			{
				checkMsgHTML += " [<strong style='color:#e44c65'>이메일</strong>] ";
				startAnimation("#email","shake");
				result = false;
			}

			
			if( result == false){
				checkMsgHTML += "를 확인해 주세요!";
				$("#checkmsg").html(checkMsgHTML);
			}
			
			return result;
		}
		
		function startAnimation(elementName, type){
			$(elementName).removeClass().addClass(type + ' animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
			      $(this).removeClass();
			    });
		}
		</script>
</body>
</html>

