package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class Note{

    String contentOfNote = new String();

    String name;

    Date date = new Date();

    String owner;

    String text;

    String directory;

    private BufferedReader keyboard;

    public void create() throws IOException{

        directory = null;
        name = null;

        contentOfNote = "";
        try {

            keyboard = new BufferedReader(new InputStreamReader(System.in));

            //Name of note
            setNameForCreateNote();
            contentOfNote += "Name: " + name + "\n";

            //Date of note
            getDate();
            contentOfNote += "Date: " + date + "\n";

            //Text of note
            setText();
            contentOfNote += "Text: " + "\n" + text;


        }catch (IOException exc){
            System.out.println("Error: " + exc);
        }

        try{

            setDirectory(name);
            OutputStreamWriter writeInFile = new OutputStreamWriter(new FileOutputStream(directory));

            writeInFile.write(contentOfNote);
            writeInFile.close();

        } catch (IOException exc){
            System.out.println("Error: " + exc);
        }
    }

    //Сделать вывод названий существующих заметок
    public String open() throws IOException {

        directory = null;
        name = null;

        contentOfNote = "";

        keyboard = new BufferedReader(new InputStreamReader(System.in));
        setDirectory(setNameForOpenNote());

        String tmp;
        try {

            System.out.println();
            BufferedReader readFromFile = new BufferedReader(new FileReader(directory));

            while (true) {
                if ((tmp = readFromFile.readLine()) == null)
                    break;
                else
                    contentOfNote += tmp + "\n";
            }


        }catch (IOException exc){
            System.out.println("Note '" + getName() + "' not found");
            directory = null;
            name = null;
        }

        return contentOfNote;
    }

    public void synchronizationWithServer() {

        try {
            InetAddress ipAddress = InetAddress.getByName(Client.address);
            Socket socket = new Socket(ipAddress, Client.port);

            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();

            DataInputStream inData = new DataInputStream(inStream);
            DataOutputStream outData = new DataOutputStream(outStream);

            outData.writeUTF(Client.username);
            outData.writeUTF(getName());
            outData.writeUTF(contentOfNote);
            outData.flush();

        } catch (IOException exc) {
            System.out.println("Error: " + exc);
        }
    }

    public void delete(){

    }

    public void edit(){

    }

    //Добавить счетчик для стандартного названия "Note", "Note1", "Note2",...
    public void setNameForCreateNote() throws IOException {

        System.out.println("\nEnter name of note: ");

        name = keyboard.readLine();
        if (name.replaceAll(" ", "").equals("")){
            name = "Note";
        }
        this.name = name;
    }

    public String setNameForOpenNote() throws IOException{

        try {
            while (name == null || name.replaceAll(" ", "").equals("")) {
                System.out.println("Enter name of note: ");
                name = keyboard.readLine();
            }
        }catch (IOException exc) {
            System.out.println("Error: " + exc);
        }
        return name;
    }

    public void setText() throws IOException {

        String text = "";
        System.out.println("\nEnter text of note: \n (Enter /e to exit)");
        String tmp;

        while (true) {
            tmp = keyboard.readLine();
            if (tmp.substring(tmp.length() - 2).equals("/e"))
                break;
            else text += tmp + "\n";
        }

        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDirectory(String name) {

        new File("Client/notes/" + owner).mkdirs();
        this.directory = "Client/notes/" + owner + "/" + name + ".txt";
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        date.toString();
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public String getDirectory() {

        return directory;
    }

}
