/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node2 {

    private static Socket socket;
    private static ArrayList array = new ArrayList(2);

    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(10999);
        System.out.println("pronto");

        try {
            while (true) {
                socket = serverSocket.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                array = (ArrayList) inputStream.readObject();
                if (array.get(0).equals("op1")) {
                    int y = (int) array.get(1);
                    int x = (int) array.get(2);
                    int resultado = 2 * y * x;

                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    output.writeInt(resultado);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            socket.close();
        }
    }
}
