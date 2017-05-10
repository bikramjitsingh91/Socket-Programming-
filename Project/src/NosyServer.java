/* Author: Bikramjit Singh
 * Project: TCP NosyServer
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.TimeZone;

public class NosyServer {

	public static void main(String argv[]) throws Exception , NullPointerException
	{
        // Get command line argument
		if (argv.length != 1) {
			System.out.println("Required arguments: port");
			return;
		}

        //String variable to store the command sent by client.
		String clientCommand = "";

        // String variable to store the result of respective commands send by client
		String responsetoCommand = "";

        //Reading the port number from the command line argument on which server will run, from the command line argument
		int serverport = Integer.parseInt(argv[0]);

	    //Serversocket is intialized for the port number read from command line
		ServerSocket welcomeSocket = new ServerSocket(serverport);

        //Client requested for connection, create a socket
		Socket connectionSocket = welcomeSocket.accept();

        //loop will continue running until the client provide the command to exit the connection
		while (true) 
		{
            //Buffer store the client requested command
			BufferedReader inFromClient = new BufferedReader(new
					InputStreamReader(connectionSocket.getInputStream()));

            //Stream where the output data will be stored
			DataOutputStream outToClient =
					new DataOutputStream(connectionSocket.getOutputStream());

            //read the clients request and stored in the clientCommand string
            clientCommand = inFromClient.readLine();

            /*if everything is right, than following if branching is used to store response
            for each command in the string variable reponsetoCommand. If the client send quit command or
            any other command command to quit the programm, server will send wrong keyword to client, and
            if client read this response than it quits the programm and close the connestion*/

            if (clientCommand.equals("date")) {
                responsetoCommand = new Date().toString();
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);
            }
            else if (clientCommand.equals("timezone")) {
                responsetoCommand = TimeZone.getDefault().getDisplayName();
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);
            }
            else if (clientCommand.equals("OSname")) {
                responsetoCommand = System.getProperty("os.name");
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);
            }
            else if (clientCommand.equals("OSversion")) {
                responsetoCommand = System.getProperty("os.version");
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);
            }
            else if (clientCommand.equals("user")) {
                responsetoCommand = System.getProperty("user.name");
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);
            }
            else {
                responsetoCommand = "wrong";
                String out = responsetoCommand + "\n" + "Enter command > \n";

                //server send response to client
                outToClient.writeBytes(out);

                System.out.println("closing the socket connection");
                //close the socket connection here
                connectionSocket.close();
                //waiting for new client to connect
                System.out.println("Connection closed. Waiting for new connection");
                connectionSocket = welcomeSocket.accept();
            }

		}

	}

}
