package com.bd;

import com.bd.view.Painel_Tela_Inicial;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class LojaVirtualApplication {

	public static void main(String[] args) {
            SpringApplication.run(LojaVirtualApplication.class, args);

            Painel_Tela_Inicial telaInicial = new Painel_Tela_Inicial();
            telaInicial.setVisible(true);
	}

}
