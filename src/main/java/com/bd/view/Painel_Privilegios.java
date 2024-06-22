/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author user
 */
public class Painel_Privilegios extends javax.swing.JDialog {

 
    public Painel_Privilegios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Tela de Privilegio");
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLBTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCBFuncionario = new javax.swing.JComboBox<>();
        jLBConcederPapel = new javax.swing.JLabel();
        jCBConcederPapel = new javax.swing.JComboBox<>();
        jLBTabelaDesejada = new javax.swing.JLabel();
        jCBTabelaDesejada = new javax.swing.JComboBox<>();
        jLbPrivilegiosConcedidos = new javax.swing.JLabel();
        jCHBSelect = new javax.swing.JCheckBox();
        jCHBUpdate = new javax.swing.JCheckBox();
        jCHBInsert = new javax.swing.JCheckBox();
        jCHBDelete = new javax.swing.JCheckBox();
        jBTNovoPapel = new javax.swing.JButton();
        jBTNAtualizarPapel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLBTitulo.setText("Privilégios");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Deseja conceder privilegios à :");

        jCBFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Indivídio", "Grupo" }));

        jLBConcederPapel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLBConcederPapel.setText("Deseja conceder o papel:");

        jCBConcederPapel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLBTabelaDesejada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLBTabelaDesejada.setText("Tabela desejada:");

        jCBTabelaDesejada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLbPrivilegiosConcedidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLbPrivilegiosConcedidos.setText("Privilégios concedidos:");

        jCHBSelect.setText("SELECT TABLE");

        jCHBUpdate.setText("UPDATE TABLE");

        jCHBInsert.setText("INSERT TABLE");

        jCHBDelete.setText("DELETE TABLE");

        jBTNovoPapel.setText("Criar");
        jBTNovoPapel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNovoPapelMouseClicked(evt);
            }
        });

        jBTNAtualizarPapel.setText("Atualizar");

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
                            .addComponent(jCBConcederPapel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBFuncionario, 0, 306, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
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
                                .addComponent(jBTNAtualizarPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addComponent(jCBFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBConcederPapel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBConcederPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBTabelaDesejada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBTabelaDesejada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNovoPapel)
                    .addComponent(jBTNAtualizarPapel))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNovoPapelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNovoPapelMouseClicked
       JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
       Painel_Cadastrar_Role cadatroRole = new Painel_Cadastrar_Role(frame, true);
       cadatroRole.setLocationRelativeTo(this);
       cadatroRole.setVisible(true);
    }//GEN-LAST:event_jBTNovoPapelMouseClicked

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
                Painel_Privilegios dialog = new Painel_Privilegios(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNovoPapel;
    private javax.swing.JComboBox<String> jCBConcederPapel;
    private javax.swing.JComboBox<String> jCBFuncionario;
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
