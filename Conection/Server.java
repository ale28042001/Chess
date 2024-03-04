import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import app.Game;

public class Server {
    private Set<PrintWriter> clientWriters = new HashSet<>();

    public void start(int port, Game partida) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new client handler thread
                Thread clientHandlerThread = new Thread(() -> handleClient(clientSocket,partida));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket, Game partida) {
        try {
            // Create input and output streams for the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()),1);
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in),1);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
    

            // Add the client writer to the set
            synchronized (clientWriters) {
                clientWriters.add(writer);
            }

            String h="";
    
            String userInput;
            boolean clientTurn = true; // Start with the client's turn

            while (true) {
                System.out.println("H"+h);
                if (clientTurn) {
                    // Read message from the client
                    String message = reader.readLine();
                    System.out.println("Received from client: " + message);

                    // Check for termination condition
                    if (message.equalsIgnoreCase("bye")) {
                        break;
                    }

                    // Set server's turn
                    clientTurn = false;

                    h+=message;
                } else {
                    // Read the message to be sent from the model or wherever it's obtained
                    String message1 = partida.getModel().a.toString();
                    
                    // Send the message to the client
                    writer.println(message1);
                    writer.flush(); // Ensure the message is sent immediately
                    
                    System.out.println("Move sent to client: " + message1);

                    // Read the message to be sent from the model or wherever it's obtained
                    String message2 = partida.getModel().b.toString();
                    
                    // Send the message to the client
                    writer.println(message2);
                    writer.flush(); // Ensure the message is sent immediately
                    
                    System.out.println("Move sent to client: " + message2);
                
                    // Set client's turn
                    clientTurn = true;
                }
                
            }

    
            // Remove the client writer from the set when the client disconnects
            synchronized (clientWriters) {
                clientWriters.remove(writer);
            }
    
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
        }
    }
    
    
    // Method to send a message to all clients
    public void sendMessageToAll(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }

    public static void main(String[] args) {
        Game partida = new Game();
        //partida.run();
        Server server = new Server();
        server.start(8083,partida);
    }
}