package com.nursultan;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public final class dbHandler {
    static String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
    static Connection con = null;
    static ResultSet rs = null;
    static Statement stmt = null;

    public final static class conn{
        public final static boolean isConnected() {
            try {
                // Here we load the driver’s class file into memory at the runtime
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
                con = DriverManager.getConnection(connectionUrl, "postgres", "qwerty123");
                stmt = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();;
                return false;
            }
            return true;
        }

        public final static ResultSet res(String str) {
            try {
                return stmt.executeQuery("select * from week7"+str);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public final static boolean insert(int id,String name, int price, Date date, String manuf) {
            try {
                stmt.execute("insert into week7 values("+id+",'" + name + "'," + price + ",'" + date + "','" + manuf + "')");
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        public final  static boolean delete(int id, String name, int price, String date, String manuf){
            try {
                stmt.execute("delete from week7 where id="+id+" and name='" + name + "' and price=" + price + " and exp_date='" + date + "' and manufacturer='" + manuf + "'");
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        public final static boolean update(int id, String name, int price, String date, String manuf){
            try {
                stmt.execute("update week7" +
                        " set name='" + name + "', price=" + price + ", exp_date='" + date + "', manufacturer='" + manuf + "'" +
                        " where id="+id);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}


