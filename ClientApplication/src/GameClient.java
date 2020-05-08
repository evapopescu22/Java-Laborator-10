
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

	public static void main(String[] args) throws IOException {
		String serverAddress = "127.0.0.1";
		int PORT = 8100;
		try (Scanner sc = new Scanner(System.in);
				Socket socket = new Socket(serverAddress, PORT);
				PrintStream out = new PrintStream(socket.getOutputStream(), true);
				Scanner sc1 = new Scanner(socket.getInputStream())) {
			while (true) {
				System.out.println("Enter a command:");
				String request = sc.nextLine();
				out.println(request);
				if (request.equals("exit")) {
					System.out.println("Closing this connection : " + socket);
					socket.close();
					System.out.println("Connection closed");
					break;
				}
				if (request.equals("stop")) {
					socket.close();
					System.out.println("Server stopped");
					break;
				}

				String response = sc1.nextLine();
				System.out.println(response);
			}
			sc.close();
		} catch (UnknownHostException e) {
			System.err.println("No server listening... " + e);
		}
	}
}
