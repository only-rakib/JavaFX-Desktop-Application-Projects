/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.sql.*;
import java.util.ArrayList;

public class database {

    private static ArrayList<String> resultSet = new ArrayList<>();

    public static void main(String args[]) throws SQLException {

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
//            Statement st = conn.createStatement();
//            String sql = "update test set name = 'Rakib' where id = 100";
//            st.executeUpdate(sql);
//            ResultSet res = st.executeQuery("select * from test");
//            while (res.next()) {
//                System.out.println(res.getInt(2) + "  " + res.getString(1));
//            }
//            conn.close();
//        } catch (Exception ex) {
//            System.out.println(ex);
//
//        }
    }

    public static void studentInfoStored() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            System.out.println("jdysvdsguyesufudsyudsy");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void storeInfo(ArrayList<String> store) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "insert into studreg_info(Roll,Unit,Name,C_O,hsc_roll,hsc_reg,hsc_pass_year,hsc_gpa,ssc_roll,ssc_reg,ssc_pass_year,ssc_gpa,division,district,DOB,pic,sig)values('"
                    + store.get(0) + "','" + store.get(16) + "','" + store.get(1) + "','" + store.get(2) + "','" + store.get(3) + "','" + store.get(4) + "','"
                    + store.get(5) + "','" + store.get(6) + ",','" + store.get(7) + "','" + store.get(8) + "','"
                    + store.get(9) + "','" + store.get(10) + "','" + store.get(11) + "','" + store.get(12) + "','"
                    + store.get(13) + "','" + store.get(14) + "','" + store.get(15) + "')";
            st.executeUpdate(ins);
            //AlertMessage.display("Success", "Successfully Added ", "Ok");
            cnn.close();
        } catch (Exception e) {
            AlertMessage.display("Error", e.toString(), "Ok");
        }

    }

    public static void sortItem(char unit) {

        System.out.println(unit);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            String ins = "SELECT * FROM studreg_info ORDER by Unit ASC";
            Statement st = cnn.createStatement();
            ResultSet res1 = st.executeQuery(ins);

            String ins1 = "SELECT Roll FROM studreg_info WHERE Unit='" + unit + "' ORDER by total_marks DESC";
            ResultSet res = st.executeQuery(ins1);

            int i = 1;
            int j = 2;
            //String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23;
            int col;
            while (res.next()) {
                System.out.println(res.getString(1));
                resultSet.add(res.getString(1));
                //resultSet.add(res.getString(1));
            }
            res.close();
            for (int k = 0; k < resultSet.size(); k++) {
                //String ins2=;
                st.executeUpdate("UPDATE studreg_info SET merit_pos=" + i + " WHERE Roll='" + resultSet.get(k) + "'");
                i++;
            }

            i = 0;
            cnn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void store_From_excel(String roll, double tmarks) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "UPDATE studreg_info SET total_marks='" + Double.toString(tmarks) + "' WHERE Roll='" + roll + "'";
            st.executeUpdate(ins);
            AlertMessage.display("Success", "Successfully Added from excel", "Ok");
            cnn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void store_SubChoice(ArrayList<String> subList, String roll) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String sub1 = null, sub2 = null, sub3 = null, sub4 = null, sub5 = null;
            int n = subList.size();
            for (int i = 0; i < n; i++) {
                switch (i) {
                    case 0:
                        sub1 = subList.get(i);
                        break;
                    case 1:
                        sub2 = subList.get(i);
                        break;
                    case 2:
                        sub3 = subList.get(i);
                        break;
                    case 3:
                        sub4 = subList.get(i);
                        break;
                    default:
                        sub5 = subList.get(i);
                        break;
                }
            }
            String ins = null;
            if (n == 2) {
                ins = "UPDATE studreg_info SET sub1='" + sub1 + "',sub2='" + sub2 + "' WHERE Roll='" + roll + "'";
            }
            if (n == 3) {
                ins = "UPDATE studreg_info SET sub1='" + sub1 + "',sub2='" + sub2 + "',sub3='" + sub3 + "' WHERE Roll='" + roll + "'";
            }
            if (n == 4) {
                ins = "UPDATE studreg_info SET sub1='" + sub1 + "',sub2='" + sub2 + "',sub3='" + sub3 + "',sub4='" + sub4 + "' WHERE Roll='" + roll + "'";
            } else {
                ins = "UPDATE studreg_info SET sub1='" + sub1 + "',sub2='" + sub2 + "',sub3='" + sub3 + "',sub4='" + sub4 + "',sub5='" + sub5 + "' WHERE Roll='" + roll + "'";
            }
            st.executeUpdate(ins);
            AlertMessage.display("Success", "Database", "Ok");
            cnn.close();
        } catch (Exception e) {
            AlertMessage.display("Error", e.toString(), "Ok");
        }
    }

    public static String getUnit(String roll) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "select Unit from studreg_info WHERE Roll='" + roll + "'";
            ResultSet res = st.executeQuery(ins);
            if (res.next()) {
                return res.getString("Unit");
            } else {
                AlertMessage.display("Error", "Not found", "Ok");
            }
            AlertMessage.display("Success", "Successfully Added getUnit", "Ok");
            cnn.close();
        } catch (Exception e) {
            AlertMessage.display("Error", e.toString(), "Ok");
        }
        return "null";
    }

    public String get_Name(String roll) {
        String name = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "SELECT Name FROM studreg_info WHERE Roll='" + roll + "'";
            ResultSet res = st.executeQuery(ins);
            name = res.getString(1);
            res.close();
            cnn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return name;
    }

    public String get_Tmarks(String roll) {
        String tmark = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "SELECT total_marks FROM studreg_info WHERE Roll='" + roll + "'";
            ResultSet res = st.executeQuery(ins);
            tmark = res.getString(1);
            res.close();
            cnn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return tmark;
    }

    public static String get_Sub1(String roll) {
        String x=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "SELECT sub1 FROM studreg_info WHERE Roll='" + roll + "'";
           ResultSet rr=st.executeQuery(ins);
           if(rr.next())
             x=rr.getString(1);
            cnn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }

    public static int getMeritPos(String roll) 
    {
        int x=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "SELECT merit_pos FROM studreg_info WHERE Roll='" + roll + "'";
           ResultSet rr=st.executeQuery(ins);
           if(rr.next())
             x=rr.getInt(1);
            cnn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }

    public void del_Row() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "delete FROM studreg_info WHERE sub1 is null";
            st.executeUpdate(ins);
            cnn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static ArrayList<BindingsWithMeritTable> merit_table(String unit) {
        ArrayList<BindingsWithMeritTable> arL = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String con = "jdbc:mysql://localhost:3306/admissiontestsysytem";
            Connection cnn = DriverManager.getConnection(con, "root", "");
            Statement st = cnn.createStatement();
            String ins = "SELECT merit_pos,Roll,Name,total_marks FROM studreg_info WHERE Unit='" + unit + "' ORDER by merit_pos ASC";
            ResultSet res = st.executeQuery(ins);
            while (res.next()) {
                arL.add(new BindingsWithMeritTable(res.getString(3), res.getString(2), res.getString(4), res.getInt(1)));
            }
            res.close();
            cnn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return arL;
    }
}
