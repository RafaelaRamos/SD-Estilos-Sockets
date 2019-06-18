/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node1.q;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node1 {

    /**
     * @param args the command line arguments
     */
    private static Socket socket;

    private static ArrayList array = new ArrayList();

    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException {

        
        //So responde operacao do tipo 1
        ServerSocket serverSocket = new ServerSocket(10999);
        int resposta =0;
        System.out.println("NODE 1 PRONTO...");

        while (true) {
            try {
                socket = serverSocket.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                array = (ArrayList) inputStream.readObject();

                if (array.get(0).equals("op1")) {
                    int y = (int) array.get(1);
                    int x = (int) array.get(2);
                    resposta = 2 * y * x;
                    

                } else if (array.get(0).equals("op2")) {
                   
                    socket = new Socket("localhost", 9701);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(array);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    resposta =input.readInt();
                }
                
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    output.writeInt(resposta);
                
                
            } catch (IOException e) {
            }
            socket.close();
        }

    }

}
