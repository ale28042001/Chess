package connection;

import model.Position;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    public Client(Socket socket){
        try{
            this.socket = socket;
            this.outStream = new ObjectOutputStream(socket.getOutputStream());
            this.inStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            closeEverything(socket, inStream, outStream);
        }
    }

    public void sendPosition(Position position){
        try{
            System.out.println("Posicion Enviada");
            outStream.writeObject(position);
            outStream.flush();
        }catch(IOException e){
            closeEverything(socket, inStream, outStream);
        }
    }

    public void closeEverything(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream){

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

    public void listenForPosition(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Position position;
                while(socket.isConnected()){
                    try{
                        position = (Position) inStream.readObject();
                        System.out.println("Me ha llegado una posicion");
                    } catch (IOException e) {
                        // Manejar IOException
                        closeEverything(socket, inStream, outStream);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket);
        client.listenForPosition();

    }

}
