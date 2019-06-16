/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node3.q;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node3 {

    public static void main(String[] args) throws IOException {
        ArrayList array = new ArrayList();
        
         ServerSocket server = new ServerSocket(10999);
        try {
            while (true) {

               
                System.out.println("node 3 iniciado ...");

                Socket socket = server.accept();

                ObjectInputStream inputStreamServer = new ObjectInputStream(socket.getInputStream());
                array = (ArrayList) inputStreamServer.readObject();

                Integer result = 0;

                Socket socket2 = null;

                if (array.get(2).equals("op2")) {
                    socket2 = new Socket("localhost", 9704);

                } else {
                    socket2 = new Socket("localhost", 9702);

                }

                int resposta = requestReply(socket2, array);

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeInt(resposta);

                output.flush();
            }
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    private static int requestReply(Socket socket, ArrayList array) throws ClassNotFoundException, IOException {

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(array);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        Integer resposta = inputStream.readInt();

        output.close();
        inputStream.close();

        return resposta;

    }

}
