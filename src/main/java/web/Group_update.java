package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/group_update")
public class Group_update extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String name = request.getParameter("name");
			String num = request.getParameter("num");
			String id = request.getParameter("id");
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "update mark_group set name = '" + name + "', num = " + num + ", change_date = now() where id = " + id + "; ";
			
			statement.executeQuery(query);

			statement.close();
			connection.close();

			request.getRequestDispatcher("group.html").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}