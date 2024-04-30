package com.doping.admin.persistence.repository;


import com.doping.admin.persistence.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Optional<Student> findByStudentNumber(String studentNumber);
}
