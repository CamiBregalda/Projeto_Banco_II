/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.model.response.FuncionarioResponse;
import com.bd.service.FornecedorService;
import com.bd.service.FuncionarioService;
import com.bd.service.ItemService;
import com.bd.service.ProdutoService;
import com.bd.service.UsuarioService;
import com.bd.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author gui
 */
public class Painel_Cadastrar_Role extends javax.swing.JDialog {
    
    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
    
    
    /**
     * Creates new form Painel_Cadastrar_Role
     */
    public Painel_Cadastrar_Role(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastro Papel");
        initComponents();
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFNomeNovaRole = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLListaNomeFuncionarios = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLListaNomeDefinitivo = new javax.swing.JList<>();
        jBTCriarRole = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Criar um novo Papel");

        jLabel2.setText("Nome do Novo Papel:");

        jLListaNomeFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLListaNomeFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jLListaNomeFuncionarios);

        jLListaNomeDefinitivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLListaNomeDefinitivoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jLListaNomeDefinitivo);

        jBTCriarRole.setText("Criar Papel");
        jBTCriarRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTCriarRoleMouseClicked(evt);
            }
        });

        jLabel3.setText("Selecione as pessoas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFNomeNovaRole, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jBTCriarRole))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(219, 219, 219))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFNomeNovaRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jBTCriarRole)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTCriarRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTCriarRoleMouseClicked
        String nomeRole = jTFNomeNovaRole.getText();
        
        List<FuncionarioResponse> pessoas = funcionarioService.buscarFuncionarios();
        List<String> funcionario = null;
        for(FuncionarioResponse elementoPessoa : pessoas){
            funcionario.add(elementoPessoa.fun_nome());
        }
    //    DefaultListModel<String> modeloLista = (DefaultListModel<String>) jTFNomeNovaRole.getModel();
    //    modeloLista.clear();
        
        for(String listaFuncionarios : funcionario){
            jTFNomeNovaRole.setText(listaFuncionarios);
        }
        
    //    funcionarioService.cadastrarRole(nomeRole, pessoas);
    }//GEN-LAST:event_jBTCriarRoleMouseClicked

    private void jLListaNomeFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLListaNomeFuncionariosMouseClicked
        //String nomeFuncionario = jLListaNomeFuncionarios.getSelectedValue();
        //jLListaNomeFuncionarios.getSelectedValue();
        //jLListaNomeFuncionarios.removeElement();
        //quando clicado no nome vai adicionar na lista 2 e ser removido da 1 lista
        DefaultListModel<String> modeloFuncionarios;
        DefaultListModel<String> modeloDefinitivo;
        modeloFuncionarios = new DefaultListModel<>();
        modeloDefinitivo = new DefaultListModel<>();
        jLListaNomeFuncionarios = new JList<>(modeloFuncionarios);
        jLListaNomeDefinitivo = new JList<>(modeloDefinitivo);
        
        String nomeFuncionario = jLListaNomeFuncionarios.getSelectedValue();
    if (nomeFuncionario != null) {
        modeloFuncionarios.removeElement(nomeFuncionario); // Remove da primeira lista
        modeloDefinitivo.addElement(nomeFuncionario); // Adiciona à segunda lista
        
    }

        
    }//GEN-LAST:event_jLListaNomeFuncionariosMouseClicked

    private void jLListaNomeDefinitivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLListaNomeDefinitivoMouseClicked
        DefaultListModel<String> modeloFuncionarios;
        DefaultListModel<String> modeloDefinitivo;
        modeloFuncionarios = new DefaultListModel<>();
        modeloDefinitivo = new DefaultListModel<>();
        jLListaNomeFuncionarios = new JList<>(modeloFuncionarios);
        jLListaNomeDefinitivo = new JList<>(modeloDefinitivo);
        
        String nomeFuncionario = jLListaNomeDefinitivo.getSelectedValue();
    if (nomeFuncionario != null) {
        modeloDefinitivo.removeElement(nomeFuncionario); // Remove da segunda lista
        modeloFuncionarios.addElement(nomeFuncionario); // Adiciona à primeira lista
    }
        
             
    }//GEN-LAST:event_jLListaNomeDefinitivoMouseClicked

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
            java.util.logging.Logger.getLogger(Painel_Cadastrar_Role.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastrar_Role.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastrar_Role.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastrar_Role.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Cadastrar_Role dialog = new Painel_Cadastrar_Role(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTCriarRole;
    private javax.swing.JList<String> jLListaNomeDefinitivo;
    private javax.swing.JList<String> jLListaNomeFuncionarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFNomeNovaRole;
    // End of variables declaration//GEN-END:variables
}
