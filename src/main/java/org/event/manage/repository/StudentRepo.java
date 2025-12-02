package org.event.manage.repository;

import org.event.manage.Model.EventModel;
import org.event.manage.Model.StudentModel;

import java.util.List;

public interface StudentRepo {
    public boolean AddStudent(StudentModel studentModel);
    public List<StudentModel> ViewAllStudent();
    public boolean DeleteStudent(int id);
    public boolean UpdateStudent(StudentModel studentModel);
    public List<StudentModel> SearchStudentByDept(String department);
    public List<StudentModel> SearchStudentByEmail(String email);
    public void exportsStudentToPDF(List<StudentModel> studentList, String path);


}
