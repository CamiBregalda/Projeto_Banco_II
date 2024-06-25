package com.bd.view;

import com.bd.mapper.*;
import com.bd.model.request.ProdutoRequest;
import com.bd.model.response.FornecedorResponse;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.*;
import com.bd.service.*;
import java.util.List;
import org.mapstruct.factory.Mappers;

import javax.swing.*;

public class Painel_Cadastro_Produto extends javax.swing.JDialog {
    ProdutoService produtoService;
    FornecedorService fornecedorService;

    public Painel_Cadastro_Produto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastro de Produto");
        inicializandoClasses();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLBTituloDaPagina = new javax.swing.JLabel();
        jLBDescricaoProduto = new javax.swing.JLabel();
        jTFDescricaoProduto = new javax.swing.JTextField();
        jLBValorProduto = new javax.swing.JLabel();
        jTFValorProduto = new javax.swing.JTextField();
        jLBQuantidadeProduto = new javax.swing.JLabel();
        jBTNCadastrarProduto = new javax.swing.JButton();
        jTFQuantidadeProduto = new javax.swing.JTextField();
        jLBFonecedores = new javax.swing.JLabel();
        jCBFornecedores = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLBTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBTituloDaPagina.setText("Cadastre o Produto");
        jLBTituloDaPagina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLBDescricaoProduto.setText("Descrição do Produto:");

        jLBValorProduto.setText("Valor:");

        jLBQuantidadeProduto.setText("Quantidade:");

        jBTNCadastrarProduto.setText("Cadastrar");
        jBTNCadastrarProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNCadastrarProdutoMouseClicked(evt);
            }
        });

        jLBFonecedores.setText("Fornecedores:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jBTNCadastrarProduto)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLBFonecedores)
                    .addComponent(jTFDescricaoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLBDescricaoProduto, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLBQuantidadeProduto, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLBValorProduto, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTFValorProduto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFQuantidadeProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
                    .addComponent(jCBFornecedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBDescricaoProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFDescricaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBValorProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLBQuantidadeProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFQuantidadeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBFonecedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jBTNCadastrarProduto)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNCadastrarProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNCadastrarProdutoMouseClicked
        String descricaoProduto = jTFDescricaoProduto.getText();
        String valorProduto = jTFValorProduto.getText();
        String quantidadeProduto = jTFQuantidadeProduto.getText();
        String fornecedor = jCBFornecedores.getSelectedItem().toString();
        
        
        String[] partes = fornecedor.split(" - ");
        int fornecedorId = 0;
        if (partes.length > 0) {
             fornecedorId = Integer.parseInt(partes[0]);
        }

        ProdutoResponse produto = produtoService.cadastrarProduto(new ProdutoRequest( null, descricaoProduto, Double.parseDouble(valorProduto), Integer.parseInt(quantidadeProduto), fornecedorId));

        if(produto == null){
            JOptionPane.showMessageDialog(this, "Produto não foi cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
        }
        
        this.dispose();
    }//GEN-LAST:event_jBTNCadastrarProdutoMouseClicked

    public void recebeDados (){
       List<FornecedorResponse> fornecedores = fornecedorService.buscarFornecedores();
       
       for (int i = 0; i < fornecedores.size(); i++){
           jCBFornecedores.addItem(fornecedores.get(i).for_codigo().toString() + " - " + fornecedores.get(i).for_descricao());
       }
    }
    
    private void inicializandoClasses(){
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        FornecedorMapper fornecedorMapper = Mappers.getMapper(FornecedorMapper.class);
        fornecedorService = new FornecedorService(fornecedorRepository, fornecedorMapper);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        ProdutoMapper produtoMapper = Mappers.getMapper(ProdutoMapper.class);
        produtoService = new ProdutoService(produtoRepository, produtoMapper);
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
            java.util.logging.Logger.getLogger(Painel_Cadastro_Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Produto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Cadastro_Produto dialog = new Painel_Cadastro_Produto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNCadastrarProduto;
    private javax.swing.JComboBox<String> jCBFornecedores;
    private javax.swing.JLabel jLBDescricaoProduto;
    private javax.swing.JLabel jLBFonecedores;
    private javax.swing.JLabel jLBQuantidadeProduto;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JLabel jLBValorProduto;
    private javax.swing.JTextField jTFDescricaoProduto;
    private javax.swing.JTextField jTFQuantidadeProduto;
    private javax.swing.JTextField jTFValorProduto;
    // End of variables declaration//GEN-END:variables
}
