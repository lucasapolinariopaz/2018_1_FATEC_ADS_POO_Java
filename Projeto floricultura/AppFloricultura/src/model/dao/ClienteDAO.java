package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.beans.Cliente;
import model.beans.Cliente_CD;

public class ClienteDAO
{
    private Connection con = null;

    public ClienteDAO()
    {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean cadastrar(Cliente cliente)
    {
        String sql = "INSERT INTO Cliente(nome, rg, cpf, endereco, num_endereco, cidade, uf, telefone, celular, email) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getNum_endereco());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getUf());
            stmt.setString(8, cliente.getTelefone());
            stmt.setString(9, cliente.getCelular());
            stmt.setString(10, cliente.getEmail());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente_CD> consultar_tabela()
    {
        String sql = "SELECT * FROM Cliente WHERE nome = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente_CD> consulta_cliente = new ArrayList<>();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Cliente_CD cliente = new Cliente_CD();
                
                cliente.setNome(rs.getString("nome"));
                cliente.setRg("rg");
                cliente.setCpf(rs.getString("cpf"));
                consulta_cliente.add(cliente);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_cliente;
    }
    
    public List<Cliente> consultar()
    {
        String sql = "SELECT * FROM Cliente WHERE nome = ? AND rg = ? AND cpf = ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> consulta_cliente = new ArrayList<>();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Cliente cliente = new Cliente();
                
                cliente.setCod_cli(rs.getInt("cod_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg("rg");
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNum_endereco(rs.getInt("num_endereco"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular("celular");
                cliente.setEmail("email");
                consulta_cliente.add(cliente);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_cliente;
    }
    
    public boolean alterar(Cliente cliente)
    {
        String sql = "UPDATE Cliente SET nome = ?, rg = ?, cpf = ?, endereco = ?, num_endereco = ?, cidade = ?, "
                + "uf = ?, telefone = ?, celular = ?, email = ? WHERE = cod_cli = ?;";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getNum_endereco());
            stmt.setString(6, cliente.getCidade());
            stmt.setString(7, cliente.getUf());
            stmt.setString(8, cliente.getTelefone());
            stmt.setString(9, cliente.getCelular());
            stmt.setString(10, cliente.getEmail());
            stmt.setInt(11, cliente.getCod_cli());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Cliente cliente)
    {
        String sql = "DELETE FROM Cliente WHERE cod_cli = ?;";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getCod_cli());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
