package com.kuang.controller;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Collection;

//员工列表
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getALL();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查询所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping ("/emp")
    public String addEmp(Employee employee){
        //保存员工的信息
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //查询所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工
    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
