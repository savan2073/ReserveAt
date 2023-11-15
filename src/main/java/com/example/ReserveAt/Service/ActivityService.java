package com.example.ReserveAt.Service;

import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    //create a new activity
    public Activity createActivity(Activity activity){
        return activityRepository.save(activity);
    }
    //get all activites
    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }
    //get activity by id
    public Activity findById(int id) {
        return activityRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
    }
    //update existing activity
    public Activity update(int id, Activity activityDetails) {
        Activity activity = activityRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activity.setActivityName(activityDetails.getActivityName());
        activity.setDescription(activityDetails.getDescription());
        activity.setPrice(activityDetails.getPrice());
        activity.setDurationOfTreatment(activityDetails.getDurationOfTreatment());
        return activityRepository.save(activity);
    }
    //delete activity
    public void delete(int id) {
        Activity activity = activityRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activityRepository.delete(activity);
    }
}
