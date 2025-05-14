package com.example.Tutorials.Controller;

import com.example.Tutorials.Entity.Comments;
import com.example.Tutorials.Entity.Tutorials;
import com.example.Tutorials.Repository.CommentsRepository;
import com.example.Tutorials.Repository.TutorialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutorials")
public class CommentViewController {

    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping
    public String listTutorials(Model model) {
        model.addAttribute("tutorials", tutorialsRepository.findAll());
        model.addAttribute("newTutorial", new Tutorials());
        model.addAttribute("newComment", new Comments());
        return "tutorials";
    }

    // Ajouter un tutoriel
    @PostMapping("/add")
    public String addTutorial(@ModelAttribute Tutorials newTutorial) {
        tutorialsRepository.save(newTutorial);
        return "redirect:/tutorials";
    }

    // Modifier un tutoriel
    @PostMapping("/edit/{id}")
    public String editTutorial(@PathVariable Long id, @ModelAttribute Tutorials updatedTutorial) {
        Tutorials tutorial = tutorialsRepository.findById(id).orElseThrow();
        tutorial.setTitle(updatedTutorial.getTitle());
        tutorial.setDescription(updatedTutorial.getDescription());
        tutorialsRepository.save(tutorial);
        return "redirect:/tutorials";
    }

    // Supprimer un tutoriel
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable Long id) {
        tutorialsRepository.deleteById(id);
        return "redirect:/tutorials";
    }



    // Ajouter un commentaire
    @PostMapping("/comments/add/{tutorialId}")
    public String addComment(@PathVariable Long tutorialId, @ModelAttribute Comments newComment) {
        Tutorials tutorial = tutorialsRepository.findById(tutorialId).orElseThrow();
        newComment.setTutorials(tutorial);
        commentsRepository.save(newComment);
        return "redirect:/tutorials";
    }

    // Modifier un commentaire
    @PostMapping("/comments/edit/{commentId}")
    public String editComment(@PathVariable Long commentId, @RequestParam String content) {
        Comments comment = commentsRepository.findById(commentId).orElseThrow();
        comment.setContent(content);
        commentsRepository.save(comment);
        return "redirect:/tutorials";
    }

    // Supprimer un commentaire
    @GetMapping("/comments/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentsRepository.deleteById(commentId);
        return "redirect:/tutorials";
    }
}
