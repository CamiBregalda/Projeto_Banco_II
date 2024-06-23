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
import org.mapstruct.factory.Mappers;

/**
 *
 * @author gui
 */
public class Painel_Cadastrar_Role extends javax.swing.JDialog {
    
    FuncionarioService funcionarioService;
    List<String> listaPapeis = new ArrayList<>();
    List<FuncionarioResponse> listaFuncionarios = new ArrayList<>();

    public Painel_Cadastrar_Role(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Tela Cadastrar Papel");
        initComponents();
        inicializandoClasses();
        recebeDados();
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFNomeNovaRole = new javax.swing.JTextField();
        jBTCriarRole = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBFuncionarios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBFuncionariosRole = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Criar um novo Papel");

        jLabel2.setText("Nome do Novo Papel:");

        jBTCriarRole.setText("Criar Papel");
        jBTCriarRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTCriarRoleMouseClicked(evt);
            }
        });

        jTBFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Todos os Funcionarios"
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
                "Funcionarios Adicionados"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTFNomeNovaRole, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(jBTCriarRole)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jBTCriarRole)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTCriarRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTCriarRoleMouseClicked
        String nomeRole = jTFNomeNovaRole.getText();
        
        ArrayList<String> funcionarios = new ArrayList<>();
        
        for (int i = 0; i < jTBFuncionariosRole.getRowCount(); i++) {
            String nomeFuncionario = (String) jTBFuncionariosRole.getValueAt(i, 1);
            funcionarios.add(nomeFuncionario);
        }

        funcionarioService.cadastrarRole(nomeRole, funcionarios);
    }//GEN-LAST:event_jBTCriarRoleMouseClicked

    private void jTBFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBFuncionariosMouseClicked
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        DefaultTableModel tabelaFuncionariosRole = (DefaultTableModel) jTBFuncionariosRole.getModel();
       
        String user = jTBFuncionarios.getValueAt(jTBFuncionarios.getSelectedRow(), 0).toString();
        
        tabelaFuncionarios.removeRow(jTBFuncionarios.getSelectedRow());
        tabelaFuncionariosRole.addRow(new Object[] {user});
    }//GEN-LAST:event_jTBFuncionariosMouseClicked

    private void recebeDados(){
        listaFuncionarios = funcionarioService.buscarFuncionarios();
        listaPapeis = funcionarioService.buscarRoles();
        
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        tabelaFuncionarios.setRowCount(0);
        
        for (FuncionarioResponse funcionario : listaFuncionarios){
            tabelaFuncionarios.addRow(new Object[]{funcionario.fun_nome()});
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTBFuncionarios;
    private javax.swing.JTable jTBFuncionariosRole;
    private javax.swing.JTextField jTFNomeNovaRole;
    // End of variables declaration//GEN-END:variables
}
