package Client;

import java.util.Scanner;

public class User {


    String name;

    String password;

    Scanner scan = new Scanner(System.in);

    public void setName(){

        name = new String();
        while (name.trim().length() == 0) {
            System.out.println("Username: ");
            name = scan.nextLine();
        }
    }

    public void setPassword(){

        System.out.println("Password: ");
        password = scan.nextLine();
    }

    public String getName() {
        return name;
    }
}