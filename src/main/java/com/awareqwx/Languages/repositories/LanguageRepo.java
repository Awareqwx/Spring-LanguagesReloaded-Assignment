package com.awareqwx.Languages.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.awareqwx.Languages.models.Language;

@Repository
public interface LanguageRepo extends CrudRepository<Language, Long>
{

    ArrayList<Language> findAll();
    
}
