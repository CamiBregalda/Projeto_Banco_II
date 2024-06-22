package com.bd.view;

import com.bd.mapper.FuncionarioMapper;
import com.bd.repository.BackupRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.service.FuncionarioService;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;


public class Painel_Infos_Backup extends javax.swing.JDialog {

    FuncionarioService funcionarioService;
    
    public Painel_Infos_Backup(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        inicializandoClasses();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLBCredenciais = new javax.swing.JLabel();
        jLBHost = new javax.swing.JLabel();
        jTFHost = new javax.swing.JTextField();
        jLBPorta = new javax.swing.JLabel();
        jTFPorta = new javax.swing.JTextField();
        jLBUserName = new javax.swing.JLabel();
        jTFDatabase = new javax.swing.JTextField();
        jLBDatabase = new javax.swing.JLabel();
        jTFUsername = new javax.swing.JTextField();
        jLBSenhaBanco = new javax.swing.JLabel();
        jPFSenhaBanco = new javax.swing.JPasswordField();
        jBTNConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBCredenciais.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLBCredenciais.setText("Credenciais Para Backup");

        jLBHost.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBHost.setText("Host:");

        jLBPorta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBPorta.setText("Porta:");

        jLBUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBUserName.setText("Nome de Usuario:");

        jLBDatabase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBDatabase.setText("Banco de Dados:");

        jLBSenhaBanco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBSenhaBanco.setText("Senha:");

        jBTNConfirmar.setText("Confirmar");
        jBTNConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNConfirmarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLBHost, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTFHost, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLBPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLBDatabase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTFDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBUserName)
                            .addComponent(jLBSenhaBanco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPFSenhaBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jTFUsername))))
                .addGap(28, 28, 28))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLBCredenciais))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jBTNConfirmar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLBCredenciais)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBHost)
                    .addComponent(jTFHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBPorta))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBDatabase)
                    .addComponent(jTFDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBUserName)
                    .addComponent(jTFUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBSenhaBanco)
                    .addComponent(jPFSenhaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBTNConfirmar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNConfirmarMouseClicked
        String host = jTFHost.getText();
        String porta = jTFPorta.getText();
        String database = jTFDatabase.getText();
        String username = jTFUsername.getText();
        String password = new String (jPFSenhaBanco.getPassword());
        
        boolean backup = funcionarioService.realizarBackup(host, porta, username, database, password);
        
        if (backup == true){
            JOptionPane.showMessageDialog(this, "Backup realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Backup não pôde ser realizado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
    }//GEN-LAST:event_jBTNConfirmarMouseClicked

    private void inicializandoClasses(){
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        BackupRepository backupRepository = new BackupRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, backupRepository, funcionarioMapper);
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
            java.util.logging.Logger.getLogger(Painel_Infos_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Infos_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Infos_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Infos_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Infos_Backup dialog = new Painel_Infos_Backup(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton jBTNConfirmar;
    private javax.swing.JLabel jLBCredenciais;
    private javax.swing.JLabel jLBDatabase;
    private javax.swing.JLabel jLBHost;
    private javax.swing.JLabel jLBPorta;
    private javax.swing.JLabel jLBSenhaBanco;
    private javax.swing.JLabel jLBUserName;
    private javax.swing.JPasswordField jPFSenhaBanco;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFDatabase;
    private javax.swing.JTextField jTFHost;
    private javax.swing.JTextField jTFPorta;
    private javax.swing.JTextField jTFUsername;
    // End of variables declaration//GEN-END:variables
}
