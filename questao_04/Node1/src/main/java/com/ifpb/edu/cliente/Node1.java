/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Cliente
 */
public class Node1 {

    public static long inicio;
    public static long fim;

    public static void main(String[] args) throws IOException, ConnectException, SocketException {
        try {
            Socket socket = null;
            
            inicio = System.currentTimeMillis();
            
          
            for (int i = 0; i < 1000; i++) {

                socket = new Socket("localhost", 10999);
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                //Nome do usuario a ser inserido
                String usuario = "Usuario" + i;
                output.writeUTF(usuario);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                boolean resposta = input.readBoolean();
                System.out.println(resposta);

            }
            //tempo de execução em segundos
            fim = (System.currentTimeMillis() - inicio) / 1000;
            System.out.println(fim);

        } catch (SocketException e) {
            fim = (System.currentTimeMillis() - inicio) / 1000;
            System.out.println(fim + "segundos");

            System.out.println("conexão caiu");
        }

    }
}
