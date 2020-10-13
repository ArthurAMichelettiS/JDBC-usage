/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.basis;

import com.comum.Entidade;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author gabriell
 * @param <E>
 */
public abstract class MySQLDAO <E extends Entidade> extends DAO {
    final String STRING_CONEXAO = "jdbc:mysql://mysql5004.site4now.net/db_a688e3_jdbc?useTimezone=true&serverTimezone=UTC";
    final String USUARIO = "a688e3_jdbc";
    final String SENHA = "AvakinMaels10";
    private String tabela;
    
    public MySQLDAO(Class entityClass) {
        super(entityClass);
    }
    
    protected void setTabela(String value){
        tabela = value;
    }
    
    @Override
    public E seleciona(int id) {
        // Não há retorno por id
        return null;
    }

    /**
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    @Override
    public E localiza (String codigo) throws SQLException {
        E entidade = null;

        //utiliza o jdbc para estabelecer conexão com o banco de dados
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {

            //obtém o comando sql para localizar deste MySQLDAO
            String SQL = getLocalizaCommand();

            //utiliza o prepared statement contra riscos como o sql injection
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {

                //utiliza o prepared statement para atribuir o código como parâmetro do string para select
                stmt.setString(1, codigo);

                //executa o comando no banco e obtém o primeiro resultado
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.first()){
                        //atribui o resultado na entidade
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }        
        return entidade;
    }

    //para ser utilizado pelos DAOs mysql para retornar o select de localizar correspondente
    protected abstract String getLocalizaCommand();

    //para ser utilizado pelos DAOs mysql para retornar o select de listagem correspondente
    protected String getListaCommand() {
        return "select * from " + tabela;
    }

    //atribui os campos de row de uma tabela na entidade
    protected abstract E preencheEntidade(ResultSet rs);

    //retorna a lista da entidade vinda da tabela do banco
    @Override
    public ArrayList<E> lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList();

        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            System.out.println("Banco conectado!");
            // ? => binding
            String SQL = getListaCommand();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {

                    //passa entre as rows da tabela criando e preenchendo as entidades para a lista
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }

        return entidades;
      }
}
