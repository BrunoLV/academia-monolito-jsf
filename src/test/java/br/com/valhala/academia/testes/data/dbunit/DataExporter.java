package br.com.valhala.academia.testes.data.dbunit;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DataExporter {

	public static void main(String[] args) throws Exception {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		try (Connection jdbcConnection = DriverManager
				.getConnection("jdbc:hsqldb:file:~/databases/academia_db;DB_CLOSE_DELAY=-1", "SA", "")) {
			IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
			Path path = Path.of(
					"/home/bruno/Dev/Workspace/work-novo/academia-monolito-jsf/src/test/resources/datasets/dataset.xml");
			Files.deleteIfExists(path);
			IDataSet fullDataSet = connection.createDataSet();
			FlatXmlDataSet.write(fullDataSet, new FileWriter(path.toString()), "utf-8");
		} catch (Exception ex) {
			Logger.getLogger(DataExporter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
