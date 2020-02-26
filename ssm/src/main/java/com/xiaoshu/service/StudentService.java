package com.xiaoshu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper mapper;

    public List<Student> getAll(Student student) {
        return mapper.getAll(student);
    }

    public Student getById(int id) {
        return mapper.getById(id);
    }

    public void delete(int id) {
        mapper.delete(id);
    }

    public void update(Student student) {
        mapper.update(student);
    }

    public void add(Student student) {
        mapper.add(student);
    }

    //分页
    public PageInfo<Student> findStudentPage(Student student, int pageNum, int pageSize, String ordername, String order) {
        PageHelper.startPage(pageNum, pageSize);
        //排序
        ordername = StringUtil.isNotEmpty(ordername) ? ordername : "sid";
        order = StringUtil.isNotEmpty(order) ? order : "desc";
        //获取全部方法
        List<Student> list = mapper.getAll(student);
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);
        return pageInfo;
    }
//用户名重复
    public Student existName(String name){
        List<Student> list = mapper.getAll(null);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSname().equals(name)){
                return list.get(i);
            }
        }
        return null;
    }
}
