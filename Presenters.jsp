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
<title>Sky Sport Presenter Admin Page</title>
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
<h2>Sky Sport Presenter details</h2>
<c:if test="${requestScope.presenterList eq null }">
<p>There are no presenters on file.</p>
</c:if>

<c:if test="${requestScope.presenterList ne null }">
<form action="/SSP/Edit" method="post" name="details">
<table>
<tr>
<th class="fn">First name</th>
<th class="ln">Last name</th>
<th class="em">Email Address</th>
<th class="ac"></th>
</tr>
<c:set var="cnt" scope="page" value="${0}"/>
<c:forEach items="${requestScope.presenterList.presenters}" var="presenter">
<tr>
<td><c:out value="${presenter.firstName}"></c:out></td>
<td><c:out value="${presenter.lastName}"></c:out></td>
<td><c:out value="${presenter.email}"></c:out></td>
<td class="ac"><button type="submit" name="submit" value="update<c:out value="${presenter.id}"></c:out>">Edit</button></td>
</tr>
<c:set var="cnt" scope="page" value="${cnt+1}"/>
</c:forEach>
</table>
</form>
</c:if>


<form action="/SSP/Confirm" method="post" name="createNew" onsubmit="return validateCreateNew();">
<table>
<tr>
<td class="fn"><input type="text" name="firstName" id="firstName" size="20" maxlength="25"></td>
<td class="ln"><input type="text" name="lastName" id="lastName" size="20" maxlength="25"></td>
<td class="em"><input type="text" name="email" id="email" size="40" maxlength="50"></td>
<td class="ac"><input type="submit" name="submit" value='create'></td>
</tr>
</table>
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