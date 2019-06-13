/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Cliente
 */
public class NewMain {

    static Random random = new Random();

    public static void main(String[] args) throws IOException, UnknownHostException{
               
        Socket socket = new Socket("localhost", 9701);
        //numeros aleatorios de 0 a 100
        int numero1 = random.nextInt(100);
        int numero2 = random.nextInt(100);
        System.out.printf(" numeros enviados:%d,%d ", numero1, numero2);
        //enviar para o node 2
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeInt(numero1);
            output.writeInt(numero2);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            int resposta = input.readInt();
            System.out.println("Resposta:" + resposta);

        } catch (IOException e) {
            System.out.println("ERRO!!!");
        }
        
        finally {
            socket.close();

        }
         
       
    }

}
