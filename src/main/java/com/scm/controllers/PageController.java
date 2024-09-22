package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {

        System.out.println("Home page handler");
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Hendro Wunga");
        model.addAttribute("githubRepository", "https://github.com/hendrowunga/SpringBoot-monolithic");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // Service

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    // contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // login
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // register
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();

        // default data bhi daal sakte hai
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");

        model.addAttribute("userForm", userForm);
        return new String("register");
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        System.out.println("Processing Registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);
        // validate form data
        // TODO::Validate userForm[Next Video]

        // save to database

        // userservice

         //UserForm--> User
         User user = User.builder()
         .name(userForm.getName())
         .email(userForm.getEmail())
         .password(userForm.getPassword())
         .about(userForm.getAbout())
         .phoneNumber(userForm.getPhoneNumber())
         .profilePic(
         "https://i.pngimg.me/thumb/f/720/c3f2c592f9.jpg")
         .build();

         User savedUser=userService.saveUser(user);
         System.out.println("user saved");
        // message = "Registration Successful"
        // redirectto login page

        return "redirect:/register";
    }

}
