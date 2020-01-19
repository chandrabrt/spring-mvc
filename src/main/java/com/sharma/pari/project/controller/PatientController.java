//package com.sharma.pari.project.controller;
//
//import com.sharma.pari.project.model.Disease;
//import com.sharma.pari.project.model.Insurance;
//import com.sharma.pari.project.model.Patient;
//import com.sharma.pari.project.model.User;
//import com.sharma.pari.project.repository.DiseaseRepository;
//import com.sharma.pari.project.repository.InsuranceRepository;
//import com.sharma.pari.project.resource.PatientDetails;
//import com.sharma.pari.project.resource.PatientDisease;
//import com.sharma.pari.project.resource.PatientDto;
//import com.sharma.pari.project.service.InsuranceService;
//import com.sharma.pari.project.service.PatientService;
//import com.sharma.pari.project.service.UserService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Controller
//public class PatientController {
//    private final UserService userService;
//    private final PatientService patientService;
//    private final InsuranceService insuranceService;
//    private final DiseaseRepository diseaseRepository;
//    private final InsuranceRepository insuranceRepository;
//
//    public PatientController(UserService userService, PatientService patientService, InsuranceService insuranceService, DiseaseRepository diseaseRepository, InsuranceRepository insuranceRepository) {
//        this.userService = userService;
//        this.patientService = patientService;
//        this.insuranceService = insuranceService;
//        this.diseaseRepository = diseaseRepository;
//        this.insuranceRepository = insuranceRepository;
//    }
//
//    @RequestMapping(value = {"/admin/diagnosis"}, method = RequestMethod.GET)
//    public ModelAndView diagnosis() {
//
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        List<PatientDisease> mostCommonDisease = patientService.commonDisease();
//        String mostCommonDiseaseName = mostCommonDisease.get(0).getName();
//
//        List<PatientDisease> leastCommonDisease = reverseList(mostCommonDisease);
//        String leastCommonDiseaseName = leastCommonDisease.get(0).getName();
//
//        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//        model.addObject("leastCommonDiseaseName", leastCommonDiseaseName);
//        model.addObject("mostCommonDiseaseName", mostCommonDiseaseName);
//        model.setViewName("analysis/diagnosis");
//        return model;
//    }
//
//    @RequestMapping(value = {"/admin/insurance"}, method = RequestMethod.GET)
//    public ModelAndView insurance() {
//
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        List<Insurance> insurances = insuranceService.findAllInsurance();
//        List<PatientDetails> patientDetailsList = new ArrayList<>();
//        for (Insurance insurance : insurances) {
//            int patients = patientService.findAllPatientByInsuranceName(insurance.getName());
//            PatientDetails patientDetails = new PatientDetails();
//            patientDetails.setInsuranceName(insurance.getName());
//            patientDetails.setNumberOfPatient(patients);
//            patientDetailsList.add(patientDetails);
//        }
//        model.addObject("patientDetailsList", patientDetailsList);
//        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//        model.addObject("insurances", insurances);
//        model.setViewName("analysis/insurance");
//        return model;
//    }
//
//    @RequestMapping(value = {"/admin/discharge"}, method = RequestMethod.GET)
//    public ModelAndView getDischarge() {
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        List<Patient> patients = patientService.findAllPatient();
//        model.addObject("patients", patients);
//        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//        model.setViewName("analysis/discharge");
//        return model;
//    }
//
//    @GetMapping("admin/discharge/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
//
//        Patient patient = patientService.findById(id);
//        model.addAttribute("patient", patient);
//        return "analysis/update";
//    }
//
//    @PostMapping(value = {"/admin/discharge/{id}"})
//    public String updatePatient(@PathVariable("id") int id, @Valid Patient patient, BindingResult bindingResult, Model model) {
////        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        if (bindingResult.hasErrors()) {
//            patient.setId(id);
//            return ("analysis/update");
//        }
//
//        patientService.updatePatient(patient);
//        model.addAttribute("patients", patientService.findAllPatient());
//        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//        model.addObject("msg", "Patient has been updated successfully!");
//        model.setViewName("analysis/admit");
//        return model;
//    }
//
//    @RequestMapping(value = {"/admin/admit"}, method = RequestMethod.GET)
//    public ModelAndView signup() {
//
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        List<Patient> patients = patientService.findAllPatient();
//        List<Disease> diseases = diseaseRepository.findAll();
//        List<Insurance> insurances = insuranceRepository.findAllInsurance();
//
//        model.addObject("patients", patients);
//        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//        model.addObject("diseases", diseases);
//        model.addObject("insurances", insurances);
//        model.setViewName("analysis/admit");
//        return model;
//    }
//
//    @RequestMapping(value = {"/admin/admit"}, method = RequestMethod.POST)
//    public ModelAndView createUser(@Valid PatientDto patientDto, BindingResult bindingResult) {
//        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        if (bindingResult.hasErrors()) {
//            model.setViewName("analysis/admit");
//        } else {
//            List<Disease> diseases = diseaseRepository.findAll();
//            List<Insurance> insurances = insuranceRepository.findAllInsurance();
//            model.addObject("diseases", diseases);
//            model.addObject("insurances", insurances);
//            patientService.addPatient(patientDto);
//            model.addObject("msg", "Patient has been added successfully!");
//            model.addObject("patient", new PatientDto());
//            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
//            model.setViewName("analysis/admit");
//        }
//        return model;
//    }
//
//    public static <T> List<T> reverseList(List<T> list) {
//        List<T> reverse = new ArrayList<>(list);
//        Collections.reverse(reverse);
//        return reverse;
//    }
//}


package com.sharma.pari.project.controller;

import com.sharma.pari.project.model.Disease;
import com.sharma.pari.project.model.Insurance;
import com.sharma.pari.project.model.Patient;
import com.sharma.pari.project.model.User;
import com.sharma.pari.project.repository.DiseaseRepository;
import com.sharma.pari.project.repository.InsuranceRepository;
import com.sharma.pari.project.resource.PatientDetails;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.resource.PatientDto;
import com.sharma.pari.project.service.InsuranceService;
import com.sharma.pari.project.service.PatientService;
import com.sharma.pari.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PatientController {
    private final UserService userService;
    private final PatientService patientService;
    private final InsuranceService insuranceService;
    private final DiseaseRepository diseaseRepository;
    private final InsuranceRepository insuranceRepository;

    public PatientController(UserService userService, PatientService patientService, InsuranceService insuranceService, DiseaseRepository diseaseRepository, InsuranceRepository insuranceRepository) {
        this.userService = userService;
        this.patientService = patientService;
        this.insuranceService = insuranceService;
        this.diseaseRepository = diseaseRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @RequestMapping(value = {"/admin/diagnosis"}, method = RequestMethod.GET)
    public ModelAndView diagnosis() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<PatientDisease> mostCommonDisease = patientService.commonDisease();
        String mostCommonDiseaseName = mostCommonDisease.get(0).getName();

        List<PatientDisease> leastCommonDisease = reverseList(mostCommonDisease);
        String leastCommonDiseaseName = leastCommonDisease.get(0).getName();

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("leastCommonDiseaseName", leastCommonDiseaseName);
        model.addObject("mostCommonDiseaseName", mostCommonDiseaseName);
        model.setViewName("analysis/diagnosis");
        return model;
    }

    @RequestMapping(value = {"/admin/insurance"}, method = RequestMethod.GET)
    public ModelAndView insurance() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Insurance> insurances = insuranceService.findAllInsurance();
        List<PatientDetails> patientDetailsList = new ArrayList<>();
        for (Insurance insurance : insurances) {
            int patients = patientService.findAllPatientByInsuranceName(insurance.getName());
            PatientDetails patientDetails = new PatientDetails();
            patientDetails.setInsuranceName(insurance.getName());
            patientDetails.setNumberOfPatient(patients);
            patientDetailsList.add(patientDetails);
        }
        model.addObject("patientDetailsList", patientDetailsList);
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("insurances", insurances);
        model.setViewName("analysis/insurance");
        return model;
    }

    @RequestMapping(value = {"/admin/discharge"}, method = RequestMethod.GET)
    public ModelAndView getDischarge() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Patient>  patients = patientService.findAllDischargePatient();
        model.addObject("patients", patients);
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.setViewName("analysis/discharge");
        return model;
    }

    @GetMapping("admin/discharge/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());

        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "analysis/update";
    }

    @PostMapping(value = {"/admin/discharge/{id}"})
    public String updatePatient(@PathVariable("id") int id, @Valid Patient patient, BindingResult bindingResult, Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (bindingResult.hasErrors()) {
            patient.setId(id);
            return "analysis/update";
        }
        model.addAttribute("patients", patientService.findAllPatient());
        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
        model.addAttribute("msg", "Patient has been updated successfully!");
        model.addAttribute("errorMessage", "Unable to update");
        return "analysis/admit";
    }

    @RequestMapping(value = {"/admin/admit"}, method = RequestMethod.GET)
    public ModelAndView signup() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Patient> patients = patientService.findAllPatient();
        List<Disease> diseases = diseaseRepository.findAll();
        List<Insurance> insurances = insuranceRepository.findAllInsurance();

        model.addObject("patients", patients);
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("diseases", diseases);
        model.addObject("insurances", insurances);
        model.setViewName("analysis/admit");
        return model;
    }

    @RequestMapping(value = {"/admin/admit/patient"}, method = RequestMethod.GET)
    public ModelAndView admitPatient() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
//        List<Patient> patients = patientService.findAllPatient();
        List<Disease> diseases = diseaseRepository.findAll();
        List<Insurance> insurances = insuranceRepository.findAllInsurance();

        model.addObject("patient", new Patient());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("diseases", diseases);
        model.addObject("insurances", insurances);
        model.setViewName("analysis/add-patient");
        return model;
    }

    @RequestMapping(value = {"/admin/admit/patient"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid PatientDto patientDto, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (bindingResult.hasErrors()) {
            model.setViewName("analysis/admit");
        } else {
            List<Disease> diseases = diseaseRepository.findAll();
            List<Insurance> insurances = insuranceRepository.findAllInsurance();
            model.addObject("diseases", diseases);
            model.addObject("insurances", insurances);
            patientService.addPatient(patientDto);
            model.addObject("msg", "Patient has been added successfully!");
            model.addObject("patient", new PatientDto());
            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.setViewName("analysis/add-patient");
        }
        return model;
    }

    public static <T> List<T> reverseList(List<T> list) {
        List<T> reverse = new ArrayList<>(list);
        Collections.reverse(reverse);
        return reverse;
    }
}
