package com.example.ReserveAt.Dto;

import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Model.Business;
import com.example.ReserveAt.Model.Employee;
import jakarta.persistence.*;

import java.util.List;

public class EmployeeDTO {
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private List<ActivityDTO> activities;
    private Long businessId;

    public EmployeeDTO(String employeeName, String employeeSurname, Long businessId) {
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.businessId = businessId;
    }

    public EmployeeDTO(Long employeeId, String employeeName, String employeeSurname, List<ActivityDTO> activities, Long businessId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.activities = activities;
        this.businessId = businessId;
    }

    public EmployeeDTO() {

    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
