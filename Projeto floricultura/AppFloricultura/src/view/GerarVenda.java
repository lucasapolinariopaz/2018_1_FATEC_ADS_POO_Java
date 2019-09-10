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
import model.beans.Venda;
import model.beans.Venda_produto;
import model.dao.VendaDAO;

/**
 *
 * @author Gustavo Lobo
 */
public class GerarVenda extends javax.swing.JFrame {

    InserirCliente_GerarVenda tela_InserirCliente_GerarVenda = new InserirCliente_GerarVenda();
    AdicionarProduto_GerarVenda tela_AdicionarProduto_GerarVenda = new AdicionarProduto_GerarVenda();

    List<Venda_produto> lista_produtos_venda = new ArrayList<>();

    /**
     * Creates new form RealizarVenda
     */
    public GerarVenda() {
        initComponents();
    }

    public void importarCliente(Cliente cliente) {
        lbltxt_ClienteCodigo.setText(String.valueOf(cliente.getCod_cli()));
        lbltxt_ClienteNome.setText(cliente.getNome());
        lbltxt_ClienteCpf.setText(cliente.getCpf());
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

    public void importarProduto(Venda_produto venda_produto) {
        this.lista_produtos_venda.add(venda_produto);
        this.preencherTabelaProdutos(lista_produtos_venda);
    }

    public boolean isInListaProduto(Venda_produto venda_produto) {
        boolean result = false;

        for (int i = 0; i < lista_produtos_venda.size(); i++) {
            if (lista_produtos_venda.get(i).getProduto().getCod_prod() == venda_produto.getProduto().getCod_prod()) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void limparCamposCliente() {
        lbltxt_ClienteCodigo.setText("");
        lbltxt_ClienteNome.setText("");
        lbltxt_ClienteCpf.setText("");
    }

    public void limparUmProduto() {
        int linha = tb_ProdutosVenda.getSelectedRow();

        lista_produtos_venda.remove(linha);

        preencherTabelaProdutos(lista_produtos_venda);
    }

    public void limparTodosCampos() {
        txt_VendaData.setText("");
        txt_VendaPagamento.setText("");
        limparCamposCliente();
        lista_produtos_venda.clear();
        preencherTabelaProdutos(lista_produtos_venda);
        lbltxt_VendaTotal.setText("");
    }
    
    public boolean validacaoCampoVazio() {
        
        boolean valida = false;

        if (txt_VendaData.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data da venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_VendaPagamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a forma de pagamento da venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (lbltxt_ClienteCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o cliente que gerou a venda", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (lista_produtos_venda.isEmpty()) {
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
        lbl_ListaProdutos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_ProdutosVenda = new javax.swing.JTable();
        btn_adicionar_produto = new javax.swing.JButton();
        btn_excluir_produto = new javax.swing.JButton();
        btn_gerar_venda = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btn_limpar_venda = new javax.swing.JButton();
        lbl_VendaTotal = new javax.swing.JLabel();
        lbltxt_VendaTotal = new javax.swing.JLabel();
        lbltxt_ClienteNome = new javax.swing.JLabel();
        lbltxt_ClienteCpf = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lbltxt_ClienteCodigo = new javax.swing.JLabel();
        lbl_ClienteCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GERAR VENDA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        lbl_ClienteNome.setText("Nome do cliente");

        lbl_ClienteCpf.setText("CPF do cliente");

        lbl_ListaProdutos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_ListaProdutos.setText("Lista de produtos da venda:");

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

        btn_gerar_venda.setText("Gerar Venda");
        btn_gerar_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gerar_vendaActionPerformed(evt);
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

        lbltxt_ClienteCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_ClienteCodigo.setText("Código");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_adicionar_produto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_excluir_produto)
                .addGap(69, 69, 69))
            .addComponent(jSeparator3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbl_ClienteCpf))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_ClienteNome, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_ClienteCodigo, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lbl_VendaTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltxt_VendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btn_gerar_venda)
                        .addGap(72, 72, 72)
                        .addComponent(btn_limpar_venda))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(lbl_ListaProdutos)
                                .addGap(138, 138, 138))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_VendaData)
                            .addComponent(lbl_VendaPagamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_VendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_VendaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btn_inserir_cliente)
                        .addGap(66, 66, 66)
                        .addComponent(btn_limpar_cliente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_gerar_venda, btn_limpar_venda});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_adicionar_produto, btn_excluir_produto});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_inserir_cliente, btn_limpar_cliente});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_VendaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_VendaData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_VendaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_VendaPagamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_inserir_cliente)
                    .addComponent(btn_limpar_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ClienteNome))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_ClienteCpf)
                            .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ListaProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_adicionar_produto)
                            .addComponent(btn_excluir_produto))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_VendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_VendaTotal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_gerar_venda)
                            .addComponent(btn_limpar_venda)))
                    .addComponent(lbl_ClienteCodigo))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbltxt_ClienteCpf, lbltxt_ClienteNome});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_gerar_venda, btn_limpar_venda});

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
    
    private void btn_limpar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpar_vendaActionPerformed
        limparTodosCampos();
    }//GEN-LAST:event_btn_limpar_vendaActionPerformed

    private void btn_inserir_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inserir_clienteActionPerformed

        if (tela_InserirCliente_GerarVenda == null) {
            tela_InserirCliente_GerarVenda = new InserirCliente_GerarVenda();
        }

        tela_InserirCliente_GerarVenda.setVisible(true);
        tela_InserirCliente_GerarVenda.linkar(this);
    }//GEN-LAST:event_btn_inserir_clienteActionPerformed

    private void btn_adicionar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionar_produtoActionPerformed
        
        if (tela_AdicionarProduto_GerarVenda == null) {
            tela_AdicionarProduto_GerarVenda = new AdicionarProduto_GerarVenda();
        }

        tela_AdicionarProduto_GerarVenda.setVisible(true);
        tela_AdicionarProduto_GerarVenda.linkar(this);
    }//GEN-LAST:event_btn_adicionar_produtoActionPerformed

    private void btn_gerar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gerar_vendaActionPerformed
        
        if (validacaoCampoVazio()) {
            if (validacaoCampoTamanho()) {
                Venda venda = new Venda();

                venda.setData(txt_VendaData.getText());
                venda.setForma_pagamento(txt_VendaPagamento.getText());
                venda.setValor(Double.parseDouble(lbltxt_VendaTotal.getText()));

                Cliente cliente = new Cliente();

                cliente.setCod_cli(Integer.parseInt(lbltxt_ClienteCodigo.getText()));
                venda.setCliente(cliente);

                VendaDAO dao = new VendaDAO();

                boolean result = dao.gerar(venda, lista_produtos_venda);

                if (result == true) {
                    limparTodosCampos();
                    JOptionPane.showMessageDialog(this, "Venda gerada", "Venda gerada", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Venda não gerada", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_gerar_vendaActionPerformed

    private void btn_limpar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpar_clienteActionPerformed
        limparCamposCliente();
    }//GEN-LAST:event_btn_limpar_clienteActionPerformed

    private void btn_excluir_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluir_produtoActionPerformed
        limparUmProduto();
    }//GEN-LAST:event_btn_excluir_produtoActionPerformed

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
            java.util.logging.Logger.getLogger(GerarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GerarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar_produto;
    private javax.swing.JButton btn_excluir_produto;
    private javax.swing.JButton btn_gerar_venda;
    private javax.swing.JButton btn_inserir_cliente;
    private javax.swing.JButton btn_limpar_cliente;
    private javax.swing.JButton btn_limpar_venda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_ClienteCodigo;
    private javax.swing.JLabel lbl_ClienteCpf;
    private javax.swing.JLabel lbl_ClienteNome;
    private javax.swing.JLabel lbl_ListaProdutos;
    private javax.swing.JLabel lbl_VendaData;
    private javax.swing.JLabel lbl_VendaPagamento;
    private javax.swing.JLabel lbl_VendaTotal;
    private javax.swing.JLabel lbltxt_ClienteCodigo;
    private javax.swing.JLabel lbltxt_ClienteCpf;
    private javax.swing.JLabel lbltxt_ClienteNome;
    private javax.swing.JLabel lbltxt_VendaTotal;
    private javax.swing.JTable tb_ProdutosVenda;
    private javax.swing.JTextField txt_VendaData;
    private javax.swing.JTextField txt_VendaPagamento;
    // End of variables declaration//GEN-END:variables

}
