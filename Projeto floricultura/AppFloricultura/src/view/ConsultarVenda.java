/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.beans.Cliente;
import model.beans.Produto;
import model.beans.Venda;
import model.beans.Venda_produto;
import model.dao.VendaDAO;

/**
 *
 * @author Gustavo Lobo
 */
public class ConsultarVenda extends javax.swing.JFrame {

    InserirCliente_ConsultarVenda tela_InserirCliente_ConsultarVenda = new InserirCliente_ConsultarVenda();
    AdicionarProduto_ConsultarVenda tela_AdicionarProduto_ConsultarVenda = new AdicionarProduto_ConsultarVenda();

    List<Venda_produto> lista_PV_antiga = new ArrayList<>();
    List<Venda_produto> lista_PV_atualizada = new ArrayList<>();

    /**
     * Creates new form RealizarVenda
     */
    public ConsultarVenda() {
        initComponents();
    }

    public void preencher_PD(String sql) {
        VendaDAO dao = new VendaDAO();

        List<Venda> consulta_venda = dao.consultar_PD(sql);

        DefaultTableModel tabela = (DefaultTableModel) tb_ConsultaVendas.getModel();
        tabela.setNumRows(0);

        consulta_venda.forEach((instancia)
                -> {
            tabela.addRow(new Object[]{
                instancia.getData(),
                instancia.getCliente().getNome(),
                instancia.getValor()
            });
        });
    }

    public void pesquisaDinamica() {
        String sql;

        if (txt_PesquisarVendaData.getText().isEmpty() && txt_PesquisarClienteNome.getText().isEmpty()) {
            sql = "SELECT v.data AS data_venda, c.nome AS nome_cliente, v.valor AS valor_venda "
                    + "FROM Venda v INNER JOIN Cliente c ON v.cod_cli = c.cod_cli "
                    + "ORDER BY v.cod_venda DESC";
        } else if (txt_PesquisarVendaData.getText().isEmpty()) {
            sql = "SELECT v.data AS data_venda, c.nome AS nome_cliente, v.valor AS valor_venda "
                    + "FROM Venda v INNER JOIN Cliente c ON v.cod_cli = c.cod_cli "
                    + "WHERE c.nome LIKE '%" + txt_PesquisarClienteNome.getText() + "%' "
                    + "ORDER BY v.cod_venda DESC";
        } else if (txt_PesquisarClienteNome.getText().isEmpty()) {
            sql = "SELECT v.data AS data_venda, c.nome AS nome_cliente, v.valor AS valor_venda "
                    + "FROM Venda v INNER JOIN Cliente c ON v.cod_cli = c.cod_cli "
                    + "WHERE v.data LIKE '%" + txt_PesquisarVendaData.getText() + "%' "
                    + "ORDER BY v.cod_venda DESC";
        } else {
            sql = "SELECT v.data AS data_venda, c.nome AS nome_cliente, v.valor AS valor_venda "
                    + "FROM Venda v INNER JOIN Cliente c ON v.cod_cli = c.cod_cli "
                    + "WHERE c.nome LIKE '%" + txt_PesquisarClienteNome.getText() + "%' AND "
                    + "v.data LIKE '%" + txt_PesquisarVendaData.getText() + "%' "
                    + "ORDER BY v.cod_venda DESC";
        }

        this.preencher_PD(sql);
    }

    public void preencherConsulta_01(String sql) {
        VendaDAO dao = new VendaDAO();

        Venda consulta_venda = dao.consultar_venda(sql);

        lbltxt_VendaCodigo.setText("" + consulta_venda.getCod_venda());
        txt_VendaData.setText(consulta_venda.getData());
        txt_VendaPagamento.setText(consulta_venda.getForma_pagamento());
        lbltxt_ClienteCodigo.setText("" + consulta_venda.getCliente().getCod_cli());
        lbltxt_ClienteNome.setText(consulta_venda.getCliente().getNome());
        lbltxt_ClienteCpf.setText(consulta_venda.getCliente().getCpf());
    }

    public void preencherTabelaProdutos(List<Venda_produto> lista_produtos_venda) {
        DefaultTableModel tabela = (DefaultTableModel) tb_ProdutosVenda.getModel();
        tabela.setNumRows(0);

        double venda_total = 0;

        for (int i = 0; i < lista_produtos_venda.size(); i++) {
            tabela.addRow(new Object[]{
                lista_produtos_venda.get(i).getProduto().getCod_prod(),
                lista_produtos_venda.get(i).getProduto().getNome(),
                lista_produtos_venda.get(i).getProduto().getPreco(),
                lista_produtos_venda.get(i).getQtd_prod_venda(),
                lista_produtos_venda.get(i).getProduto().getPreco() * lista_produtos_venda.get(i).getQtd_prod_venda()
            });

            venda_total += lista_produtos_venda.get(i).getProduto().getPreco() * (double) lista_produtos_venda.get(i).getQtd_prod_venda();
        }

        lbltxt_VendaTotal.setText(String.valueOf(venda_total));
    }

    public void preencherConsulta_02(String sql) {
        lista_PV_antiga.clear();
        lista_PV_atualizada.clear();

        int i;

        VendaDAO dao = new VendaDAO();

        lista_PV_antiga = dao.consultar_lista_prod_venda(sql);

        for (i = 0; i < lista_PV_antiga.size(); i++) {
            lista_PV_atualizada.add(lista_PV_antiga.get(i));
        }

        int qtd_estoque, qtd_prod_venda_antiga;

        for (i = 0; i < lista_PV_atualizada.size(); i++) {
            //Controle Virtual do estoque
            qtd_estoque = lista_PV_atualizada.get(i).getProduto().getQuantidade();
            qtd_prod_venda_antiga = lista_PV_atualizada.get(i).getQtd_prod_venda();
            qtd_estoque += qtd_prod_venda_antiga;

            //Infelizmente foi preciso Re-Setar a instância da classe Venda_produto na lista nessa lógica
            Venda_produto venda_produto = new Venda_produto();
            venda_produto.setQtd_prod_venda(lista_PV_atualizada.get(i).getQtd_prod_venda());

            Produto produto = new Produto();
            produto.setCod_prod(lista_PV_atualizada.get(i).getProduto().getCod_prod());
            produto.setNome(lista_PV_atualizada.get(i).getProduto().getNome());
            produto.setPreco(lista_PV_atualizada.get(i).getProduto().getPreco());
            produto.setQuantidade(qtd_estoque);
            venda_produto.setProduto(produto);

            Venda venda = new Venda();
            venda.setCod_venda(lista_PV_atualizada.get(i).getVenda().getCod_venda());
            venda_produto.setVenda(venda);

            lista_PV_atualizada.set(i, venda_produto);
        }

        this.preencherTabelaProdutos(lista_PV_atualizada);
    }

    public void limparCamposCliente() {
        lbltxt_ClienteCodigo.setText("");
        lbltxt_ClienteNome.setText("");
        lbltxt_ClienteCpf.setText("");
    }

    public void limparUmProduto() {
        int linha = tb_ProdutosVenda.getSelectedRow();

        lista_PV_atualizada.remove(linha);

        preencherTabelaProdutos(lista_PV_atualizada);
    }

    public void limparTodosCampos() {
        txt_PesquisarVendaData.setText("");
        txt_PesquisarClienteNome.setText("");
        lbltxt_VendaCodigo.setText("");
        txt_VendaData.setText("");
        txt_VendaPagamento.setText("");
        limparCamposCliente();
        lista_PV_antiga.clear();
        lista_PV_atualizada.clear();
        preencherTabelaProdutos(lista_PV_antiga);
        lbltxt_VendaTotal.setText("");
    }

    public void importarCliente(Cliente cliente) {
        lbltxt_ClienteCodigo.setText(String.valueOf(cliente.getCod_cli()));
        lbltxt_ClienteNome.setText(cliente.getNome());
        lbltxt_ClienteCpf.setText(cliente.getCpf());
    }

    public boolean isInListaProdutoAntiga(int cod_prod) {
        boolean result = false;

        for (int i = 0; i < lista_PV_antiga.size(); i++) {
            if (lista_PV_antiga.get(i).getProduto().getCod_prod() == cod_prod) {
                result = true;
                break;
            }
        }

        return result;
    }

    public int qtd_ProdutoVendaAntiga(int cod_prod) {
        int qtd_prod_venda_antiga = 0;

        for (int i = 0; i < lista_PV_antiga.size(); i++) {
            if (lista_PV_antiga.get(i).getProduto().getCod_prod() == cod_prod) {
                qtd_prod_venda_antiga = lista_PV_antiga.get(i).getQtd_prod_venda();
                break;
            }
        }

        return qtd_prod_venda_antiga;
    }

    public boolean isInListaProdutoAtualizada(Venda_produto venda_produto) {
        boolean result = false;

        for (int i = 0; i < lista_PV_atualizada.size(); i++) {
            if (lista_PV_atualizada.get(i).getProduto().getCod_prod() == venda_produto.getProduto().getCod_prod()) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void importarProduto(Venda_produto venda_produto) {
        this.lista_PV_atualizada.add(venda_produto);
        this.preencherTabelaProdutos(this.lista_PV_atualizada);
    }

    public boolean validacaoCampoVazio() {

        boolean valida = false;

        if (txt_VendaData.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data da venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_VendaPagamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a forma de pagamento da venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (lbltxt_ClienteCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o cliente que gerou a venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (lista_PV_atualizada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe os produtos que foram vendidos", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            valida = true;
        }

        return valida;
    }

    public boolean validacaoCampoTamanho() {

        boolean valida = false;

        if (txt_VendaData.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Campo data, tamanho máximo: 20 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_VendaPagamento.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Campo forma de pagamento, tamanho máximo: 50 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            valida = true;
        }

        return valida;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_VendaData = new javax.swing.JLabel();
        lbl_VendaPagamento = new javax.swing.JLabel();
        txt_VendaPagamento = new javax.swing.JTextField();
        txt_VendaData = new javax.swing.JTextField();
        btn_inserir_cliente = new javax.swing.JButton();
        btn_limpar_cliente = new javax.swing.JButton();
        lbl_ClienteNome = new javax.swing.JLabel();
        lbl_ClienteCpf = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_lista_produtos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ProdutosVenda = new javax.swing.JTable();
        btn_adicionar_produto = new javax.swing.JButton();
        btn_excluir_produto = new javax.swing.JButton();
        btn_alterar_venda = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btn_limpar_venda = new javax.swing.JButton();
        lbl_VendaTotal = new javax.swing.JLabel();
        lbltxt_VendaTotal = new javax.swing.JLabel();
        lbltxt_ClienteNome = new javax.swing.JLabel();
        lbltxt_ClienteCpf = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btn_excluir_venda = new javax.swing.JButton();
        lbl_PesquisarVendaData = new javax.swing.JLabel();
        txt_PesquisarVendaData = new javax.swing.JTextField();
        lbl_PesquisarClienteNome = new javax.swing.JLabel();
        txt_PesquisarClienteNome = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_ConsultaVendas = new javax.swing.JTable();
        lbltxt_VendaCodigo = new javax.swing.JLabel();
        lbltxt_ClienteCodigo = new javax.swing.JLabel();
        lbl_ClienteCodigo = new javax.swing.JLabel();
        lbl_VendaCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONSULTAR VENDA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lbl_VendaData.setText("Data");

        lbl_VendaPagamento.setText("Forma de Pagamento");

        btn_inserir_cliente.setText("Inserir Cliente");
        btn_inserir_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inserir_clienteActionPerformed(evt);
            }
        });

        btn_limpar_cliente.setText("Limpar cliente");
        btn_limpar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpar_clienteActionPerformed(evt);
            }
        });

        lbl_ClienteNome.setText("Nome do Cliente");

        lbl_ClienteCpf.setText("CPF do Cliente");

        lbl_lista_produtos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_lista_produtos.setText("Lista de produtos da venda:");

        tb_ProdutosVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Preço", "Quantidade", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_ProdutosVenda.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tb_ProdutosVenda);

        btn_adicionar_produto.setText("Adicionar Produto");
        btn_adicionar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionar_produtoActionPerformed(evt);
            }
        });

        btn_excluir_produto.setText("Excluir Produto");
        btn_excluir_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir_produtoActionPerformed(evt);
            }
        });

        btn_alterar_venda.setText("Salvar Alterações");
        btn_alterar_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterar_vendaActionPerformed(evt);
            }
        });

        btn_limpar_venda.setText("Limpar Campos");
        btn_limpar_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpar_vendaActionPerformed(evt);
            }
        });

        lbl_VendaTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_VendaTotal.setText("Valor total da venda:");

        lbltxt_VendaTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteCpf.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_excluir_venda.setText("Excluir Venda");
        btn_excluir_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluir_vendaActionPerformed(evt);
            }
        });

        lbl_PesquisarVendaData.setText("Pesquisar Data da Venda");

        txt_PesquisarVendaData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisarVendaDataKeyReleased(evt);
            }
        });

        lbl_PesquisarClienteNome.setText("Pesquisar Nome do Cliente");

        txt_PesquisarClienteNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisarClienteNomeKeyReleased(evt);
            }
        });

        tb_ConsultaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Nome Cliente", "Total Venda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_ConsultaVendas.getTableHeader().setReorderingAllowed(false);
        tb_ConsultaVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ConsultaVendasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_ConsultaVendas);

        lbltxt_VendaCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ClienteCodigo.setText("Código do Cliente");

        lbl_VendaCodigo.setText("Código");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_PesquisarClienteNome)
                            .addComponent(lbl_PesquisarVendaData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_PesquisarVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_PesquisarClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_ClienteNome)
                            .addComponent(lbl_ClienteCodigo)
                            .addComponent(lbl_ClienteCpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_VendaPagamento)
                                    .addComponent(lbl_VendaData)
                                    .addComponent(lbl_VendaCodigo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_VendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_VendaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltxt_VendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_inserir_cliente)
                                .addGap(64, 64, 64)
                                .addComponent(btn_limpar_cliente)
                                .addGap(86, 86, 86))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl_lista_produtos)
                                        .addGap(128, 128, 128))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_alterar_venda)
                                .addGap(36, 36, 36)
                                .addComponent(btn_excluir_venda)
                                .addGap(31, 31, 31)
                                .addComponent(btn_limpar_venda)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_VendaTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbltxt_VendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_adicionar_produto)
                                .addGap(83, 83, 83)
                                .addComponent(btn_excluir_produto)
                                .addGap(80, 80, 80))))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_alterar_venda, btn_excluir_venda, btn_limpar_venda});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_adicionar_produto, btn_excluir_produto});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_inserir_cliente, btn_limpar_cliente});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_PesquisarVendaData)
                            .addComponent(txt_PesquisarVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_PesquisarClienteNome)
                            .addComponent(txt_PesquisarClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbltxt_VendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_VendaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_VendaData)))
                            .addComponent(lbl_VendaCodigo))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_VendaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_VendaPagamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_inserir_cliente)
                            .addComponent(btn_limpar_cliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_ClienteNome)))
                            .addComponent(lbl_ClienteCodigo))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addComponent(lbl_ClienteCpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_lista_produtos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_adicionar_produto)
                            .addComponent(btn_excluir_produto))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_VendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_VendaTotal))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_limpar_venda)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_alterar_venda)
                                .addComponent(btn_excluir_venda)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbltxt_ClienteCpf, lbltxt_ClienteNome});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_alterar_venda, btn_excluir_venda, btn_limpar_venda});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_adicionar_produto, btn_excluir_produto});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_inserir_cliente, btn_limpar_cliente});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btn_inserir_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inserir_clienteActionPerformed
        
        if(tela_InserirCliente_ConsultarVenda == null)
            tela_InserirCliente_ConsultarVenda = new InserirCliente_ConsultarVenda();
        
        tela_InserirCliente_ConsultarVenda.setVisible(true);
        tela_InserirCliente_ConsultarVenda.linkar(this);
    }//GEN-LAST:event_btn_inserir_clienteActionPerformed

    private void btn_adicionar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionar_produtoActionPerformed
        
        if(lbltxt_VendaCodigo.getText().isEmpty())        
            JOptionPane.showMessageDialog(this, "Informe uma venda para alterar", 
                            "Adicionar Produto", JOptionPane.ERROR_MESSAGE);
        else
        {
            if(tela_AdicionarProduto_ConsultarVenda == null)
                tela_AdicionarProduto_ConsultarVenda = new AdicionarProduto_ConsultarVenda();
        
            tela_AdicionarProduto_ConsultarVenda.setVisible(true);
            tela_AdicionarProduto_ConsultarVenda.linkar(this, lbltxt_VendaCodigo.getText());  
        }
    }//GEN-LAST:event_btn_adicionar_produtoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.pesquisaDinamica();
    }//GEN-LAST:event_formWindowOpened

    private void txt_PesquisarVendaDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisarVendaDataKeyReleased
        this.pesquisaDinamica();
    }//GEN-LAST:event_txt_PesquisarVendaDataKeyReleased

    private void txt_PesquisarClienteNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisarClienteNomeKeyReleased
        this.pesquisaDinamica();
    }//GEN-LAST:event_txt_PesquisarClienteNomeKeyReleased

    private void tb_ConsultaVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ConsultaVendasMouseClicked
        
        int linha = tb_ConsultaVendas.getSelectedRow();
        String sql;
        
        sql = "SELECT v.cod_venda, v.data, v.forma_pagamento, c.cod_cli, c.nome, c.cpf "
                + "FROM Venda v INNER JOIN Cliente c ON v.cod_cli = c.cod_cli "
                + "WHERE v.data = '" + tb_ConsultaVendas.getValueAt(linha, 0) + "' AND "
                + "c.nome = '" + tb_ConsultaVendas.getValueAt(linha, 1) + "' AND "
                + "v.valor = " + tb_ConsultaVendas.getValueAt(linha, 2);
        
        this.preencherConsulta_01(sql);
        
        sql = "SELECT vp.cod_venda, vp.cod_prod, p.nome, p.preco, p.quantidade, vp.qtd_prod_venda "
                + "FROM Venda_produto vp INNER JOIN Produto p ON vp.cod_prod = p.cod_prod "
                + "WHERE vp.cod_venda = " + lbltxt_VendaCodigo.getText();
        
        this.preencherConsulta_02(sql);
    }//GEN-LAST:event_tb_ConsultaVendasMouseClicked

    private void btn_limpar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpar_clienteActionPerformed
        this.limparCamposCliente();
    }//GEN-LAST:event_btn_limpar_clienteActionPerformed

    private void btn_excluir_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir_produtoActionPerformed
        this.limparUmProduto();
    }//GEN-LAST:event_btn_excluir_produtoActionPerformed

    private void btn_limpar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpar_vendaActionPerformed
        
        this.limparTodosCampos();
        this.pesquisaDinamica();
    }//GEN-LAST:event_btn_limpar_vendaActionPerformed

    private void btn_alterar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterar_vendaActionPerformed
        
        if (lbltxt_VendaCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escolha uma venda para poder alterá-la",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (validacaoCampoVazio()) {
            if (validacaoCampoTamanho()) {
                
                Venda venda = new Venda();

                venda.setCod_venda(Integer.parseInt(lbltxt_VendaCodigo.getText()));
                venda.setData(txt_VendaData.getText());
                venda.setForma_pagamento(txt_VendaPagamento.getText());
                venda.setValor(Double.parseDouble(lbltxt_VendaTotal.getText()));

                Cliente cliente = new Cliente();

                cliente.setCod_cli(Integer.parseInt(lbltxt_ClienteCodigo.getText()));
                venda.setCliente(cliente);

                VendaDAO dao = new VendaDAO();

                boolean result = dao.alterar(venda, lista_PV_antiga, lista_PV_atualizada);

                if (result == true) {
                    limparTodosCampos();
                    this.pesquisaDinamica();
                    JOptionPane.showMessageDialog(this, "Venda alterada", "Venda alterada", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Venda não alterada", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_alterar_vendaActionPerformed

    private void btn_excluir_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir_vendaActionPerformed
        
         if (lbltxt_VendaCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escolha uma venda para poder excluí-la",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Venda venda = new Venda();

            venda.setCod_venda(Integer.parseInt(lbltxt_VendaCodigo.getText()));
            venda.setData(txt_VendaData.getText());
            venda.setForma_pagamento(txt_VendaPagamento.getText());
            venda.setValor(Double.parseDouble(lbltxt_VendaTotal.getText()));

            Cliente cliente = new Cliente();

            cliente.setCod_cli(Integer.parseInt(lbltxt_ClienteCodigo.getText()));
            venda.setCliente(cliente);

            VendaDAO dao = new VendaDAO();

            boolean result = dao.excluir(venda, lista_PV_antiga);

            if (result == true) {
                limparTodosCampos();
                this.pesquisaDinamica();
                JOptionPane.showMessageDialog(this, "Venda excluída", "Venda excluída", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Venda não excluída", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_excluir_vendaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar_produto;
    private javax.swing.JButton btn_alterar_venda;
    private javax.swing.JButton btn_excluir_produto;
    private javax.swing.JButton btn_excluir_venda;
    private javax.swing.JButton btn_inserir_cliente;
    private javax.swing.JButton btn_limpar_cliente;
    private javax.swing.JButton btn_limpar_venda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_ClienteCodigo;
    private javax.swing.JLabel lbl_ClienteCpf;
    private javax.swing.JLabel lbl_ClienteNome;
    private javax.swing.JLabel lbl_PesquisarClienteNome;
    private javax.swing.JLabel lbl_PesquisarVendaData;
    private javax.swing.JLabel lbl_VendaCodigo;
    private javax.swing.JLabel lbl_VendaData;
    private javax.swing.JLabel lbl_VendaPagamento;
    private javax.swing.JLabel lbl_VendaTotal;
    private javax.swing.JLabel lbl_lista_produtos;
    private javax.swing.JLabel lbltxt_ClienteCodigo;
    private javax.swing.JLabel lbltxt_ClienteCpf;
    private javax.swing.JLabel lbltxt_ClienteNome;
    private javax.swing.JLabel lbltxt_VendaCodigo;
    private javax.swing.JLabel lbltxt_VendaTotal;
    private javax.swing.JTable tb_ConsultaVendas;
    private javax.swing.JTable tb_ProdutosVenda;
    private javax.swing.JTextField txt_PesquisarClienteNome;
    private javax.swing.JTextField txt_PesquisarVendaData;
    private javax.swing.JTextField txt_VendaData;
    private javax.swing.JTextField txt_VendaPagamento;
    // End of variables declaration//GEN-END:variables

}
