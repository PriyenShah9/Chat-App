
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
 * Client class for sending objects to server
 */


public class Client {

    Message mc = new Message();
          
    
    public void myClient(String address, int Port){
        try{
            
            Socket s = new Socket(address ,Port);

            try {

                OutputStream outStream = s.getOutputStream();
                PrintWriter out = new PrintWriter(outStream, true);

                out.println(mc.getMessage());

            }
            finally {
                
                s.close();
                
                
            }
        }
        catch(IOException ioexc) {
            ioexc.printStackTrace();
        }
        
    }

     //end public
} //end class    

