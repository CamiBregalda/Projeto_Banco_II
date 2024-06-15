package com.bd.view;

public class Painel_Tela_Inicial extends javax.swing.JFrame {

    public Painel_Tela_Inicial() {
        setTitle("Tela Inicial");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLBEntrar = new javax.swing.JLabel();
        jTBEntrarFuncionario = new javax.swing.JToggleButton();
        jTBEntrarUsuario = new javax.swing.JToggleButton();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLBNaoPossuiCadastro = new javax.swing.JLabel();
        jBTCadastrarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLBEntrar.setText("Como Deseja Entrar:");

        jTBEntrarFuncionario.setText("Funcionario");
        jTBEntrarFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBEntrarFuncionarioMouseClicked(evt);
            }
        });

        jTBEntrarUsuario.setText("Usuario");
        jTBEntrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBEntrarUsuarioMouseClicked(evt);
            }
        });

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setText("Bando's Vendas Online");

        jLBNaoPossuiCadastro.setText("Não Possui Cadastro?");

        jBTCadastrarUsuario.setText("Cadastrar");
        jBTCadastrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTCadastrarUsuarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLBEntrar)
                .addGap(139, 139, 139))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLBNaoPossuiCadastro))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTBEntrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTBEntrarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLBTituloDaPagina)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jBTCadastrarUsuario)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLBTituloDaPagina)
                .addGap(27, 27, 27)
                .addComponent(jLBEntrar)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTBEntrarFuncionario)
                    .addComponent(jTBEntrarUsuario))
                .addGap(40, 40, 40)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLBNaoPossuiCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTCadastrarUsuario)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTCadastrarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTCadastrarUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jBTCadastrarUsuarioMouseClicked

    private void jTBEntrarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBEntrarUsuarioMouseClicked
       Painel_Login login = new Painel_Login(this, true);
       login.pessoa = "Usuario";
       login.setLocationRelativeTo(this);
       login.setVisible(true);
    }//GEN-LAST:event_jTBEntrarUsuarioMouseClicked

    private void jTBEntrarFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBEntrarFuncionarioMouseClicked
       Painel_Login login = new Painel_Login(this, true);
       login.pessoa = "Funcionario";  
       login.setLocationRelativeTo(this);
       login.setVisible(true);
    }//GEN-LAST:event_jTBEntrarFuncionarioMouseClicked

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
            java.util.logging.Logger.getLogger(Painel_Tela_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Tela_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Tela_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Tela_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Painel_Tela_Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTCadastrarUsuario;
    private javax.swing.JLabel jLBEntrar;
    private javax.swing.JLabel jLBNaoPossuiCadastro;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jTBEntrarFuncionario;
    private javax.swing.JToggleButton jTBEntrarUsuario;
    // End of variables declaration//GEN-END:variables
}
