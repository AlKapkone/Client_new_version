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

    private Socket socket;
    private Scanner response;
    private PrintWriter request;

    public void start() {

        try {
            socket = new Socket(host, port);
            System.out.println("Connected to " + host + " on port " + port);
            response = new Scanner(socket.getInputStream());
            request = new PrintWriter(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    
    public void executeUser(User user, String command){
        String sendMessage = getJson(user);

        if (setConect(command)) {
            System.out.println(sendMessage);
            write(sendMessage);
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
    
    public void exit() throws IOException {

        if(setConect("Exi")){
            System.out.println("Exit successfuly");
        }
        socket.close();
    }
    
    private boolean setConect(String command){
        request.write(command + "\n");
        request.flush();
        return response.nextLine().equals("Ok");
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
