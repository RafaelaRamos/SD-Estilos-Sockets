/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Cliente
 */
public class NewMain {

    private static Socket socket;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9701);

        Socket socket2 = new Socket("localhost", 10999);
        System.out.println("pronto ... ");

        try {
            while (true) {
                //recuperação de requisicao do node1
                socket = serverSocket.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                int numero = input.readInt();
                int numero2 = input.readInt();

                System.out.println("numero recuperado" + numero2);
                System.out.println("numero recuperado" + numero);
                //Verificando se os numeros são iguais
                //caso os numeros são iguais retorna 0 para o node1
                if (numero == numero2) {
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    output.writeInt(0);
                } else {
                    //Numeros diferentes envia para o node 3

                    DataOutputStream output = new DataOutputStream(socket2.getOutputStream());
                    output.writeInt(numero);
                    output.writeInt(numero2);

                    //recebe a resposta do node 3
                    DataInputStream in = new DataInputStream(socket2.getInputStream());
                    int resposta = in.readInt();
                    System.out.println("Resposta" + resposta);

                    //  enviar para o node 1
                    output = new DataOutputStream(socket.getOutputStream());
                    output.writeInt(resposta);

                }

            }
        } catch (IOException e) {
            System.out.println("Erro inesperado");
        } finally {
            if (socket != null && socket2 != null) {
                socket.close();
            }
            socket2.close();
        }
    }
}
