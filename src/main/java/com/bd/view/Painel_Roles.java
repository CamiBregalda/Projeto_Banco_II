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


public class Painel_Roles extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;

    public Painel_Roles(java.awt.Frame parent, boolean modal) {
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLBProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(jTFBarraPesquisa)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jBTNCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLBProdutos1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBTNBarraPesquisa1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBTNAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)))))
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

        //utilizar o "is empty"
        
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
        // conferir se a role existe
    }//GEN-LAST:event_jBTNAtualizarMouseClicked

    private void recebeDados(){
        List<FuncionarioResponse> listaFuncionarios = funcionarioService.buscarFuncionarios();
        String role = jTFBarraPesquisa.getText();
        ArrayList<String> papel = funcionarioService.buscarRoles();
        

        DefaultTableModel tabelaSemRole = (DefaultTableModel) jLListaFuncionarios.getModel();
        tabelaSemRole.setRowCount(0);
        
        DefaultTableModel tabelaComRole = (DefaultTableModel) jLListaFuncionarios1.getModel();
        tabelaComRole.setRowCount(0);
        
        
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
