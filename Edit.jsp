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
<title>Sky Sport Presenter Edit Page</title>
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
<p>Editing presenter</p>
<c:if test="${requestScope.presenter eq null }">
<p>Attempted to edit nothing - error.</p>
</c:if>

<c:if test="${requestScope.presenter ne null }">
<form action="/SSP/Confirm" method="post" name="update" onsubmit="return validateUpdate()">
<table>

<c:set var="presenter" value="${requestScope.presenter}" />
<tr>
<td><input type="text" name="firstName" id="firstName" size="20" maxlength="25" value="<c:out value="${presenter.firstName}"></c:out>"></td>
<td><input type="text" name="lastName" id="lastName" size="20" maxlength="25" value="<c:out value="${presenter.lastName}"></c:out>"></td>
<td><input type="text" name="email" id="email" size="40" maxlength="50" value="<c:out value="${presenter.email}"></c:out>"></td>
<td><input type="submit" name="submit" value='update'><input type="submit" name="submit" value='delete'></td>
</tr>

</table>
<input type="hidden" name="id" id="id" value="<c:out value="${presenter.id}"></c:out>">
</form>
</c:if>

<form action="/SSP/Presenter" method="post" name="start">
<button type="submit" name="submit" value="cancel">Cancel</button>
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