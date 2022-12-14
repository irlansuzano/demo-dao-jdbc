package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    /**
     * Método responsável pela criação da conexão com o banco de dados
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() {
        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return conn;
    }

    /**
     * Método responsavel pelo carregamento das propriedades do arquivo de banco
     * para a função de conexão
     *
     * @return Properties
     * @throws IOException
     */

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Método responsável por encerrar uma conexão
     *
     */
    public static void closeConnection() {
        try {
            if (conn != null) {
            conn.close();
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement statement){
        try{
            if(statement != null){
                statement.close();
            }
        }catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
    }
}
