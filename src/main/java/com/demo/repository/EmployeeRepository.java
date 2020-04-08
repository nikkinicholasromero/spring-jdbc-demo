package com.demo.repository;

import com.demo.mapper.EmployeeMapper;
import com.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {
    private static final String FIND_ALL = "SELECT * FROM EMPLOYEES";
    private static final String FIND_BY_ID = "SELECT * FROM EMPLOYEES WHERE ID = :ID";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL, employeeMapper);
    }

    public Employee findById(String id) {
        Map<String, String> namedparam = new HashMap<>();
        namedparam.put("ID", id);
        return jdbcTemplate.query(FIND_BY_ID, namedparam, employeeMapper).get(0);
    }
}
