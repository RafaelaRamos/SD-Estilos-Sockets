/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.node1.q;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public class Node1 {

    public static void main(String[] args) {
        try  {
            Socket socket =null ;
            ArrayList array = new ArrayList(2);
            array.add(2);
            array.add(4);
            array.add("op1");
            
            
            if(array.get(2).equals("op1")){
             socket  = new Socket("localhost", 10999);
            }
            else{
            socket  = new Socket("localhost", 9702);
           }
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(array);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            int resposta = inputStream.readInt();

            System.out.println(resposta);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
