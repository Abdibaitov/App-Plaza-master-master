package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.Application;
import peaksoft.model.Genre;
import peaksoft.service.impl.ApplicationService;
import peaksoft.service.impl.GenreService;

import java.util.List;

@Controller
@RequestMapping("/appications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final GenreService genreService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, GenreService genreService) {
        this.applicationService = applicationService;
        this.genreService = genreService;
    }

    @GetMapping("/addApps")
    public String addAplication(Model model) {
        model.addAttribute("application", new Application());
        return "/applications/save";
    }
    @PostMapping("/saveApps")
    public String saveApplication(@ModelAttribute("applications") Application application) {
        applicationService.save(application);
        return "redirect:/applications";
    }
    @GetMapping("/findAll")
    public String findAllApplication(Model model){
        model.addAttribute("appList",applicationService.findAll());
        return "applications/find-all";
    }
    @ModelAttribute("genreList")
    public List<Genre> genreList(){
        return genreService.findAll();
    }


}
