/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.servidor;

import com.ifpb.edu.usuario.Usuario;
import com.ifpb.edu.usuario.UsuarioPersiste;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Cliente
 */
public class Node2 {

   

    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException, SQLException {

        Usuario u = new Usuario();
        UsuarioPersiste insert = new UsuarioPersiste();

        ServerSocket server = new ServerSocket(10999);
        System.out.println("node 2 iniciado ...");
        int count = 0;
        
        while (true) {

            try (Socket socket = server.accept()) {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String usuario = inputStream.readUTF();
                System.out.println(usuario);
                u.setNome(usuario);
                DataOutputStream output =new DataOutputStream(socket.getOutputStream());
                output.writeBoolean(true);
                
                insert.insertPost(u);
                insert.insertMySql(u);
            }
            

        }
    
    
    }
 
    }
    
   
       
    

