package com.console;

import com.business.Acesso;
import com.comum.DadosUsuario;
import com.comum.Entidade;
import com.dao.acesso.DadosUsuarioMySQLDAO;
import com.dao.acesso.UsuarioMySQLDAO;
import com.comum.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try{
            while (ExibeLogin()){
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static Boolean ExibeLogin() throws SQLException {
        Usuario user = new Usuario();
        Scanner scan = new Scanner(System.in);
        System.out.println("**** LOGIN ****");
        System.out.println("Digite seu usuário");
        user.setLogin(scan.nextLine());
        System.out.println("Digite sua senha");
        user.setSenha(scan.nextLine());


        if (Acesso.validaLogin(user)){

            System.out.println("Sucesso");

            for (Object d:
                    Acesso.listaDadosUsuario()) {
                DadosUsuario dt = (DadosUsuario) d;
                System.out.println("Id: " + dt.getId());
                System.out.println("Nome: "+dt.getNome());
                System.out.println("Sobrenome: " + dt.getSobrenome());
                System.out.println("Numero teste: " + dt.getNumteste());
            }

            return true;
        }
        else{
            System.out.println("Login inválido");
        }
        return false;
    }
}
