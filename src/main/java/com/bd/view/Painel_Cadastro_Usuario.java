/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.bd.view;

import com.bd.mapper.*;
import com.bd.repository.*;
import com.bd.service.*;
import javax.swing.JOptionPane;
import org.mapstruct.factory.Mappers;


public class Painel_Cadastro_Usuario extends javax.swing.JDialog {

    ProdutoService produtoService;
    FornecedorService fornecedorService;
    UsuarioService usuarioService;
    FuncionarioService funcionarioService;
    ItemService itemService;
    VendaService vendaService;

    public Painel_Cadastro_Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Cadastrar usuário");
        inicializandoClasses();
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
        jLBNomeCompleto = new javax.swing.JLabel();
        jTFNomeCompleto = new javax.swing.JTextField();
        jLBNomeUsuario = new javax.swing.JLabel();
        jTFNomeUsuario = new javax.swing.JTextField();
        jLBcpfUsuario = new javax.swing.JLabel();
        jTFcpfUsuario = new javax.swing.JTextField();
        jLBSenhaUsuario = new javax.swing.JLabel();
        jPFSenhaUsuario = new javax.swing.JPasswordField();
        jLBConfirmarSenha = new javax.swing.JLabel();
        jPFConfirmarSenha = new javax.swing.JPasswordField();
        jBTNCadastrar = new javax.swing.JButton();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jBTNLogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBNomeCompleto.setText("Nome Completo:");

        jLBNomeUsuario.setText("Nome de Usuário:");

        jLBcpfUsuario.setText("CPF:");

        jLBSenhaUsuario.setText("Senha:");

        jLBConfirmarSenha.setText("Confirme sua Senha:");

        jBTNCadastrar.setText("Cadastrar");
        jBTNCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNCadastrarMouseClicked(evt);
            }
        });

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLBTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBTituloDaPagina.setText("Cadastre-se");
        jLBTituloDaPagina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jBTNLogar.setText("Logar");
        jBTNLogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNLogarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBTNLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jBTNCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLBSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBcpfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeCompleto)
                    .addComponent(jTFcpfUsuario)
                    .addComponent(jPFSenhaUsuario)
                    .addComponent(jLBNomeCompleto)
                    .addComponent(jLBNomeUsuario)
                    .addComponent(jTFNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(jLBConfirmarSenha)
                    .addComponent(jPFConfirmarSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLBNomeCompleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBcpfUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFcpfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLBSenhaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLBConfirmarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBTNCadastrar)
                    .addComponent(jBTNLogar))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNCadastrarMouseClicked
        // TODO add your handling code here:
        String nomeCompleto = jTFNomeCompleto.getText();
        String userName = jTFNomeUsuario.getText();
        String cpfUsuario = jTFcpfUsuario.getText();
        String senhaUsuario = new String(jPFSenhaUsuario.getPassword());
        String ConfirmarSenha = new String(jPFConfirmarSenha.getPassword());
        
        //validar as senhas
        if (senhaUsuario == ConfirmarSenha){
            //passar as informações em suas respectivas colunas na tabela 
            //Chamar método cadastrar e passar as informações no new usuarioRequest
            //user_nome = jTFNomeCompleto;
            
        } else {
            JOptionPane.showMessageDialog(this, "As senhas precisam ser iguais");
        }
    }//GEN-LAST:event_jBTNCadastrarMouseClicked

    private void jBTNLogarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNLogarMouseClicked
        //direcionar o Usuário para a tela de login.
        Painel_Usuario user = new Painel_Usuario();
        user.setLocationRelativeTo(this);
        user.setVisible(true);
    }//GEN-LAST:event_jBTNLogarMouseClicked

    private void inicializandoClasses(){
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        FornecedorMapper fornecedorMapper = Mappers.getMapper(FornecedorMapper.class);
        fornecedorService = new FornecedorService(fornecedorRepository, fornecedorMapper);

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, funcionarioMapper);

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
            java.util.logging.Logger.getLogger(Painel_Cadastro_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Cadastro_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Cadastro_Usuario dialog = new Painel_Cadastro_Usuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNCadastrar;
    private javax.swing.JButton jBTNLogar;
    private javax.swing.JLabel jLBConfirmarSenha;
    private javax.swing.JLabel jLBNomeCompleto;
    private javax.swing.JLabel jLBNomeUsuario;
    private javax.swing.JLabel jLBSenhaUsuario;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JLabel jLBcpfUsuario;
    private javax.swing.JPasswordField jPFConfirmarSenha;
    private javax.swing.JPasswordField jPFSenhaUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNomeCompleto;
    private javax.swing.JTextField jTFNomeUsuario;
    private javax.swing.JTextField jTFcpfUsuario;
    // End of variables declaration//GEN-END:variables
}
