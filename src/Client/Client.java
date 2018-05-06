package Client;

import java.io.*;

public class Client {

    static int port = 14123;
    static String address = "127.0.0.1";
    static String username;

    public static void main(String[] args) throws IOException{

        User user = new User();
        Note note = new Note();

        String action;

        BufferedReader keyboard;

        user.setName();
        username = user.getName();
        note.setOwner(username);

        do {

            System.out.println("Chose action: ");
            System.out.println("/n - create new note | /o - open note | /s - synchronization | /exit - exit\n");

            keyboard = new BufferedReader(new InputStreamReader(System.in));
            action = keyboard.readLine();

            switch (action){
                //Create new note
                case "/n":
                    note.create();
                    System.out.println("\nNote is created!\n");
                    break;
                //Open note
                case "/o":
                    System.out.println(note.open());
                    System.out.println();
                    break;
                //Synchronization note with server
                //Синхронизируется только открытая заметка "/o". Сделать синхронизацию сразу всех существующих заметок
                //Заметки отправляются только на сервер. Сделать прием всех заметок с сервера
                case "/s":
                    note.synchronizationWithServer();
                    break;
            }
        }
        while (!action.equals("/exit"));
    }


    /*public String enterUsername(){

        Scanner scan = new Scanner(System.in);
        while (username.trim().length() == 0) {
            System.out.println("Username: ");
            username = scan.nextLine();
        }
        return username;
    }*/

}
