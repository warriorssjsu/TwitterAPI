<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Status</title>
</head>
<body style="background-color: powderblue;">
<a href="/"><h3>Back to Home Page</h3></a>
<h3>Updated Status Successfully - Please check twitter account</h3>
<table border="1">
<tr><td><h3>"id" :</h3></td><td><h3> ${id}</h3></td></tr>
<tr><td><h3>"createdAt" :</h3></td><td><h3> ${createdAt}</h3></td></tr>
<tr><td><h3>"text" :</h3></td><td><h3> ${text}</h3></td></tr>
<tr><td><h3>"source" :</h3></td><td><h3> ${source}</h3></td></tr>
<tr><td><h3>"retweetCount" :</h3></td><td><h3> ${retweetCount}</h3></td></tr>
</table>
</body>
</html>