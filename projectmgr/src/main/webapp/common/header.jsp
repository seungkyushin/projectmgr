<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<header id="header">
	<h1 id="logo">
		<a href="./main">KYU</a>
	</h1>
	<nav id="nav">
		<ul>
		
			<li><a href="#skill" class="scrolly">설명</a></li>
			<li>
				<a href="#">프로젝트</a>
				<ul id="nav-projectList">
				</ul>
			</li>
							
			<c:choose>
				<c:when test="${empty sessionScope.email}">
					<li><a href="./join">가입하기</a></li>
					<li><a href="./login" class="button primary">로그인</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="./profileCheck">프로필</a></li>
					<li><a href="./logout" class="button primary">로그 아웃</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</nav>
</header>





	


