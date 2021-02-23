package de.akademie.util;

/**
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @author Simme
 *
 */
public class DatabaseAccess {

	/**
	 * @param args
	 */
	public static Connection connectToDatabase() {
		Connection conn = null;
		final Logger logger = Logger.getLogger(DatabaseAccess.class.getName());
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/pizzadienst"
					+ "?user=root"
					+ "&password=akademie2019"
					+ "&serverTimezone=UTC"
					);
			if(conn.isValid(10)) {
				logger.info("connected to database!");
			}
			else {
				logger.info("Connection failed");
			}
		}
		catch (SQLException e) {
			logger.info("*** Exception!");
			logger.info(e.toString());
		}
		//logger.info("*** Returning");
		return conn;
	}
}

