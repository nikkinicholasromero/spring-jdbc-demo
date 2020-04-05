package com.demo.repository;

import com.demo.mapper.EmployeeMapper;
import com.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String FIND_ALL_EMPLOYEE = "SELECT * FROM EMPLOYEES";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEE, employeeMapper);
    }

}
