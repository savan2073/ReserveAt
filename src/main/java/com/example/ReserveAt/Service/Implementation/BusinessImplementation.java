package com.example.ReserveAt.Service.Implementation;

import com.example.ReserveAt.Dto.*;
import com.example.ReserveAt.Model.*;
import com.example.ReserveAt.Repository.BusinessRepository;
import com.example.ReserveAt.Repository.ReviewRepository;
import com.example.ReserveAt.Repository.WorkingHoursRepository;
import com.example.ReserveAt.Response.LoginMessage;
import com.example.ReserveAt.Service.BusinessService;
import com.example.ReserveAt.Service.JwtTokenProvider;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessImplementation implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private WorkingHoursRepository workingHoursRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addBiz(BusinessDTO businessDTO) {
        List<Employee> employees = convertToEmployeeList(businessDTO.getEmployees());
        Business business = new Business(
                businessDTO.getBusinessId(),
                businessDTO.getBusinessName(),
                businessDTO.getCity(),
                businessDTO.getAddress(),
                businessDTO.getRating(),
                businessDTO.getDescription(),
                employees,
                businessDTO.getBusinessType(),
                businessDTO.getEmail(),
                this.passwordEncoder.encode(businessDTO.getPassword()),
                businessDTO.getReviews(),
                businessDTO.getPhotoPath(),
                null
        );
        businessRepository.save(business);
        return business.getBusinessName() + business.getBusinessType();
    }

    private List<Employee> convertToEmployeeList(List<EmployeeDTO> employeeDTOs) {
        if (employeeDTOs == null) {
            return null;
        }
        return employeeDTOs.stream().map(this::convertToEmployee).collect(Collectors.toList());
    }

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        Long businessId = employeeDTO.getBusinessId();
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + businessId));
        return new Employee(
                employeeDTO.getEmployeeId(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getEmployeeSurname(),
                null,
                business
                // inne potrzebne pola...
        );
    }


    @Override
    public LoginMessage loginBiz(LoginDTO loginDTO) {
        String msg = "";
        Business business = businessRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Business not found"));
        if (business != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = business.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
            if (isPasswordRight) {
                String token = jwtTokenProvider.generateToken(
                        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()),
                        "business" // Określenie roli jako "business"
                );
                Optional<Business> company1 = businessRepository.findBizByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (company1.isPresent()) {
                    return new LoginMessage("Login success", true, token, business.getBusinessId(), "business");
                } else {
                    return new LoginMessage("Login failed", false);
                }
            } else {
                return new LoginMessage("Password is wrong", false);
            }
        } else {
            return new LoginMessage("Email does not exist", false);
        }
    }

    @Override
    public BusinessDTO getBizDetailsCard(Long businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found."));
        int reviewCount = reviewRepository.countByBusinessBusinessId(businessId);

        double rating = business.getRating();

        return new BusinessDTO(
                business.getBusinessId(),
                business.getBusinessName(),
                business.getCity(),
                null,
                rating,
                null,
                null,
                null,
                null,
                null,
                null,
                business.getPhotoPath(),
                reviewCount,
                null
        );
    }

    @Override
    public BusinessDTO getBusinessDetails(String email) {
        Business business = businessRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Business not found"));
        return convertToBusinessDTO(business);
    }

    @Override
    public Page<BusinessDTO> findBusinessesByTypeAndCity(City city, BusinessType businessType, Pageable pageable) {
        Page<Business> businesses = businessRepository.findByCityAndBusinessType(city, businessType, pageable);
        return businesses.map(this::convertToBusinessDTO);
    }

    @Override
    public BusinessDTO getBusinessByNameAndCity(String businessName, String cityString) {
        City city;
        try {
            city = City.valueOf(cityString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowa nazwa miasta.");
        }
        Business business = businessRepository.findByBusinessNameAndCity(businessName, city)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono biznesu"));
        return convertToBusinessDTO(business);
    }

    private BusinessDTO convertToBusinessDTO(Business business) {
        int reviewCount = reviewRepository.countByBusinessBusinessId(business.getBusinessId());

        // Konwersja pracowników
        List<EmployeeDTO> employeeDTOs = business.getEmployees().stream().map(employee -> {
            // Tworzenie listy ActivityDTO z listy Activity
            List<ActivityDTO> activityDTOs = employee.getActivities().stream()
                    .map(this::convertToActivityDTO)
                    .collect(Collectors.toList());

            // Tworzenie EmployeeDTO
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeSurname(),
                    activityDTOs, // Użyj przekonwertowanej listy ActivityDTO
                    employee.getBusiness().getBusinessId() // Zakładając, że chcesz tu ID biznesu
            );

            return employeeDTO;
        }).collect(Collectors.toList());

        // Konwersja godzin pracy
        List<WorkingHoursDTO> workingHoursDTOs = business.getWorkingHours().stream()
                .map(workingHours -> new WorkingHoursDTO(
                        workingHours.getId(),
                        workingHours.getDayOfWeek(),
                        workingHours.getStartTime(),
                        workingHours.getEndTime(),
                        workingHours.getBusiness().getBusinessId()
                ))
                .collect(Collectors.toList());


        return new BusinessDTO(
                business.getBusinessId(),
                business.getBusinessName(),
                business.getCity(),
                business.getAddress(),
                business.getRating(),
                business.getDescription(),
                employeeDTOs,
                business.getBusinessType(),
                business.getEmail(),
                null,
                business.getReviews(),
                business.getPhotoPath(),
                reviewCount,
                workingHoursDTOs
        );
    }

    private ActivityDTO convertToActivityDTO(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getActivityName(),
                activity.getDescription(),
                activity.getPrice(),
                (int) activity.getDurationOfTreatment().toMinutes()
        );
    }


    public void addWorkingHours(Long businessId, List<WorkingHoursDTO> workingHoursDTOs) {
        for (WorkingHoursDTO dto : workingHoursDTOs) {
            System.out.println("DTO DayOfWeek: " + dto.getDayOfWeek() + ", StartTime: " + dto.getStartTime() + ", EndTime: " + dto.getEndTime());
        }
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new EntityNotFoundException("Business not found with id: " + businessId));

        List<WorkingHours> workingHoursList = workingHoursDTOs.stream()
                .map(dto -> {
                    WorkingHours workingHours = new WorkingHours();
                    workingHours.setBusiness(business);
                    workingHours.setDayOfWeek(dto.getDayOfWeek());
                    workingHours.setStartTime(dto.getStartTime());
                    workingHours.setEndTime(dto.getEndTime());

                    System.out.println("zmapowane godziny pracy: " + workingHours);

                    return workingHours;
                })
                .toList();

        workingHoursRepository.saveAll(workingHoursList);
    }



    public List<WorkingHoursDTO> getWorkingHours(Long businessId) {
        List<WorkingHours> workingHours = workingHoursRepository.findByBusinessBusinessId(businessId);

        return workingHours.stream()
                .map(workingHour -> new WorkingHoursDTO(
                        workingHour.getId(),
                        workingHour.getDayOfWeek(),
                        workingHour.getStartTime(),
                        workingHour.getEndTime(),
                        workingHour.getBusiness().getBusinessId()
                ))
                .collect(Collectors.toList());
    }

    public void updateWorkingHours(Long businessId, List<WorkingHoursDTO> workingHoursDTOs) {
        for (WorkingHoursDTO dto : workingHoursDTOs) {
            WorkingHours workingHours = workingHoursRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("WorkingHours not found with id: " + dto.getId()));

            workingHours.setDayOfWeek(dto.getDayOfWeek());
            workingHours.setStartTime(dto.getStartTime());
            workingHours.setEndTime(dto.getEndTime());
            workingHoursRepository.save(workingHours);
        }
    }

    public void deleteWorkingHours(Long workingHoursId) {
        if (!workingHoursRepository.existsById(workingHoursId)) {
            throw new EntityNotFoundException("WorkingHours not found with id: " + workingHoursId);
        }
        workingHoursRepository.deleteById(workingHoursId);
    }
}
