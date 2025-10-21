/**
 * Name(s): 	Mack Bautista, Patrick Dang
 * Email: 		mbaut981@mtroyal.ca, pdang289@mtroyal.ca
 * Course: 		COMP3533-002
 * Instructor: 	Mingwei Gong
 * Lab: 		5
 * Due Date: 	Oct. 23, 2025
 */

package Networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static final String WORDS_TXT = "words.txt";

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String response;

		FileReaderHelper reader = new FileReaderHelper(WORDS_TXT);
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
			System.out.println("ServerSocket created, waiting for incoming connections...");
			Socket connectionSocket = welcomeSocket.accept();

			System.out.println("Accepted TCP connection from " +
				connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());

			BufferedReader inFromClient =
				new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient =
				new DataOutputStream(connectionSocket.getOutputStream());

			try {
				while ((clientSentence = inFromClient.readLine()) != null) {

					if (clientSentence.equalsIgnoreCase("exit")) {
						System.out.println("Client disconnected.");
						break;
					}

					response = reader.getMatchingWords(clientSentence);
					outToClient.writeBytes(response + "\n");
					System.out.println("Processed prefix: " + clientSentence);
				}
			} catch (IOException e) {
				System.out.println("Connection lost with client.");
			} finally {
				connectionSocket.close();
			}
		}
	}
}
