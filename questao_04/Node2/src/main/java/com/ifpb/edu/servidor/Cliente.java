/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.servidor;

import com.ifpb.edu.usuario.UsuarioPersiste;
import com.ifpb.edu.usuario.Usuario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliente
 */
public class Cliente extends Thread  {

    private final Socket socket;
    private Usuario u = new Usuario();
    private UsuarioPersiste dao = new UsuarioPersiste();
    private String nome;

    public Cliente(Socket socket) {

        this.socket = socket;
    }

   
    @Override
    public void run() {
        DataInputStream input = null;
         DataOutputStream output = null;
         boolean resposta = false;
        while (true) {
            
            
           
            try {
                input = new DataInputStream(socket.getInputStream());
                nome = input.readUTF();
                u.setNome(nome);
                dao.insertMySql(u);
                dao.insertPost(u);
                resposta = true;
                output=new DataOutputStream(socket.getOutputStream());
                output.writeBoolean(resposta);
            } catch (IOException | SQLException | ClassNotFoundException ex) {
            }
             
                finally{
            if(socket == null){
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
                
           

        }
    }
}

    


