package com.example.sqldemo.service;

import com.example.sqldemo.connection.ConnectFactory;
import com.example.sqldemo.connection.KingbaseConnection;
import com.example.sqldemo.connection.MysqlConnection;
import com.example.sqldemo.utils.IniFileReader;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@Service
public class SqlService {

    IniFileReader iniReader = new IniFileReader("src/main/resources/application.properties");

    public SqlService() throws IOException {
    }

    public Object[] execute(List<String>sqls, ConnectFactory connectFactory) {
        return connectFactory.execute(sqls);
    }

    public Object[] execute(List<String> sqls, String type) {
        ConnectFactory connectFactory = new KingbaseConnection();
        connectFactory.init(type, iniReader.getStrValue("kingbase.url"), iniReader.getStrValue("kingbase.username"), iniReader.getStrValue("kingbase.password"));
        return execute(sqls, connectFactory);
    }

    public Object[] execute(List<String> sqls) {
        ConnectFactory connectFactory = new MysqlConnection();
        connectFactory.init("mysql", iniReader.getStrValue("mysql.url"), iniReader.getStrValue("mysql.username"), iniReader.getStrValue("mysql.password"));
        return execute(sqls, connectFactory);
    }
}