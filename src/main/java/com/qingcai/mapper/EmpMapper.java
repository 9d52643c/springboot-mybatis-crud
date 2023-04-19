package com.qingcai.mapper;

import com.qingcai.pojo.Emp;
import org.apache.ibatis.annotations.*;

import javax.crypto.interfaces.PBEKey;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //删除员工
    @Delete("delete from emp where id = #{id}")
    public void delete(Integer id);

    //自动将生成的主键值，赋值给emp对象的id属性
    @Options(useGeneratedKeys = true,keyProperty = "id")
    //新增员工
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    //更新修改员工
    @Update("update emp set username = #{username}, name = #{name}, gender = #{gender}, image = #{image}," +
            "job = #{job}, entrydate = #{entrydate}, dept_id = #{deptId}, update_time=#{updateTime} where id = #{id}")
    public void update(Emp emp);

    //根据ID查询员工
    // /*
    @Select("select * from mybatis.emp where id = #{id}")
    public Emp getById(Integer id);     // */
    //方案一：给（数据库）字段取别名，让别名与实体类属性一致
    /*
    @Select("select id, username, password, name, gender, image, job, entrydate," +
            " dept_id deptId, create_time createTime, update_time updateTime from emp where id = #{id};")
    public Emp getById(Integer id);     */
    //方案二：通过@Results,@Result注解手动映射封装
    /*
    @Results({
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from mybatis.emp where id = #{id}")
    public Emp getById(Integer id);     */
    //方案三：开启mybatis的驼峰命名自动映射开关

    //条件查询员工
    /*1. $
    @Select("select * from mybatis.emp where name like '%${name}%' and gender = #{gender} and " +
            "entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
     */
    //2. concat
//    @Select("select * from mybatis.emp where name like concat('%', #{name}, '%') and gender = #{gender} and " +
//            "entrydate between #{begin} and #{end} order by update_time desc")
//    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //XML配置文件
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //动态更新修改员工
    public void update2(Emp emp);

    //批量删除员工操作
    public void deleteByIds(List<Integer> ids);
}
