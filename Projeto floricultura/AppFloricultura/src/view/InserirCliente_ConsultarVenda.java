/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.beans.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Gustavo Lobo
 */
public class InserirCliente_ConsultarVenda extends javax.swing.JFrame {

    private ConsultarVenda tela_ConsultarVenda;
    
    /**
     * Creates new form ConsultaCliente
     */
    public InserirCliente_ConsultarVenda(){
        initComponents();
    }

    public void linkar(ConsultarVenda tela_ConsultarVenda)
    {
        this.tela_ConsultarVenda = tela_ConsultarVenda;
    }
    
    public void preencher_PD(String sql)
    {
        ClienteDAO dao = new ClienteDAO();
        
        List<Cliente> consulta_cliente = dao.consultar_PD(sql);
        
        DefaultTableModel tabela = (DefaultTableModel) tb_clientes.getModel();
        tabela.setNumRows(0);
        
        consulta_cliente.forEach((instancia) -> 
        {
            tabela.addRow(new Object[]
            {
                instancia.getNome(),
                instancia.getRg(),
                instancia.getCpf()
            });
        });
    }
    
    public void pesquisaDinamica()
    {
        String sql = "SELECT * FROM Cliente WHERE "
        + "nome LIKE '%" + txt_PesquisaClienteNome.getText() + "%'";

        this.preencher_PD(sql);
    }
    
    public void preencherConsulta(String sql)
    {
        ClienteDAO dao = new ClienteDAO();
        
        Cliente consulta_cliente = dao.consultar(sql);  
        
        lbltxt_ClienteCodigo.setText("" + consulta_cliente.getCod_cli());
        lbltxt_ClienteNome.setText(consulta_cliente.getNome());
        lbltxt_ClienteRg.setText(consulta_cliente.getRg());
        lbltxt_ClienteCpf.setText(consulta_cliente.getCpf());
        lbltxt_ClienteEndereco.setText(consulta_cliente.getEndereco());
        lbltxt_ClienteN_endereco.setText("" + consulta_cliente.getNum_endereco());
        lbltxt_ClienteCidade.setText(consulta_cliente.getCidade());
        lbltxt_ClienteEstado.setText(consulta_cliente.getUf());
        lbltxt_ClienteTelefone.setText(consulta_cliente.getTelefone());
        lbltxt_ClienteCelular.setText(consulta_cliente.getCelular());
        lbltxt_ClienteEmail.setText(consulta_cliente.getEmail());
    }
    
    public void limparTodosCampos()
    {
        txt_PesquisaClienteNome.setText("");
        lbltxt_ClienteCodigo.setText("");
        lbltxt_ClienteNome.setText("");
        lbltxt_ClienteRg.setText("");
        lbltxt_ClienteCpf.setText("");
        lbltxt_ClienteEndereco.setText("");
        lbltxt_ClienteN_endereco.setText("");
        lbltxt_ClienteCidade.setText("");
        lbltxt_ClienteEstado.setText("");
        lbltxt_ClienteTelefone.setText("");
        lbltxt_ClienteCelular.setText("");
        lbltxt_ClienteEmail.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_Nome = new javax.swing.JLabel();
        lbl_Rg = new javax.swing.JLabel();
        lbl_Cpf = new javax.swing.JLabel();
        lbl_Endereco = new javax.swing.JLabel();
        lbl_Numero = new javax.swing.JLabel();
        lbl_Cidade = new javax.swing.JLabel();
        lbl_Uf = new javax.swing.JLabel();
        lbl_Telefone = new javax.swing.JLabel();
        lbl_Celular = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        btn_inserir = new javax.swing.JButton();
        btn_limpar = new javax.swing.JButton();
        lbl_PesquisaNome = new javax.swing.JLabel();
        txt_PesquisaClienteNome = new javax.swing.JTextField();
        lbl_codigo = new javax.swing.JLabel();
        lbltxt_ClienteCodigo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();
        lbltxt_ClienteNome = new javax.swing.JLabel();
        lbltxt_ClienteRg = new javax.swing.JLabel();
        lbltxt_ClienteCpf = new javax.swing.JLabel();
        lbltxt_ClienteEndereco = new javax.swing.JLabel();
        lbltxt_ClienteN_endereco = new javax.swing.JLabel();
        lbltxt_ClienteCidade = new javax.swing.JLabel();
        lbltxt_ClienteEstado = new javax.swing.JLabel();
        lbltxt_ClienteTelefone = new javax.swing.JLabel();
        lbltxt_ClienteCelular = new javax.swing.JLabel();
        lbltxt_ClienteEmail = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSERIR CLIENTE (CONSULTAR VENDA)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lbl_Nome.setText("Nome");

        lbl_Rg.setText("RG");

        lbl_Cpf.setText("CPF");

        lbl_Endereco.setText("Endereço");

        lbl_Numero.setText("Número");

        lbl_Cidade.setText("Cidade");

        lbl_Uf.setText("Estado");

        lbl_Telefone.setText("Telefone");

        lbl_Celular.setText("Celular");

        lbl_Email.setText("E-mail");

        btn_inserir.setText("Inserir");
        btn_inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inserirActionPerformed(evt);
            }
        });

        btn_limpar.setText("Limpar");
        btn_limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparActionPerformed(evt);
            }
        });

        lbl_PesquisaNome.setText("Pesquisar Nome");

        txt_PesquisaClienteNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisaClienteNomeKeyReleased(evt);
            }
        });

        lbl_codigo.setText("Código");

        lbltxt_ClienteCodigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "RG", "CPF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tb_clientes.getTableHeader().setReorderingAllowed(false);
        tb_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_clientes);

        lbltxt_ClienteNome.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteRg.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteCpf.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteEndereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteN_endereco.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteCidade.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteTelefone.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteCelular.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbltxt_ClienteEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_PesquisaNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_PesquisaClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_Cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Endereco, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Numero, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Cidade, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Uf, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Telefone, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Celular, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Email, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Rg, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Nome, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_codigo, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                            .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbltxt_ClienteRg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteN_endereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteTelefone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteCelular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbltxt_ClienteEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                                .addComponent(btn_inserir)
                                .addGap(84, 84, 84)
                                .addComponent(btn_limpar)
                                .addGap(98, 98, 98))))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_inserir, btn_limpar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_PesquisaNome)
                    .addComponent(txt_PesquisaClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_codigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Nome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteRg, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Rg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltxt_ClienteCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Cpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Endereco)
                            .addComponent(lbltxt_ClienteEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Numero)
                            .addComponent(lbltxt_ClienteN_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Cidade)
                            .addComponent(lbltxt_ClienteCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Uf)
                            .addComponent(lbltxt_ClienteEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Telefone)
                            .addComponent(lbltxt_ClienteTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Celular)
                            .addComponent(lbltxt_ClienteCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Email)
                            .addComponent(lbltxt_ClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_inserir)
                            .addComponent(btn_limpar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_inserir, btn_limpar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.pesquisaDinamica();
    }//GEN-LAST:event_formWindowOpened

    private void txt_PesquisaClienteNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaClienteNomeKeyReleased
        this.pesquisaDinamica();
    }//GEN-LAST:event_txt_PesquisaClienteNomeKeyReleased

    private void tb_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clientesMouseClicked
        
        int linha = tb_clientes.getSelectedRow();

        String sql = "SELECT * FROM Cliente WHERE "
        + "nome = '" + tb_clientes.getValueAt(linha, 0) + "' AND "
        + "rg = '" + tb_clientes.getValueAt(linha, 1) + "' AND "
        + "cpf = '" + tb_clientes.getValueAt(linha, 2) + "'";

        this.preencherConsulta(sql);
    }//GEN-LAST:event_tb_clientesMouseClicked

    private void btn_limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparActionPerformed
        
        this.limparTodosCampos();
        this.pesquisaDinamica();
    }//GEN-LAST:event_btn_limparActionPerformed

    private void btn_inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inserirActionPerformed
        
        Cliente cliente = new Cliente();
        
        cliente.setCod_cli(Integer.parseInt(lbltxt_ClienteCodigo.getText()));
        cliente.setNome(lbltxt_ClienteNome.getText());
        cliente.setCpf(lbltxt_ClienteCpf.getText());
        
        if(tela_ConsultarVenda != null)
        {
            tela_ConsultarVenda.importarCliente(cliente);
            this.limparTodosCampos();
        }
    }//GEN-LAST:event_btn_inserirActionPerformed

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
            java.util.logging.Logger.getLogger(InserirCliente_ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InserirCliente_ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InserirCliente_ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InserirCliente_ConsultarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                new InserirCliente_ConsultarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_inserir;
    private javax.swing.JButton btn_limpar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_Celular;
    private javax.swing.JLabel lbl_Cidade;
    private javax.swing.JLabel lbl_Cpf;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_Endereco;
    private javax.swing.JLabel lbl_Nome;
    private javax.swing.JLabel lbl_Numero;
    private javax.swing.JLabel lbl_PesquisaNome;
    private javax.swing.JLabel lbl_Rg;
    private javax.swing.JLabel lbl_Telefone;
    private javax.swing.JLabel lbl_Uf;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbltxt_ClienteCelular;
    private javax.swing.JLabel lbltxt_ClienteCidade;
    private javax.swing.JLabel lbltxt_ClienteCodigo;
    private javax.swing.JLabel lbltxt_ClienteCpf;
    private javax.swing.JLabel lbltxt_ClienteEmail;
    private javax.swing.JLabel lbltxt_ClienteEndereco;
    private javax.swing.JLabel lbltxt_ClienteEstado;
    private javax.swing.JLabel lbltxt_ClienteN_endereco;
    private javax.swing.JLabel lbltxt_ClienteNome;
    private javax.swing.JLabel lbltxt_ClienteRg;
    private javax.swing.JLabel lbltxt_ClienteTelefone;
    private javax.swing.JTable tb_clientes;
    private javax.swing.JTextField txt_PesquisaClienteNome;
    // End of variables declaration//GEN-END:variables
}
