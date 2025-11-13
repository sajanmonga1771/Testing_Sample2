package com.example.grammar.service;

import com.example.grammar.model.Noun;
import com.example.grammar.model.Sentence;
import com.example.grammar.model.Verb;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {

    public Sentence createSentence(Noun noun, Verb verb) {
        String sentenceText = noun.getWord() + " " + verb.getBaseForm() + " quickly.";
        return new Sentence(sentenceText);
    }
}