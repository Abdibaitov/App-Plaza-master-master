package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.User;
import peaksoft.model.enums.Role;
import peaksoft.service.impl.ApplicationService;
import peaksoft.service.impl.UserService;

@Controller
@RequestMapping()
public class UserController {
    private final UserService userService;
    private final ApplicationService applicationService;

    @Autowired
    public UserController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user");
        return "users/save";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        User user1 = userService.findById(user.getId());
        model.addAttribute("app", applicationService.findAll());
        if (user1.getRole().equals(Role.ADMIN)) {
            return "role/pageForAdmin";
        }
        return "role/pageForUser";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("userUpdate", user);
        return "users/update";
    }

    @PostMapping("/save-update/{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model) {
        userService.update(id, user);
        model.addAttribute("user", userService.findById(user.getId()));
        return "role/pageForUser";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        User user1 = userService.findById(id);
        userService.deleteById(user1.getId());
        return "redirect:/users";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-in";
    }

    @GetMapping("get-sign-in")
    public String getSignIn(@ModelAttribute("user") User user, Model model) {
        User user1 = userService.getUserByUserNameAndPassword(user.getEmail(), user.getPassword());
        model.addAttribute("user1", user1);
        model.addAttribute("app1", applicationService.findAll());
        if (user1.getRole().equals(Role.ADMIN)) {
            return "role/pageForAdmin";
        }
        return "role/pageForUser";
    }

    @PostMapping("/download/{userId}/{appId}")
    public String addApplicationByUser(@PathVariable("userId") Long userId, @PathVariable("appId") Long appId, Model model) {
        userService.addApplicationByUser(userId, appId);
        User user11 = userService.findById(userId);
        model.addAttribute("user1",user11);
        model.addAttribute("appp",applicationService.findAll());
        return "role/pageForUser";
    }
    @GetMapping("/main")
    public String getMain(){
        return "users/main-page";
    }
    @GetMapping("/qwerty")
    public String adminOruser(@ModelAttribute("user") User user) {
        userService.save(user);
        User user1 = userService.findById(user.getId());
//        model.addAttribute("app", applicationService.findAll());
        if (user1.getRole().equals(Role.ADMIN)) {
            return "role/pageForAdmin";
        }
        return "role/pageForUser";
    }



}
