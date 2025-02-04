/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bd.view;

import com.bd.model.Funcionario;
import com.bd.service.FuncionarioService;
import com.bd.mapper.*;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.*;
import com.bd.service.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.mapstruct.factory.Mappers;


public class Painel_Gerente extends javax.swing.JFrame {

    FornecedorService fornecedorService;
    FuncionarioService funcionarioService;

    public Painel_Gerente() {
        setTitle("Tela Gerente");
        inicializandoClasses();
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTFBarraPesquisa = new javax.swing.JTextField();
        jBTNBarraPesquisa = new javax.swing.JButton();
        jCBFuncionario = new javax.swing.JCheckBox();
        jCBFornecedor = new javax.swing.JCheckBox();
        jLBFuncionario = new javax.swing.JLabel();
        jLBFornecedor = new javax.swing.JLabel();
        jCBFuncoes = new javax.swing.JComboBox<>();
        jBTNAplicarFuncoes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBFuncionario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBFornecedor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBTNBarraPesquisa.setText("Pesquisar");
        jBTNBarraPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNBarraPesquisaMouseClicked(evt);
            }
        });

        jCBFuncionario.setText("Funcionário");

        jCBFornecedor.setText("Fornecedor");

        jLBFuncionario.setText("Funcionários:");

        jLBFornecedor.setText("Fornecedores:");

        jCBFuncoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Backup", "Privilégios" }));

        jBTNAplicarFuncoes.setText("Aplicar");
        jBTNAplicarFuncoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAplicarFuncoesMouseClicked(evt);
            }
        });

        jTBFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Cargo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTBFuncionario);

        jTBFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTBFornecedor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBTNBarraPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jCBFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBTNAplicarFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLBFuncionario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 555, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLBFornecedor)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNBarraPesquisa)
                    .addComponent(jCBFuncoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNAplicarFuncoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBFuncionario)
                    .addComponent(jCBFornecedor))
                .addGap(38, 38, 38)
                .addComponent(jLBFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNAplicarFuncoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAplicarFuncoesMouseClicked
        String opcao = jCBFuncoes.getSelectedItem().toString();

        if(opcao == "Backup"){
            Painel_Backup telaBackup = new Painel_Backup(this, true);
            telaBackup.setLocationRelativeTo(this);
            telaBackup.setVisible(true);

        }
        else if(opcao == "Privilégios"){
            Painel_Privilegios telaPrivilegios = new Painel_Privilegios();
            telaPrivilegios.setLocationRelativeTo(this);
            telaPrivilegios.setVisible(true);
        }
    }//GEN-LAST:event_jBTNAplicarFuncoesMouseClicked

    private void jBTNBarraPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNBarraPesquisaMouseClicked
        String pesquisa = jTFBarraPesquisa.getText();

        List<FuncionarioResponse> listaFuncionarios;
        List<FornecedorResponse> listaFornecedores;

        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionario.getModel();
        tabelaFuncionarios.setRowCount(0);

        DefaultTableModel tabelaFornecedores = (DefaultTableModel) jTBFornecedor.getModel();
        tabelaFornecedores.setRowCount(0);


        if(jCBFuncionario.isSelected() && pesquisa != null) {
            listaFuncionarios = funcionarioService.buscarFuncionariosPeloNome(pesquisa);
        } else {
            listaFuncionarios = funcionarioService.buscarFuncionarios();
        }
        for(int i = 0; i < listaFuncionarios.size(); i++){
            tabelaFuncionarios.addRow(new Object[]{listaFuncionarios.get(i).fun_codigo().toString(), listaFuncionarios.get(i).fun_nome(), listaFuncionarios.get(i).fun_funcao()});
        }

        if(jCBFornecedor.isSelected() && pesquisa != null){
            listaFornecedores = fornecedorService.buscarFornecedoresPeloNome(pesquisa);
        } else {
            listaFornecedores = fornecedorService.buscarFornecedores();
        }
        for(int i = 0; i < listaFornecedores.size(); i++){
            tabelaFornecedores.addRow(new Object[]{listaFornecedores.get(i).for_codigo(), listaFornecedores.get(i).for_descricao()});
        }
    }//GEN-LAST:event_jBTNBarraPesquisaMouseClicked

//Adicionar função para listar os nomes quando forem pesquisados

    public void receberDados(){

        List<FuncionarioResponse> listaFuncionarios = funcionarioService.buscarFuncionarios();
        List<FornecedorResponse> listaFornecedores = fornecedorService.buscarFornecedores();

        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionario.getModel();
        tabelaFuncionarios.setRowCount(0);

        DefaultTableModel tabelaFornecedores = (DefaultTableModel) jTBFornecedor.getModel();
        tabelaFornecedores.setRowCount(0);

        for (int i = 0; i < listaFuncionarios.size(); i++) {
            tabelaFuncionarios.addRow(new Object[]{listaFuncionarios.get(i).fun_codigo().toString(), listaFuncionarios.get(i).fun_nome(), listaFuncionarios.get(i).fun_funcao()});
        }

        for (int i = 0; i < listaFornecedores.size(); i++) {
            tabelaFornecedores.addRow(new Object[]{listaFornecedores.get(i).for_codigo(), listaFornecedores.get(i).for_descricao()});
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
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Painel_Gerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNAplicarFuncoes;
    private javax.swing.JButton jBTNBarraPesquisa;
    private javax.swing.JCheckBox jCBFornecedor;
    private javax.swing.JCheckBox jCBFuncionario;
    private javax.swing.JComboBox<String> jCBFuncoes;
    private javax.swing.JLabel jLBFornecedor;
    private javax.swing.JLabel jLBFuncionario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTBFornecedor;
    private javax.swing.JTable jTBFuncionario;
    private javax.swing.JTextField jTFBarraPesquisa;
    // End of variables declaration//GEN-END:variables
}
