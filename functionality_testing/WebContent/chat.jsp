<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.*, java.util.ArrayList, javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Web Chat Client</title>
		<script>
			var socket;
			var cache;
			//var username = "user";
			//var review = "review";
			function connectToServer() {
				socket = new WebSocket("ws://localhost:8080/CS201-WebSockets/ServerWS");
				socket.onopen = function(event) {
					document.getElementById("mychat").innerHTML += "Connected!<br />";
				}
				
				socket.onmessage = function(event) {
					if(event.data.substring(0, 5) == "CACHE"){
						document.chatform.message.value = event.data.substring(5);
					} else if(event.data.substring(0, 6) == "UNREAD") {		
						document.getElementById("unread_msg").innerHTML = event.data.substring(6);
						if(document.getElementById("unread_msg").innerHTML.length > 0){
							var unread_num = parseInt(event.data.substring(6));
							if(unread_num > 0){
								document.getElementById("unread_msg").style.display = "block";
							} else {
								document.getElementById("unread_msg").style.display = "none";
							}
						}
					} else if(event.data.substring(0, 2) == "UR"){
						var msg_start = event.data.indexOf("MSGS");
						var unread_msg = event.data.substring(2, msg_start);
						var msg = event.data.substring(msg_start + 4);
						var msgs;
						if(msg.length > 0){
							msgs = msg.split("\n");
						}
						document.getElementById("inbox_display").style.display = "block";
						var fields = document.getElementsByClassName("inbox_disp");
						var max_length = 0;
						if(msgs.length < 10){
							max_length = msgs.length;
						} else {
							max_length = 10;
						}
						
						for(var i = 0; i < max_length; i++){
							fields[i].innerHTML = msgs[i];
							if(i < unread_msg){
								fields[i].style.fontWeight = "bold";
							}
						}
					}
				}
				
				socket.onclose = function(event) {
				//	document.getElementById("mychat").innerHTML += "Disconnected!<br />";
				}
			}
			
			function sendMessage() {
				socket.send(document.chatform.message.value);
			//	return false;
				
			}
			
			function sendMessage(msg){
				socket.send(msg);
			}
			
			function storeCache(){
				sendMessage("CACHE" + document.getElementById("hidden_username").innerHTML + "MSG" + document.chatform.message.value);
			}
			
			function login() {
				var text = document.getElementById("hidden_username").innerHTML;
				sendMessage("LOGIN" + text);
				if(text != "null"){
					socket.send("REQMSG" + text);
				}
				return false;
			}
			
			function upvote(event) {
				var id = event.parentElement.childNodes[1].innerHTML;
				sendMessage("UPVOTE" + id);
			}
			
			function read() {
				sendMessage("READ" + document.getElementById("hidden_username").innerHTML);
			}
			
			function refresh() {
				var text = document.getElementById("hidden_username").innerHTML;
				if(text != "null"){
					sendMessage("REQMSG" + text);
				}
			}
			
			function cacheRestore() {
				sendMessage("LOGIN" + document.getElementById("hidden_username").innerHTML);
			}
			
		</script>
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
	</head>
	<body onload="connectToServer()" onunload="storeCache()">
		<%  User user = new User("Userc");
			//User user = null;
			ArrayList<Review> reviews = new ArrayList<>();
			char rev_content = 'a';
			for(int i = 0; i < 10; i++){
				rev_content = (char)(rev_content + 1);
				String rev_str = Character.toString(rev_content);
				reviews.add(new Review(i, "User" + rev_content, rev_str));
			}
		
		%>
		<form name="chatform" onsubmit="return login()">
			<input type="text" name="message" onblur="storeCache()" /><br />
			<input type="submit" name="submit" value = "Cache test" />
		</form>
		
		
		<table id="reviews">
			<%for(int i = 0; i < reviews.size(); i++){ %>
				<tr>
					<td>
						<div id="reviews_wraps" class="reviews">
							<%=reviews.get(i).getContent() %>
							<div style="display:none;"><%=reviews.get(i).getID()%></div>
							<img src="images/upvote.png" class="upvote" onclick="upvote(this)">
						</div>
					</td>
				</tr>
			<%} %>
		</table>
		<div id="inbox_wrapper">
			<div id="unread_msg" style="display:none;"></div>
			<img src="images/msg.png" id="inbox" onclick="read()">
			<img src="images/refresh.png" id="refresh_icon" onclick="refresh()">
			<button type="button" onclick="cacheRestore()">Restore</button>
		</div>
		<div id="inbox_display">
			<table>
				<%for(int i = 0; i < 10; i++){ %>
					<tr>
						<td><div class="inbox_disp">a</div></td>
					</tr>
				<%} %>
			</table>
		</div>
		
		<%if(user != null){ %>
			<div id="hidden_username" ><%=user.getName()%></div>
		<%} else {%>
			<div id="hidden_username" ></div>
		<%} %>
		<br />
	</body>
</html>