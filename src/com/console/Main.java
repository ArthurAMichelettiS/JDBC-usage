package com.console;

import com.comum.Entidade;
import com.dao.acesso.DadosUsuarioMySQLDAO;
import com.dao.acesso.UsuarioMySQLDAO;
import com.comum.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
        DadosUsuarioMySQLDAO dados = new DadosUsuarioMySQLDAO();
        try{
            Usuario user = new Usuario();
            Scanner scan = new Scanner(System.in);
            System.out.println("**** LOGIN ****");
            System.out.println("Digite seu usuário");
            user.setLogin(scan.nextLine());
            System.out.println("Digite sua senha");
            user.setSenha(scan.nextLine());

            user = (Usuario) dao.localiza(user.getLogin());

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }

        try{
            Usuario user = new Usuario();
            Scanner scan = new Scanner(System.in);
            System.out.println("**** LOGIN ****");
            System.out.println("Digite seu usuário");
            user.setLogin(scan.nextLine());
            System.out.println("Digite sua senha");
            user.setSenha(scan.nextLine());

            System.out.println(dados.localiza(user.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
