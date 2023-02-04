package com.example.EmployeeMS.controller;

import com.example.EmployeeMS.dto.EmployeeDTO;
import com.example.EmployeeMS.dto.ResponseDTO;
import com.example.EmployeeMS.entity.Employee;
import com.example.EmployeeMS.service.EmployeeService;
import com.example.EmployeeMS.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;

//    Add employee started
    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        try
        {
            String res = employeeService.saveEmployee(employeeDTO);
            if(res.equals("00"))
            {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Saved");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06"))
            {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Registered Account");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
            else
            {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Something Went Wrong");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex)
        {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    Add employee ended

//    Update employee started
    @PutMapping(value = "/updateEmployee" )
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        try
        {
            String res = employeeService.updateEmployee(employeeDTO);
            if(res.equals("00"))
            {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Updated");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("01"))
            {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not A Registered Employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
            else
            {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Something Went Wrong");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception ex)
        {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    Update employee ended

//    Get all employee started
    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees()
    {
        try
        {
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Successfully Get");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }
        catch (Exception ex)
        {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    Get all employee ended

//    Search  employee by id started
    @GetMapping(value = "/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID)
    {
        try
        {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);
            if (employeeDTO != null)
            {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Searched");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }
            else
            {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available for this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex)
        {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    Search  employee by id ended

}
