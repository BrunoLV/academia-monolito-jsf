package br.com.valhala.academia.db.liquibase;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class ProdutorLiquibase {

	private Properties propriedadesBancoDados;

	@Produces
	@LiquibaseType
	public CDILiquibaseConfig criaConfiguracao() {
		CDILiquibaseConfig configuracao = new CDILiquibaseConfig();
		configuracao.setChangeLog("liquibase/db.changelog-mestre.yaml");
		return configuracao;
	}

	@Produces
	@LiquibaseType
	public DataSource criaDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(propriedadesBancoDados.getProperty("database.driver"));
		ds.setUrl(propriedadesBancoDados.getProperty("database.url"));
		ds.setUsername(propriedadesBancoDados.getProperty("database.usuario"));
		ds.setPassword(propriedadesBancoDados.getProperty("database.senha"));
		return ds;
	}

	@Produces
	@LiquibaseType
	public ResourceAccessor criaResourceAccessor() {
		return new ClassLoaderResourceAccessor(getClass().getClassLoader());
	}

	@PostConstruct
	public void inicia() {
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			propriedadesBancoDados = new Properties();
			propriedadesBancoDados.load(stream);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
