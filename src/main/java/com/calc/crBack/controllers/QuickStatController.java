package com.calc.crBack.controllers;


import com.calc.crBack.models.QuickStatblock;
import com.calc.crBack.services.QuickStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("quick-stats")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class QuickStatController {
    private final QuickStatService quickStatService;

    @PostMapping("/process")
    public ResponseEntity<QuickStatblock> process(@RequestBody QuickStatblock monsterStats){
//        testing to see if the correct object is received
        if(!(monsterStats==null)){
            System.out.println("CR: " + monsterStats.getCR());
            System.out.println("HP: " + monsterStats.getHP());
            System.out.println("AC: " + monsterStats.getAC());
            System.out.println("DefCR: " + monsterStats.getDefCR());
            System.out.println("DmgRnd: " + monsterStats.getDmgRnd());
            System.out.println("Atk: " + monsterStats.getAtk());
            System.out.println("OffCR: " + monsterStats.getOffCR());
        }
        Optional<QuickStatblock> optional = quickStatService.process(monsterStats);
        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/beep")
    public ResponseEntity<Integer> beep(){
        return ResponseEntity.ok(5);
    }

}
