package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.ActivityDTO;
import com.example.ReserveAt.Model.Activity;
import com.example.ReserveAt.Model.Employee;
import com.example.ReserveAt.Repository.ActivityRepository;
import com.example.ReserveAt.Repository.EmployeeRepository;
import com.example.ReserveAt.Service.ActivityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
@Service
public class ActivityImplementation implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Activity addActivity(ActivityDTO activityDTO, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));

        Activity activity = new Activity();
        activity.setActivityName(activityDTO.getActivityName());
        activity.setDescription(activityDTO.getDescription());
        activity.setPrice(activityDTO.getPrice());
        // Konwersja czasu trwania z minut na Duration
        activity.setDurationOfTreatment(Duration.ofMinutes(activityDTO.getDurationOfTreatment()));
        activity.setEmployee(employee);
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + id));
    }

    @Override
    public Activity update(Long id, ActivityDTO activityDTO) {
        Activity activity = findById(id); //metodfa findById, aby upewnić się że istnieje

        activity.setActivityName(activityDTO.getActivityName());
        activity.setDescription(activityDTO.getDescription());
        activity.setPrice(activityDTO.getPrice());
        activity.setDurationOfTreatment(Duration.ofMinutes(activityDTO.getDurationOfTreatment()));

        return activityRepository.save(activity);
    }

    @Override
    public void delete(Long id) {
        Activity activity = findById(id);
        activityRepository.delete(activity);
    }

    @Override
    public List<Activity> findAllByEmployeeId(Long employeeId) {
        return activityRepository.findAllByEmployeeEmployeeId(employeeId);
    }
}
