package connection;

import model.Position;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Integer playerNumber;

    public ClientHandler(Socket socket, int playerNumber){

        try{
            this.socket = socket;
            this.outStream = new ObjectOutputStream(socket.getOutputStream());
            this.inStream = new ObjectInputStream(socket.getInputStream());
            this.playerNumber = playerNumber;
            clientHandlers.add(this);
            sendPlayerNumber(this.playerNumber);
        } catch(IOException e){
            closeEverything(socket, inStream, outStream);
        }

    }


    @Override
    public void run() {

        Object inputObject;

        while(socket.isConnected()){
            try {
                inputObject = inStream.readObject();
                if(inputObject instanceof Position){
                    Position position = (Position) inputObject;
                    broadcastPosition(position);
                }
            } catch (IOException e) {
                // Manejar IOException
                closeEverything(socket, inStream, outStream);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastPosition(Position position){
        for(ClientHandler clientHandler: clientHandlers){
            try {
                if(clientHandler.playerNumber != this.playerNumber){
                    clientHandler.outStream.writeObject(position);
                    clientHandler.outStream.flush();
                }
            } catch (IOException e){
                closeEverything(socket, inStream, outStream);
            }
        }
    }

    public void sendPlayerNumber(Integer playerNumber){
        for (ClientHandler clientHandler: clientHandlers){
            try{
                if(clientHandler.playerNumber == this.playerNumber){
                    clientHandler.outStream.writeObject(playerNumber);
                    clientHandler.outStream.flush();
                }
            } catch(IOException e){
                closeEverything(socket, inStream, outStream);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
    }

    public void closeEverything(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream){
        removeClientHandler();

        try{
            if(inStream != null){
                inStream.close();
            }
            if(outStream != null){
                outStream.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
