package org.event.manage.Service;

import org.event.manage.Model.StudentModel;
import org.event.manage.repository.StudentRepo;
import org.event.manage.repository.StudentRepoImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    StudentRepo srepo = new StudentRepoImpl();
    @Override
    public boolean AddStudent(StudentModel studentModel) {
        return srepo.AddStudent(studentModel);
    }

    @Override
    public List<StudentModel> ViewAllStudent() {
        return srepo.ViewAllStudent();
    }

    @Override
    public boolean DeleteStudent(int id) {
        return srepo.DeleteStudent(id);
    }

    @Override
    public boolean UpdateStudent(StudentModel studentModel) {
        return srepo.UpdateStudent(studentModel);
    }

    @Override
    public List<StudentModel> SearchStudentByDept(String department) {
        return srepo.SearchStudentByDept(department);
    }

    @Override
    public List<StudentModel> SearchStudentByEmail(String email) {
        return srepo.SearchStudentByEmail(email);
    }

    @Override
    public void exportsStudentToPDF(List<StudentModel> studentList, String path) {
        srepo.exportsStudentToPDF(studentList, path);
    }
}
