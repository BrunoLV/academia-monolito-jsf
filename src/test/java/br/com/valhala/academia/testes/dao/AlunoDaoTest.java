package br.com.valhala.academia.testes.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.weld.junit5.auto.ActivateScopes;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.valhala.academia.db.dao.AlunoDao;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.Endereco;
import br.com.valhala.academia.modelo.Telefone;
import br.com.valhala.academia.testes.produtores.ProdutorEntityManager;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

@ExtendWith(WeldJunit5AutoExtension.class)
@AddPackages(value = { ProdutorEntityManager.class, AlunoDao.class })
@ActivateScopes(value = { RequestScoped.class })
class AlunoDaoTest {

	private static void executaLiquibase(Connection connection) throws DatabaseException, LiquibaseException {
		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(connection));
		Liquibase liquibase = new Liquibase("src/main/resources/liquibase/db.changelog-mestre.yaml",
				new FileSystemResourceAccessor(), database);
		liquibase.dropAll();
		liquibase.update("test");
	}

	@BeforeAll
	static void setupGeral() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:academia_db", "SA", "")) {
				executaLiquibase(connection);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Inject
	AlunoDao dao;

	@Test
	@DisplayName("Deve pesquisar aluno por id e trazer todos os dados carregados")
	void deveCarregarAlunoCompletoPorId() {

		Aluno aluno = dao.buscaPorIdComTodosOsDados(0L);

		assertAll(() -> assertThat(aluno, is(notNullValue())),
				() -> assertThat(aluno.getEnderecos(), is(not(emptyCollectionOf(Endereco.class)))),
				() -> assertThat(aluno.getTelefones(), is(not(emptyCollectionOf(Telefone.class)))));

	}

	@Test
	@DisplayName("Deve injetar o DAO corretamente.")
	void deveInjetarDao() {
		assertThat(dao, is(notNullValue()));
	}

	@BeforeEach
	void setup() throws DatabaseUnitException, SQLException {

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:academia_db", "SA", "")) {
				IDatabaseConnection dbConnection = new DatabaseConnection(connection);
				dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
						new HsqldbDataTypeFactory());
				IDataSet dataSet = new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("datasets/dataset_teste_aluno.xml"));
				DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
