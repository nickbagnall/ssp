<!DOCTYPE html>
<%@page import="org.nick.bagnall.ssp.SkySportPresenter"%>
<html>
<head>
<title>Sky Sport Presenter Admin Page</title>
<link rel="stylesheet" type="text/css" href="css/ssp.css">
<script src="js/validation.js"></script>
<%@ page import="org.nick.bagnall.ssp.*" %>
<%@ page import="java.util.List" %>
</head>

<body>
<header>
Welcome to the Sky Sports Presenters admin page
</header>
<nav>
</nav>
<article>
<%
PresenterList presenterList = (PresenterList)session.getAttribute("presenterList");
List<SkySportPresenter> skySportPresenters = null;
if (presenterList != null) {
  skySportPresenters = presenterList.getPresenters();
}
//PresenterList presenters = (PresenterList)request.getParameterValues("presenterList");
//String[] presenters = request.getParameterValues("presenterList");
if (skySportPresenters != null) {
%>
<form action="/SSP/Presenter" method="post" name="details" onsubmit="return validate()">>
<table>
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Action</th>
</tr>
<%
for (int i = 0; i < skySportPresenters.size(); i++) {
%>
<tr>
<td><%= skySportPresenters.get(i).getFirstName() %></td>
<td><%= skySportPresenters.get(i).getLastName() %></td>
<td><input type="submit" name="Submit" value="update<%= i %>"><input type="submit" name="Submit" value="delete<%= i %>"></td>
</tr>
<%
}
%>
</table>
</form>
<%
} else {
%>
<p>There are no presenters on file.</p>
<%
}
%>

<form action="/SSP/Presenter" method="post" name="details" onsubmit="return validate()">
First Name: <input type="text" name="firstName" size="35"><br />
Last Name: <input type="text" name="lastName" size="35"><br />
<input type="submit" name="Submit" value="Add Presenter">
</form>
</article>

</body>
</html>
