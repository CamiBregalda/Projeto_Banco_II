/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

/**
 *
 * @author user
 */
public class Painel_Venda_Funcionario extends javax.swing.JDialog {

    /**
     * Creates new form Painel_Venda_Funcionario
     */
    public Painel_Venda_Funcionario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Venda Funcionário");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLBNomeProduto = new javax.swing.JLabel();
        jLBPreco = new javax.swing.JLabel();
        jTFPreco = new javax.swing.JTextField();
        jLBQtdEstoque = new javax.swing.JLabel();
        jTFEstoque = new javax.swing.JTextField();
        jLBFornecedor = new javax.swing.JLabel();
        jLBFornecedorMostrar = new javax.swing.JLabel();
        jBTNAlterar = new javax.swing.JButton();
        jBTNOcultarProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBNomeProduto.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLBNomeProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBNomeProduto.setText("Nome do Produto");

        jLBPreco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBPreco.setText("Preço:");

        jLBQtdEstoque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBQtdEstoque.setText("Disponível em estoque:");

        jLBFornecedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLBFornecedor.setText("Fornecedor:");

        jBTNAlterar.setText("Alterar");

        jBTNOcultarProduto.setText("Ocultar Produto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLBNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jBTNAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(jBTNOcultarProduto)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jTFPreco))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBQtdEstoque)
                            .addComponent(jLBFornecedor))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBFornecedorMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFEstoque))))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLBNomeProduto)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBPreco)
                    .addComponent(jTFPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBQtdEstoque)
                    .addComponent(jTFEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLBFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLBFornecedorMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNOcultarProduto)
                    .addComponent(jBTNAlterar))
                .addContainerGap(64, Short.MAX_VALUE))
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
    private javax.swing.JButton jBTNComprar;
    private javax.swing.JButton jBTNComprar1;
    private javax.swing.JButton jBTNComprar2;
    private javax.swing.JButton jBTNComprar3;
    private javax.swing.JButton jBTNOcultarProduto;
    private javax.swing.JComboBox<String> jCBListaFuncionarios1;
    private javax.swing.JComboBox<String> jCBListaFuncionarios2;
    private javax.swing.JComboBox<String> jCBListaFuncionarios3;
    private javax.swing.JComboBox<String> jCBListaFuncionarios4;
    private javax.swing.JLabel jLBFornecedor;
    private javax.swing.JLabel jLBFornecedorMostrar;
    private javax.swing.JLabel jLBListaFuncionarios1;
    private javax.swing.JLabel jLBListaFuncionarios2;
    private javax.swing.JLabel jLBListaFuncionarios3;
    private javax.swing.JLabel jLBListaFuncionarios4;
    private javax.swing.JLabel jLBNomeProduto;
    private javax.swing.JLabel jLBNomeProduto1;
    private javax.swing.JLabel jLBNomeProduto2;
    private javax.swing.JLabel jLBNomeProduto3;
    private javax.swing.JLabel jLBNomeProduto4;
    private javax.swing.JLabel jLBPreco;
    private javax.swing.JLabel jLBPreco1;
    private javax.swing.JLabel jLBPreco2;
    private javax.swing.JLabel jLBPreco3;
    private javax.swing.JLabel jLBPreco4;
    private javax.swing.JLabel jLBPrecoMostrar1;
    private javax.swing.JLabel jLBPrecoMostrar2;
    private javax.swing.JLabel jLBPrecoMostrar3;
    private javax.swing.JLabel jLBPrecoMostrar4;
    private javax.swing.JLabel jLBQtdEstoque;
    private javax.swing.JLabel jLBQtdEstoque1;
    private javax.swing.JLabel jLBQtdEstoque2;
    private javax.swing.JLabel jLBQtdEstoque3;
    private javax.swing.JLabel jLBQtdEstoque4;
    private javax.swing.JLabel jLBQtdEstoqueMostrar1;
    private javax.swing.JLabel jLBQtdEstoqueMostrar2;
    private javax.swing.JLabel jLBQtdEstoqueMostrar3;
    private javax.swing.JLabel jLBQtdEstoqueMostrar4;
    private javax.swing.JLabel jLBQuantidade1;
    private javax.swing.JLabel jLBQuantidade2;
    private javax.swing.JLabel jLBQuantidade3;
    private javax.swing.JLabel jLBQuantidade4;
    private javax.swing.JLabel jLBValorTotal1;
    private javax.swing.JLabel jLBValorTotal2;
    private javax.swing.JLabel jLBValorTotal3;
    private javax.swing.JLabel jLBValorTotal4;
    private javax.swing.JLabel jLBValorTotalMostrar1;
    private javax.swing.JLabel jLBValorTotalMostrar2;
    private javax.swing.JLabel jLBValorTotalMostrar3;
    private javax.swing.JLabel jLBValorTotalMostrar4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSpinner jSPNQuantidade1;
    private javax.swing.JSpinner jSPNQuantidade2;
    private javax.swing.JSpinner jSPNQuantidade3;
    private javax.swing.JSpinner jSPNQuantidade4;
    private javax.swing.JTextField jTFEstoque;
    private javax.swing.JTextField jTFPreco;
    // End of variables declaration//GEN-END:variables
}
