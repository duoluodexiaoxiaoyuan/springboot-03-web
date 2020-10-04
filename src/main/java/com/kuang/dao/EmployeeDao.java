package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.apache.tomcat.util.net.TLSClientHelloExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee>  employees=null;

    @Autowired
    //员工有所属的部门
    private DepartmentDao departmentDao;
    //先按住alt+shift,然后鼠标左键点自己要同步的位置
    static {
        employees=new HashMap<Integer, Employee>();  //创建一个员工表
        employees.put(1001,new Employee(1001,"aa","123@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"bb","123@qq.com",1,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"cc","123@qq.com",1,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"dd","123@qq.com",1,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"ee","123@qq.com",1,new Department(105,"市场部")));
    }

    private static Integer initId=1006;

    //增加员工
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //查询员工全部信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }









}
