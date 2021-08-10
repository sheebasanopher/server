package com.sheeba.server.repository;

import com.sheeba.server.domain.LibraryStudentPojo;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<LibraryStudentPojo, Integer> {
    LibraryStudentPojo findByName(String name);

    LibraryStudentPojo findByNameAndDepartment(String name, String department);

}
