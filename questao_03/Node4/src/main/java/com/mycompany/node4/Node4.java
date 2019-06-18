/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node4 {

    public static void main(String[] args) throws IOException {
        int resposta = 0;
        ServerSocket serverSocket = new ServerSocket(9704);
        System.out.println("node 4 iniciado ...");
        while (true) {

            try {
                Socket socket = serverSocket.accept();

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ArrayList array = (ArrayList) input.readObject();

                int x = (int) array.get(0);
                int y = (int) array.get(1);
                //op1 realiza a soma
                if (array.get(2).equals("op1")) {
                    resposta = x + y;
                    //op2 realiza a subtracao
                } else if (array.get(2).equals("op2")) {
                    resposta = x - y;
                }
                
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeInt(resposta);
                outputStream.flush();

                input.close();
                outputStream.close();
            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();

            }
        }
    }
}
