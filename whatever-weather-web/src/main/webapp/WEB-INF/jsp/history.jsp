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
			<b> Weather History for: ${location.city}, ${location.region},
				${location.country} </b> 
				
			<br/>

			<c:forEach items="${weathers}" var="weather">
				<ul>
					<li>Temperature: <c:out value="${weather.condition.temp}" /></li>
					<li>Condition: <c:out value="${weather.condition.text}" /></li>
					<li>Humidity: <c:out value="${weather.atmosphere.humidity}" /></li>
					<li>Wind Chill: <c:out value="${weather.wind.chill}" /></li>
					<li>Date: <c:out value="${weather.date}" /></li>
				</ul>
			</c:forEach>

		</div>

		<footer>
			<p>&copy; Company 2014</p>
		</footer>

		<jsp:include page="fragments/footer.jsp" />

	</div>

</body>
</html>
