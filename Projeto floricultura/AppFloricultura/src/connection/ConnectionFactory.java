package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory
{
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/BDFloricultura";
    private static final String USER = "FLORICULTURA";
    private static final String PASS = "floricultura";
    
    public static Connection getConnection()
    {
        try
        {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            //ex.printStackTrace();
            //System.exit(1);           
            throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void closeConnection(Connection con)
    {
        if(con != null)
        {
            try
            {
                con.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt)
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
        
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
        
        closeConnection(con, stmt);
    }
    
    public static void closeConnection(PreparedStatement stmt, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
        
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
    }
    
    public static void closeConnection(PreparedStatement stmt)
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
    }
}
