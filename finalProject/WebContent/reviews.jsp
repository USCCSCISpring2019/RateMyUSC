<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.*, java.util.ArrayList, javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<style> 
		::placeholder {
			font-style: italic;
		}
		
		#hidden_username {
			display: none;
		}
	</style>
	<style>
			.upvote {
				height: 30px;
				width: 30px;
			}
			
			#inbox_wrapper {
				position: absolute;
				left: 50%;
				top: 20%;
			}
			
			#inbox {
				height: 50px;
				width: 50px;
			}
			
			#refresh_icon {
				height: 50px;
				width: 50px;
			}
			
			#unread_msg {
				font-size: 36px;
				height: 50px;
				width: 50px;
				
				border-radius: 50%;
				background-color: red;
				text-align: center;
			}
			
			#inbox_display {
				display: none;
				border: solid 1px;
				text-size: 20px;
				position: absolute;
				left: 50%;
				top: 50%;
			}
			
		</style>
	<link rel="stylesheet" href="assets/css/reviews.css" />
	<script>
		function login() {
			var xhttp = new XMLHttpRequest();
				
			xhttp.open("GET", "TestLogin?username=" + document.getElementById("username").value
				+ "&password=" + document.getElementById("password").value
				+ "&login_submit=" + document.getElementById("login_submit").value, false);
				xhttp.send();
				
			if(xhttp.responseText.length > 0){
				document.getElementById("error_msg").innerHTML = xhttp.responseText;
				return false;
			}
			return true;
		}
	</script>
</head>

<body>

		<%  User user = (User) session.getAttribute("user"); 
			System.out.println("User is = " + user.getEmail());
		%>
	
	<!-- Header -->
	<header id="header">
				<div class="inner">
					<a href="index.jsp" class="logo">Rate My USC</a>
					<nav id="nav">
						<a href="index.jsp">Home</a>
						<span id="login">
						<a  href="#" onclick="document.getElementById('login_overlay').style.display = 'block';">Login</a>
						</span>
						<span id="register">
						<a href="register.html">Register</a>
						</span>
						<span id="profile" style="display:none">
						<a href="profile.html">Profile</a>
						</span>
					</nav>
				</div>
			</header>
	<div id="mySidenav" class="sidenav" style="margin-top:85px;">
  		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  		<a href="#" onclick="openProfs()">Professors</a>
 		<a href="#" onclick="openDepartments()">Departments</a>
 		<a href="#" onclick="openClasses()">Classes</a>
  		<a href="#" onclick="openRating()">Rate</a>
	</div>
	<div id ="sideside" class="sidenav2" style="margin-top:85px; margin-left:250px; border-color: red;">
		<form>
			<h3 style="margin:25px;">Department</h3>
			<select style="margin:25px; width:auto">
				<option>Arts and Humanities</option>
				<option>Natural Sciences</option>
				<option>Social Sciences</option>
				<option>Engineering</option>
			</select>
			<h3 style="margin:25px;">Name</h3>
			<input type="text" style="margin:25px; width:85%">
			<input type="submit" value="Search" style="margin:25px">
		</form>
	</div>
	<div id="login_overlay">
		<form id="user_login" action="review.jsp" onsubmit="return login()">
			<div style="height:80px;"></div>
			<div id="" class="login_fields">Email</div><br />
			<input id="username" name="username" class="login_fields" type="text"><br />
			<div style="height:20px;"></div>
			<div class="login_fields">Password</div><br />
			<input id="password" name="password" class="login_fields" type="password"><br />
			<div style="height:15px;"></div>
			<div id="error_msg" class="login_fields"></div>
			<input id="login_submit" name="login_submit" type="submit" value="Log in">
		</form>
		<img id="login_icon" src="images/tj.jpg">
		<img id="cancel_icon" src="images/cancel.png" onclick="document.getElementById('login_overlay').style.display = 'none';">
	</div>
	<div id ="sideside2" class="sidenav2" style="margin-top:85px; margin-left:250px; border-color: red;">
		<form>
			<h3 style="margin:25px;">Department</h3>
			<select style="margin:25px; width:auto">
				<option>Arts and Humanities</option>
				<option>Natural Sciences</option>
				<option>Social Sciences</option>
				<option>Engineering</option>
			</select>
			<h3 style="margin:25px;">Name</h3>
			<input type="submit" value="Search" style="margin:25px">
		</form>
	</div>

	<div id ="sideside3" class="sidenav2" style="margin-top:85px; margin-left:250px; border-color: red;">
		<form>
			<h3 style="margin:25px;">Classes</h3>
			<select style="margin:25px; width:auto">
				<option>Arts and Humanities</option>
				<option>Natural Sciences</option>
				<option>Social Sciences</option>
				<option>Engineering</option>
			</select>
			<h3 style="margin:25px;">Name</h3>
			<input type="text" style="margin:25px; width:85%">
			<input type="submit" value="Search" style="margin:25px">
		</form>
	</div>

	<div id ="sideside4" class="sidenav2" style="margin-top:85px; margin-left:250px; border-color: red;">
		<form>
			<h3 style="margin:25px; margin-bottom:0px;">Rate </h3>
			<label>
				<input id="ex1" style="margin:30px; margin-right: 5px" type="radio" name="rate" value="Professor" checked="checked"> Professor
				<input id="ex2" style="margin:30px; margin-right: 5px" type="radio" name="rate" value="Department"> Department
				<input id="ex3" style="margin:30px; margin-right: 5px" type="radio" name="rate" value="Class"> Class
			</label>
			<hr style="margin:16px">

			<h3 style="margin:25px;">I want to rate</h3>
			<input id="submitText" type="text" style="margin:25px; width:85%; border-color:red" placeholder="Professor's Name">
			<input type="submit" value="Search" style="margin:25px">
		</form>
	</div>
	
	
		<div id="hidden_username" ><%if(user != null){ %><%=user.getName()%><%} else {}%></div>
	
<script>
	var exx1 = document.getElementById('ex1');
	var exx2 = document.getElementById('ex2');
        var exx3 = document.getElementById('ex3');
	
	exx1.onclick = function() {
		document.getElementById('submitText').placeholder = "Professor's Name";	
	}
	exx2.onclick = function() {
		document.getElementById('submitText').placeholder = "Department Name";	
	}
	exx3.onclick = function() {
		document.getElementById('submitText').placeholder = "Class Name";	
	}
	
</script>

		
	<div id="main">
		<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Search</span>

		<div class ="field half first"> 
			<h2> Top Professors Leaderboard </h2>
			<ul>
				<li>Jeffrey Miller</li>
				<li>Sandra Batista</li>
			</ul>
		</div>
	</div>

	<div id="inbox_wrapper">
			<div id="unread_msg" style="display:none;"></div>
			<img src="images/msg.png" id="inbox" onclick="read()">
			<img src="images/refresh.png" id="refresh_icon" onclick="refresh()">
			<button type="button" onclick="cacheRestore()">Restore</button>
	</div>

<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
  
}
function openProfs() {
	closesidesides();
	document.getElementById("sideside").style.width = "500px";
	document.getElementById("sideside").style.border = "solid";
	document.getElementById("sideside").style.borderColor = "red";
}
function openDepartments() {
	closesidesides();
document.getElementById("sideside2").style.width = "500px";
	document.getElementById("sideside2").style.border = "solid";
	document.getElementById("sideside2").style.borderColor = "red";
}
function openClasses() {
	closesidesides();
document.getElementById("sideside3").style.width = "500px";
	document.getElementById("sideside3").style.border = "solid";
	document.getElementById("sideside3").style.borderColor = "red";
}
function openRating() {
	closesidesides();
document.getElementById("sideside4").style.width = "500px";
	document.getElementById("sideside4").style.border = "solid";
	document.getElementById("sideside4").style.borderColor = "red";
}


function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
  closesidesides();
}

function closesidesides() {
document.getElementById("sideside").style.width = "0";
  document.getElementById("sideside").style.border = "none";
document.getElementById("sideside2").style.width = "0";
  document.getElementById("sideside2").style.border = "none";
document.getElementById("sideside3").style.width = "0";
  document.getElementById("sideside3").style.border = "none";
document.getElementById("sideside4").style.width = "0";
  document.getElementById("sideside4").style.border = "none";
}

</script>

	<script>
			<% if (user != null) { %>
			
				document.getElementById("login").style.display = "none";
				document.getElementById("register").style.display = "none";
				document.getElementById("profile").style.display = "inline";
			<%	} %>
			</script>

   
</body>
</html> 