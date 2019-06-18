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
        //So responde operacao op2
        ServerSocket serverSocket = new ServerSocket(9701);
        System.out.println("NODE 3 PRONTO...");
        //try{  
        while (true) {

            socket = serverSocket.accept();

            inputStream = new ObjectInputStream(socket.getInputStream());
            array = (ArrayList) inputStream.readObject();
            //verifica o tipo de operacao
            if (array.get(0).equals("op2")) {
                int x = (int) array.get(1);
                int y = (int) array.get(2);
                int resultado = (2 * x) / y;
                
                output = new DataOutputStream(socket.getOutputStream());
                output.writeInt(resultado);
               
                //redireciona a requisicao para o node 1 ou 2
              } else if (array.get(0).equals("op1")) {
                socket2 = new Socket("localhost", 10999);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket2.getOutputStream());
                outputStream.writeObject(array);
                socket2.close();
            }

        }
    }
}
