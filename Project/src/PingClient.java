/* Author: Bikramjit Singh
 * CSE: CSE03248
 * Student ID: 211249893
 * Teacher: Natalija Vlajic
 * Project: Ping Client
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;


public class PingClient {

	public static void main(String[] args) throws IOException
	{
		// Get command line argument.
		if (args.length != 1)
		{
			System.out.println("Required arguments: port");
			return;
		}
		
		//reading the port, client will use from the command line argument provided by user
		int port = Integer.parseInt(args[0]);
		
		/*Instructions for the user how to run the client code*/
		System.out.println("Comand Syntax: ping <destination addr> <destination port> \n"
				+ "Use CTRL^C to quit");
		
		System.out.print("ping ");
		Scanner in = new Scanner(System.in);
		
		//Get the server name client wants to connect to, and post number of the server
		
		String host = in.next();
		int serverport = in.nextInt();
		in.close();
		
		/*Creating sending and receiving buffer to store the date which will be sent to server 
		and the data which received from server*/
		
		byte[] sendData = new byte[1204];
		byte[] receiveData = new byte[1204];
		
		//Create UDP socket with the port number provided by the user in the command line argument
		DatagramSocket clientSocket = new DatagramSocket(port);
		
		//Get the IP address of the host specified by the user
		InetAddress IPAddress = InetAddress.getByName(host);
		
		//Initialize the time value which will be by 
		int timeout = 1000;

		//Loop is used to send 10 ping request to server, that'swhy loop is runing from 0 to 9.
		for(int seq = 0; seq < 10; seq ++)
		{
			//Date instance is used to create timestamp for each ping
			Date timestamp = new Date();

			//Payload is created using sequence number, timestamp and CRLF
			String payload = "PING " + seq + " " + timestamp + "\r\n";

			//Payload is loaded into sendData buffer in the Bytes
			sendData = payload.getBytes();

			/*Datagramp packet is created for the packet to be send using senddata buffer
			and the size of senddata buffer*/
			DatagramPacket sendPacket =	new DatagramPacket(sendData, sendData.length, 
						IPAddress, serverport);

			/*Datagram created to store the incoming packet from the server, it creates using the recoverData buffer
			where the incoming packet will store and the size of the receiveData buffer*/
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			//packet is sent to sever from the socket
			clientSocket.send(sendPacket);

			//timer is set for 1 sec to check test the pakcet delivery
			clientSocket.setSoTimeout(timeout);

			//Try catch block is used to catch the ScoketTimeoutException when the timeout happen and packet is not recived yet
			try
			{
				//datagramp packet recved from the server, is placed in the receive packet datagram.
				clientSocket.receive(receivePacket);

				//recived packet is stored in string variable
				String recived = new String(receivePacket.getData());

				//IP address from where packet is send, extracted and stored in the string
				String recaddrr = receivePacket.getAddress().toString();

				/*if no expection occur, that means packet is send and recived properly and
				following message in printed in the screen.*/
				System.out.println("Received from : " + recaddrr + " " +recived.trim());
			}
			catch(SocketTimeoutException e)
			{
				/*
					if exception occured in the try block, it means timme out is happened and packet is dropped,
					so this will print out the message for the dropped packet.
				 */
				System.out.println("PING " + seq + " Request timed out.");
				continue;
			}
			
		}
		//After the loop end, client close the connection.
		clientSocket.close();
				
	}

}
