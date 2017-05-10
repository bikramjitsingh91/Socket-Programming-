# Socket-Programming
This code is used to give get infomration of server machine like 
    what OS_version server is running on, 
    whats user name who currenlty logged into server machine
    Whats the time Zone of the server system 
    
How to use this software:
 To use this you first need to start the Server by compiling and running NosyServer class
 When you run NosyServer you need to provide the port number in argument.
 Here's the example how you can start and run this file
  javac NosyServer.java
  java NosyServer "Port Number"
  
  Once your server is started you can run the NosyClient which can request information from
  the server by execting the following commands.
     date - print the date and time of server’s system
     timezone - print the time zone of server’s system
		 OSname - print the name of server’s operating system (OS)
		 OSversion - print the of version number of server’s OS
		 user - print the name of the user logged onto (i.e. running) the server
		 exit - exit the program
     
   Here's the exmaple about how you can start the client
   javac NosyClient.java
   java NosyClient "hostName" "Port Number"
  
