<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en" style="font-size: 10px">

<head>
<meta charset="UTF-8">
<mete name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>
<link rel="stylesheet" href="../../resource/css/style2.css"
	type="text/css">
</head>

<body>

	<%--  <jsp:include page="../../inc/header.jsp"/>  --%>
	<tiles:insertAttribute name="header" />
	</div>
	<!-- visual block -->
	<div id="visual">
		<div class="content-box" style="position: relative"></div>
	</div>

	<!-- main block -->
	<div id="body">
		<!-- clear-fix 이제 안씀.-->
		<div class="content-box">

			<%-- <jsp:include page="../../inc/aside.jsp"/> --%>
			<tiles:insertAttribute name="aside" />
			<!--   main 부분 -->
			<tiles:insertAttribute name="main" />
		</div>
	</div>
	<%--  <jsp:include page="../../inc/footer.jsp"/> --%>
	<tiles:insertAttribute name="footer" />
</body>

</html>