/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.servidor;

import com.ifpb.edu.usuario.Usuario;
import com.ifpb.edu.usuario.UsuarioPersiste;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Cliente
 */
public class NodeThread {
    
    //Tratar requisições usando Threads
  private static Socket socket =null;
    public static void main(String[] args) throws IOException {
       
        ServerSocket server = new ServerSocket(10999);
        System.out.println("Aguardando requisições ...");
        
       try{ while (true) {
           
           socket = server.accept();
           //cria uma thread para cada requisição
           Cliente c =  new Cliente(socket);
           c.start();

        }}
       catch(SocketException ex){
       socket.close();
       }

       
    }
}


