package com.bd.view;

import com.bd.infra.Login;
import com.bd.mapper.FuncionarioMapper;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.FuncionarioRepository;
import com.bd.service.FuncionarioService;
import com.bd.service.UsuarioService;
import org.mapstruct.factory.Mappers;

import java.util.Objects;
import javax.swing.JOptionPane;

public class Painel_Login extends javax.swing.JDialog {

    String pessoa;
    Painel_Tela_Inicial telaCadastro;
    FuncionarioService funcionarioService;
    UsuarioService usuarioService;

    public Painel_Login(java.awt.Frame parent, boolean modal) {
        super();
        setTitle("Realizar Login");
        inicializandoClasses();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLBTituloDaPagina = new javax.swing.JLabel();
        jLBNomeLogin = new javax.swing.JLabel();
        jTFNomeLogin = new javax.swing.JTextField();
        jLBSenhaLogin = new javax.swing.JLabel();
        jTFSenhaLogin = new javax.swing.JTextField();
        jBTNEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLBTituloDaPagina.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLBTituloDaPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLBTituloDaPagina.setText("Login");

        jLBNomeLogin.setText("Informe seu Nome:");

        jLBSenhaLogin.setText("Informe sua Senha:");

        jBTNEntrar.setText("Entrar");
        jBTNEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBTNEntrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLBTituloDaPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNomeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBNomeLogin)
                    .addComponent(jLBSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jBTNEntrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBTituloDaPagina)
                .addGap(39, 39, 39)
                .addComponent(jLBNomeLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLBSenhaLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jBTNEntrar)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBTNEntrarMouseClicked
        String username = jTFNomeLogin.getText();
        String password = jTFSenhaLogin.getText();
        
        // Configurar as informações de login no Singleton Login
        Login.getInstance().setUser(username);
        Login.getInstance().setSenha(password);
        
        if(Objects.equals(pessoa, "Usuario")){
            if(usuarioService.logarUsuario(username, password)){
                Painel_Usuario usuario = new Painel_Usuario();
                usuario.setLocationRelativeTo(this);
                usuario.setVisible(true);
                this.dispose();
                telaCadastro.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nome ou senha incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            FuncionarioResponse funcionario = funcionarioService.logarFuncionario(username, password);
            
            if(Objects.equals(funcionario.fun_funcao(), "Gerente")){
                Painel_Gerente gerente = new Painel_Gerente();
                gerente.setLocationRelativeTo(this);
                gerente.setVisible(true);
            } else {
                if(funcionarioService.logarFuncionario(username, password) != null){
                    Painel_Funcionario fun = new Painel_Funcionario();
                    fun.setLocationRelativeTo(this);
                    fun.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Nome ou senha incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        this.dispose();
        telaCadastro.dispose();
    }//GEN-LAST:event_jBTNEntrarMouseClicked

    public void teste(){
        String username = jTFNomeLogin.getText();
        String password = jTFSenhaLogin.getText();

        // Configurar as informações de login no Singleton Login
        Login.getInstance().setUser(username);
        Login.getInstance().setSenha(password);
        
        FuncionarioResponse funcionario = funcionarioService.logarFuncionario(username, password);
        
        try {
            if(funcionario.fun_funcao().equals("Gerente")){
                Painel_Usuario usuario = new Painel_Usuario();
                usuario.setLocationRelativeTo(this);
                usuario.setVisible(true);
                this.dispose();
                telaCadastro.dispose();
            } else {

            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Nome ou senha incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        this.dispose();
        telaCadastro.dispose();
    }

    private void inicializandoClasses(){
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, funcionarioMapper);
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
            java.util.logging.Logger.getLogger(Painel_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Painel_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Painel_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Painel_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Painel_Login dialog = new Painel_Login(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBTNEntrar;
    private javax.swing.JLabel jLBNomeLogin;
    private javax.swing.JLabel jLBSenhaLogin;
    private javax.swing.JLabel jLBTituloDaPagina;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNomeLogin;
    private javax.swing.JTextField jTFSenhaLogin;
    // End of variables declaration//GEN-END:variables
}
