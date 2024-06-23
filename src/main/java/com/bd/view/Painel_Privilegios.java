package com.bd.view;

import com.bd.mapper.*;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.*;
import com.bd.service.*;
import org.mapstruct.factory.Mappers;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Painel_Privilegios extends javax.swing.JFrame {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
 
    public Painel_Privilegios() {
        setTitle("Tela de Privilégio");
        initComponents();
        inicializandoClasses();
        receberDados();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLBTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCBRoles = new javax.swing.JComboBox<>();
        jLBConcederPapel = new javax.swing.JLabel();
        jCBFuncionariosRole = new javax.swing.JComboBox<>();
        jLBTabelaDesejada = new javax.swing.JLabel();
        jCBTabelaDesejada = new javax.swing.JComboBox<>();
        jLbPrivilegiosConcedidos = new javax.swing.JLabel();
        jCHBSelect = new javax.swing.JCheckBox();
        jCHBUpdate = new javax.swing.JCheckBox();
        jCHBInsert = new javax.swing.JCheckBox();
        jCHBDelete = new javax.swing.JCheckBox();
        jBTNovoPapel = new javax.swing.JButton();
        jBTNAtualizarPapel = new javax.swing.JButton();
        jBTNConceder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLBTitulo.setText("Privilégios");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Deseja conceder privilegios ao papel :");

        jCBRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCBRolesMouseClicked(evt);
            }
        });

        jLBConcederPapel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLBConcederPapel.setText("Deseja conceder a quem:");

        jCBFuncionariosRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));

        jLBTabelaDesejada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLBTabelaDesejada.setText("Tabela desejada:");

        jLbPrivilegiosConcedidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLbPrivilegiosConcedidos.setText("Privilégios concedidos:");

        jCHBSelect.setText("SELECT TABLE");

        jCHBUpdate.setText("UPDATE TABLE");

        jCHBInsert.setText("INSERT TABLE");

        jCHBDelete.setText("DELETE TABLE");

        jBTNovoPapel.setText("Criar Papel");
        jBTNovoPapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNovoPapelMouseClicked(evt);
            }
        });

        jBTNAtualizarPapel.setText("Atualizar Papel");
        jBTNAtualizarPapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAtualizarPapelMouseClicked(evt);
            }
        });
        jBTNAtualizarPapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNAtualizarPapelActionPerformed(evt);
            }
        });

        jBTNConceder.setText("Conceder Privilégio");
        jBTNConceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNConcederMouseClicked(evt);
            }
        });
        jBTNConceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNConcederActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLBTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLBConcederPapel)
                            .addComponent(jLBTabelaDesejada)
                            .addComponent(jCBTabelaDesejada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBFuncionariosRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBRoles, 0, 306, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jCHBSelect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLbPrivilegiosConcedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jBTNovoPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCHBUpdate))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jCHBDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCHBInsert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addComponent(jBTNAtualizarPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jBTNConceder, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLBTitulo)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBConcederPapel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBFuncionariosRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBTabelaDesejada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBTabelaDesejada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLbPrivilegiosConcedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCHBSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCHBUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCHBInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCHBDelete)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBTNovoPapel)
                    .addComponent(jBTNAtualizarPapel))
                .addGap(18, 18, 18)
                .addComponent(jBTNConceder)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBRolesMouseClicked
        String opcao = jCBRoles.getSelectedItem().toString();

        List<String> funcionariosRole = funcionarioService.funcionarioPertenceRole(opcao);

        for(int i = 0; i < funcionariosRole.size(); i++){
            jCBFuncionariosRole.addItem(funcionariosRole.get(i));
        }

    }//GEN-LAST:event_jCBRolesMouseClicked

    private void jBTNAtualizarPapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAtualizarPapelMouseClicked
        // TODO add your handling code here:
        Painel_Atualizar_Roles roles = new Painel_Atualizar_Roles(this, true);
        roles.setLocationRelativeTo(this);
        roles.setVisible(true);
        
    }//GEN-LAST:event_jBTNAtualizarPapelMouseClicked

    private void jBTNAtualizarPapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNAtualizarPapelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTNAtualizarPapelActionPerformed

    private void jBTNConcederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNConcederMouseClicked
        // TODO add your handling code here:
        ArrayList<String> permissoes = new ArrayList<>();

        if(jCHBSelect.isSelected()){
            permissoes.add("SELECT");
        }
        else if(jCHBInsert.isSelected()){
            permissoes.add("INSERT");
        }
        else if(jCHBUpdate.isSelected()){
            permissoes.add("UPDATE");
        }
        else if(jCHBDelete.isSelected()){
            permissoes.add("DELETE");
        }

        String[] listaPermissoes = new String[permissoes.size()];

        for(int i = 0; i < listaPermissoes.length; i++){
            listaPermissoes[i] = permissoes.get(i);
        }

        if(jCBFuncionariosRole.getSelectedItem().toString().equals("Todos")){
            funcionarioService.concederPrivilegioGrupo(jCBRoles.getSelectedItem().toString(), jCBTabelaDesejada.getSelectedItem().toString(), listaPermissoes);
        } else{
           funcionarioService.concederPrivilegioUsuario(jCBFuncionariosRole.getSelectedItem().toString(), jCBTabelaDesejada.getSelectedItem().toString(), listaPermissoes);
        }

        JOptionPane.showMessageDialog(this, "Privilégios atualizados com sucesso!");
        this.dispose();
    }//GEN-LAST:event_jBTNConcederMouseClicked

    private void jBTNConcederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNConcederActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTNConcederActionPerformed

    private void jBTNovoPapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNovoPapelMouseClicked
        // TODO add your handling code here:
        Painel_Cadastrar_Role cadastroRole = new Painel_Cadastrar_Role(this, true);
        cadastroRole.setLocationRelativeTo(this);
        cadastroRole.setVisible(true);
    }//GEN-LAST:event_jBTNovoPapelMouseClicked

    public void receberDados(){
        List<String> listaRoles = funcionarioService.buscarRoles();

        for(int i = 0; i < listaRoles.size(); i++){
            jCBRoles.addItem(listaRoles.get(i));
        }
        
        String[] tabelas = new String[5];
        tabelas[0] = "tb_fornecedores";
        tabelas[1] = "tb_funcionarios";
        tabelas[2] = "tb_itens";
        tabelas[3] = "tb_produtos";
        tabelas[4] = "tb_vendas";

        for(int i = 0; i < tabelas.length; i++){
            jCBTabelaDesejada.addItem(tabelas[i]);
        }
    }

    private void inicializandoClasses(){
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        FornecedorMapper fornecedorMapper = Mappers.getMapper(FornecedorMapper.class);
        fornecedorService = new FornecedorService(fornecedorRepository, fornecedorMapper);

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        BackupRepository backupRepository = new BackupRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, backupRepository, funcionarioMapper);

        ItemRepository itemRepository = new ItemRepository();
        ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);
        itemService = new ItemService(itemRepository, itemMapper);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);
        produtoService = new ProdutoService(produtoRepository, produtoMapper);

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
        usuarioService = new UsuarioService(usuarioRepository, usuarioMapper);

        VendaRepository vendaRepository = new VendaRepository();
        VendaMapper vendaMapper = Mappers.getMapper(VendaMapper.class);
        vendaService = new VendaService(vendaRepository, vendaMapper);
    }

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
            java.util.logging.Logger.getLogger(Painel_Privilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Privilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Privilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Privilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Privilegios dialog = new Painel_Privilegios();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNAtualizarPapel;
    private javax.swing.JButton jBTNConceder;
    private javax.swing.JButton jBTNovoPapel;
    private javax.swing.JComboBox<String> jCBFuncionariosRole;
    private javax.swing.JComboBox<String> jCBRoles;
    private javax.swing.JComboBox<String> jCBTabelaDesejada;
    private javax.swing.JCheckBox jCHBDelete;
    private javax.swing.JCheckBox jCHBInsert;
    private javax.swing.JCheckBox jCHBSelect;
    private javax.swing.JCheckBox jCHBUpdate;
    private javax.swing.JLabel jLBConcederPapel;
    private javax.swing.JLabel jLBTabelaDesejada;
    private javax.swing.JLabel jLBTitulo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLbPrivilegiosConcedidos;
    // End of variables declaration//GEN-END:variables
}
