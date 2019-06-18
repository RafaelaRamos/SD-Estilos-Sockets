/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Client {
    
    private static final ArrayList array = new ArrayList(2);

    private static Socket socket;

    public static void main(String[] args) throws IOException, SocketException {
        //tipo de operacao
        array.add(0, "op1");
        //x
        array.add(1, 10);
        //y
        array.add(2, 10);
// Caso ocorra erro ao se conectar com o node 1 na porta 10999 ira solicitar requisicao no node 3 porta 9701
        try {

            socket = new Socket("localhost", 10999);

            requestReply(socket, array);
        } catch (ConnectException e) {
            
            socket = new Socket("localhost", 9701);

            requestReply(socket, array);

        } catch (SocketException s) {
            s.printStackTrace();

        }

    }
    //recebe requisicao |resposta
    public static void requestReply(Socket socket, ArrayList array) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(array);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        int resposta = input.readInt();
        System.out.println(resposta);

    }
}
