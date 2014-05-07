<%@page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/headTag.jsp" />

<body>

	<jsp:include page="fragments/bodyHeader.jsp" />

	<div class="container-fluid">
		<div class="row">

			<b>Current Weather Conditions for: ${weather.location.city},
				${weather.location.region}, ${weather.location.country}</b><br />

			<ul>
				<li>Temperature: ${weather.condition.temp}</li>
				<li>Condition: ${weather.condition.text}</li>
				<li>Humidity: ${weather.atmosphere.humidity}</li>
				<li>Wind Chill: ${weather.wind.chill}</li>
				<li>Date: ${weather.date}</li>
			</ul>

		</div>

		<footer>
			<p>&copy; Company 2014</p>
		</footer>

		<jsp:include page="fragments/footer.jsp" />

	</div>

</body>
</html>
