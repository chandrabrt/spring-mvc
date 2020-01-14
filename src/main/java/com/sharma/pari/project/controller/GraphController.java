package com.sharma.pari.project.controller;

import com.sharma.pari.project.model.User;
import com.sharma.pari.project.resource.Province;
import com.sharma.pari.project.service.InsuranceService;
import com.sharma.pari.project.service.PatientService;
import com.sharma.pari.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class GraphController {

    private final UserService userService;
    private final PatientService patientService;
    private final InsuranceService insuranceService;

    public GraphController(UserService userService, PatientService patientService, InsuranceService insuranceService) {
        this.userService = userService;
        this.patientService = patientService;
        this.insuranceService = insuranceService;
    }

    @GetMapping("/admin/demographics")
    public String barGraph(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
        int totalAdmitPatient = patientService.totalAdmit();
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        surveyMap.put("Province 1", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE1), totalAdmitPatient));
        surveyMap.put("Province 2", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE2), totalAdmitPatient));
        surveyMap.put("Province 3", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE3), totalAdmitPatient));
        surveyMap.put("Province 4", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE4), totalAdmitPatient));
        surveyMap.put("Province 5", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE5), totalAdmitPatient));
        surveyMap.put("Province 6", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE6), totalAdmitPatient));
        surveyMap.put("Province 7", (int) calculatePercentage(patientService.totalAdmitPatient(Province.PROVINCE7), totalAdmitPatient));
        model.addAttribute("surveyMap", surveyMap);
        return "analysis/demographics";
    }

    public double calculatePercentage(double province, double totalAdmitPatient) {
        return province * 100 / totalAdmitPatient;
    }

}
