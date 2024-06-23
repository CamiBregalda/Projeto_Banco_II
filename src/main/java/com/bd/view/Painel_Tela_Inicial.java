package com.bd.view;

public class Painel_Tela_Inicial extends javax.swing.JFrame {
    
    public Painel_Tela_Inicial() {
        setTitle("Tela Inicial");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTBEntrar = new javax.swing.JToggleButton();
        jLBTituloDaPagina = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTBEntrar.setText("Logar");
        jTBEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBEntrarMouseClicked(evt);
            }
        });

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLBTituloDaPagina.setText("Bando's Vendas Online");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLBTituloDaPagina)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jTBEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLBTituloDaPagina)
                .addGap(68, 68, 68)
                .addComponent(jTBEntrar)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTBEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBEntrarMouseClicked
       Painel_Login login = new Painel_Login(this, true);
       login.telaCadastro = this;
       login.setLocationRelativeTo(this);
       login.setVisible(true);
    }//GEN-LAST:event_jTBEntrarMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Tela_Inicial telaInicial = new Painel_Tela_Inicial();
                telaInicial.setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JToggleButton jTBEntrar;
    // End of variables declaration//GEN-END:variables
}
