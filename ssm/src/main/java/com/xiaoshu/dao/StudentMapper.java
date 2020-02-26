package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> getAll(Student student);

    Student getById(int id);

    void delete(int id);

    void update(Student student);

    void add(Student student);
}
