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
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Class Name: TCPClient
 * Purpose: Implements the client-side portion of a TCP connection that allows
 *          a user to input a word prefix and send it to a {@link TCPServer}.
 *          The server then responds with all words from its repository that
 *          share the same prefix.
 */
public class TCPClient {

	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		Socket clientSocket = new Socket("localhost", 6789);
		System.out.println("Client successfully established TCP connection.\n"
				+ "Client(local) end of the connection uses port " 
				+ clientSocket.getLocalPort() 
				+ " and server(remote) end of the connection uses port "
				+ clientSocket.getPort());

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		sentence = inFromUser.readLine();
		while (sentence.toLowerCase().compareTo("exit") != 0) {
			outToServer.writeBytes(sentence + '\n');

			modifiedSentence = inFromServer.readLine();

			System.out.println("The following words that start with: " + sentence + ": " + modifiedSentence);
			sentence = inFromUser.readLine();
		}

		clientSocket.close();
	}
}
