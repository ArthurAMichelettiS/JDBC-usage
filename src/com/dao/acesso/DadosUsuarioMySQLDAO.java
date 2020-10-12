package com.dao.acesso;

import com.comum.DadosUsuario;
import com.comum.Entidade;
import com.comum.Usuario;
import com.dao.basis.MySQLDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DadosUsuarioMySQLDAO <E extends Entidade> extends MySQLDAO
{
    public DadosUsuarioMySQLDAO() {
        super(Usuario.class);
        setTabela("tbDadosUsuario");
    }

    //atribui os campos de login e senha de uma tabela em um usuário
    @Override
    protected E preencheEntidade(ResultSet rs) {
        DadosUsuario entidade = new DadosUsuario();
        try {
            entidade.setLogin(rs.getString("Login"));
            entidade.setSenha(rs.getString("Senha"));
        } catch (SQLException ex) {
            Logger.getLogger(com.dao.acesso.UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)entidade;
    }


    @Override
    public Entidade seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    //retorna o select para localizar um login
    @Override
    protected String getLocalizaCommand(){
        return "select * from tbDadosUsuario where nome = ?";
    }
}

