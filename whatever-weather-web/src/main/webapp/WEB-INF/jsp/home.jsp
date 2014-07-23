<%@page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="fragments/headTag.jsp" />

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<style type="text/css">
hr {
	margin: 0 0;
}

#map_canvas_alt {
	width: 100%;
	height: 100%;
	min-height: 100%;
	display: block;
	border-radius: 10px;
	-webkit-border-radius: 10px;
}

#map_canvas {
	width: 500px;
	height: 400px;
	background-color: #CCC;
	min-height: 100%;
	display: block;
	border-radius: 10px;
	-webkit-border-radius: 10px;
}

.well {
	width: 100%;
	height: 100%;
	min-height: 100%;
}

.alert {
	border: 1px solid rgba(229, 223, 59, 0.78);
}
</style>
<!--script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script-->
<script src="https://maps.googleapis.com/maps/api/js"></script>


<script type="text/javascript">
	function initialize() {
		var latlng = new google.maps.LatLng(51.4800, 0.00);
		var settings = {
			zoom : 10,
			center : latlng,
			mapTypeControl : false,
			mapTypeControlOptions : {
				style : google.maps.MapTypeControlStyle.DROPDOWN_MENU
			},
			navigationControl : true,
			navigationControlOptions : {
				style : google.maps.NavigationControlStyle.SMALL
			},
			mapTypeId : google.maps.MapTypeId.TERRAIN
		};

		var map = new google.maps.Map(document.getElementById("map_canvas"),
				settings);

		google.maps.event.addListener(map, 'click', function(event) {
			$('#latitude').val(event.latLng.lat());
			$('#longitude').val(event.latLng.lng());
		});

		var wespiMarker = new google.maps.Marker({
			position : latlng,
			map : map,
			title : "London, UK"
		});
		startws();

	}

	function startws() {
		var socket = new WebSocket("ws://api.cosm.com:8080");
		socket.onopen = function() {
			socket
					.send('{"method" : "subscribe", "resource" : "/feeds/129364", "headers" : {"X-ApiKey" : "AvKdOy4jlZ7QNRoJjwS_F6q30m-SAKw5S0JEdzZrT3NMZz0g"}}');
		}
		socket.onclose = function() {
			//try to reconnect in 5 seconds
			setTimeout(function() {
				startws()
			}, 5000);
		};

		socket.onmessage = function(msg) {
			var response = JSON.parse(msg.data);
			if ("body" in response) {
				var streams = response.body.datastreams;
				for (var i = 0; i < streams.length; i++) {
					st = streams[i];
					var prev = $("#" + st.id);
					var clone = prev.clone();
					clone.css('position', 'absolute');
					clone.css('left', prev.position().left);
					clone.css('top', prev.position().top);
					$('body').append(clone);
					prev.hide();
					if (st.unit) {
						prev.text(st.current_value + " " + st.unit.symbol);
					} else {
						prev.text(st.current_value);
					}
					clone.fadeOut(300, function() {
						clone.remove();
					});
					prev.fadeIn(300);
				}
			}
		};

	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>

<body>
	<jsp:include page="fragments/bodyHeader.jsp" />

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 main">
				<form:form method="POST" action="weather.x"
					modelAttribute="weatherUser">

					<h1 class="page-header">Whatever The Weather!</h1>
					<!-- Main jumbotron for a primary marketing message or call to action -->
					<div class="jumbotron">
						<div class="container">
							<div class="row">
								<div class="col-md-4">
									Sidebar content
								</div>
								<div class="col-md-8">
									<p>
										<button type="submit" value="Get My Location" id="geolocate"
											class="btn btn-primary btn-lg">My Location</button>
										<button type="submit" value="Run Weather Report" id="submit"
											class="btn btn-primary btn-lg">Get Weather</button>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row placeholders">
						<div class="col-xs-6 col-sm-3 placeholder">
							<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
								alt="Generic placeholder thumbnail">
							<p>Location</p>
							<table>
								<tr>
									<td><form:label path="latitude">Latitude</form:label></td>
									<td><form:input path="latitude" id="latitude" /></td>
								</tr>
								<tr>
									<td><form:label path="longitude">longitude</form:label></td>
									<td><form:input path="longitude" id="longitude" /></td>
								</tr>
							</table>
						</div>
						<div class="col-xs-6 col-sm-3 placeholder" id="map_canvas"></div>
					</div>
				</form:form>
			</div>

			<h2 class="sub-header">Weather Reports</h2>
			<div class="row-fluid">
				<div class="col-md-12">
					<b>Weather Report for: ${report.region}, ${report.latitude},
						${report.longitude}, ${report.date}</b> <br />
					<div class="row-fluid">
					</div>
						<c:forEach items="${report.sourceMap}" var="entry">
							<c:set var="sourcedata" value="${entry.value}" />
							<c:set var="source" value="${entry.key}" />
							<div class="col-md-4">
								<b>Recorded Weather Conditions for source: <c:out
										value="${source}" /></b> <br />

								<c:forEach items="${sourcedata.recordings}" var="recording">
									<ul>
										<li>From source: <c:out value="${recording.source}" /></li>
										<li>Source Time: <c:out value="${recording.sourceTime}" /></li>
										<li>Min Temperature: ${recording.condition.minTemp}</li>
										<li>Max Temperature: ${recording.condition.maxTemp}</li>
										<li>Condition: ${recording.condition.text}</li>
										<li>Description: ${recording.condition.description}</li>
										<li>Icon: ${recording.condition.icon}</li>
										<li>Code: ${recording.condition.code}</li>
										<li>Pressure: ${recording.atmosphere.pressure}</li>
										<li>Humidity: ${recording.atmosphere.humidity}</li>
										<li>Wind Chill: ${recording.wind.chill}</li>
										<li>Date: ${recording.writeTime}</li>
									</ul>
								</c:forEach>

								<b>Forecast Weather Conditions</b> <br />

								<c:forEach items="${sourcedata.forecasts}" var="forecast">
									<ul>
										<li>From source: <c:out value="${forecast.source}" /></li>
										<li>Source Time: <c:out value="${forecast.sourceTime}" /></li>
										<li>Min Temperature: <c:out
												value="${forecast.condition.minTemp}" /></li>
										<li>Max Temperature: <c:out
												value="${forecast.condition.maxTemp}" /></li>
										<li>Condition: <c:out value="${forecast.condition.text}" /></li>
										<li>Description: ${forecast.condition.description}</li>
										<li>Icon: ${forecast.condition.icon}</li>
										<li>Code: ${forecast.condition.code}</li>
										<li>Pressure: ${forecast.atmosphere.pressure}</li>
										<li>Humidity: <c:out
												value="${forecast.atmosphere.humidity}" /></li>
										<li>Wind Chill: <c:out value="${forecast.wind.chill}" /></li>
										<li>From Time: <c:out
												value="${forecast.condition.fromTime}" /></li>
										<li>To Time: <c:out value="${forecast.condition.toTime}" /></li>
									</ul>
								</c:forEach>
								<br />
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<p>&copy; Company 2014</p>
		</footer>

		<jsp:include page="fragments/footer.jsp" />

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!--script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="<spring:url value='/resources/bootstrap/js/bootstrap.js' htmlEscape='true' />">
		
	</script-->
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#geolocate')
									.click(
											function() {
												if (navigator.geolocation) {
													navigator.geolocation
															.getCurrentPosition(
																	geoSuccess,
																	geoError)
												} else {
													alert("Browser does not support geolocation");
												}
											});

							function geoSuccess(position) {
								alert("!!geo located");
								$('#latitude').val(position.coords.latitude);
								$('#longitude').val(position.coords.longitude);
							}

							function geoError(error) {
								switch (error.code) {
								case error.PERMISSION_DENIED:
									alert("Geolocate PERMISSION_DENIED: "
											+ error.message);
									break;
								case error.POSITION_UNAVAILABLE:
									alert("Geolocate POSITION_UNAVAILABLE: "
											+ error.message);
									break;
								case error.TIMEOUT:
									alert("Geolocate TIMEOUT: " + error.message);
									break;
								default:
									alert("Geolocate code " + error.code + ': '
											+ error.message);
									break;
								}
							}

						});
	</script>
	<script type="text/javascript">
		var GoSquared = {};
		GoSquared.acct = "GSN-287476-A";
		(function(w) {
			function gs() {
				w._gstc_lt = +new Date;
				var d = document, g = d.createElement("script");
				g.type = "text/javascript";
				g.src = "//d1l6p2sc9645hc.cloudfront.net/tracker.js";
				var s = d.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(g, s);
			}
			w.addEventListener ? w.addEventListener("load", gs, false) : w
					.attachEvent("onload", gs);
		})(window);
	</script>
	<script type="text/javascript">
		var _gauges = _gauges || [];
		(function() {
			var t = document.createElement('script');
			t.type = 'text/javascript';
			t.async = true;
			t.id = 'gauges-tracker';
			t.setAttribute('data-site-id', '510e99ccf5a1f50f85000093');
			t.src = '//secure.gaug.es/track.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(t, s);
		})();
	</script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-38207510-1' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>
