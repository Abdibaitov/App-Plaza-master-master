package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.Genre;
import peaksoft.service.impl.GenreService;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;


    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("addGenre")
    public String addGenre(Model model){
        model.addAttribute("genre", new Genre());
        return "/genress/save";
    }
    @PostMapping("/saveGenres")
    public String saveGenre(@ModelAttribute("genre")Genre genre){
        genreService.save(genre);
        return "redirect:/genres";
    }



}
