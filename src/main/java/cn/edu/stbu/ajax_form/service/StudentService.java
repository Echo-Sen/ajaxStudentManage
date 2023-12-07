package cn.edu.stbu.ajax_form.service;

import cn.edu.stbu.ajax_form.domain.Student;
import cn.edu.stbu.ajax_form.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StudentService {



    List<Student> findAll();

    public Page<Student> findAll(Example<Student> student,Pageable pageable);

    Student findById(Integer id);
    List<Student> findByName(String name);
    List<Student> findByNameAndPassword(String name,String password);

    Student insert(Student student) ;

    Student update(Student student) ;

    void delete(Integer id) ;

    void delete(Student  student) ;

    //    public List<Student> delete(Integer id) {
//        return studentRepository.deleteStudent(id);
//    }
    List<Student> findByScore(String score);

    Page<Student> findAll(Pageable pageable);
}