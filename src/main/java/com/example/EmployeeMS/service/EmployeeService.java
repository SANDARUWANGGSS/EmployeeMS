package com.example.EmployeeMS.service;

import com.example.EmployeeMS.dto.EmployeeDTO;
import com.example.EmployeeMS.entity.Employee;
import com.example.EmployeeMS.repo.EmployeeRepo;
import com.example.EmployeeMS.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmployee(EmployeeDTO employeeDTO)
    {
        if(employeeRepo.existsById(employeeDTO.getEmpID()))
        {
            return VarList.RSP_DUPLICATED;
        }
        else
        {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
}