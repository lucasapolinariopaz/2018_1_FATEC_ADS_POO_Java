package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.beans.Cliente;
import model.beans.Produto;
import model.beans.Venda;
import model.beans.Venda_produto;

public class VendaDAO
{
    private Connection con = null;

    public VendaDAO()
    {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean debitar_estoque(Venda_produto venda_produto)
    {
        con = ConnectionFactory.getConnection();
        
        String sql = "UPDATE Produto SET quantidade = ? WHERE cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_produto.getProduto().getQuantidade() - venda_produto.getQtd_prod_venda());
            stmt.setInt(2, venda_produto.getProduto().getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO debitar_estoque: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean repor_estoque(Venda_produto venda_produto)
    {
        con = ConnectionFactory.getConnection();
        
        String sql = "UPDATE Produto SET quantidade = ? WHERE cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_produto.getProduto().getQuantidade() + venda_produto.getQtd_prod_venda());
            stmt.setInt(2, venda_produto.getProduto().getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO repor_estoque: " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
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
            System.err.println("Erro VendaDAO gerar (Tabela Venda): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public int consultar_pk_venda(Venda venda)
    {
        con = ConnectionFactory.getConnection();
        
        int pk_venda = 0;
        
        String sql = "SELECT cod_venda FROM Venda WHERE data = ? AND valor = ? AND forma_pagamento = ? AND cod_cli = ?";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, venda.getData());
            stmt.setDouble(2, venda.getValor());
            stmt.setString(3, venda.getForma_pagamento());
            stmt.setInt(4, venda.getCliente().getCod_cli());
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                pk_venda = Integer.parseInt(rs.getString("cod_venda"));                
            }
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
    
    public boolean gerar(Venda_produto venda_produto)
    {
        con = ConnectionFactory.getConnection();
                
        String sql = "INSERT INTO Venda_produto(cod_venda, cod_prod, qtd_prod_venda) VALUES(?, ?, ?)";
                
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_produto.getVenda().getCod_venda());
            stmt.setInt(2, venda_produto.getProduto().getCod_prod());
            stmt.setInt(3, venda_produto.getQtd_prod_venda());
                
            stmt.executeUpdate();
           
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO gerar (Tabela Venda_produto): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean gerar(Venda venda, List<Venda_produto> lista_produtos_venda)
    {       
        boolean result;
        int pk_venda;
        
        result = gerar(venda);
        
        if(result == true)
        {
            pk_venda = consultar_pk_venda(venda);
            venda.setCod_venda(pk_venda);
            
            for(int i = 0; i < lista_produtos_venda.size(); i++)
            {
                lista_produtos_venda.get(i).setVenda(venda);
                result = gerar(lista_produtos_venda.get(i));
                
                if(result == false)
                    break;
                else
                {
                    result = debitar_estoque(lista_produtos_venda.get(i));
                    
                    if(result == false)
                        break;
                }   
            }
        }
        
        return result;
    }   
    
    public List<Venda> consultar_PD(String sql)
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
            System.err.println("Erro VendaDAO consultar_PD: " + ex);
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
            
            
            while(rs.next())
            {
                consulta_venda.setCod_venda(Integer.parseInt(rs.getString("cod_venda")));
                consulta_venda.setData(rs.getString("data"));
                consulta_venda.setForma_pagamento(rs.getString("forma_pagamento"));
                
                Cliente cliente_consulta = new Cliente();
            
                cliente_consulta.setCod_cli(Integer.parseInt(rs.getString("cod_cli")));
                cliente_consulta.setNome(rs.getString("nome"));
                cliente_consulta.setCpf(rs.getString("cpf"));
            
                consulta_venda.setCliente(cliente_consulta);
            }        
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
    
    public List<Venda_produto> consultar_lista_prod_venda(String sql)
    {
        List<Venda_produto> consulta_produtos_venda = new ArrayList<>();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Venda_produto venda_produto = new Venda_produto();
                venda_produto.setQtd_prod_venda(Integer.parseInt(rs.getString("qtd_prod_venda")));
                
                Produto produto = new Produto();
                produto.setCod_prod(Integer.parseInt(rs.getString("cod_prod")));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(Double.parseDouble(rs.getString("preco")));
                venda_produto.setProduto(produto);
                
                Venda venda = new Venda();
                venda.setCod_venda(Integer.parseInt(rs.getString("cod_venda")));
                venda_produto.setVenda(venda);
                
                consulta_produtos_venda.add(venda_produto);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO consultar_prod_venda: " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return consulta_produtos_venda;
    }
    
    public boolean alterar(Venda venda)
    {
        String sql = "UPDATE Venda SET data = ?, valor = ?, forma_pagamento = ?, cod_cli = ? WHERE cod_venda = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, venda.getData());
            stmt.setDouble(2, venda.getValor());
            stmt.setString(3, venda.getForma_pagamento());
            stmt.setInt(4, venda.getCliente().getCod_cli());
            stmt.setInt(5, venda.getCod_venda());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO alterar (Tabela Venda): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean alterar(Venda_produto venda_produto)
    {
        con = ConnectionFactory.getConnection();
        
        String sql = "UPDATE Venda_produto SET qtd_prod_venda = ? WHERE cod_venda = ? AND cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_produto.getQtd_prod_venda());
            stmt.setInt(2, venda_produto.getVenda().getCod_venda());
            stmt.setInt(3, venda_produto.getProduto().getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO alterar (Tabela Venda_produto): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Venda_produto venda_produto)
    {
        con = ConnectionFactory.getConnection();
        
        String sql = "DELETE FROM Venda_produto WHERE cod_venda = ? AND cod_prod = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda_produto.getVenda().getCod_venda());
            stmt.setInt(2, venda_produto.getProduto().getCod_prod());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO excluir (Tabela Venda_produto): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean alterar(List<Venda_produto> lista_PV_antiga, List<Venda_produto> lista_PV_atualizada)
    {
        boolean result = false, compara = false;
        int i, j;
        
        //Varredura Lista de produtos antiga
        for(i = 0; i < lista_PV_antiga.size(); i++)
        {            
            for(j = 0; j < lista_PV_atualizada.size(); j++)
            {
                compara = ((lista_PV_antiga.get(i).getVenda().getCod_venda() == 
                        lista_PV_atualizada.get(j).getVenda().getCod_venda()) && 
                        (lista_PV_antiga.get(i).getProduto().getCod_prod() == 
                        lista_PV_atualizada.get(j).getProduto().getCod_prod()));
                        
                if(compara == true)
                    break;
            }
            
            if(compara == true)
            {
                result = alterar(lista_PV_atualizada.get(j));
                
                if(result == false)
                    break;
                else
                {
                    result = repor_estoque(lista_PV_antiga.get(i));
                    
                    if(result == false)
                        break;
                    else
                    {
                        result = debitar_estoque(lista_PV_atualizada.get(j));
                        
                        if(result == false)
                            break;
                    }                    
                }
            }
            else
            {
                result = excluir(lista_PV_antiga.get(i));
                
                if(result == false)
                    break;
                else
                {
                    result = repor_estoque(lista_PV_antiga.get(i));
                    
                    if(result == false)
                        break;
                }
            }  
        }
        
        if(result == true)
        {
            //Varredura Lista de produtos atualizada
            for(i = 0; i < lista_PV_atualizada.size(); i++)
            {            
                for(j = 0; j < lista_PV_antiga.size(); j++)
                {
                    compara = ((lista_PV_atualizada.get(i).getVenda().getCod_venda() == 
                            lista_PV_antiga.get(j).getVenda().getCod_venda()) && 
                            (lista_PV_atualizada.get(i).getProduto().getCod_prod() == 
                            lista_PV_antiga.get(j).getProduto().getCod_prod()));
                
                    if(compara == true)
                        break;
                }
            
                if(compara == false)
                {
                    result = gerar(lista_PV_atualizada.get(i));
                    
                    if(result == false)
                        break;
                    else
                    {
                        result = debitar_estoque(lista_PV_atualizada.get(i));
                        
                        if(result == false)
                            break;
                    }
                }
            }                  
        }
        
        return result; 
    }
    
    public boolean alterar(Venda venda, List<Venda_produto> lista_PV_antiga, List<Venda_produto> lista_PV_atualizada)
    {
        boolean result = alterar(venda);
        
        if(result == true)
            result = alterar(lista_PV_antiga, lista_PV_atualizada);
                
        return result;
    }
    
    public boolean excluir(List<Venda_produto> lista_produtos_venda)
    {
        boolean result = false;
        
        for(int i = 0; i < lista_produtos_venda.size(); i++)
        {
            result = excluir(lista_produtos_venda.get(i));
              
            if(result == false)
                break;
            else
            {
                result = repor_estoque(lista_produtos_venda.get(i));
                  
                if(result == false)
                    break;
            }   
        }
        
        return result;
    }
    
    public boolean excluir(Venda venda)
    {
        con = ConnectionFactory.getConnection();
        
        String sql = "DELETE FROM Venda WHERE cod_venda = ?";
        
        PreparedStatement stmt = null;
        
        try 
        {
            stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, venda.getCod_venda());
            
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex)
        {
            System.err.println("Erro VendaDAO excluir (Tabela Venda): " + ex);
            return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Venda venda, List<Venda_produto> lista_PV_antiga)
    {
        boolean result = excluir(lista_PV_antiga);
        
        if(result == true)
            excluir(venda);
        
        return result;
    }
}
