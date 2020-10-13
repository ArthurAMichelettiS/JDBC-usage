package com.business;

import com.comum.DadosUsuario;
import com.comum.Usuario;
import com.dao.acesso.DadosUsuarioMySQLDAO;
import com.dao.acesso.UsuarioMySQLDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Acesso {

    public static boolean validaLogin(Usuario u) throws SQLException {
        UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
        Usuario encontrado = (Usuario) dao.localiza(u.getLogin());
        return true;
    }

    public static Object[] listaDadosUsuario() throws SQLException {
        DadosUsuarioMySQLDAO dados = new DadosUsuarioMySQLDAO();
        return dados.lista().toArray();
    }
}
