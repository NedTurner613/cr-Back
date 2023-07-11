package com.calc.crBack.controllers;

import com.calc.crBack.models.GimmeMan;
import com.calc.crBack.services.GimmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gimme")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class GimmeController {
    private final GimmeService gimmeService;

    @PostMapping("/process")
    public ResponseEntity<GimmeMan> process(@RequestBody GimmeMan input){

        Optional<GimmeMan> optional = gimmeService.process(input);
        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok(optional.get());
    }

//    public ResponseEntity<GimmeMan> process(@RequestBody GimmeMan input){
//        GimmeMan response = new GimmeMan();
//        Integer value = input.getInput();
//        Optional<Integer> optional = gimmeService.process(value);
//        if(!optional.isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        response.setInput(value);
//        response.setResult(optional.get());
//
//
//
//        return ResponseEntity.ok(response);
//    }
}
