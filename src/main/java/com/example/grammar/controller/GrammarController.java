package com.example.grammar.controller;

import com.example.grammar.model.Noun;
import com.example.grammar.model.Sentence;
import com.example.grammar.model.Verb;
import com.example.grammar.service.SentenceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grammar")
public class GrammarController {

    private final SentenceService sentenceService;

    public GrammarController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/sentence")
    public Sentence getSentence(@RequestParam String noun,
                                @RequestParam boolean plural,
                                @RequestParam String verb) {
        Noun n = new Noun(noun, plural);
        Verb v = new Verb(verb, verb + "ed");
        return sentenceService.createSentence(n, v);
    }
}