package com.example.thrift;


import com.example.thrift.mythrift.InvalidOperationException;
import com.example.thrift.mythrift.StudentResource;
import com.example.thrift.mythrift.StudentService;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;


public class StudentServiceImpl implements StudentService.Iface {

    @Override
    public StudentResource get(int id) throws InvalidOperationException, TException {
        //..find student by id
        return new StudentResource();
    }

    @Override
    public void save(StudentResource resource) throws InvalidOperationException, TException {
        //..saving
        System.out.println("Student saved!!");
    }

    @Override
    public List<StudentResource> getList() throws InvalidOperationException, TException {
        List<StudentResource> studentResourceList = new ArrayList<>();
        StudentResource studentResource = new StudentResource();
        studentResource.setId(112);
        studentResource.setStudentName("Lucy");
        studentResource.setStudentid("157112");
        studentResourceList.add(studentResource);

        return studentResourceList;
    }

    @Override
    public boolean ping() throws InvalidOperationException, TException {
        return true;
    }
}
