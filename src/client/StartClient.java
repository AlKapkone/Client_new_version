package client;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartClient {

    private final String host;
    private final int port;

    StartClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket sock;
    private Scanner response;
    private PrintWriter request;

    public void start() {
        try {
            sock = new Socket(host, port);
            System.out.println("Connected to " + host + " on port " + port);
            response = new Scanner(sock.getInputStream());
            request = new PrintWriter(sock.getOutputStream());


        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void addUser(User user) throws IOException {

        String json = getJson(user);

        if (setConect("Add")) {
            System.out.println(json);
            write(json);
            System.out.println(response.nextLine());
        }
    }
    
    public void deleteUser(User user) {
        String json = getJson(user);

        if (setConect("Del")) {
            System.out.println(json);
            write(json);
            System.out.println(response.nextLine());
        }
    }

    public void updateUser(User user) {

        String json = getJson(user);

        if (setConect("Upd")) {
            System.out.println(json);
            write(json);
            System.out.println(response.nextLine());
        }
    }

    public String getAllUser() {
        String allUsers = "";
        if(setConect("Get")){
            write("getusers");
            allUsers = response.nextLine();
        }
        return allUsers;
    }
    
    private boolean setConect(String command){
        request.write(command + "\n");
        request.flush();
        return response.nextLine().equals("ok");
    }

    private String getJson(User user) {

        List<User> usersData = new ArrayList<>();
        usersData.add(user);
        Gson gson = new Gson();
        return gson.toJson(usersData);
    }
    
    private void write(String writeContent){
        request.write(writeContent + "\n");
            request.flush();
    }
}
