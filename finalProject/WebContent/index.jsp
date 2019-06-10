<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.*, java.util.ArrayList, javax.servlet.http.HttpSession" %>
<!DOCTYPE HTML>
<!--
	Introspect by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Rate My USC</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
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
		
		<% 
		User user = null;
		if(session.getAttribute("user") != null)
				user = (User) session.getAttribute("user"); 
		%>
		

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<a href="index.jsp" class="logo">Rate My USC</a>
					<nav id="nav">
						<a href="index.jsp">Home</a>
						<a href="reviews.jsp">Reviews</a>
						<span id="login">
						<a  href="#" onclick="document.getElementById('login_overlay').style.display = 'block';">Login</a>
						</span>
						<span id="register">
						<a href="register.html">Register</a>
						</span>
						<span id="logout" style="display:none;">
						<a href="LogoutServlet">Log Out</a>
						</span>
					</nav>
				</div>
			</header>
			<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

			<div id="login_overlay">
				<form id="user_login" action="index.jsp" onsubmit="return login()">
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
		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h1>RMUSC: <span>A Rating Site<br />
					By usc students</span></h1>
					<ul class="actions">
						<li><a href="register.html" class="button alt">Get Started</a></li>
					</ul>
				</div>
			</section>

		<!-- One -->
			<section id="one">
				<div class="inner">
					<header>
						<h2>Our Philosophy</h2>
					</header>
					<p>Suspendisse mauris. Fusce accumsan mollis eros. Pellentesque a diam sit amet mi ullamcorper vehicula. Integer adipiscin sem. Nullam quis massa sit amet nibh viverra malesuada. Nunc sem lacus, accumsan quis, faucibus non, congue vel, arcu, erisque hendrerit tellus. Integer sagittis. Vivamus a mauris eget arcu gravida tristique. Nunc iaculis mi in ante.</p>
					<ul class="actions">
						<li><a href="#" class="button alt">Learn More</a></li>
					</ul>
				</div>
			</section>

		<!-- Two -->
			<section id="two">
				<div class="inner">
					<article>
						<div class="content">
							<header>
								<h3>Pellentesque adipis</h3>
							</header>
							<div class="image fit">
								<img src="images/pic01.jpg" alt="" />
							</div>
							<p>Cumsan mollis eros. Pellentesque a diam sit amet mi magna ullamcorper vehicula. Integer adipiscin sem. Nullam quis massa sit amet lorem ipsum feugiat tempus.</p>
						</div>
					</article>
					<article class="alt">
						<div class="content">
							<header>
								<h3>Morbi interdum mol</h3>
							</header>
							<div class="image fit">
								<img src="images/pic02.jpg" alt="" />
							</div>
							<p>Cumsan mollis eros. Pellentesque a diam sit amet mi magna ullamcorper vehicula. Integer adipiscin sem. Nullam quis massa sit amet lorem ipsum feugiat tempus.</p>
						</div>
					</article>
				</div>
			</section>

		<!-- Three -->
			<section id="three">
				<div class="inner">
					<article>
						<div class="content">
							<span class="icon fa-laptop"></span>
							<header>
								<h3>Tempus Feugiat</h3>
							</header>
							<p>Morbi interdum mollis sapien. Sed ac risus. Phasellus lacinia, magna lorem ullamcorper laoreet, lectus arcu.</p>
							<ul class="actions">
								<li><a href="#" class="button alt">Learn More</a></li>
							</ul>
						</div>
					</article>
					<article>
						<div class="content">
							<span class="icon fa-diamond"></span>
							<header>
								<h3>Aliquam Nulla</h3>
							</header>
							<p>Ut convallis, sem sit amet interdum consectetuer, odio augue aliquam leo, nec dapibus tortor nibh sed.</p>
							<ul class="actions">
								<li><a href="#" class="button alt">Learn More</a></li>
							</ul>
						</div>
					</article>
					<article>
					<div class="content">
							<span class="icon fa-laptop"></span>
							<header>
								<h3>Sed Magna</h3>
							</header>
							<p>Suspendisse mauris. Fusce accumsan mollis eros. Pellentesque a diam sit amet mi ullamcorper vehicula.</p>
							<ul class="actions">
								<li><a href="#" class="button alt">Learn More</a></li>
							</ul>
						</div>
					</article>
				</div>
			</section>

		<!-- Footer -->
			<section id="footer">
				<div class="inner">
					<header>
						<h2>Get in Touch</h2>
					</header>
					<form method="post" action="#">
						<div class="field half first">
							<label for="name">Name</label>
							<input type="text" name="name" id="name" />
						</div>
						<div class="field half">
							<label for="email">Email</label>
							<input type="text" name="email" id="email" />
						</div>
						<div class="field">
							<label for="message">Message</label>
							<textarea name="message" id="message" rows="6"></textarea>
						</div>
						<ul class="actions">
							<li><input type="submit" value="Send Message" class="alt" /></li>
						</ul>
					</form>
					<div class="copyright">
						&copy; Untitled Design: <a href="https://templated.co/">TEMPLATED</a>. Images <a href="https://unsplash.com/">Unsplash</a>
					</div>
				</div>
			</section>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
			<script>
			<% if (user != null) { %>
				document.getElementById("login").style.display = "none";
				document.getElementById("register").style.display = "none";
				document.getElementById("logout").style.display = "inline";
			<%	} %>
			</script>
	</body>
</html>