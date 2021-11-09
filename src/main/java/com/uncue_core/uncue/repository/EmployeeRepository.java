package com.uncue_core.uncue.repository;

import com.uncue_core.uncue.collections.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByUid(String uid);
    Optional<Employee> findById(String id);
    void deleteByid(String id);

}
