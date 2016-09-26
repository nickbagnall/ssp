<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="description" content="A collection of example web projects">
<meta name="keywords" content="HTML,JSP,CSS,XML,JavaScript">
<meta name="author" content="Nick Bagnall">
<title>Sky Sport Presenter Confirm Page</title>
<link rel="stylesheet" type="text/css" href="css/ssp.css">
<script src="js/validation.js"></script>
<%@ page import="org.bagnall.nick.ssp.*" %>
<%@ page import="java.util.List" %>
</head>

<body>
<header>
<h1><span id="sky">sky</span><span id="sports">SPORTISH</span></h1>
</header>

<nav>
</nav>

<article>
<p>Sky Sport Presenter Change Confirmation</p>
<c:if test="${requestScope.success eq true }">
<p>The changes have been applied.</p>
</c:if>

<c:if test="${requestScope.success ne true }">
<p>The changes were not applied.</p>
</c:if>

<form action="/SSP/Presenter" method="post" name="start">
<button type="submit" name="submit" value="complete">Ok</button>
</form>


</article>

<footer>
<p>About Us</p>
<p>Privacy and Cookies Notices</p>
<p>Terms and Conditions</p>
<p>Accessibility</p>
</footer>
</body>
</html>