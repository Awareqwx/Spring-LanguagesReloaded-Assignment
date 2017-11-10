package com.awareqwx.Languages.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.awareqwx.Languages.models.Language;
import com.awareqwx.Languages.repositories.LanguageRepo;

@Service
public class LanguageService
{
    
    private LanguageRepo languageRepo;

    public LanguageService(LanguageRepo lr)
    {
        languageRepo = lr;
    }
    
    public void add(String name, String creator, String version)
    {
        languageRepo.save(new Language(name, creator, version));
    }

    public void add(Language l)
    {
        languageRepo.save(l);
    }
    
    public ArrayList<Language> allLanguages()
    {
        return languageRepo.findAll();
    }
    
    public void remove(Long id)
    {
        languageRepo.delete(id);
    }

    public Language get(Long index)
    {
        return languageRepo.findOne(index);
    }
    
    public void edit(Language l)
    {
        languageRepo.save(l);
    }

}
