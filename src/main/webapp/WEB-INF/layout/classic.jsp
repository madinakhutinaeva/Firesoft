<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>
#logoutBut {
	text-decoration: none;
	color: #9d9d9d;
}

#logoutBut:hover {
	color: #ffe
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<spring:url value="/" />'>Firesoft.io</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href='<spring:url value="/" />'>Home</a></li>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href='<spring:url value="/users.html" />'>Users</a></li>
					</sec:authorize>
					<!-- temporary disable posting -->
					<sec:authorize access="hasRole('ROLE_ADMIN')">					
						<li><a href='<spring:url value="/addPost.html" />'>Add post</a></li>
					</sec:authorize>
					<sec:authorize access="! isAuthenticated()">
						<li><a id="modal_trigger" style="margin-left: 2px" href="#modal">Login or register</a></li>
					</sec:authorize>				
					<sec:authorize access="isAuthenticated()">
					    <li><a href='<spring:url value="/account.html" />'>My account</a></li>
						<li><c:url var="logoutUrl" value="logout" />
							<form style="margin-left: 15px; margin-top: 15px " action="${logoutUrl}"
								method="post" onSubmit="JavaScript:signOut()">
								<a id="logoutBut" class="navbar-inverse nav a navbar-nav"
									href="<spring:url value="/logout" />">Logout</a>
								<sec:csrfInput />
							</form></li>
					</sec:authorize>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<tiles:insertAttribute name="body" />

	<br>
	<br>
	<center>
		<tiles:insertAttribute name="footer" />
	</center>

</body>

</html>
