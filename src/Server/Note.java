package Server;

import java.io.*;

public class Note {

    String name;

    String owner;

    String directory;

    public void setName(String name){
        this.name = name;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public void setDirectory(String name) {

        new File("Server/notes/" + owner).mkdirs();
        this.directory = "Server/notes/" + owner + "/" + name + ".txt";
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getDirectory() {
        return directory;
    }

}
