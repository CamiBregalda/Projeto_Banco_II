package com.bd.view;

import com.bd.mapper.FuncionarioMapper;
import com.bd.repository.BackupRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.service.FuncionarioService;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;


public class Painel_Backup extends javax.swing.JDialog {

    FuncionarioService funcionarioService;
        
    public Painel_Backup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Tela de Backup");
        inicializandoClasses();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBTNExecutarBackup = new javax.swing.JButton();
        jBTNAgendarBackup = new javax.swing.JButton();
        jCBAgendarBackup = new javax.swing.JComboBox<>();
        jLBTitulo = new javax.swing.JLabel();
        jLBAgendar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBTNExecutarBackup.setText("Executar");
        jBTNExecutarBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNExecutarBackupMouseClicked(evt);
            }
        });

        jBTNAgendarBackup.setText("Aplicar");
        jBTNAgendarBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAgendarBackupMouseClicked(evt);
            }
        });

        jCBAgendarBackup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Diariamente", "Semanalmente", "Mensalmente", "Anualmente" }));

        jLBTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLBTitulo.setText("Backup");

        jLBAgendar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBAgendar.setText("Agendar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLBAgendar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBAgendarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jBTNAgendarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLBTitulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jBTNExecutarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLBTitulo)
                .addGap(46, 46, 46)
                .addComponent(jLBAgendar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBAgendarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNAgendarBackup))
                .addGap(58, 58, 58)
                .addComponent(jBTNExecutarBackup)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNAgendarBackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAgendarBackupMouseClicked
        String periodo = jCBAgendarBackup.getSelectedItem().toString();
        LocalDateTime data = LocalDateTime.now();
        
        if (periodo != "Selecione"){
            if(periodo == "Diariamente"){
                data = data.plusDays(1);
            }
            if (periodo == "Semanalmente"){
                data = data.plusDays(7);
            }
            if (periodo == "Mensalmente"){
                data = data.plusDays(30);
            }
            if (periodo == "Anualmente"){
                data = data.plusDays(365);
             }
            funcionarioService.programarBackup(data);
            
            JOptionPane.showMessageDialog(this, "Backup agendado com sucesso!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Backup não pôde ser agendada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBTNAgendarBackupMouseClicked

    private void jBTNExecutarBackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNExecutarBackupMouseClicked
        Painel_Infos_Backup infos = new Painel_Infos_Backup(this, true);
        infos.setLocationRelativeTo(this);
        infos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBTNExecutarBackupMouseClicked

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
            java.util.logging.Logger.getLogger(Painel_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Backup dialog = new Painel_Backup(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNAgendarBackup;
    private javax.swing.JButton jBTNExecutarBackup;
    private javax.swing.JComboBox<String> jCBAgendarBackup;
    private javax.swing.JLabel jLBAgendar;
    private javax.swing.JLabel jLBTitulo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
