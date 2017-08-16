package me.yling.contactapp0816.controllers;

import me.yling.contactapp0816.models.Contact;
import me.yling.contactapp0816.repositories.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    ContactRepo contactRepo;

    @RequestMapping("/")
    public String listContacts(Model model)
    {
        model.addAttribute("contacts",contactRepo.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String contactForm(Model model)
    {
        model.addAttribute("contact", new Contact());
        return "contactform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Contact contact, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "contactform";
        }
        contactRepo.save(contact);
        return "redirect:/";

    }

    @RequestMapping("/detail/{id}")
    public String showContact(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("contact", contactRepo.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateContact(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("contact", contactRepo.findOne(id));
        return "contactform";
    }

    @RequestMapping("/delete/{id}")
    public String delContact(@PathVariable("id") long id, Model model)
    {
        contactRepo.delete(id);
        return "redirect:/";
    }






}
