package com.jag.cud.controller;


import com.jag.cud.entity.Department;
import com.jag.cud.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    // Save operation
    @PostMapping("/departments")
    public Department saveDepartment(
            @RequestBody Department department) {
//        LOGGER.info("Inside DepartmentController saveDepartment() ");
        return departmentService.saveDepartment(department);
    }

    // Read operation
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
//        LOGGER.info("Inside DepartmentController fetchDepartmentList() ");
        return departmentService.fetchDepartmentList();
    }

    // Update operation
    @PutMapping("/departments/{id}")
    public Department
    updateDepartment(@RequestBody Department department,
                     @PathVariable("id") Long departmentId) {
//        LOGGER.info("Inside DepartmentController updateDepartment() ");
        return departmentService.updateDepartment(
                department, departmentId);
    }

    // Delete operation
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id")
                                       Long departmentId) {
//        LOGGER.info("Inside DepartmentController deleteDepartmentById() ");
        departmentService.deleteDepartmentById(
                departmentId);
        return "Deleted Successfully";
    }
}
