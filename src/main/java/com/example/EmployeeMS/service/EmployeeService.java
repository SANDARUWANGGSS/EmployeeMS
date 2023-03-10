package com.example.EmployeeMS.service;

import com.example.EmployeeMS.dto.EmployeeDTO;
import com.example.EmployeeMS.entity.Employee;
import com.example.EmployeeMS.repo.EmployeeRepo;
import com.example.EmployeeMS.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

//    Add employee started
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
//    Add employee ended

//    Update employee started
    public String updateEmployee(EmployeeDTO employeeDTO)
    {
        if (employeeRepo.existsById(employeeDTO.getEmpID()))
        {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return  VarList.RSP_SUCCESS;
        }
        else
        {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
//    Update employee ended

//    Get all employee started
    public List<EmployeeDTO> getAllEmployee()
    {
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }
//    Get all employee ended

//    Search employee by id started
    public EmployeeDTO searchEmployee(int empID)
    {
        if (employeeRepo.existsById(empID))
        {
            Employee employee = employeeRepo.findById(empID).orElse(null);
            return modelMapper.map(employee, EmployeeDTO.class);
        }
        else
        {
            return null;
        }
    }
//    Search employee by id ended

//    Delete employee by id started
    public String deleteEmployee(int empID)
    {
        if (employeeRepo.existsById(empID))
        {
            employeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }
        else
        {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
//    Delete employee by id ended
}
