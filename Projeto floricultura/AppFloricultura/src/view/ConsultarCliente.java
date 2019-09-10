/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.beans.Cliente;
import model.dao.ClienteDAO;

/**
 *
 * @author Gustavo Lobo
 */
public class ConsultarCliente extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaCliente
     */
    public ConsultarCliente() {
        initComponents();
    }

    public void limparTodosCampos() {
        txt_PesquisaNome.setText("");
        lbltxt_codigo.setText("");
        txt_nome.setText("");
        txt_rg.setText("");
        txt_cpf.setText("");
        txt_endereco.setText("");
        txt_n_endereco.setText("");
        txt_cidade.setText("");
        txt_estado.setText("");
        txt_telefone.setText("");
        txt_cel.setText("");
        txt_email.setText("");
    }

    public void preencher_PD(String sql) {
        ClienteDAO dao = new ClienteDAO();

        List<Cliente> consulta_cliente = dao.consultar_PD(sql);

        DefaultTableModel tabela = (DefaultTableModel) tb_clientes.getModel();
        tabela.setNumRows(0);

        consulta_cliente.forEach((instancia)
                -> {
            tabela.addRow(new Object[]{
                instancia.getNome(),
                instancia.getRg(),
                instancia.getCpf()
            });
        });
    }

    public void pesquisaDinamica() {
        String sql = "SELECT * FROM Cliente WHERE nome LIKE '%" + txt_PesquisaNome.getText() + "%'";

        this.preencher_PD(sql);
    }

    public void preencherConsulta(String sql) {
        ClienteDAO dao = new ClienteDAO();

        Cliente consulta_cliente = dao.consultar(sql);

        lbltxt_codigo.setText("" + consulta_cliente.getCod_cli());
        txt_nome.setText(consulta_cliente.getNome());
        txt_rg.setText(consulta_cliente.getRg());
        txt_cpf.setText(consulta_cliente.getCpf());
        txt_endereco.setText(consulta_cliente.getEndereco());
        txt_n_endereco.setText("" + consulta_cliente.getNum_endereco());
        txt_cidade.setText(consulta_cliente.getCidade());
        txt_estado.setText(consulta_cliente.getUf());
        txt_telefone.setText(consulta_cliente.getTelefone());
        txt_cel.setText(consulta_cliente.getCelular());
        txt_email.setText(consulta_cliente.getEmail());
    }

    public boolean validacaoCampoVazio() {
        boolean valida = false;

        if (txt_nome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_rg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o RG do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_cpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o CPF do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_endereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o endereco do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_n_endereco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o número do endereço do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_cidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a cidade do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_estado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o estado do cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            valida = true;
        }

        return valida;
    }

    public boolean validacaoCampoTamanho() {
        boolean valida = false;

        if (txt_nome.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Campo nome, tamanho máximo: 100 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_rg.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Campo RG, tamanho máximo: 20 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_cpf.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Campo CPF, tamanho máximo: 20 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_endereco.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Campo endereço, tamanho máximo: 100 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_n_endereco.getText().length() > 4) {
            JOptionPane.showMessageDialog(this, "Campo número do endereço, tamanho máximo: 4 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_cidade.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Campo cidade, tamanho máximo: 50 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_estado.getText().length() > 2) {
            JOptionPane.showMessageDialog(this, "Campo estado, tamanho máximo: 2 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_telefone.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Campo telefone, tamanho máximo: 20 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_cel.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Campo celular, tamanho máximo: 20 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (txt_email.getText().length() > 100) {
            JOptionPane.showMessageDialog(this, "Campo e-mail, tamanho máximo: 100 caracteres",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            valida = true;
        }

        return valida;
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
        txt_endereco = new javax.swing.JTextField();
        txt_cpf = new javax.swing.JTextField();
        txt_nome = new javax.swing.JTextField();
        txt_rg = new javax.swing.JTextField();
        txt_n_endereco = new javax.swing.JTextField();
        txt_cidade = new javax.swing.JTextField();
        txt_estado = new javax.swing.JTextField();
        txt_telefone = new javax.swing.JTextField();
        txt_cel = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        btn_alterar = new javax.swing.JButton();
        btn_limpar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        lbl_PesquisaNome = new javax.swing.JLabel();
        txt_PesquisaNome = new javax.swing.JTextField();
        lbl_codigo = new javax.swing.JLabel();
        lbltxt_codigo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONSULTA DE CLIENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        btn_alterar.setText("Alterar");
        btn_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alterarActionPerformed(evt);
            }
        });

        btn_limpar.setText("Limpar");
        btn_limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limparActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        lbl_PesquisaNome.setText("Pesquisar Nome");

        txt_PesquisaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisaNomeKeyReleased(evt);
            }
        });

        lbl_codigo.setText("Código");

        lbltxt_codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
                        .addComponent(txt_PesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_cel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_telefone, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_estado, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_cidade, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_n_endereco, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_endereco, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_email)
                                    .addComponent(lbltxt_codigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_rg, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_nome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_alterar)
                                .addGap(53, 53, 53)
                                .addComponent(btn_excluir)
                                .addGap(49, 49, 49)
                                .addComponent(btn_limpar)
                                .addGap(53, 53, 53))))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_alterar, btn_excluir, btn_limpar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_PesquisaNome)
                    .addComponent(txt_PesquisaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_codigo)
                            .addComponent(lbltxt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Nome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Rg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Cpf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Endereco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_n_endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Numero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Cidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Uf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Telefone))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Celular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Email))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_alterar)
                            .addComponent(btn_excluir)
                            .addComponent(btn_limpar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_alterar, btn_excluir, btn_limpar});

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

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btn_limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limparActionPerformed
        
        this.limparTodosCampos();
        this.pesquisaDinamica();
    }//GEN-LAST:event_btn_limparActionPerformed

    private void tb_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clientesMouseClicked

        int linha = tb_clientes.getSelectedRow();
                        
        String sql = "SELECT * FROM Cliente WHERE "
                + "nome = '" + tb_clientes.getValueAt(linha, 0) + "' AND "
                + "rg = '" + tb_clientes.getValueAt(linha, 1) + "' AND "
                + "cpf = '" + tb_clientes.getValueAt(linha, 2) + "'";
        
        this.preencherConsulta(sql);
    }//GEN-LAST:event_tb_clientesMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.pesquisaDinamica();       
    }//GEN-LAST:event_formWindowOpened

    private void btn_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alterarActionPerformed
        
        if (lbltxt_codigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escolha um cliente para poder alterar o cadastro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (validacaoCampoVazio()) {
            if (validacaoCampoTamanho()) {

                Cliente cliente = new Cliente();

                cliente.setCod_cli(Integer.parseInt(lbltxt_codigo.getText()));
                cliente.setNome(txt_nome.getText());
                cliente.setRg(txt_rg.getText());
                cliente.setCpf(txt_cpf.getText());
                cliente.setEndereco(txt_endereco.getText());
                cliente.setNum_endereco(Integer.parseInt(txt_n_endereco.getText()));
                cliente.setCidade(txt_cidade.getText());
                cliente.setUf(txt_estado.getText());
                cliente.setTelefone(txt_telefone.getText());
                cliente.setCelular(txt_cel.getText());
                cliente.setEmail(txt_email.getText());

                ClienteDAO dao = new ClienteDAO();

                boolean result = dao.alterar(cliente);

                if (result == true) {
                    this.limparTodosCampos();
                    this.pesquisaDinamica();
                    JOptionPane.showMessageDialog(this, "Cliente alterado", "Cliente alterado", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não alterado", "Erro", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_alterarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        
        if (lbltxt_codigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escolha um cliente para poder excluir o cadastro",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Cliente cliente = new Cliente();

            cliente.setCod_cli(Integer.parseInt(lbltxt_codigo.getText()));

            ClienteDAO dao = new ClienteDAO();

            boolean result = dao.excluir(cliente);

            if (result == true) {
                this.limparTodosCampos();
                this.pesquisaDinamica();
                JOptionPane.showMessageDialog(this, "Cliente excluído", "Cliente excluído", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não excluído", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_PesquisaNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaNomeKeyReleased
        this.pesquisaDinamica();
    }//GEN-LAST:event_txt_PesquisaNomeKeyReleased

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
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConsultarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alterar;
    private javax.swing.JButton btn_excluir;
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
    private javax.swing.JLabel lbltxt_codigo;
    private javax.swing.JTable tb_clientes;
    private javax.swing.JTextField txt_PesquisaNome;
    private javax.swing.JTextField txt_cel;
    private javax.swing.JTextField txt_cidade;
    private javax.swing.JTextField txt_cpf;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_endereco;
    private javax.swing.JTextField txt_estado;
    private javax.swing.JTextField txt_n_endereco;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_rg;
    private javax.swing.JTextField txt_telefone;
    // End of variables declaration//GEN-END:variables
}
