package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/mark_add")
public class Mark_add extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String wifi_id = request.getParameter("wifi_id");
			String wifi_name = request.getParameter("wifi_name");
			String mark_group = request.getParameter("mark_group");
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "insert into mark (group_name, wifi_name, regit_date, wifi_id) values ('" + mark_group
					+ "', '" + wifi_name + "', now(), '" + wifi_id + "');";
			
			statement.executeQuery(query);

			statement.close();
			connection.close();
			
			request.getRequestDispatcher("hello.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}