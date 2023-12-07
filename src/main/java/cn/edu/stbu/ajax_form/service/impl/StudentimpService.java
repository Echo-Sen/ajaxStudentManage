package cn.edu.stbu.ajax_form.service.impl;

import cn.edu.stbu.ajax_form.domain.Student;
import cn.edu.stbu.ajax_form.repository.StudentRepository;
import cn.edu.stbu.ajax_form.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentimpService implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();

    }
    public Page<Student> findAll(Pageable pageable)
    {
        return studentRepository.findAll(pageable);
    }
    public Page<Student> findAll(Example<Student> student, Pageable pageable){
        return studentRepository.findAll(student,pageable);
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByNameLike(name);
    }

    public List<Student> findByNameAndPassword(String name, String password) {
        return studentRepository.findByNameAndPassword(name, password);
    }

    public Student insert(Student student) {
        return studentRepository.save(student);

    }

    public Student update(Student student) {
        return studentRepository.save(student);

    }

    public void delete(Integer id) {
        Student student = new Student();
        student.setId(id);
        studentRepository.delete(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    //    public List<Student> delete(Integer id) {
//        return studentRepository.deleteStudent(id);
//    }
    public List<Student> findByScore(String score) {
        return studentRepository.findByScore(score);
    }

}