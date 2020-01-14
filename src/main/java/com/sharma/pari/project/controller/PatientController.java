package com.sharma.pari.project.controller;

import com.sharma.pari.project.model.Insurance;
import com.sharma.pari.project.model.User;
import com.sharma.pari.project.resource.PatientDetails;
import com.sharma.pari.project.resource.PatientDisease;
import com.sharma.pari.project.service.InsuranceService;
import com.sharma.pari.project.service.PatientService;
import com.sharma.pari.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PatientController {
    private final UserService userService;
    private final PatientService patientService;
    private final InsuranceService insuranceService;

    public PatientController(UserService userService, PatientService patientService, InsuranceService insuranceService) {
        this.userService = userService;
        this.patientService = patientService;
        this.insuranceService = insuranceService;
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

    public static<T> List<T> reverseList(List<T> list)
    {
        List<T> reverse = new ArrayList<>(list);
        Collections.reverse(reverse);
        return reverse;
    }
}
