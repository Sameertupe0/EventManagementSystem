package org.event.manage.repository;

import org.event.manage.Model.AdminLogin;
import org.event.manage.dbConfig.DbInitialize;
import java.sql.*;

public class AdminLoginRepoImpl extends DbInitialize implements AdminLoginRepo{
    @Override
    public boolean isAdminLogin(AdminLogin login) {
        try{
            stmt = con.prepareStatement("select * from adminLogin where username=? and password=?");
            stmt.setString(1,login.getUsername());
            stmt.setString(2,login.getPassword());
            rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception e){
            System.out.println(e);
            return false;

        }

    }
}
