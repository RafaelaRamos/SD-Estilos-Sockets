/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Cliente
 */
public class Cliente {

    /**
     * @param args the command line arguments
 * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 9701)) {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            DataInputStream input = new DataInputStream(socket.getInputStream());
            int resposta = input.readInt();
            System.out.println("Resposta:" + resposta);

        } catch (IOException e) {
            System.out.println("ERRO!!!");
        }
    }
}
