package com.awareqwx.Languages.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.awareqwx.Languages.models.Language;
import com.awareqwx.Languages.services.LanguageService;

@Controller
public class LanguageController
{

    private LanguageService languageService;

    public LanguageController(LanguageService ls)
    {
        languageService = ls;
    }

    @RequestMapping("/languages")
    public String index(Model model)
    {
        model.addAttribute("language", new Language());
        model.addAttribute("languages", languageService.allLanguages());
        return "index.jsp";
    }

    @PostMapping("/languages/add")
    public String add(@Valid @ModelAttribute("language") Language l, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("languages", languageService.allLanguages());
            return "index.jsp";
        } else
        {
            languageService.add(l);
            return "redirect:/languages/" + (languageService.allLanguages().size() - 1);
        }
    }

    @RequestMapping("/languages/delete/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        languageService.remove(id);
        return "redirect:/languages";
    }

    @RequestMapping("/languages/{id}")
    public String show(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("id", id);
        model.addAttribute("language", languageService.get(id));
        return "show.jsp";
    }

    @RequestMapping("/languages/edit/{index}")
    public String edit(@PathVariable("index") Long id, Model model)
    {
        Language language = languageService.get(id);
        if (language != null){
            model.addAttribute("language", language);
            return "edit.jsp";
        }else{
            return "redirect:/languages";
        }
    }

    @PostMapping("/languages/update/{index}")
    public String update(@PathVariable("index") Long id, @Valid @ModelAttribute("language") Language l,
            BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("language", languageService.get(id));
            return "edit.jsp";
        } else
        {
            l.setId(id);
            languageService.edit(l);
            return "redirect:/languages/" + id;
        }
    }

}
