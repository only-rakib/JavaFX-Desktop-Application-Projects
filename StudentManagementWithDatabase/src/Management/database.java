/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.*;

public class database {

    public static void main(String args[]) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
            Statement st = conn.createStatement();
            String sql = "update test set name = 'Rakib' where id = 100";
            st.executeUpdate(sql);
            ResultSet res = st.executeQuery("select * from test");
            while (res.next()) {
                System.out.println(res.getInt(2) + "  " + res.getString(1));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

}
