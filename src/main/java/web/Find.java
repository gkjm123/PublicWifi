package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Find extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");

		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();
			
			
			String query1 = "insert into history (x, y, search_date) values ('" + x + "', '" + y + "', now());";
			statement.executeQuery(query1);
			
			
			String query2 = "select *, round(pow((X_num-" + x + ")*111*(X_num-" + x + ")*111+(Y_num-" + y
					+ ")*89*(Y_num-" + y + ")*89, 0.5), 4) as dist from wifi\r\n"
					+ "where X_num != '0.0' and Y_num != '0.0'\r\n" + "order by dist\r\n" + "limit 20;";
			ResultSet rs = statement.executeQuery(query2);

			String html = "";
			
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				String bg = (cnt % 2 == 1) ? "white" : "#EEEEEE";
				html += "<tr bgcolor=\"" + bg + "\">";
				html += "<td>" + rs.getString(17) + "</td>";
				for (int i = 1; i < 17; i++) {
					html += "<td>";
					if (i == 3) {
						html += "<a href=\"detail.html?id=" + rs.getString(1) + "\">" + rs.getString(3) + "</a>";
					}
					else {
						html += rs.getString(i);
					}
					
					html += "</td>";
				}
				html += "</tr>";
			}
			
			statement.close();
			connection.close();

			request.setAttribute("html", html);
			request.getRequestDispatcher("near.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
