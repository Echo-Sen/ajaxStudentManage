package cn.edu.stbu.ajax_form.webapi.student;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.*;
//import org.springframework.util.StringUtils;

import cn.edu.stbu.ajax_form.core.PageUtils;
import cn.edu.stbu.ajax_form.domain.Student;
import cn.edu.stbu.ajax_form.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import static org.hibernate.sql.InFragment.NULL;


@RestController
@RequestMapping("/webapi/student")

public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> getAll(){
//        List<Student> students = studentService.findAll();
//        return students;
        return studentService.findAll();
    }
    @GetMapping("/getByPage")
    public PageUtils getByPage(@RequestParam(value="page",defaultValue = "0")Integer page,
                               @RequestParam(value="size",defaultValue="10")Integer size,
                               @RequestParam(value="name",defaultValue = "")String name,
                                @RequestParam(value = "no",defaultValue ="") String no){
        //按id排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Page<Student> studentPage;
        Pageable pageable = null;
        if(StringUtils.isEmpty(name)){
            pageable=PageRequest.of(page,size,sort);
            studentPage=studentService.findAll(pageable);
        } else if(StringUtils.isNumeric(name)){
            //是数字说明输入的是id
            Student student = new Student();
            student.setId(Integer.valueOf(name));
            pageable = PageRequest.of(0, 10, sort);

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

            Example<Student> example = Example.of(student, matcher);

            studentPage = studentService.findAll(example, pageable);

        }
        else {
            Student student = new Student();
            student.setName(name);
            pageable = PageRequest.of(0, 10, sort);

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("no", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

            Example<Student> example = Example.of(student, matcher);

            studentPage = studentService.findAll(example, pageable);
        }

        return new PageUtils(studentPage.getContent(), studentPage.getTotalElements());
    }
    @GetMapping("/getId/{id}")
    public Student getId(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        student.setPassword("");
        return student;
    }

    @GetMapping("/getName/{name}")
    public List<Student> getName(@PathVariable String name) {
        return  studentService.findByName(name);
    }

    @GetMapping("/getScore/{score}")
    public List<Student> getScore(@PathVariable String score) {
        return studentService.findByScore(score);
    }
    @PostMapping("/insert")
    public Student insert(Student student) {
        return studentService.insert(student);
    }
    @PutMapping("/update")
    public int update(@Valid Student student, @NotNull BindingResult bindingResult){
        //读取旧数据
        Student oldStudent=studentService.findById(student.getId());

        if(StringUtils.isEmpty(student.getNo())){
            student.setNo(oldStudent.getNo());
        }
        studentService.update(student);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return -1;
        }
        if(student.getName()!=NULL)
            return  1;
        else return 0;
    }

//    @PutMapping("/update")
//    public Student update(Student student) {
//        Student oldStudent = studentService.findById(student.getId());
//        if(StringUtils.isEmpty(student.getPassword())){
//            student.setPassword(oldStudent.getPassword());
//        }
//        return studentService.update(student);
//    }
    @PostMapping(value = "/add")
    public int add(@Valid Student student, @NotNull BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return -1;
        }
        studentService.insert(student);

        String myName = student.getName();
        System.out.println(myName);
        if(myName!=NULL)
            return  1;
        return 0;
    }
    @DeleteMapping("/delete/{id}")
    public int delete( @PathVariable Integer id){

        if(studentService.findById(id)==null)
            return -1;

        studentService.delete(id);

        if(studentService.findById(id)==null)
            return 1;
        return 0;
    }
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable Integer id) {
//        studentService.delete(id);
//    }

}
