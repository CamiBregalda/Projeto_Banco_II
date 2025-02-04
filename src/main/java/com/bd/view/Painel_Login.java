package com.bd.view;

import com.bd.infra.Login;
import com.bd.mapper.FuncionarioMapper;
import com.bd.model.response.FuncionarioResponse;
import com.bd.repository.BackupRepository;
import com.bd.repository.FuncionarioRepository;
import com.bd.service.FuncionarioService;
import org.mapstruct.factory.Mappers;
import javax.swing.JOptionPane;

public class Painel_Login extends javax.swing.JDialog {

    String pessoa;
    Painel_Tela_Inicial telaCadastro;
    FuncionarioService funcionarioService;

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
        jPFSenhaLogin = new javax.swing.JPasswordField();
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jBTNEntrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFNomeLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(jLBNomeLogin)
                    .addComponent(jPFSenhaLogin)
                    .addComponent(jLBSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
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
                .addComponent(jPFSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        String password = new String (jPFSenhaLogin.getPassword());
 
        try {
            // Configurar as informações de login no Singleton Login
            Login.getInstance().setUser(username);
            Login.getInstance().setSenha(password);

            FuncionarioResponse funcionario = funcionarioService.logarFuncionario(username, password);
            
            if(funcionario.fun_funcao().equals("Gerente")){
                boolean backupPlanejado = funcionarioService.checarBackup();
                
                if(backupPlanejado){
                    Painel_Infos_Backup infos = new Painel_Infos_Backup(this, true);
                    infos.setLocationRelativeTo(this);
                    infos.setVisible(true);
                } else {
                    Painel_Gerente gerente = new Painel_Gerente();
                    gerente.receberDados();
                    gerente.setLocationRelativeTo(this);
                    gerente.setVisible(true);
                }
            } else {
                Painel_Funcionario fun = new Painel_Funcionario();
                fun.receberDados();
                fun.setLocationRelativeTo(this);
                fun.setVisible(true);
            }    
            
            this.dispose();
            telaCadastro.dispose();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Nome ou senha incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBTNEntrarMouseClicked

    private void inicializandoClasses(){
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        BackupRepository backupRepository = new BackupRepository();
        FuncionarioMapper funcionarioMapper = Mappers.getMapper(FuncionarioMapper.class);
        funcionarioService = new FuncionarioService(funcionarioRepository, backupRepository, funcionarioMapper);
    }

    public static void main(String args[]) {
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
    private javax.swing.JPasswordField jPFSenhaLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNomeLogin;
    // End of variables declaration//GEN-END:variables
}
