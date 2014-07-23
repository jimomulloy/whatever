<%@page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!--
Whatever the Weather :: a Commonline Demo App
-->

<meta charset="utf-8">
<title>Whatever the Weather :: a Commonline Demo App</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->
<!--meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /-->

<!--spring:url value="/webjars/bootstrap/3.1.1/css/bootstrap.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" /-->

<spring:url
	value="/webjars/jquery-ui/1.9.2/css/smoothness/jquery-ui-1.9.2.custom.css"
	var="jQueryUiCss" />
<link href="${jQueryUiCss}" rel="stylesheet"></link>

<!--spring:url
	value="/webjars/bootstrap/3.1.1/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url
	value="/webjars/bootstrap/3.1.1/css/bootstrap-theme.min.css"
	var="bootstrapThemeCss" />
<link href="${bootstrapThemeCss}" rel="stylesheet" />

<spring:url value="/webjars/jquery/2.0.3/js/jquery.min.js"
	var="jQueryUi" />
<script src="${jQueryUi}"></script>

<spring:url value="/webjars/bootstrap/3.1.1/js/bootstrap.min.js"
	var="bootstrapJs" />
<script src="${bootstrapJs}"></script-->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<spring:url value="/resources/js/holder.js" var="holderJs" />
<script src="${holderJs}"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
<spring:url
	value="/webjars/jquery-ui/1.9.2/js/jquery-ui-1.9.2.custom.js"
	var="jQueryUi" />
<script src="${jQueryUi}"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<spring:url value="/resources/css/wtw.css" var="wtwCss" />
<link href="${wtwCss}" rel="stylesheet" />

<!--script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script-->

<!-- Placed at the end of the document so the pages load faster -->
<!-- script
	src="<spring:url value='/resources/bootstrap/js/bootstrap.js' htmlEscape='true' />">
</script-->

<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!--script type="text/javascript"> $(document).ready(function () { $('.dropdown-toggle').dropdown(); }); </script>
<script>
!function(a){a(function(){if(navigator.userAgent.match(/IEMobile\/10\.0/)){var b=document.createElement("style");b.appendChild(document.createTextNode("@-ms-viewport{width:auto!important}")),document.querySelector("head").appendChild(b)}{var c=a(window),d=a(document.body);a(".navbar").outerHeight(!0)+10}d.scrollspy({target:".bs-docs-sidebar"}),c.on("load",function(){d.scrollspy("refresh")}),a(".bs-docs-container [href=#]").click(function(a){a.preventDefault()}),setTimeout(function(){var b=a(".bs-docs-sidebar");b.affix({offset:{top:function(){var c=b.offset().top,d=parseInt(b.children(0).css("margin-top"),10),e=a(".bs-docs-nav").height();return this.top=c-e-d},bottom:function(){return this.bottom=a(".bs-docs-footer").outerHeight(!0)}}})},100),setTimeout(function(){a(".bs-top").affix()},100),a(".tooltip-demo").tooltip({selector:"[data-toggle=tooltip]",container:"body"}),a(".tooltip-test").tooltip(),a(".popover-test").popover(),a(".bs-docs-navbar").tooltip({selector:"a[data-toggle=tooltip]",container:".bs-docs-navbar .nav"}),a("[data-toggle=popover]").popover(),a("#loading-example-btn").click(function(){var b=a(this);b.button("loading"),setTimeout(function(){b.button("reset")},3e3)})})}(jQuery);
</script-->
