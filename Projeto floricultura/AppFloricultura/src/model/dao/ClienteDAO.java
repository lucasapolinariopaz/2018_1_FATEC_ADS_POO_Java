package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.beans.Cliente;

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
            System.err.println("Erro ClienteDAO cadastrar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> consultar_tabela(String sql)
    {
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
                
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                
                consulta_cliente.add(cliente);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ClienteDAO consultar_tabela: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_cliente;
    }
    
    public Cliente consultar(String sql)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Cliente consulta_cliente = new Cliente();
        
        try 
        {            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                consulta_cliente.setCod_cli(Integer.parseInt(rs.getString("cod_cli")));
                consulta_cliente.setNome(rs.getString("nome"));
                consulta_cliente.setRg(rs.getString("rg"));
                consulta_cliente.setCpf(rs.getString("cpf"));
                consulta_cliente.setEndereco(rs.getString("endereco"));
                consulta_cliente.setNum_endereco(Integer.parseInt(rs.getString("num_endereco")));
                consulta_cliente.setCidade(rs.getString("cidade"));
                consulta_cliente.setUf(rs.getString("uf"));
                consulta_cliente.setTelefone(rs.getString("telefone"));
                consulta_cliente.setCelular(rs.getString("celular"));
                consulta_cliente.setEmail(rs.getString("email"));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ClienteDAO consultar: " + ex);
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
                + "uf = ?, telefone = ?, celular = ?, email = ? WHERE cod_cli = ?";
        
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
            System.err.println("Erro ClienteDAO alterar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Cliente cliente)
    {
        String sql = "DELETE FROM Cliente WHERE cod_cli = ?";
        
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
            System.err.println("Erro ClienteDAO excluir: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
