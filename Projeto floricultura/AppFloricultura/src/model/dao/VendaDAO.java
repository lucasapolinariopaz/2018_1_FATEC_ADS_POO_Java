package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.beans.Cliente;
import model.beans.Venda;
import model.beans.Venda_produto;

public class VendaDAO
{
    private Connection con = null;

    public VendaDAO()
    {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean gerar(Venda venda)
    {
        String sql = "INSERT INTO Venda(data, valor, forma_pagamento, cod_cli) VALUES(?, ?, ?, ?)";
                
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, venda.getData());
            stmt.setDouble(2, venda.getValor());
            stmt.setString(3, venda.getForma_pagamento());
            stmt.setInt(4, venda.getCliente().getCod_cli());
                       
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO cadastrar (Tabela Venda) cadastrar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int consultar_pk_venda(String sql)
    {
        int pk_venda = 0;
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            pk_venda = Integer.parseInt(rs.getString("cod_venda"));
            
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO consultar_pk_venda: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return pk_venda;
    }
    
    public boolean gerar(List<Venda_produto> lista_produtos_venda)
    {
        String sql = "INSERT INTO Venda_produto(cod_venda, cod_prod, qtd_prod_venda) VALUES(?, ?, ?)";
                
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            for(int i = 0; i < lista_produtos_venda.size(); i++)
            {
                stmt.setInt(1, lista_produtos_venda.get(i).getVenda().getCod_venda());
                stmt.setInt(2, lista_produtos_venda.get(i).getProduto().getCod_prod());
                stmt.setInt(3, lista_produtos_venda.get(i).getQtd_prod_venda());
                
                stmt.executeUpdate();
            }
           
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO cadastrar (Tabela Venda_produto) cadastrar: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Venda> consultar_tabela(String sql)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Venda> consulta_venda = new ArrayList<>();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Venda venda = new Venda();
                
                venda.setData(rs.getString("data_venda"));
                venda.setValor(Double.parseDouble(rs.getString("valor_venda")));
                
                Cliente cliente = new Cliente();
                
                cliente.setNome(rs.getString("nome_cliente"));
                
                venda.setCliente(cliente);
                
                consulta_venda.add(venda);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO consultar_tabela: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_venda;
    }
    
    public Venda consultar_venda(String sql)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Venda consulta_venda = new Venda();
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            consulta_venda.setCod_venda(Integer.parseInt(rs.getString("cod_venda")));
            consulta_venda.setData(rs.getString("data"));
            consulta_venda.setValor(Double.parseDouble(rs.getString("valor")));
            consulta_venda.setForma_pagamento(rs.getString("forma_pagamento"));
            
            Cliente cliente_consulta = new Cliente();
            
            cliente_consulta.setCod_cli(Integer.parseInt(rs.getString("cod_cli")));
            
            consulta_venda.setCliente(cliente_consulta);
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO consultar_venda: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_venda;
    }
    
    /*
    public List<Venda_produto> consultar_prod_venda(String sql)
    {
        
    }
    */
}
