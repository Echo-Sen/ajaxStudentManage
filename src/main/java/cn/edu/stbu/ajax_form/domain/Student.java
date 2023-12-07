package cn.edu.stbu.ajax_form.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * 针对tb_stu的实体类
 * */
@Data
@Entity(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*姓名*/
    @Size(min=2,max=20)
    private String name;

    /*学号*/
    @NotNull
    @Size(min=11,max=11)
    private String no;

    /*密码*/
    private String password;

    /*性别 ：
    0未知；1男；2女*/
    private Integer sex;

    /*年龄*/
    private Integer age;

    /*分数*/
    private String score;
}
