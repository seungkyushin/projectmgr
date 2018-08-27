<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>KYU - MAIN</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/action.css" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/popup.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">

<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>

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
<body>
	<div id="main-page-wrapper">
		<%@ include file="/common/header.jsp"%>
		<div id="projectList-wrapper" class="content">
			<!-- Banner -->
			<section id="banner">
				<div class="content">

					<h2>'${visiter.name}'님 프로필</h2>
					<p id="checkmsg"></p>

					<div class='table-wrapper'>
						<form id="formData" method="post" action="./modifyProfile"
							enctype="multipart/form-data">

							<span onclick=document.all.file.click();
								class="image animated bounce infinite"> <img
								src="${fileInfo.urlPath}" id="profileImage" alt="" />
								
							</span>
							
							 <input type="file" name="file" id="imageFile" style="display:none">



							<table>
								<tbody id="profile-table">

									<tr>
										<td>이름</td>
										<td><input type="text" id="name" name="name"
											value="${visiter.name}" readonly></td>
									</tr>
									<tr>
										<td>이메일</td>
										<td><input type="text" id="email" name="email"
											value="${visiter.email}" readonly></td>
									</tr>
									<tr>
										<td>소속</td>
										<td><input type="text" value="${visiter.organization}"
											id="organization" name="organization" maxlength="20"></td>

									</tr>
									<tr>
										<td>패스워드 변경</td>
										<td><input type="password" id="password" name="password"
											maxlength="20"></td>
									</tr>
									<tr>
										<td>패스워드 변경 확인</td>
										<td><input type="password" id="password-check"
											name="password-check" maxlength="20"></td>
									</tr>
									

								</tbody>
							</table>

								<input id="sendBtn" type="button" 
									class="button" value="수정 하기">
										
								<input id="sendDeleteBtn" type="button"
									class="button primary" value="계정 삭제">
							
						</form>
					</div>
				</div>
			</section>
		</div>



		<%@ include file="/common/footer.jsp"%>
	</div>
</body>

<script>
	$(document).ready(function() {

		$("#sendBtn").on("click", function(event) {
			event.preventDefault();
			modify();
		});

		$("#sendDeleteBtn").on("click", function(event) {
			event.preventDefault();
			if (isCheckForm() == true) {
				if (confirm("정말 삭제 하시겠습니까?") == true)
					$("#formData").attr("action", "./delete");
				$("#formData").submit();
			}
		});

		$("#formData").keypress(function(e) {
			if (e.which == 13) {
				modify();
			}
		});

		
        const elImage = document.querySelector("#imageFile");
        elImage.addEventListener("change", function(evt){
            const image = evt.target.files[0];
            if(!valideImageType(image)) { 
                console.warn("invalide image file type");
                return;
            }
            //이렇게 넣으면 이미지 정보가 화면에 노출됩니다.
            const elImage = document.querySelector("#profileImage");
            elImage.src = window.URL.createObjectURL(image);
        });
        
	});

	function modify() {
		if (isCheckForm() == true) {
			if (confirm("정말 수정 하시겠습니까?") == true)
				$("#formData").submit();
		}

	}
	function isCheckForm() {

		var password = $("#password").val();
		var passwordCheck = $("#password-check").val();
		var checkMsgHTML = "";
		var result = true;

		$("#checkmsg").html("");

	

		if ( password.length >= 0 && password != passwordCheck ) {
			checkMsgHTML += " <strong style='color:#e44c65'>비밀번호</strong> ";
			checkMsgHTML += "가 일치 하지 않습니다";
			startAnimation("#password", "shake");
			startAnimation("#password-check", "shake");
			result = false;
		}

		$("#checkmsg").html(checkMsgHTML);

		return result;
	}
	
	function valideImageType(image) {
		const result = ([ 'image/jpeg',
						  'image/png',
						  'image/jpg' ].indexOf(image.type) > -1);
		return result;
	}
	
</script>
</html>


