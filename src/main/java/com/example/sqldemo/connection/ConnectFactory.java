package com.example.sqldemo.connection;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接工程
 */
@Component
public abstract class ConnectFactory {

    protected String driverName;
    protected String url;
    protected String userName;
    protected String password;


    //获取数据库连接
    abstract Connection getConnection();

    protected void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    //设置驱动名
    void setDriverName(String d) {
        if (StringUtils.isNullOrEmpty(d)) driverName = "com.mysql.cj.jdbc.Driver";
        else driverName = d;
    }

    public void init(String type, String init_url, String init_userName, String init_password) {
        switch (type) {
            case "mysql":
                setDriverName(null);
                break;
            case "kingbase":
                setDriverName("com.kingbase8.Driver");
                break;
            default:
                setDriverName(null);
        }
        url = init_url;
        userName = init_userName;
        password = init_password;
        getConnection();
    }

    public Object[] execute(List<String> sql) {
        Connection connection = getConnection();
        Object[] res = new Object[sql.size()];
        try {
            //关闭自动提交，防止出错产生脏数据
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            for (int i = 0; i < sql.size(); i++) {
                res[i] = sql.get(i).startsWith("select") ? getResult(statement.executeQuery(sql.get(i))) : String.valueOf(statement.executeUpdate(sql.get(i)));
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    closeConnection(connection);
                } catch (SQLException e) {
                    System.err.println("connection closed error");
                    e.printStackTrace();
                }
            }
        }
        return res;
    }


    Object getResult(ResultSet r) throws SQLException {
        long length = r.getMetaData().getColumnCount();
        Object res;
        if (length > 1) {
            List<String> tempRes = new ArrayList<>();
            while (r.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i <= length; i++) {
                    String name = r.getMetaData().getColumnLabel(i);
                    String value = r.getString(name);
                    stringBuilder.append(name).append(":").append(value).append(";");
                }
                tempRes.add(stringBuilder.toString());
            }
            res = tempRes;
        } else {
            r.next();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= length; i++) {
                String name = r.getMetaData().getColumnLabel(i);
                String value = r.getString(name);
                stringBuilder.append(name).append(":").append(value).append(";");
            }
            res = stringBuilder.toString();
        }
        return res;
    }

}
