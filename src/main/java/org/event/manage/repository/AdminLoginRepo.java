package org.event.manage.repository;

import org.event.manage.Model.AdminLogin;
import org.event.manage.dbConfig.DbInitialize;

public interface AdminLoginRepo {
    public boolean isAdminLogin(AdminLogin login);
    public boolean isStudentLogin(AdminLogin login);



}
