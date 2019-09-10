package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.beans.Produto;

public class ProdutoDAO
{
    private Connection con = null;

    public ProdutoDAO()
    {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean cadastrar(Produto produto)
    {
        String sql = "INSERT INTO Produto(nome, preco, categoria, quantidade) VALUES(?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidade());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ProdutoDAO cadastrar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Produto> consutar_tabela(String sql)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> consulta_produto = new ArrayList<>();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Produto produto = new Produto();
                
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setPreco(Double.parseDouble(rs.getString("preco")));
                
                consulta_produto.add(produto);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ProdutoDAO consultar_tabela: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_produto;
    }
    
    public Produto consultar(String sql)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Produto consulta_produto = new Produto();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                consulta_produto.setCod_prod(Integer.parseInt(rs.getString("cod_prod")));
                consulta_produto.setNome(rs.getString("nome"));
                consulta_produto.setPreco(Double.parseDouble(rs.getString("preco")));
                consulta_produto.setCategoria(rs.getString("categoria"));
                consulta_produto.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ProdutoDAO consultar: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_produto;
    }
    
    public boolean alterar(Produto produto)
    {
        String sql = "UPDATE Produto SET nome = ?, preco = ?, categoria = ?, quantidade = ? WHERE cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getCategoria());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ProdutoDAO alterar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Produto produto)
    {
        String sql = "DELETE FROM Produto WHERE cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
        
            stmt.setInt(1, produto.getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro ProdutoDAO excluir: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
