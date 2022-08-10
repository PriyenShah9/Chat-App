
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
 * Chats class create a GUI for the chatroom and objects and the methods required for functinality
 */


public class Chats extends JFrame implements ActionListener, MouseListener {
  

    JPanel pnlContain;
    JTextField txtMessage;
    JTextArea txtConversation;
    JLabel lblMessage, lblConversation;
    JButton btnSend, button3, button2, button1;
    String time;
    JMenu File;
    JMenuItem sendmess, changeuser, clearmess;
    JMenuBar menubar;
    JToolBar toolbar;
    
    
            
    
    Client c = new Client();  //instantiate client
    
    Message m = new Message();
    
    
    Person sender = new Person();
    Person receiver = new Person();
    public Chats() {
        

        Container cp = getContentPane();
        
        
        lblMessage = new JLabel("Message: ");
        txtMessage = new JTextField(25);
        
        txtMessage.addKeyListener(new KeyAdapter() {
            
         public void keyTyped(KeyEvent ke) {
            String value = txtMessage.getText();
            int l = value.length();
            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE
                    || ke.getKeyChar()== KeyEvent.VK_DELETE || ke.getKeyChar()== KeyEvent.VK_SPACE || (Character.isLowerCase(ke.getKeyChar()))){
               txtMessage.setEditable(true);
               
            } 
            
            else {
               txtMessage.setEditable(false);
               //btnSend.requestFocusInWindow();
               
            }
         }
      });
        
        lblConversation = new JLabel("Chatbox:");
        txtConversation = new JTextArea(30,30);
        
        txtConversation.addMouseListener(this);
        

        btnSend = new JButton("Send");
        btnSend.addActionListener(this);
        btnSend.addMouseListener(this);
        
        
        menubar = new JMenuBar();
        File = new JMenu("File");
        sendmess = new JMenuItem("Send Message");
        changeuser = new JMenuItem("Change User");
        clearmess = new JMenuItem("Clear Message");
        //clearmess.addActionListener(this);
        
        File.add(sendmess);
        File.add(changeuser);
        File.add(clearmess);
        menubar.add(File);
        
        //JToolBar tool = new JToolBar();
        toolbar = new JToolBar();  
        toolbar.setRollover(true);  
        button1 = new JButton("Send Message");  
        toolbar.add(button1);
        button1.addActionListener(this);
        button2 = new JButton("Change User");  
        toolbar.add(button2);
        button2.addActionListener(this);
        button3 = new JButton("Clear Message");  
        toolbar.add(button3);
        button3.addActionListener(this);
        //toolbar.add(new JComboBox(new String[] { "Opt-1", "Opt-2", "Opt-3", "Opt-4" }));  
        
        
        
        pnlContain = new JPanel();
        pnlContain.add(toolbar);
        pnlContain.add(menubar);
        
        pnlContain.add(lblMessage);
        pnlContain.add(txtMessage);
        pnlContain.add(lblConversation);
        pnlContain.add(txtConversation);
        pnlContain.add(btnSend);
        cp.add(pnlContain);
        txtConversation.append("Please begin the chat \n");

    }
    
    /**
     *
     * @param e
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnSend){   
            timestamp();

            m.setMessage(txtMessage.getText());
            if("END".equals(m.getMessage())){
                txtConversation.append("Program has ended");
                System.exit(0);
            }
            
            txtConversation.append("\n"+"("+time + ") " +sender.getUserName()+ ": " +txtMessage.getText() + "\n");
            sendMessage("("+time + ") " +sender.getUserName()+ ": " +txtMessage.getText() + "\n");
            txtMessage.setText("");
            txtMessage.requestFocusInWindow();

        }
        
        
        if (e.getSource() == button3) {
            
            txtMessage.setText("");
            
        }
        if (e.getSource() == button2) {
            
            sender.setUserName("New user");
            
        }
        if (e.getSource() == button1){   
            timestamp();

            m.setMessage(txtMessage.getText());
            if("END".equals(m.getMessage())){
                txtConversation.append("Program has ended");
                System.exit(0);
            }
            
            txtConversation.append("\n"+"("+time + ") " +sender.getUserName()+ ": " +txtMessage.getText() + "\n");
            sendMessage("("+time + ") " +sender.getUserName()+ ": " +txtMessage.getText() + "\n");
            txtMessage.setText("");
            txtMessage.requestFocusInWindow();

        }
        
    }
    
    

    public void sendMessage (String messages){
        

        c.mc.setMessage(messages);
        
        c.myClient(receiver.getIP(), receiver.getPort());
        
        
    }
    
    public void timestamp() {
        
        try {
            
            
            
            
            Socket ts = new Socket("time-A.timefreq.bldrdoc.gov", 13);
            
            try {
            
                InputStream inStream = ts.getInputStream();
                Scanner in = new Scanner(inStream);
                String line = in.nextLine();
                while(in.hasNextLine()){
                    line = in.nextLine();
                    System.out.println(line);
                    String line2 = line.substring(5, 23);
                    time = line2; 
                } 
            } finally{
                ts.close();
            }
            

            
        } catch(IOException ioexc){
            ioexc.printStackTrace();
        }
        

        
    }

    public static void main(String[] args) {

        
        
        Chats m2 = new Chats();
        m2.setSize(400, 700);
        m2.setVisible(true);
        
        m2.sender.setUserName("User 2");
        m2.sender.setIP("127.0.0.1");
        m2.sender.setPort(8190);
        m2.sender.setIDNum(2);
        m2.receiver.setUserName("User 1");
        m2.receiver.setIP("127.0.0.1");
        m2.receiver.setPort(8189);
        m2.receiver.setIDNum(1);
        
        Server s = new Server();
        s.myServer(8190, m2);
        
        


    }

    @Override
    public void mouseClicked(MouseEvent e) {
         
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == txtConversation){
            txtConversation.setBackground(Color.GREEN);
        }
        if (e.getSource() == btnSend){
            txtMessage.requestFocus();
                
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        txtConversation.setBackground(Color.GRAY);
        
    }

    
    
}
