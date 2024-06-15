package com.bd.view;

import com.bd.mapper.FornecedorMapper;
import com.bd.mapper.FuncionarioMapper;
import com.bd.mapper.ProdutoMapper;
import com.bd.mapper.UsuarioMapper;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.FornecedorRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.repository.ProdutoRepository;
import com.bd.repository.UsuarioRepository;
import com.bd.service.FornecedorService;
import com.bd.service.FuncionarioService;
import com.bd.service.ProdutoService;
import com.bd.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Painel_Funcionario extends javax.swing.JFrame {
    
    ProdutoService produtoService;
    FornecedorService fornecedorService;
    List<ProdutoResponse> produtos = new ArrayList<ProdutoResponse>();
    
    public Painel_Funcionario() {
        setTitle("Tela Estoque de Funcionario");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTFBarraPesquisa = new javax.swing.JTextField();
        jBTNBarraPesquisa = new javax.swing.JButton();
        jLBProdutos = new javax.swing.JLabel();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jLBBarraPesquisa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBProdutos = new javax.swing.JTable();
        jBTCadastrarProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBTNBarraPesquisa.setText("Pesquisar");
        jBTNBarraPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNBarraPesquisaMouseClicked(evt);
            }
        });

        jLBProdutos.setText("Produtos:");

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setText("Estoque de Produtos");

        jLBBarraPesquisa.setText("Buscar Produto:");

        jTBProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Fornecedor"
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
        jTBProdutos.setColumnSelectionAllowed(true);
        jTBProdutos.getTableHeader().setReorderingAllowed(false);
        jTBProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBProdutosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTBProdutos);

        jBTCadastrarProduto.setText("Cadastrar Novo Produto");
        jBTCadastrarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTCadastrarProdutoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jLBTituloDaPagina)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBProdutos)
                            .addComponent(jLBBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBTNBarraPesquisa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBTCadastrarProduto))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLBBarraPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFBarraPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBTNBarraPesquisa)
                    .addComponent(jBTCadastrarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLBProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNBarraPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNBarraPesquisaMouseClicked
        produtos = produtoService.buscarProdutos();
        
        DefaultTableModel tabela = (DefaultTableModel) jTBProdutos.getModel();
        tabela.setRowCount(0);
            
        for (int i = 0; i < produtos.size(); i++) {
            FornecedorResponse fornecedor = fornecedorService.buscarFornecedorPeloId(Long.valueOf(produtos.get(i).tb_fornecedores_for_codigo()));
            
            tabela.addRow(new Object[]{produtos.get(i).pro_codigo(), produtos.get(i).pro_descricao(), fornecedor.for_descricao()});
        }
    }//GEN-LAST:event_jBTNBarraPesquisaMouseClicked

    private void jTBProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBProdutosMouseClicked
        String produtoId = jTBProdutos.getValueAt(jTBProdutos.getSelectedRow(), 0).toString();
        int i;
        
        for (i = 0; i < produtos.size(); i++) {
            if(produtos.get(i).pro_codigo().equals(produtoId)){
                break;
            }  
        }
        
        FornecedorResponse fornecedor = fornecedorService.buscarFornecedorPeloId(Long.valueOf(produtos.get(i).tb_fornecedores_for_codigo()));
        
        Painel_Venda_Funcionario vendaFuncionario = new Painel_Venda_Funcionario(this, true);
        vendaFuncionario.pro_codigo = produtos.get(i).pro_codigo();
        
        //Criar método recebeProdutos que seta os valores na tela Painel_Venda_Funcionario
        vendaFuncionario.recebeProduto(produtos.get(i).pro_descricao(), produtos.get(i).pro_valor(), produtos.get(i).pro_quantidade(), fornecedor.for_descricao());
        vendaFuncionario.setLocationRelativeTo(this);
        vendaFuncionario.setVisible(true);
    }//GEN-LAST:event_jTBProdutosMouseClicked

    private void jBTCadastrarProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTCadastrarProdutoMouseClicked
        Painel_Cadastro_Produto cadastroProduto = new Painel_Cadastro_Produto(this, true);
        cadastroProduto.setLocationRelativeTo(this);
        cadastroProduto.setVisible(true);
    }//GEN-LAST:event_jBTCadastrarProdutoMouseClicked

     private void inicializandoClasses(){/*
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioMapper usuarioMapper = null;
        usuarioService = new UsuarioService(usuarioRepository, usuarioMapper);*/
        
        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoMapper produtoMapper = null;
        produtoService = new ProdutoService(produtoRepository, produtoMapper);
        
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        FornecedorMapper fornecedorMapper = null;
        fornecedorService = new FornecedorService(fornecedorRepository, fornecedorMapper);
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
            java.util.logging.Logger.getLogger(Painel_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Painel_Funcionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTCadastrarProduto;
    private javax.swing.JButton jBTNBarraPesquisa;
    private javax.swing.JLabel jLBBarraPesquisa;
    private javax.swing.JLabel jLBProdutos;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTBProdutos;
    private javax.swing.JTextField jTFBarraPesquisa;
    // End of variables declaration//GEN-END:variables
}
