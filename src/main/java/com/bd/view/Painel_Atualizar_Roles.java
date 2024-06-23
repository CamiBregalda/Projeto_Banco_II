/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.infra.Login;
import com.bd.mapper.FornecedorMapper;
import com.bd.mapper.FuncionarioMapper;
import com.bd.mapper.ItemMapper;
import com.bd.mapper.ProdutoMapper;
import com.bd.mapper.UsuarioMapper;
import com.bd.mapper.VendaMapper;
import com.bd.model.response.FuncionarioResponse;
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
import com.bd.service.ProdutoService;
import com.bd.service.UsuarioService;
import com.bd.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;
import org.mapstruct.factory.Mappers;


public class Painel_Atualizar_Roles extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
    List<String> papeis = new ArrayList<>();

    public Painel_Atualizar_Roles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        recebeDados();
        inicializandoClasses();
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
        jLBProdutos1 = new javax.swing.JLabel();
        jBTNBarraPesquisa1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTBFuncionarios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTBFuncionariosRole = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBTNAtualizar.setText("Atualizar");
        jBTNAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAtualizarMouseClicked(evt);
            }
        });

        jLBProdutos.setText("Funcionários que não estão na role:");

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBTituloDaPagina.setText("Buscar Papeis");

        jLBBarraPesquisa.setText("Buscar Papel/Adicionar novo:");

        jLBProdutos1.setText("Funcionários da role:");

        jBTNBarraPesquisa1.setText("Pesquisar");
        jBTNBarraPesquisa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNBarraPesquisa1MouseClicked(evt);
            }
        });

        jTBFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTBFuncionarios);

        jTBFuncionariosRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTBFuncionariosRole);

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLBProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLBProdutos1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBTNBarraPesquisa1)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNBarraPesquisa1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBProdutos)
                    .addComponent(jLBProdutos1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jBTNAtualizar)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNBarraPesquisa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNBarraPesquisa1MouseClicked
        String pesquisa = jTFBarraPesquisa.getText();
        
        List<String> papel = funcionarioService.buscarRoles();
        
        List<String> listaFuncionarios = funcionarioService.funcionarioPertenceRole(pesquisa);

    }//GEN-LAST:event_jBTNBarraPesquisa1MouseClicked

    private void jBTNAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAtualizarMouseClicked
        // conferir se a role existe
    }//GEN-LAST:event_jBTNAtualizarMouseClicked

    private void recebeDados(){
        List<FuncionarioResponse> listaFuncionarios = funcionarioService.buscarFuncionarios();
        papeis = funcionarioService.buscarRoles();
        
        DefaultTableModel tabelaFuncionarios = (DefaultTableModel) jTBFuncionarios.getModel();
        tabelaFuncionarios.setRowCount(0);
        
        DefaultTableModel tabelaFuncionariosRole = (DefaultTableModel) jTBFuncionarios.getModel();
        tabelaFuncionariosRole.setRowCount(0);
/*
        for (int i = 0; i < produtos.size(); i++) {
            FornecedorResponse fornecedor = fornecedorService.buscarFornecedorPeloId(Long.valueOf(produtos.get(i).tb_fornecedores_for_codigo()));
            tabela.addRow(new Object[]{produtos.get(i).pro_codigo(), produtos.get(i).pro_descricao(), fornecedor.for_descricao()});
        }
        
        for (int i = 0; i < papel.size(); i++) {
            funcionarios.add(listaFuncionarios.get(i).fun_nome());
        }
        
       
        
        if(!role.isEmpty()){
            for (int i = 0; i < papel.size(); i++) {
                if(papel.get(i) == role){
                    List<String> funcionarios = funcionarioService.funcionarioPertenceRole(role);
                    tabelaComRole.addRow(new Object[]{funcionarioService.funcionarioPertenceRole(papel.get(i))}); 
                   
                    tabelaSemRole.addRow(new Object[]{funcionarioService.buscarFuncionarios()});
                } 
            }
        }
        */
        //funcionarioPertenceRole
    }
    
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
    
    
    public static void main(String args[]) {
        
        Login.getInstance().setUser("postgres");//remover antes de subir no git
        Login.getInstance().setSenha("postgress");//possibilidade de logar
        
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
    private javax.swing.JButton jBTNBarraPesquisa1;
    private javax.swing.JLabel jLBBarraPesquisa;
    private javax.swing.JLabel jLBProdutos;
    private javax.swing.JLabel jLBProdutos1;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTBFuncionarios;
    private javax.swing.JTable jTBFuncionariosRole;
    private javax.swing.JTextField jTFBarraPesquisa;
    // End of variables declaration//GEN-END:variables
}
