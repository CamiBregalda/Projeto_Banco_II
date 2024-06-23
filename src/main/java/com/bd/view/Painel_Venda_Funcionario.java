package com.bd.view;

import com.bd.mapper.*;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.*;
import com.bd.service.*;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;

public class Painel_Venda_Funcionario extends javax.swing.JDialog {

    long pro_codigo;
    FornecedorResponse fornecedor;
    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
    
    public Painel_Venda_Funcionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Venda Funcionário");
        inicializandoClasses();//ver depois 
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLBNomeProduto = new javax.swing.JLabel();
        jLBCodigo = new javax.swing.JLabel();
        jLBCodigoMostrar = new javax.swing.JLabel();
        jLBPreco = new javax.swing.JLabel();
        jTFPreco = new javax.swing.JTextField();
        jLBQtdEstoque = new javax.swing.JLabel();
        jTFEstoque = new javax.swing.JTextField();
        jLBFornecedor = new javax.swing.JLabel();
        jLBFornecedorMostrar = new javax.swing.JLabel();
        jBTNAlterar = new javax.swing.JButton();
        jBTNDeletarProduto = new javax.swing.JButton();
        jBTNVender = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBNomeProduto.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLBNomeProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBNomeProduto.setText("Nome do Produto");

        jLBCodigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBCodigo.setText("Codigo:");

        jLBPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBPreco.setText("Preço:");

        jLBQtdEstoque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBQtdEstoque.setText("Disponível em estoque:");

        jLBFornecedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBFornecedor.setText("Fornecedor:");

        jBTNAlterar.setText("Alterar");
        jBTNAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNAlterarMouseClicked(evt);
            }
        });

        jBTNDeletarProduto.setText("Deletar ");
        jBTNDeletarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNDeletarProdutoMouseClicked(evt);
            }
        });

        jBTNVender.setText("Vender");
        jBTNVender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNVenderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLBNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBQtdEstoque)
                            .addComponent(jLBFornecedor)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLBCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLBPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jBTNAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jTFPreco)
                            .addComponent(jLBFornecedorMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLBCodigoMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBTNDeletarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jBTNVender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLBNomeProduto)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLBCodigo)
                    .addComponent(jLBCodigoMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLBPreco)
                    .addComponent(jTFPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBQtdEstoque)
                    .addComponent(jTFEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBFornecedor)
                    .addComponent(jLBFornecedorMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jBTNVender)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNDeletarProduto)
                    .addComponent(jBTNAlterar))
                .addGap(26, 26, 26))
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
    
    private void jBTNAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNAlterarMouseClicked
        //Pega os novos valores dos TextField e faz um ProdutoRequest, passa esse produto para o método AtualizarProduto()
        String nome = jLBNomeProduto.getText();
        Double preco = Double.parseDouble(jTFPreco.getText());
        int estoque = Integer.parseInt(jTFEstoque.getText());
        
        ProdutoResponse atualizar = produtoService.atualizarProduto(pro_codigo, new ProdutoRequest((int) pro_codigo, nome, preco, estoque, fornecedor.for_codigo()));

        this.dispose();
    }//GEN-LAST:event_jBTNAlterarMouseClicked
  
    private void jBTNDeletarProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNDeletarProdutoMouseClicked
        boolean produto = produtoService.deletarProduto(pro_codigo);
        
        if(produto){
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso");
        } else {
            JOptionPane.showMessageDialog(this, "Produto não pode ser reemovido");
        }
        
        this.dispose();
    }//GEN-LAST:event_jBTNDeletarProdutoMouseClicked

    private void jBTNVenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNVenderMouseClicked
        String codigoProduto = jLBCodigoMostrar.getText();

        ProdutoResponse produto = produtoService.buscarProdutoPeloId(Long.parseLong(codigoProduto));

        Painel_Vendas vender = new Painel_Vendas(this, true);
        vender.setLocationRelativeTo(this);
        vender.produtoId = produto.pro_codigo();
        vender.recebeDados(produto);
        vender.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBTNVenderMouseClicked

    public void recebeDados(ProdutoResponse produto, FornecedorResponse fornecedor){
        jLBNomeProduto.setText(produto.pro_descricao());
        jLBCodigoMostrar.setText(Long.toString(produto.pro_codigo()));
        jLBFornecedorMostrar.setText(fornecedor.for_descricao());
        jTFPreco.setText(Double.toString(produto.pro_valor()));
        jTFEstoque.setText(Integer.toString(produto.pro_quantidade()));
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
            java.util.logging.Logger.getLogger(Painel_Venda_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Venda_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Venda_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Venda_Funcionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Venda_Funcionario dialog = new Painel_Venda_Funcionario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNAlterar;
    private javax.swing.JButton jBTNDeletarProduto;
    private javax.swing.JButton jBTNVender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLBCodigo;
    private javax.swing.JLabel jLBCodigoMostrar;
    private javax.swing.JLabel jLBFornecedor;
    private javax.swing.JLabel jLBFornecedorMostrar;
    private javax.swing.JLabel jLBNomeProduto;
    private javax.swing.JLabel jLBPreco;
    private javax.swing.JLabel jLBQtdEstoque;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFEstoque;
    private javax.swing.JTextField jTFPreco;
    // End of variables declaration//GEN-END:variables
}
