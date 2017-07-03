/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thiago.carros.util;

import br.com.thiago.carros.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thiago
 */
public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/sistema-carros";
    private static final String USUARIO = "root";
    private static final String SENHA = "tsp180292";

    public static Connection getConexao() throws ErroSistema{
        if(conexao == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO,USUARIO,SENHA);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("O drive do Banco de Dados não foi encontrado!", ex);
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possivel conectar com o Banco de Dados!", ex);
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar a conexão do banco de dados!", ex);
            }
        }
    }
    
    
    
}
