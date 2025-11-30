package org.event.manage.dbConfig;

import com.mysql.cj.jdbc.Driver;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DbInitialize {

    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    private FileInputStream fis;
    public DbInitialize(){

        try{

           File f = new File("");
           String path = f.getAbsolutePath()+"\\src\\main\\resources\\db.properties";
           fis = new FileInputStream(path);
            Properties p = new Properties();
            p.load(fis);
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            String url = p.getProperty("url");
            String driverClass = p.getProperty("driver");


            Class.forName(driverClass);
            con = DriverManager.getConnection(url,username,password);
            if(con!=null){
                System.out.println("Database is connected");
            }
            else{
                System.out.println("Database is not connected");
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }



}
