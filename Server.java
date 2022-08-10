
import java.io.*;
import java.net.*;
import java.util.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author priyenshah
 * 
 * Priyen Shah
 * shah563@purdue.edu
 * Server class has functionality to create a server for a port. Main method intializes a server based on a Chats object
 */



public class Server {
    
    int Port;
    Message ms = new Message();
    
    
    public void myServer(int Port, Chats z){
        try {
            ServerSocket s = new ServerSocket(Port); //the server socket
            boolean over = false;
            System.out.println("server on" + Port);
            while(!over) { //put in a loop that keeps running
                Socket incoming = s.accept(); //accept a connection from a client
                System.out.println("accept");
                try {
                    InputStream inStream = incoming.getInputStream(); // the INPUT stream handler
                    Scanner in = new Scanner(inStream); //setup of input
                    
                    boolean done = false;
                    
                    while (!done){ //while there are lines to read, for this connection
                        
                       
                        String lineIn = in.nextLine();
                        ms.setMessage(lineIn);
                        z.txtConversation.append(lineIn + "\n");
                        
                        //System.out.println(ms.getMessage() + " server");

                        if (lineIn.trim().equals("END")){ //to kill the server, enter "BYE" from the client
                            done = true;
                            System.out.println("Program has ended");
                        }
                        
                    }
                    in.close();
                }
                catch(Exception exc1){

                    exc1.printStackTrace();
                }
            }
        }
        catch(Exception exc2) {
            exc2.printStackTrace();
        }
        
        
    }
    
    
    
    
    public static void main(String args[]){
        
        
        Chats m1 = new Chats();
        m1.setSize(450, 700);
        m1.setVisible(true);
        
        m1.sender.setUserName("User 1");
        m1.sender.setIP("127.0.0.1");
        m1.sender.setPort(8189);
        m1.sender.setIDNum(1);
        m1.receiver.setUserName("User 2");
        m1.receiver.setIP("127.0.0.1");
        m1.receiver.setPort(8190);
        m1.receiver.setIDNum(2);
        
        Server s1 = new Server();
        s1.myServer(8189, m1);
  
    }



}