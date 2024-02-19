The application consists of one activity and 3 fragments. 

In the first fragment, the user enters the name of the city, and a request is sent to the server. 
If the server sends information about the city, we save the city in the database and add it to the list on the screen. 
When the user clicks on any item in the list, they navigate to the second fragment. 

The second fragment contains information about the weather for today and a list with the weather for the next 6 days. 
By clicking on any item in the list, the user navigates to the third fragment. 

The third fragment displays detailed information about the weather on a specific day.


For the development of the application, the following technologies and libraries  were used:
- Koin
- Coroutine
- Room
- MVVM
- Picasso
- Fragment
- NavFragment
