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

        ServerSocket serverSocket = new ServerSocket(10999);
        System.out.println("PRONTO...");

        while (true) {
            try {
                socket = serverSocket.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                array = (ArrayList) inputStream.readObject();

                if (array.get(0).equals("op1")) {
                    int y = (int) array.get(1);
                    int x = (int) array.get(2);
                    int resultado = 2 * y * x;
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    output.writeInt(resultado);

                } else if (array.get(0).equals("op2")) {
                    InetAddress addr = InetAddress.getByName("10.0.0.1");
                    socket = new Socket(addr, 9701);
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(array);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    String confirm = input.readUTF();
                    System.out.println(confirm);

                }
            } catch (IOException e) {
            }
            socket.close();
        }

    }

}
