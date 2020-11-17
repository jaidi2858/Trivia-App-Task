# Trivia-App-Task
A quiz app developed for a technical task


====================================================================================
COMPLETED SECTION   



TASK - Points :
Point 1 completed
Point 2 completed - Section a,b,c completed
Point 3 completed - Section a,b,c,d completed
Point 4 completed 
Point 5 (NOT COMPLETED) -  Unable to implement it due to time constraint --  Need some time to complete it 

FLOW DETAILS :

Application design completed including icons added for categories
Popup designed for Difficulty level and Option selection
Quiz design is not fully pro level ( Due to time constraint , Mostly focused on functionality)

10 categories hard coded and inserted in local database on first run of app
If categories alreday present in database then fetch and show

User can select category and then 
User will select options(True/False , Multiple choice)
User will select difficulty level

Then questions screen will appear and user will have to answer the all questions
On submition of quiz Points earning calculated according to difficulty

Local database implemented using (Room Database)
Points earning saving in database against category
Solved questions also saved in the local database

======================================================================================

THIRD PARTY DEPENDENCIES 



SDP LIBRARY (com.intuit.sdp:sdp-android:1.0.6)  
Used for the design responsivness on different devices (margin , padding etc )

GLIDE LIBRARY (com.github.bumptech.glide:glide:4.9.0)
Used for the loading of images of categories

RETROFIT LIBRARY(com.squareup.retrofit2:retrofit:2.6.2)
Used for network calling - Getting data from api

KOIN LIBRARY (org.koin:koin-android:2.1.6)
Used for dependency injection 


=========================================================================================

KNOWN BUGS



Question and answers text are not proper from api ( Special characters are showing like words e.g &amp...etc)



=========================================================================================

IMPROVMENTS


YES Its quiz design can be improved
We can add more modules in it ( Like Game with lifes , 
Online records sync , Login/Signup , Payments against earning points , Unlock levels ...etc)

