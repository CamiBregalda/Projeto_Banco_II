/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.infra.Login;
import com.bd.mapper.*;
import com.bd.model.request.VendaRequest;
import com.bd.model.response.FuncionarioResponse;
import com.bd.model.response.ProdutoResponse;
import com.bd.repository.*;
import com.bd.service.*;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;

public class Painel_Vendas extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;
    Integer produtoId;
    
    public Painel_Vendas(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        setTitle("Venda de Itens");
        inicializandoClasses();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLBNomeProduto = new javax.swing.JLabel();
        jLBQuantidade = new javax.swing.JLabel();
        jLBPreco = new javax.swing.JLabel();
        jLBPrecoMostrar = new javax.swing.JLabel();
        jLBValorTotal = new javax.swing.JLabel();
        jLBValorTotalMostrar = new javax.swing.JLabel();
        jLBQtdEstoque = new javax.swing.JLabel();
        jLBQtdEstoqueMostrar = new javax.swing.JLabel();
        jBTNComprar = new javax.swing.JButton();
        jCBListaFuncionarios = new javax.swing.JComboBox<>();
        jLBListaFuncionarios = new javax.swing.JLabel();
        jSPNQuantidade = new javax.swing.JSpinner();
        jBTNCalcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBNomeProduto.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLBNomeProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBNomeProduto.setText("Nome do Produto");

        jLBQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBQuantidade.setText("Quantidade:");

        jLBPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBPreco.setText("Preço:");

        jLBValorTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBValorTotal.setText("Valor total:");

        jLBQtdEstoque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBQtdEstoque.setText("Disponível em estoque:");

        jBTNComprar.setText("Comprar");
        jBTNComprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNComprarMouseClicked(evt);
            }
        });

        jLBListaFuncionarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBListaFuncionarios.setText("Funcionário responsável:");

        jBTNCalcular.setText("Calcular Total");
        jBTNCalcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNCalcularMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLBNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLBQtdEstoque)
                            .addComponent(jLBQuantidade)
                            .addComponent(jLBListaFuncionarios))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCBListaFuncionarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLBQtdEstoqueMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLBPrecoMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jSPNQuantidade))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBValorTotal)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jBTNCalcular)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBValorTotalMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBTNComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 39, Short.MAX_VALUE)))))
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLBNomeProduto)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBListaFuncionarios)
                    .addComponent(jCBListaFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBPreco)
                    .addComponent(jLBPrecoMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLBQtdEstoque)
                    .addComponent(jLBQtdEstoqueMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBQuantidade)
                    .addComponent(jSPNQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBValorTotal)
                    .addComponent(jLBValorTotalMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNCalcular)
                    .addComponent(jBTNComprar))
                .addGap(37, 37, 37))
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

    private void jBTNComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNComprarMouseClicked
        String nomeFuncionario = jCBListaFuncionarios.getSelectedItem().toString();
        String quantidade = jSPNQuantidade.getValue().toString();  
        
        FuncionarioResponse funcionario = funcionarioService.buscarFuncionarioPeloNome(nomeFuncionario);
       
        String venda = vendaService.realizarVenda(funcionario.fun_codigo(), produtoId, Integer.parseInt(quantidade));
        
        JOptionPane.showMessageDialog(this, venda);
    }//GEN-LAST:event_jBTNComprarMouseClicked

    private void jBTNCalcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNCalcularMouseClicked
        double valor = Double.parseDouble(jLBPrecoMostrar.getText().replace(",", "."));
        int quantidadeCompra = (Integer) jSPNQuantidade.getValue();
      
        double preco = valor * quantidadeCompra;
        System.out.println("Preco: " + preco);
        jLBValorTotalMostrar.setText(String.format("%.2f", preco));
    }//GEN-LAST:event_jBTNCalcularMouseClicked

    public void recebeDados(ProdutoResponse produto){
       jLBNomeProduto.setText(produto.pro_descricao());
      
        List<FuncionarioResponse> funcionarios = funcionarioService.buscarFuncionarios();
        for (FuncionarioResponse funcionario : funcionarios) {
            jCBListaFuncionarios.addItem(funcionario.fun_nome());
        }

       jLBPrecoMostrar.setText(String.format("%.2f", produto.pro_valor()));
       jLBQtdEstoqueMostrar.setText(String.valueOf(produto.pro_quantidade()));
       jLBValorTotalMostrar.setText("0.0");
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Vendas dialog = new Painel_Vendas(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton jBTNCalcular;
    private javax.swing.JButton jBTNComprar;
    private javax.swing.JComboBox<String> jCBListaFuncionarios;
    private javax.swing.JLabel jLBListaFuncionarios;
    private javax.swing.JLabel jLBNomeProduto;
    private javax.swing.JLabel jLBPreco;
    private javax.swing.JLabel jLBPrecoMostrar;
    private javax.swing.JLabel jLBQtdEstoque;
    private javax.swing.JLabel jLBQtdEstoqueMostrar;
    private javax.swing.JLabel jLBQuantidade;
    private javax.swing.JLabel jLBValorTotal;
    private javax.swing.JLabel jLBValorTotalMostrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSPNQuantidade;
    // End of variables declaration//GEN-END:variables
}
