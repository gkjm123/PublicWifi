package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/group.html")
public class Group extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "select * from mark_group;";
			ResultSet rs = statement.executeQuery(query);
			
			String html = "";
			
			html += "<tr style=\"color:white; font-weight : bold;\" bgcolor=\"#3cb371\">"
					+ "<td width=\"5%\">ID</td><td width=\"30%\">북마크 이름</td>"
					+ "<td width=\"5%\">순서</td><td width=\"25%\">등록일자</td><td width=\"25%\">수정일자</td><td width=\"10%\">비고</td></tr>";
			
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				String bg = (cnt % 2 == 1) ? "white" : "#EEEEEE";
				html += "<tr bgcolor=\"" + bg + "\">";
				for (int i = 1; i < 6; i++) {
					html += "<td>";
					if (rs.getString(i) == null) {
						html += "";
					}
					else {
						html += rs.getString(i);
					}
					html += "</td>";
				}
				html += "<td><a href=\"group_update.jsp?id=" + rs.getString(1) + "&name="
						+ rs.getString(2) + "&num=" + rs.getString(3) + "\">수정</a>"
						+ " <a href=\"group_delete.html?id=" + rs.getString(1) + "\">삭제</a></td>";
				html += "</tr>";
			}

			statement.close();
			connection.close();
			
			request.setAttribute("html", html);
			request.getRequestDispatcher("group.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}