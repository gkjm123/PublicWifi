package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/mark_delete")
public class Mark_delete extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String id = request.getParameter("wifi_id");
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "delete from mark where wifi_id='" + id + "';";
			
			statement.executeQuery(query);

			statement.close();
			connection.close();

			request.getRequestDispatcher("mark.html").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}