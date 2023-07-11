package com.calc.crBack.services;


import com.calc.crBack.models.GimmeMan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GimmeService {

    public Optional<GimmeMan> process(GimmeMan man){
        man.setResult(man.getInput()*2);
        man.setInput(man.getInput()+1);
        return Optional.of(man);
    }
//    public Optional<Integer> process(Integer input){
//        System.out.println(input + input);
//        return Optional.of(input + input);
//    }

}
