/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Cliente
 */
public class UsuarioPersiste {

//Inserir no MySql
    public void insertMySql(Usuario u) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sd?useSSL=false&useTimezone=true&serverTimezone=UTC",
                "root", "secret");
        
        String sql = "INSERT INTO usuario (nome) VALUES (?)";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, u.getNome());
        stm.executeUpdate();
        stm.close();
        connection.close();
    }

    //Inserir no postgreSQL
    public void insertPost(Usuario u) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/sd",
                "postgres", "secret");
        
        String sql = "INSERT INTO usuario (nome) VALUES (?)";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setString(1, u.getNome());
        stm.executeUpdate();
        stm.close();
        connection.close();
    }

}
