package com.xiaoshu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.*;
import com.xiaoshu.service.MajorService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.StudentService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService service;
    @Autowired
    private OperationService operationService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private MajorService majorService;
    static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping("studentIndex")
    public String index(HttpServletRequest request, Integer menuid) throws Exception {
        List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
        request.setAttribute("operationList", operationList);
        //搜索下拉框
        List<Major> majorList=majorService.getAll();
        request.setAttribute("majorList", majorList);
        return "student";
    }

    @RequestMapping(value="studentList",method=RequestMethod.POST)
    public void StudentList(HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
        try {
            Student student = new Student();
            String sname = request.getParameter("sname");
            String majorid = request.getParameter("majorid");

            if (StringUtil.isNotEmpty(sname)) {
                student.setSname(sname);
            }
            if (StringUtil.isNotEmpty(majorid) && !"0".equals(majorid)) {
                student.setMid(Integer.parseInt(majorid));
            }
            Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
            Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
            PageInfo<Student> studentList= service.findStudentPage(student,pageNum,pageSize,"","");

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("total",studentList.getTotal() );
            jsonObj.put("rows", studentList.getList());
            WriterUtil.write(response,jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户展示错误",e);
            throw e;
        }
    }



    // 新增或修改
    @RequestMapping("reserveStudent")
    public void reserveUser(HttpServletRequest request, Student student, HttpServletResponse response){
        Integer sid = student.getSid();
        JSONObject result=new JSONObject();
//        System.out.println(student);
        try {
            if (sid != null) {   // userId不为空 说明是修改
                    student.setSid(sid);
                    service.update(student);
                    result.put("success", true);
            }else {   // 添加
                if(service.existName(student.getSname())==null){  // 没有重复可以添加
                    service.add(student);
                    result.put("success", true);
                } else {
                    result.put("success", true);
                    result.put("errorMsg", "该用户名被使用");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", true);
            result.put("errorMsg", "对不起，操作失败");
        }
        WriterUtil.write(response, result.toString());
    }


    @RequestMapping("deleteStudent")
    public void delUser(HttpServletRequest request,HttpServletResponse response){
        JSONObject result=new JSONObject();
        try {
            String[] ids=request.getParameter("ids").split(",");
            System.out.println(Arrays.toString(ids));
            for (String id : ids) {
                service.delete(Integer.parseInt(id));
            }
            result.put("success", true);
            result.put("delNums", ids.length);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errorMsg", "对不起，删除失败");
        }
        WriterUtil.write(response, result.toString());
    }
}
