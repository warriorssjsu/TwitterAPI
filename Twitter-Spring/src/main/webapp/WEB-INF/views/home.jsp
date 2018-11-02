<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 
<title>Home</title>
</head>
<body  style="background-color: powderblue;">
<script type="text/javascript" >
function check()
            {
                if (document.getElementById('status').value==""
                 || document.getElementById('status').value==undefined)
                {
                    alert ("Please Enter status to be updated");
                    return false;
                }
                return true;
            }
</script>
<h1>Welcome to the Twitter API - Team Warriors</h1>
<h1>Below are the 9 APIs we have developed</h1>

<ul style="list-style-type:square">
<li><a href="/status/Warriors"><h3>Get Warriors Status</h3></a></li>
<li><a href="/showFriendship/Warriors"><h3>Show Friendship</h3></a></li>
<li><a href="/Basketball"><h3>Get Tags</h3></a></li>
<li><a href="/create/Warriors"><h3>Create Friendship</h3></a></li>
<li><a href="/reTweet/Warriors"><h3>Retweet a tweet</h3></a></li>
<li><a href="/unRetweet/Warriors"><h3>Un-Retweet a tweet</h3></a></li>
<li><a href="/trends/Warriors"><h3>Get Available Trends</h3></a></li>
<li><a href="#" onclick="document.getElementById('inputField').style.display = 'block';"><h3>Update Status</h3></a>

<div id="inputField" style="display:none;">
<form method="post" action="/update/status">
   <input type="text" name="status" id="status" />
   <input type="submit" value="Update" class="btn" onclick ="return check();" >
   </form>
</div>
</li>
<li><a href="/timeline/Warriors"><h3>Get Warriors Home Timeline</h3></a></li>
</ul>

</body>
</html>