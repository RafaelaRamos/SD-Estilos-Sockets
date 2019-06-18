/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node2 {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9702);
       System.out.println("node 2 iniciado ...");
        int resposta = 0;

        while (true) {

            try {

                Socket socket = server.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ArrayList array = (ArrayList) inputStream.readObject();
                Socket socket2 = null;
                //Se for do tipo op1 envia direto para o node 4
                if (array.get(2).equals("op1")) {
                    socket2 = new Socket("localhost", 9704);
                   

                } else {
                    //Se nao envia para o node 3
                    socket2 = new Socket("localhost", 10999);
                   

                }

                resposta = requestReply(socket2, array);

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeInt(resposta);
                
                output.flush();

            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Integer requestReply(Socket socket2, ArrayList array) throws ClassNotFoundException, IOException {

        ObjectOutputStream outputStream = new ObjectOutputStream(socket2.getOutputStream());
        outputStream.writeObject(array);

        DataInputStream inputStream = new DataInputStream(socket2.getInputStream());
        Integer resposta = inputStream.readInt();
       

        return resposta;
    }

}
