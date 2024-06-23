/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.infra.Login;
import com.bd.mapper.FuncionarioMapper;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.BackupRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.mapstruct.factory.Mappers;

public class Painel_Atualizar_Roles extends javax.swing.JDialog {

    FuncionarioService funcionarioService;
    List<String> listaPapeis = new ArrayList<>();
    List<FuncionarioResponse> listaFuncionarios = new ArrayList<>();

    public Painel_Atualizar_Roles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Tela Atualizar Papeis");
        initComponents();
        inicializandoClasses();
        recebeDados();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBTNAtualizar = new javax.swing.JButton();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jLBBarraPesquisa = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBFuncionarios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBFuncionariosRole = new javax.swing.JTable();
        jCBPapel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBTNAtualizar.setText("Atualizar");
        jBTNAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAtualizarMouseClicked(evt);
            }
        });

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBTituloDaPagina.setText("Buscar Papeis");

        jLBBarraPesquisa.setText("Escolher Papel:");

        jTBFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Funcionarios Não Pertencentes a Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTBFuncionarios);

        jTBFuncionariosRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionarios Pertencentes a Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTBFuncionariosRole);

        jCBPapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBPapelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                    .addComponent(jCBPapel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jBTNAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLBBarraPesquisa)
                .addGap(7, 7, 7)
                .addComponent(jCBPapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jBTNAtualizar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAtualizarMouseClicked
        List<String> funcionarios = new ArrayList<>();
        String role = jCBPapel.getSelectedItem().toString();
        
        for (int i = 0; i < jTBFuncionariosRole.getRowCount(); i++) {
            String nomeFuncionario = (String) jTBFuncionariosRole.getValueAt(i, 0);
            funcionarios.add(nomeFuncionario);
        }
        
        List<String> fun = funcionarioService.funcionarioPertenceRole(role);
        
        funcionarios = funcionarios.stream()
            .filter(funcionario -> !fun.contains(funcionario))
            .collect(Collectors.toList());

        System.out.println(funcionarios.get(0));
        
        funcionarioService.atualizarUsersRole(role, funcionarios);
    }//GEN-LAST:event_jBTNAtualizarMouseClicked

    private void jTBFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBFuncionariosMouseClicked
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        DefaultTableModel tabelaFuncionariosRole = (DefaultTableModel) jTBFuncionariosRole.getModel();
       
        String user = jTBFuncionarios.getValueAt(jTBFuncionarios.getSelectedRow(), 0).toString();
        
        tabelaFuncionarios.removeRow(jTBFuncionarios.getSelectedRow());
        tabelaFuncionariosRole.addRow(new Object[] {user});
    }//GEN-LAST:event_jTBFuncionariosMouseClicked

    private void jCBPapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBPapelActionPerformed
        String role = jCBPapel.getSelectedItem().toString();
        
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        tabelaFuncionarios.setRowCount(0);
        
        DefaultTableModel tabelaFuncionariosRole = (DefaultTableModel) jTBFuncionariosRole.getModel();
        tabelaFuncionariosRole.setRowCount(0);
        
        if(!role.isEmpty()){
            if(listaPapeis.contains(role)){
                List<String> fun = funcionarioService.funcionarioPertenceRole(role);
                
                for (String funcionario : fun){
                    tabelaFuncionariosRole.addRow(new Object[]{funcionario});
                }
                
                for (FuncionarioResponse funcionario : listaFuncionarios){
                    if(!fun.contains(funcionario.fun_nome())){
                        tabelaFuncionarios.addRow(new Object[]{funcionario.fun_nome()});
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Papel não encontrado!");
            }
        }
    }//GEN-LAST:event_jCBPapelActionPerformed

    private void recebeDados(){
        listaFuncionarios = funcionarioService.buscarFuncionarios();
        listaPapeis = funcionarioService.buscarRoles();
        
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        tabelaFuncionarios.setRowCount(0);
        
        for (FuncionarioResponse funcionario : listaFuncionarios){
            tabelaFuncionarios.addRow(new Object[]{funcionario.fun_nome()});
        }
        
        for (String papel : listaPapeis) {
            jCBPapel.addItem(papel);
        }
    }
    
    private void inicializandoClasses(){
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        BackupRepository backupRepository = new BackupRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, backupRepository, funcionarioMapper);
    }
        
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Atualizar_Roles dialog = new Painel_Atualizar_Roles(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jCBPapel;
    private javax.swing.JLabel jLBBarraPesquisa;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTBFuncionarios;
    private javax.swing.JTable jTBFuncionariosRole;
    // End of variables declaration//GEN-END:variables
}
