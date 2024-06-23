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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;


public class Painel_Roles extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;

    public Painel_Roles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTFBarraPesquisa = new javax.swing.JTextField();
        jBTNAtualizar = new javax.swing.JButton();
        jLBProdutos = new javax.swing.JLabel();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jLBBarraPesquisa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLListaFuncionarios = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLListaFuncionarios1 = new javax.swing.JList<>();
        jLBProdutos1 = new javax.swing.JLabel();
        jBTNBarraPesquisa1 = new javax.swing.JButton();
        jBTNCriar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBTNAtualizar.setText("Atualizar");
        jBTNAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAtualizarMouseClicked(evt);
            }
        });

        jLBProdutos.setText("Funcionários que não estão na role:");

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setText("Buscar Papeis");

        jLBBarraPesquisa.setText("Buscar Papel/Adicionar novo:");

        jScrollPane1.setViewportView(jLListaFuncionarios);

        jScrollPane2.setViewportView(jLListaFuncionarios1);

        jLBProdutos1.setText("Funcionários da role:");

        jBTNBarraPesquisa1.setText("Pesquisar");
        jBTNBarraPesquisa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNBarraPesquisa1MouseClicked(evt);
            }
        });

        jBTNCriar.setText("Criar");
        jBTNCriar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNCriarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jLBTituloDaPagina)
                .addGap(0, 258, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jBTNCriar)
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLBProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(jTFBarraPesquisa))
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBProdutos1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jBTNAtualizar))
                            .addComponent(jBTNBarraPesquisa1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLBBarraPesquisa)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNBarraPesquisa1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBProdutos)
                    .addComponent(jLBProdutos1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNCriar)
                    .addComponent(jBTNAtualizar))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNBarraPesquisa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNBarraPesquisa1MouseClicked
                                             
        String pesquisa = jTFBarraPesquisa.getText();
        jLListaFuncionarios.setEnabled(false);
        
        List<String> listaFuncionarios = funcionarioService.funcionarioPertenceRole(pesquisa);

        // Limpa o modelo da lista existente antes de adicionar novos elementos
        DefaultListModel<String> modeloLista = (DefaultListModel<String>) jLListaFuncionarios.getModel();
        modeloLista.clear();

        
        for (String funcionario : listaFuncionarios) {
            modeloLista.addElement(funcionario);
        }  // TODO add your handling code here:
    }//GEN-LAST:event_jBTNBarraPesquisa1MouseClicked

    private void jBTNCriarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNCriarMouseClicked
        Painel_Cadastrar_Role cadastrar = new Painel_Cadastrar_Role(this, true);
        cadastrar.setLocationRelativeTo(this);
        cadastrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBTNCriarMouseClicked

    private void jBTNAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAtualizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTNAtualizarMouseClicked

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
            java.util.logging.Logger.getLogger(Painel_Roles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Roles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Roles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Roles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Roles dialog = new Painel_Roles(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNAtualizar;
    private javax.swing.JButton jBTNBarraPesquisa1;
    private javax.swing.JButton jBTNCriar;
    private javax.swing.JLabel jLBBarraPesquisa;
    private javax.swing.JLabel jLBProdutos;
    private javax.swing.JLabel jLBProdutos1;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JList<String> jLListaFuncionarios;
    private javax.swing.JList<String> jLListaFuncionarios1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFBarraPesquisa;
    // End of variables declaration//GEN-END:variables
}
