#Team 5 Final Project

High-Level Requirements
We are creating a lite version of RateMyProfessor exclusively made for USC students and those interested in the real opinions of USC students.  The web application will allow USC students to make an account and sign in using their USC email address. [At this time, there are no plans to verify that this email address is valid and that they are a current USC student]. Students that are logged in will be allowed to use all of the existing functionality of the website including, but not limited to, adding reviews, updating reviews, and searching through the site. Users that are not logged-in will be allowed to traverse the application and search through it but will not be allowed to make changes.
All users will be able to search for professors from the homepage, and they are able to toggle between search from the entire school or by the department. A leaderboard consisting of the top 5 professors in USC will be displayed on the homepage based on their overall rating double value, and users can go directly into their details by clicking on the names. When leaving a review, the user can assign an overall score as well as scores for independent categories such as accessibility. The user also has the option to choose between using their username or being anonymous. The website should check if the user has already posted a review on the professor before, if the user has already posted a review, it should not allow the user to post an additional review. Instead, it should allow the user to edit review.
The page for each professor should display a list of reviews with the option of them being sorted by helpfulness, posted time, or rating. The page should also display a short statistic that includes the distribution of the reviews ratings and the average rating for each category, as well as the average overall rating. The page will have a link to their own personal/professional website URL (if available) along with a profile picture (if available), a list of known classes, and a short summary about the professor. For each review, the other users are able to upvote or downvote the review based on the helpfulness. The review with the highest helpfulness score(num of upvote - num of downvote) will be displayed first if sorted by helpfulness
	Finally, the user will be able to log out from their account. This will put the user back into guest mode where their site-functionalities are limited only to searching.  Alternatively, closing the browser for an extended period of time will automatically log the user out from the website. 










Technical Specifications

Hours
Requirement
Task
3
Database (MYSQL)
Create a new database and create two tables. The first table will be to populate user information, including login/pw/school year/major/etc. The second table will used as a server to store data from user reviews
7
(UI) Landing page and template for other pages
Design the look and feel of the landing page. Use existing popular templates (i.e. Google, Tesla, RMP) instead of building from the ground up.  Site doe
7
(UI) Build user login functionality
Build buttons on navigation bar that will allow users to login/sign up for the web application. The form should be validated on the backend to ensure the input is all valid 
(eg. email ends with “@usc.edu”, name is alpha, birthdate is in correct format, grade year is selected from dropdown box)


7
(UI) Build Professor page
Build professor page that summarizes them. Give them a picture and provide details (eg) specific courses that they have taught in the past. 
6
Functionality
Leaving Reviews
Build site functionality that allows logged-in users to leave reviews. Initially design just a textbox that will be submitted to the back end.  And also take an integer/double as a numeric rating value that we can use later to average the Professor’s ratings
6
Database
Connect the existing reviews/data/professor average data and connect it to the existing tables in the database.  Also, use the GROUP BY function in SQL in order to average the professors ratings. 
7
Average Rating
Retrieve the average rating from database for each professor and use it to display it on the Professor’s profile page




Detailed Design
Database (Initial)
Create database
Create tables
One table for user information
One table for Professor information/ review data
Store a column for rating under Professors that will be used to calculate average rating
Use the GROUP BY, COUNT, and AVG, function of SQL in order to get each Professor’s average rating
UI Design
Build landing page using existing templates and Twitter’s bootstrap
Replicate well-designed pages like Tesla, Facebook, Twitter, RMP, etc.
LOGIN
Build user sign up page that will take a form and basic user info like name, email, password, school year, major, etc.
Build user login page that will take user’s username/password
Build navbar with two buttons that allow users to log in or sign up
Build navbar with two buttons that allow users to change their account information or log out
Build user account information page. This will be a table that displays their existing data and textboxes that allow them to change the data
Professor Page
Build Professor page using template that follows landing page
At the top, display basic Professor information like image, name, links to their own website (if available), and known classes that they have taught at USC
Leave their average rating
Build a textbox that allows users to leave reviews
Build a table that displays the Professor’s existing reviews
DataBase (Final)
Connect the front-end forms that submit to backend to submit to the DB in the back-end
This is for both the user information and data reviews





Testing (by priority)
Login
Ensure users are able to sign up
Ensure users are able to log in
Ensure users are able to log out
UI
Ensure navigation to and from each JSP and through the search bar is functioning correctly
Ensure every button works correctly and design/site feel is good
Database
Ensure that user logins are stored correctly and are being returned properly when requested
Ensure that the average rating function is returning the correct value
