package org.event.manage.Service;

import org.event.manage.Model.AdminLogin;
import org.event.manage.repository.AdminLoginRepo;
import org.event.manage.repository.AdminLoginRepoImpl;

public class AdminLoginServiceImpl implements AdminLoginService{

    AdminLoginRepo adrepo = new AdminLoginRepoImpl();
    @Override
    public boolean isAdminLogin(AdminLogin login) {
        return adrepo.isAdminLogin(login);
    }
}
