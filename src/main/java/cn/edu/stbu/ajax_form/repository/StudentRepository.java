package cn.edu.stbu.ajax_form.repository;

import cn.edu.stbu.ajax_form.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByNameLike(String name);
    //    List<Student> deleteStudent(Integer id);
    List<Student> findByNameAndPassword(String name,String password);
    List<Student> findByScore(String score);

}