/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.mapper.FornecedorMapper;
import com.bd.mapper.FuncionarioMapper;
import com.bd.mapper.ItemMapper;
import com.bd.mapper.ProdutoMapper;
import com.bd.mapper.UsuarioMapper;
import com.bd.mapper.VendaMapper;
import com.bd.repository.BackupRepository;
import com.bd.repository.FornecedorRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.repository.ItemRepository;
import com.bd.repository.ProdutoRepository;
import com.bd.repository.UsuarioRepository;
import com.bd.repository.VendaRepository;
import com.bd.service.FornecedorService;
import com.bd.service.FuncionarioService;
import com.bd.service.ItemService;
import com.bd.service.PostgreSQLBackup;
import com.bd.service.ProdutoService;
import com.bd.service.UsuarioService;
import com.bd.service.VendaService;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;


public class Painel_Backup extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
        
    public Painel_Backup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
       //atualizar no banco, e realizar o backup de maneira automatica
       String periodo = jCBAgendarBackup.getSelectedItem().toString();
       LocalDateTime data = LocalDateTime.now();
        
       if (periodo != "Selecione"){
            if(periodo == "Diariamente"){
                data = data.plusDays(1);
                //realizar todo dia
            }
            if (periodo == "Semanalmente"){
                data = data.plusDays(7);
                //realizar toda semana
            }
            if (periodo == "Mensalmente"){
                data = data.plusDays(30);
                //realizar todo mes
            }
            if (periodo == "Anualmente"){
                data = data.plusDays(365);
                //relizar todo ano
             }
            funcionarioService.programarBackup(data);
       }
       
       JOptionPane.showMessageDialog(this, "Backup agendado com sucesso!");
       this.dispose();
    }//GEN-LAST:event_jBTNAgendarBackupMouseClicked

    private void jBTNExecutarBackupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNExecutarBackupMouseClicked
        Painel_Infos_Backup infos = new Painel_Infos_Backup(this, true);
        infos.setLocationRelativeTo(this);
        infos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBTNExecutarBackupMouseClicked

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
