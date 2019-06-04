package com.example.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class ApplicationService {

	@Autowired
	UserRepository userRepository;
	
	public void savingCustomer(List<User> user) throws Exception {

		FileHandler fh;
		Logger logger = Logger.getLogger("MyLog");

		try {
			fh = new FileHandler("C:/Users/38595/eclipse-workspace/.metadata/.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			for (User element : user) {
				if (customerInDB(element)) {
					userRepository.save(new User(element.getIme(), element.getPrezime(), element.getPbr(),
							element.getGrad(), element.getTelefon()));
				} else {
					logger.warning(element.getIme() + " vec postoji u bazi podataka");
					logger.info(element.getIme() + " vec postoji u bazi podataka");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
		}
	}

	public boolean customerInDB(User element) {
		try {
			// create our database connection
			String myDriver = "org.postgresql.Driver";
			String myUrl = "jdbc:postgresql://localhost/postgres";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "postgres", "sammir");

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM users";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				int id = rs.getInt("id");
				String ime = rs.getString("name");
				String prezime = rs.getString("prezime");
				int pbr = rs.getInt("pbr");
				String grad = rs.getString("grad");
				String telefon = rs.getString("telefon");

				if (element.getIme().matches(ime) && element.getPrezime().matches(prezime)
						&& element.getGrad().matches(grad) && element.getPbr() == pbr) {
					return false;
				}
			}
			((Connection) st).close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return true;
	}
}
