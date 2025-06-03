package com.pato_palavra.controllers;

import com.pato_palavra.models.WordRegisterResponseModel;
import com.pato_palavra.services.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pato_palavra.services.WordService;

@RestController
@RequestMapping("/api/words")
public class WordController {
    
    @Autowired
    private WordService wordService;

    @Autowired
    private ContextService contextService;

    @PostMapping("/register")
    public ResponseEntity<WordRegisterResponseModel> registerWord(@RequestBody innerRequestBody requestBody) {
        WordRegisterResponseModel response = wordService.registerWord(contextService.getCurrentUsername(), requestBody.getWord());

        if (response.isSuccess())
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().body(response);

    }


}

class innerRequestBody{
    private String word;

    public innerRequestBody() {
    }

    public innerRequestBody(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
