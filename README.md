# Project Name:  Bus Reservation system

#### ( a console based application with Relational DB for reliable Storage )

## How it function

- #### User and Admin has Different Interface
- #### User gets all the User functionalities with Least Privilege to the data ,can only use functionalities specified in User Interface
- ## User Interface
- - #####  enter 0 for Admin Interface:  --> redirected to Login Interface
- - #####  enter 1 to getBusInfo: 
- -  #####  enter 2 to BookTickets: 
- -  #####  enter 3 to CancelTickets: 
- -  #####   enter -1 to Exit : 


- ## Admin Interface (has all the Privilege to Add,Remove,Generate Report for Bus)
- - ##### enter 0 to Add Buses: 
- -  ##### enter 1 to remove Buses: 
- -  #####  enter 2 to generate Bus Report: 
- -  #####   enter -1 to Exit :


 ## OOP features Used
-  #### Used Encapsulation To bind the Data and its method together ,where all the data members are private,only methods were public.each variable has its own Getters and Setters
-  #### Implemented Data Abstraction and Hiding is Achieved through Interface,Where Only the Functionality/Service can be experienced by User and Admin and Internal Implementation were Hidden,Providing High Degree of Security
- #### Utilised Inheritance , Promoting Code Reusablity ,avoiding Writing Repeative code
-  #### Achieved Polymorphism ,Here a Object of type Parent Class takes Many Forms,Bringing More Generic and Great Degree of Flexibility for the Application

## Features

- Used Relational Database MySQL for storing the Data Permanently
- Reliable Application with almost No Erros where All Potential Errors were Handled Properly With try catch
- Utilised JDBC Driver for making  DB Connections
- Achieved DataHiding With Interface to avoid Security Vulnerablities ,Preventing UnAuthorized Access
