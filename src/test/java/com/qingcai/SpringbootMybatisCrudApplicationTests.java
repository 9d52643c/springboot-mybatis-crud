package com.qingcai;

import com.qingcai.mapper.EmpMapper;
import com.qingcai.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    //删
    @Test
    public void tsetDelete() {
        empMapper.delete(16);
    }

    //增
    @Test
    public void textInsert() {
        //构造员工对象
        Emp emp=new Emp();
        emp.setUsername("QingCai1");
        emp.setName("青菜1");
        emp.setImage("1.jpg");
        emp.setGender((short) 1);
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        //执行新增员工信息操作
        empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    //改
    @Test
    public void textUpdate(){
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Qing");
        emp.setName("青");
        emp.setImage("1.jpg");
        emp.setGender((short) 1);
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        //执行更新员工操作
        empMapper.update(emp);
    }

    //查
    //根据ID查询员工
    @Test
    public void textGetById() {
        Emp emp = empMapper.getById(18);
        System.out.println(emp);
    }
    //根据条件查询员工
    @Test
    public void textList() {
        //静态SQL
//        List<Emp> empList = empMapper.list("张", (short) 1, LocalDate.of(2010,1,1),LocalDate.of(2020,1,1));

        //动态SQL
//        List<Emp> empList = empMapper.list("张",null,null,null);
        List<Emp> empList = empMapper.list(null,(short) 1,null,null);
        System.out.println(empList);
    }

    //动态更新员工- 更新ID为18的员工username更新为 CaiCai, name更新为 菜菜， gender更新为2
    @Test
    public void testUpdate2() {
        Emp emp = new Emp();
        emp.setId(16);
        emp.setUsername("QingCaiCai");
        emp.setName("青菜菜");
        emp.setGender((short) 2);
        emp.setUpdateTime(LocalDateTime.now());

        //执行更新员工操作
        empMapper.update2(emp);
    }

    @Test
    public void testDeleteByIds() {
        List<Integer> ids = Arrays.asList(13, 14, 15);
        empMapper.deleteByIds(ids);
    }
}
