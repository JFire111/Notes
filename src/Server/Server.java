package Server;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException{

        int port = 14123;

        String pathToFile = "/home/jv/JavaProjects/IdeaProjects/Notes/src/Server/ListOfNotes";

        try{

            //Добавить авторизацию пользователя
            //Добавить многопоточность для подключения нескольких польователей

            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("------------");

            try {
                Socket socket = serverSocket.accept();

                System.out.println("Client is connected");

                Note note = new Note();

                try {
                    InputStream inStream = socket.getInputStream();
                    OutputStream outStream = socket.getOutputStream();

                    DataInputStream inData = new DataInputStream(inStream);
                    DataOutputStream outData = new DataOutputStream(outStream);

                    String message;

                    //Принимается только первое сообщение. Исправить
                    while (true) {
                        note.setOwner(inData.readUTF());
                        note.setName(inData.readUTF());
                        note.setDirectory(note.getName());
                        message = inData.readUTF();
                        System.out.println("Message: " + message);
                        OutputStreamWriter saveInFile = new OutputStreamWriter(new FileOutputStream(note.getDirectory()));
                        saveInFile.write(message);
                        saveInFile.close();
                    }

                }catch (Exception exc){
                    System.out.println("Server error: " + exc);
                }
            } catch (Exception exc){
                System.out.println("Server error: " + exc);
            }
        } catch (Exception exc){
            System.out.println("Server error: " + exc);
        }
    }
}