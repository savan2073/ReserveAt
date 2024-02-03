package com.example.ReserveAt.Controller;

import com.example.ReserveAt.Dto.ActivityDTO;
import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    //create new activity
    @PostMapping("/add/{employeeId}")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO, @PathVariable Long employeeId) {
        Activity newActivity = activityService.addActivity(activityDTO, employeeId);
        ActivityDTO newActivityDTO = convertToDTO(newActivity);
        return ResponseEntity.ok(newActivityDTO);
    }

    //convert entity to dto
    private ActivityDTO convertToDTO(Activity activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivityName(activity.getActivityName());
        activityDTO.setDescription(activity.getDescription());
        activityDTO.setPrice(activity.getPrice());
        activityDTO.setDurationOfTreatment((int) activity.getDurationOfTreatment().toMinutes());
        // Dodaj pozostałe potrzebne pola
        return activityDTO;
    }
    //convert dto to entity
    private Activity convertToEntity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setActivityName(activityDTO.getActivityName());
        activity.setDescription(activityDTO.getDescription());
        activity.setPrice(activityDTO.getPrice());
        // Konwersja minut na Duration
        activity.setDurationOfTreatment(Duration.ofMinutes(activityDTO.getDurationOfTreatment()));
        return activity;
    }
    //get all activites
    @GetMapping()
    public ResponseEntity<List<Activity>> getAllActivites() {
        List<Activity> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }
    //get activity by id
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.findById(id);
        return ResponseEntity.ok(activity);
    }
    //update existing activity
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        Activity updatedActivity = activityService.update(id, activityDTO);
        return ResponseEntity.ok(updatedActivity);
    }
    //delete activity
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByEmployeeId(@PathVariable Long employeeId) {
        List<Activity> activities = activityService.findAllByEmployeeId(employeeId);
        List<ActivityDTO> activityDTOs = activities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(activityDTOs);
    }
}
