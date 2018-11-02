<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Followers</title>
</head>
<body style="background-color: powderblue;">
<a href="/"><h3>Back to Home Page</h3></a>
<h3>Friendship between ${getName} and BradPitt :</h3>
<table border="1">
<tr><td><h3>accessLevel:</h3></td><td><h3> ${accessLevel}</h3></td></tr>
<tr><td><h3>targetUserId:</h3></td><td><h3> ${targetUserId}</h3></td></tr>
<tr><td><h3>targetUserScreenName:</h3></td><td><h3> ${targetUserScreenName}</h3></td></tr>
<tr><td><h3>sourceUserId:</h3></td><td><h3> ${sourceUserId}</h3></td></tr>
<tr><td><h3>sourceUserScreenName: </h3></td><td><h3> ${sourceUserScreenName}</h3></td></tr>
<tr><td><h3>targetFollowedBySource:</h3></td><td><h3> ${targetFollowedBySource}</h3></td></tr>
</table>
</body>
</html>