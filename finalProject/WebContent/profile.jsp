<!DOCTYPE HTML>
<!--
	Introspect by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Profile</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script type="text/javascript">
			function getInfo(){
				document.getElementById("fname").value ="<%=(String)session.getAttribute("fname") %>"; 
				document.getElementById("lname").value = "<%=(String)session.getAttribute("lname") %>";
				document.getElementById("email").value = "<%=(String)session.getAttribute("user") %>"; 
				document.getElementById("major").value = "<%=(String)session.getAttribute("major")%>";
			}
			function changeProfile(){
				var xhttp = new XMLHttpRequest();			
				xhttp.open("GET", "ChangeProfile", false); // put all the parameters in the url
				xhttp.send();

				if(xhttp.responseText.length > 0){
					document.getElementById("error_msg").innerHTML = xhttp.responseText;
					return false;
				}
				return true;
				
			}
		</script>
	</head>
	<body onload = "getInfo()">
		

		<!-- Header -->
			<header id="header">
				<div class="inner">
					<a href="index.jsp" class="logo">Rate My USC</a>
					<nav id="nav">
						<a href="index.html">Home</a>
						<a href="reviews.html">Reviews</a>
						<a>Logout</a>
						
					</nav>
				</div>
			</header>
			<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>

		<!-- Main -->

			<section id="main" >
				<div class="inner">
					<header class="major special">
						<h1>User's Profile Page</h1>
						<div class="inner">
						<h3><a href="history.html" class="button">My review history</a></h3>
						<h3>Edit Profile</h1>
						
					<form method="post" action="index.jsp" onsubmit="return changeProfile()">
						<div class="field" style="width:48.5%;">
							<label for="email">USC Email (not editable)</label>
							<input type="text" name="email" id="email" style = "user-select: none;" />
							<script>
							document.getElementById("email").addEventListener("mousedown", function(event){
								  event.preventDefault();
							});
							</script>
						</div>
						
						<div class="field half first">
							<label for="fname">First name</label>
							<input type="text" name="fname" id="fname"/>
						</div>
						<div class="field half">
							<label for="lname">Last name</label>
							<input type="text" name="lname" id="lname"/>
						</div>
						<div class="field half first" style="width:48.5%">
							<label for="email">New Password</label>
							<input type="password" name="password" id="pw" />
						</div>

						<div class="field half" style="width:48.5%">
							<label for="email">Confirm New Password</label>
							<input type="password" name="password_copy" id="pwcopy" />
						</div>

						<div class="field" style="width:48.5%">
							<label for="email">Major</label>
							<input type="text" name="major" id="major"/>
						</div>							

						<ul class="actions">
							<li><input type="submit" value="Submit Edit" class="alt" /></li>
						</ul>
					</form>
					<div class="copyright">
					</div>

				</div>
			</section>

		<!-- Footer -->
			<section id="footer">
								</div>
			</section>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>