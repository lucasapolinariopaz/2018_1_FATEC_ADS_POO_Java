package org.me.Floricultura;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        Consulta_cliente = new javax.swing.JMenu();
        Cadastro_cliente = new javax.swing.JMenuItem();
        Consultar_cliente = new javax.swing.JMenuItem();
        Menu_produto = new javax.swing.JMenu();
        Cadastrar_produto = new javax.swing.JMenuItem();
        Consultar_produto = new javax.swing.JMenuItem();
        Menu_venda = new javax.swing.JMenu();
        Cadastrar_venda = new javax.swing.JMenuItem();
        Consultar_venda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Consulta_cliente.setText("Cliente");

        Cadastro_cliente.setText("Cadastrar");
        Cadastro_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadastro_clienteActionPerformed(evt);
            }
        });
        Consulta_cliente.add(Cadastro_cliente);

        Consultar_cliente.setText("Consultar/Alterar/Excluir");
        Consultar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consultar_clienteActionPerformed(evt);
            }
        });
        Consulta_cliente.add(Consultar_cliente);

        jMenuBar1.add(Consulta_cliente);

        Menu_produto.setText("Produto");

        Cadastrar_produto.setText("Cadastrar");
        Cadastrar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadastrar_produtoActionPerformed(evt);
            }
        });
        Menu_produto.add(Cadastrar_produto);

        Consultar_produto.setText("Consultar/Alterar/Excluir");
        Consultar_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consultar_produtoActionPerformed(evt);
            }
        });
        Menu_produto.add(Consultar_produto);

        jMenuBar1.add(Menu_produto);

        Menu_venda.setText("Venda");

        Cadastrar_venda.setText("Gerar Venda");
        Cadastrar_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadastrar_vendaActionPerformed(evt);
            }
        });
        Menu_venda.add(Cadastrar_venda);

        Consultar_venda.setText("Consultar/Alterar/Excluir");
        Menu_venda.add(Consultar_venda);

        jMenuBar1.add(Menu_venda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Cadastro_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadastro_clienteActionPerformed
        CadastroCliente c1 = new CadastroCliente();
        c1.setVisible(true);
    }//GEN-LAST:event_Cadastro_clienteActionPerformed

    private void Consultar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Consultar_clienteActionPerformed
        ConsultaCliente c2 = new ConsultaCliente();
        c2.setVisible(true);
    }//GEN-LAST:event_Consultar_clienteActionPerformed

    private void Cadastrar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadastrar_produtoActionPerformed
        CadastroProduto c3 = new CadastroProduto();
        c3.setVisible(true);
    }//GEN-LAST:event_Cadastrar_produtoActionPerformed

    private void Consultar_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Consultar_produtoActionPerformed
        ConsultaProduto c4 = new ConsultaProduto();
        c4.setVisible(true);
    }//GEN-LAST:event_Consultar_produtoActionPerformed

    private void Cadastrar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadastrar_vendaActionPerformed
        GerarVenda v1 = new GerarVenda();
        v1.setVisible(true);
    }//GEN-LAST:event_Cadastrar_vendaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Cadastrar_produto;
    private javax.swing.JMenuItem Cadastrar_venda;
    private javax.swing.JMenuItem Cadastro_cliente;
    private javax.swing.JMenu Consulta_cliente;
    private javax.swing.JMenuItem Consultar_cliente;
    private javax.swing.JMenuItem Consultar_produto;
    private javax.swing.JMenuItem Consultar_venda;
    private javax.swing.JMenu Menu_produto;
    private javax.swing.JMenu Menu_venda;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
