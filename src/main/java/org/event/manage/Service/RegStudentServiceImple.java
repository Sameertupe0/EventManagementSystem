package org.event.manage.Service;

import org.event.manage.Model.EventModel;
import org.event.manage.Model.RegModel;
import org.event.manage.repository.RegStudentRepo;
import org.event.manage.repository.RegisterStudentRepoImpl;

import java.util.List;

public class RegStudentServiceImple implements RegStudentService{

    RegStudentRepo regStudentRepo = new RegisterStudentRepoImpl();
    @Override
    public boolean RegisterStudent(RegModel regModel) {
        return regStudentRepo.RegisterStudent(regModel);
    }

    @Override
    public List<Object[]> getRegistrationEventWise() {
        return regStudentRepo.getRegistrationEventWise();
    }

    @Override
    public List<Object[]> getRegistrationStudentWise() {
        return regStudentRepo.getRegistrationStudentWise();
    }

    @Override
    public boolean CheckEventCapacity(RegModel regModel) {
        return regStudentRepo.CheckEventCapacity(regModel);
    }

    @Override
    public boolean DeleteUsingRegId(int id) {
        return regStudentRepo.DeleteUsingRegId(id);
    }

    @Override
    public boolean UpdateStudentEvent(RegModel regModel) {
        return regStudentRepo.UpdateStudentEvent(regModel);
    }

    @Override
    public void exportsStudentToPDF(List<Object[]> obj, String path) {
        regStudentRepo.exportsStudentToPDF(obj,path);
    }
}
