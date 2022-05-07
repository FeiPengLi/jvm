package org.example.ex7;

import java.sql.*;

/**
 * packageName:org.example.ex7
 * author:李朋飞
 * time:2021/12/4 14:32
 * ProjectName:jvm
 * ClassName: DBUtils
 */
public class DBUtils {
    public static final String URL="jdbc:mysql://localhost:3306/delay_order?serverTimezone=GMT%2b8";
    public static final String USER="root";
    public static final String PASSWORD="root1234";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取数据库连接
        Connection conn= DriverManager.getConnection(URL,USER ,PASSWORD);
        //操作数据库，实现增删改查
        Statement statement=conn.createStatement();
        ResultSet rs=  statement.executeQuery("select order_no ,order_note from order_exp");
        while(rs.next()){
            System.out.println(rs.getString("order_no")+","+rs.getString("order_note"));
        }

    }
}
