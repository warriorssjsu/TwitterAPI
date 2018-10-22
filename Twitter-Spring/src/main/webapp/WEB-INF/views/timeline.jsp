<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Timeline</title>
</head>
<body>
<a href="/"><h3>Back to Home Page</h3></a>
<h3>Timeline Data : </h3>
<table border="1">
<tr><td><h3>"id" :</h3></td><td><h3> ${id}</h3></td></tr>
<tr><td><h3>"createdAt" :</h3></td><td><h3> ${createdAt}</h3></td></tr>
<tr><td><h3>"text" :</h3></td><td><h3> ${text}</h3></td></tr>
<tr><td><h3>"source" :</h3></td><td><h3> ${source}</h3></td></tr>
<tr><td><h3>"retweetCount" :</h3></td><td><h3> ${retweetCount}</h3></td></tr>
<tr><td><h3>"userName" :</h3></td><td><h3> ${userName}</h3></td></tr>
<tr><td><h3>"screenName" :</h3></td><td><h3> ${screenName}</h3></td></tr>
</table>
</body>
</html>