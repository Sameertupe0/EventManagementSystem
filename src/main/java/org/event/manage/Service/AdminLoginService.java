package org.event.manage.Service;

import org.event.manage.Model.AdminLogin;

public interface AdminLoginService {
    public boolean isAdminLogin(AdminLogin login);
    public boolean isStudentLogin(AdminLogin login);
}
