
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
	private Socket socket = null;
	ServerSocket serverSocket = null;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Scanner sc = new Scanner(socket.getInputStream());
			String request = sc.nextLine();

			PrintStream out = new PrintStream(socket.getOutputStream());
			while ((!request.equals("exit")) && (!request.equals("stop"))) {
				String response = "Server received the request ... ";
				out.println(response);
			}

		} catch (IOException e) {
			System.err.println("Communication error... " + e);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	public void close() {

	}

}
