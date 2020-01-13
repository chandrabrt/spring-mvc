package com.sharma.pari.project.controller;

import com.sharma.pari.project.model.User;
import com.sharma.pari.project.service.InsuranceService;
import com.sharma.pari.project.service.PatientService;
import com.sharma.pari.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;
    private final PatientService patientService;
    private final InsuranceService insuranceService;

    public UserController(UserService userService, PatientService patientService, InsuranceService insuranceService) {
        this.userService = userService;
        this.patientService = patientService;
        this.insuranceService = insuranceService;
    }

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value= {"/admin"}, method=RequestMethod.GET)
    public ModelAndView admin(){

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("user", new User());
        //total patient
        model.addObject("totalAdmit", patientService.totalAdmit());
        //total discharge patient
        model.addObject("totalDischarge", patientService.totalDischarge());
        //total insurance
        model.addObject("totalInsurance", insuranceService.totalInsurance());
        model.addObject("averageLengthOfStay", insuranceService.totalInsurance());
        model.setViewName("home/admin");
        return model;
    }

    @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "startDate", defaultValue = "2020/01/01") String startDate,
                             @RequestParam(value = "endDate", defaultValue = "2020/01/01") String endDate) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("userName", user.getFirstname() + " " + user.getLastname());

        //total patient
        model.addObject("totalAdmitPatient", patientService.totalAdmitPatient(startDate, endDate));
        //total discharge patient
        model.addObject("totalDischargePatient", patientService.totalDischargePatient(startDate, endDate));
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}
