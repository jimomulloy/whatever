<%@page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link href="<spring:url value='/resources/assets/css/bootstrap-united.css' htmlEscape='true' />" rel="stylesheet" />
<link href="<spring:url value='/resources/bootstrap/css/bootstrap-responsive.css' htmlEscape='true' />" rel="stylesheet" />
<style>
body {
	height: 100%;
	margin: 0;
	background: url("<spring:url  value='/resources/assets/img/books.jpg' htmlEscape='true' />");
	background-size: 1440px 800px;
	background-repeat: no-repeat;
	display: compact;
}
</style>
</head>
<body>
	<div class="navbar navbar-default">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="signup.x">Signup</a></li>
				<li><a href="login.x">Login</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Explore<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Contact us</a></li>
						<li class="divider"></li>
						<li><a href="#">Further Actions</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<div class="container">
		<div class="jumbotron">
			<div>
				<h1>Welcome to Whatever the Weather Demo Site!</h1>
				<p>To get started, you need to enter your details to register with
					us. Or login to access your details, if you are already registered.</p>
			</div>

			<a class="btn btn-primary" href="weather.x">Weather Report</a> <a
				class="btn btn-primary" href="history.x">Weather History</a>
		</div>

		<div></div>
	</div>
	<script src="<spring:url value='/resources/js/jquery-1.8.3.js' htmlEscape='true' />">
		
	</script>

	<script src="<spring:url value='/resources/bootstrap/js/bootstrap.js' htmlEscape='true' />">
		
	</script>

</body>
</html>
