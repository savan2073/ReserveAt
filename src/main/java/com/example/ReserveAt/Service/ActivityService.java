package com.example.ReserveAt.Service;

import com.example.ReserveAt.Dto.ActivityDTO;
import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ActivityService {

    //create a new activity
    public Activity addActivity(ActivityDTO activityDTO, Long employeeId);

    //get all activites
    public List<Activity> getAllActivities();

    //get activity by id
    public Activity findById(Long id);

    //update existing activity
    public Activity update(Long id, ActivityDTO activityDTO);

    //delete activity
    public void delete(Long id);

    //get all activities by employeeid
    List<Activity> findAllByEmployeeId(Long employeeId);
}
