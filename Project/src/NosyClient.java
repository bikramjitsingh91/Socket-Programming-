/* Author: Bikramjit Singh
 * Project: TCP NosyClient
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Stream;

public class NosyClient 
{
	
	public static void main (String args[]) throws Exception {

        // Get command line argument.
		if (args.length != 2) 
		{
			System.out.println("Required arguments: <host> <port>");
			return;
		}

		//Menu which will be printed on screen first time client start the programm
		String output = "= = = = = = = = = = = = = = = = Menu = = = = = = = = = = = = = = = = = = = = = =\n" +
		                " 	date - print the date and time of server’s system\n" +
		                " 	timezone - print the time zone of server’s system\n" +
		                " 	OSname - print the name of server’s operating system (OS)\n" +
		                " 	OSversion - print the of version number of server’s OS\n" +
		                " 	user - print the name of the user logged onto (i.e. running) the server\n" +
		                " 	exit - exit the program\n" +
		                "= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =\n"+
		                "Enter command > ";
		
		//store the host name provided in the command line argument
        String host = args[0];

        //store the port number on which server is running, provided in the command line
		int serverport = Integer.parseInt(args[1]);


		Scanner in = new Scanner(System.in);
		
        String responsetoCommand = "";

        //  Client Socket is created using host and the port number
        Socket clientSocket = new Socket(host, serverport);

        //Datagram stream store the command which will be sent to server
		DataOutputStream outToServer =
				new DataOutputStream(clientSocket.getOutputStream());

        //Buufer store the result obtained from the server
		BufferedReader inFromServer = new BufferedReader(new
				InputStreamReader(clientSocket.getInputStream()));

		//print the menu for user
        System.out.print(output);

		Scanner input = new Scanner(System.in);

        //boolean variale to keep track of the loop enidng.
        boolean loopend = true;

        // loop will continue, untill user enter the exit/ or anyother command to exit the programm
		while(loopend)
		{
            //user input is stored in the varible clientCommand
            String clientCommand = input.nextLine();

            //Passing the user input to the server through the DataOutputStream
			outToServer.writeBytes(clientCommand + "\n");
            //read the response from the server from the BufferedReader
			String res1 = inFromServer.readLine();

            /*if server send wrong keyword that means user want to exit the programm,
            so client just make the loopendfalse and exit's the loop. Else it will print the server respone
            to the users command*/
			if(res1.equals("wrong")) {
                loopend = false;
            }
            else {
                String res2 = inFromServer.readLine();

                System.out.print(res1 + "\n" + res2);
            }
		}
        //close the socket connection
        clientSocket.close();
		

	}
}
