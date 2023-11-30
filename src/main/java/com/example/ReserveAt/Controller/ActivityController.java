package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    //create new activity
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity newActivity = activityService.createActivity(activity);
        return ResponseEntity.ok(newActivity);
    }
    //get all activites
    @GetMapping()
    public ResponseEntity<List<Activity>> getAllActivites() {
        List<Activity> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }
    //get activity by id
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable int id) {
        Activity activity = activityService.findById(id);
        return ResponseEntity.ok(activity);
    }
    //update existing activity
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable int id, @RequestBody Activity activityDetails) {
        Activity updatedActivity = activityService.update(id, activityDetails);
        return ResponseEntity.ok(updatedActivity);
    }
    //delete activity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable int id) {
        activityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
