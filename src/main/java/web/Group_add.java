package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/group_add")
public class Group_add extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String name = request.getParameter("name");
			String num = request.getParameter("num");
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "insert into mark_group "
					+ "(name, num, regit_date) "
					+ "values"
					+ "('" + name + "', '" + num + "', now());";
			
			statement.executeQuery(query);

			statement.close();
			connection.close();

			request.getRequestDispatcher("group.html").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}