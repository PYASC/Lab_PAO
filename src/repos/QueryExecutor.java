package repos;

import Entities.*;
import Mappers.Mappers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class QueryExecutor {
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/pao";

    static final String user = "admin";
    static final String password = "admin";


    public static void main(String[] args){
        // testing functino ; should be removed later;

        List<PerishableWeightedProductEntity> ls1 = ProductRepository.getAllPerishableWPE();
        List<PerishableUnitaryProductEntity> ls2 = ProductRepository.getAllPerishableUPE();
        System.out.println(ls1);
        System.out.println(ls2);
    }

    public static <T> List<T> executeReadQuery(String sql, Function<ResultSet, T> rowMapper){

        Connection conn = null;
        Statement stmt = null;
        List<T> result = new ArrayList<T>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                T x = rowMapper.apply(rs);
                if(x != null)
                    result.add(x);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            try {
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    public static int executeUpdateQuery(String sql){
        Connection conn = null;
        Statement stmt = null;
        int rows = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            rows = stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return rows;
    }

}
