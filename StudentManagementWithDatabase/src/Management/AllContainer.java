/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.*;

/**
 *
 * @author Rakib
 */
public class AllContainer {

    public static boolean login(String name, String pass) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from login");
            while (rs.next()) {
                if (name.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                    conn.close();
                    return true;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            AlertMessage.display("Message", "SQL Server Connection Error", "Ok");
        }

        return false;
    }

    public static void print(String name, String id, String dept) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
            Statement st = conn.createStatement();
            String query = " insert into info (ID,Name,Dept)"
                    + " values (?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, dept);
            pstm.execute();
            AlertMessage.display("Success", "Successfully Add", "Ok");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            AlertMessage.display("Message", "SQL Server Connection Error", "Ok");
        }
        //break;
    }

}
