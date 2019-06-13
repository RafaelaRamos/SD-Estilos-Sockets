/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Cliente
 */
public class NewMain {

    private static Socket socket;
    
    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
       
ServerSocket serverSocket = new ServerSocket(10999);
           System.out.println("Node3 pronto ... ");
        
        try {
             
            while (true) {
                //recuperação de requisicao
                socket = serverSocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                int numero = input.readInt();
                int numero2 = input.readInt();
                System.out.println(numero2);
                System.out.println(numero);

                //y^y +x^x
                int resposta = (int) ((Math.pow(numero, numero)) + (Math.pow(numero2, numero2)));
                System.out.println(resposta);
                //retorno do resultado do calculo
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeInt(resposta);
               

            }
        } catch (IOException e) {
             System.out.println("erro inesperado");

        } finally {
            if(socket != null)
            socket.close();

        }
    }
}
