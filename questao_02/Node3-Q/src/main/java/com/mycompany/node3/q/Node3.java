/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node3.q;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node3 {

    private static Socket socket;
    private static ArrayList array = new ArrayList(2);
    private static Socket socket2;
    private static DataOutputStream output;
    private static ObjectInputStream inputStream;

    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(9701);

        //try{  
        while (true) {

            socket = serverSocket.accept();

            inputStream = new ObjectInputStream(socket.getInputStream());
            array = (ArrayList) inputStream.readObject();

            if (array.get(0).equals("op2")) {
                int x = (int) array.get(1);
                int y = (int) array.get(2);
                int resultado = (2 * x) / y;
                System.out.println(resultado);
                
                if(socket.getInetAddress().equals("10.0.0.1")){
                output = new DataOutputStream(socket.getOutputStream());
                output.writeInt(resultado);
                output.writeUTF("ok");
                }
                
                else{
                  output = new DataOutputStream(socket.getOutputStream());
                    output.writeInt(resultado);
                    System.out.println("dados" +resultado);
                    output.close();
                }
               
                   
            
              
            } else if (array.get(0).equals("op1")) {
                socket2 = new Socket("localhost", 10999);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket2.getOutputStream());
                outputStream.writeObject(array);
                socket2.close();
            }

        }
    }
}
