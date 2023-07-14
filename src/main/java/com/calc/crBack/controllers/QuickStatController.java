package com.calc.crBack.controllers;


import com.calc.crBack.models.Boop;
import com.calc.crBack.models.GimmeMan;
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
            System.out.println("CR: " + monsterStats.getCr());
            System.out.println("HP: " + monsterStats.getHp());
            System.out.println("AC: " + monsterStats.getAc());
            System.out.println("DefCR: " + monsterStats.getDefCR());
            System.out.println("DmgRnd: " + monsterStats.getDmgRnd());
            System.out.println("Atk: " + monsterStats.getAtk());
            System.out.println("DC: " + monsterStats.getDc());
            System.out.println("Boolean: " + monsterStats.getAtkVsDc());
            System.out.println("OffCR: " + monsterStats.getOffCR());
        }
        Optional<QuickStatblock> optional = quickStatService.process(monsterStats);
        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

//        test the values extracted
        if(optional.isPresent()){
            QuickStatblock test = optional.get();
            System.out.println("MOD CR: " + test.getCr());
            System.out.println("MOD HP: " + test.getHp());
            System.out.println("MOD AC: " + test.getAc());
            System.out.println("MOD DefCR: " + test.getDefCR());
            System.out.println("MOD DmgRnd: " + test.getDmgRnd());
            System.out.println("MOD Atk: " + test.getAtk());
            System.out.println("MOD OffCR: " + test.getOffCR());
        }

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/beep")
    public ResponseEntity<Boop> beep(@RequestBody Boop boop){
        boop.setForst(boop.getForst()+1);
        return ResponseEntity.ok(boop);
    }

}
