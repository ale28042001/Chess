package connection;

import controller.Controller;
import model.Board;
import model.Position;
import model.Utils;
import view.ChessView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private Controller controller;

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
                        controller.notify(position);


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

    public void setController(Controller controller){
        this.controller = controller;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket);
        client.listenForPosition();
        ChessView view = new ChessView();
        Board model = Utils.createGame(view);
        Controller controller = new Controller(view, model, client);
        client.setController(controller);
        controller.repaintPieces();

    }

}
