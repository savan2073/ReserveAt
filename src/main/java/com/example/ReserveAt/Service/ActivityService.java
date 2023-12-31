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
    public Activity findById(int id);

    //update existing activity
    public Activity update(int id, Activity activityDetails);

    //delete activity
    public void delete(int id);
}
