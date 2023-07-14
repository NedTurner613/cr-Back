package com.calc.crBack.services;

import com.calc.crBack.models.QuickStatblock;
import com.calc.crBack.models.Reference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuickStatService {
    private final Reference reference = new Reference();

    public Optional<QuickStatblock> process(QuickStatblock monsterStats){

        //        calculate and set defensive CR
        monsterStats.setDefCR(calculateDefCR(monsterStats.getHp(), monsterStats.getAc()));
//        calculate and set offensive CR depending on whether using atk bonus or save dc
        if(!monsterStats.getAtkVsDc()) {
            monsterStats.setOffCR(calculateOffCRbyAtk(monsterStats.getDmgRnd(), monsterStats.getAtk()));
        } else {
            monsterStats.setOffCR(calculateOffCRbyDc(monsterStats.getDmgRnd(), monsterStats.getDc()));
        }

//        turn off and def CR into doubles for more accurate calculation, average, then convert back to int
        int CR = (int)Math.round(((double)monsterStats.getDefCR()+(double)monsterStats.getOffCR())/2);

        monsterStats.setCr(CR);

        return Optional.of(monsterStats);
    }


    public int calculateDefCR(int HP, int AC){
//        determine expected CR by HP
        int expectedCR = reference.hpToExpectedCR(HP);
//        modify CR by diff b/w expected AC (by expected CR) and actual AC
        expectedCR += (AC-reference.crToExpectedAC(expectedCR))/2;

        return expectedCR;
    }


    public int calculateOffCRbyAtk(int DmgRnd, int Atk){
//        determine expected CR by dmg per round
        int expectedCR = reference.dmgToExpectedCR(DmgRnd);
        System.out.println("Expected Offensive CR based on Dmg per round: "+ expectedCR);
//        modify CR by diff b/w expected Atk and actual attack bonus
        System.out.println("Expected Atk Bonus by expected CR: " + reference.crToExpectedAtkBonus(expectedCR));
        System.out.println("Monster's attack bonus: " + Atk);
        expectedCR += (Atk-reference.crToExpectedAtkBonus(expectedCR))/2;

        return expectedCR;
    }

    public int calculateOffCRbyDc(int DmgRnd, int dc){
//        determine expected CR by dmg per round
        int expectedCR = reference.dmgToExpectedCR(DmgRnd);
//        modify CR by diff b/w expected Atk and actual attack bonus
        expectedCR += (dc-reference.crToExpectedSaveDC(expectedCR))/2;

        return expectedCR;
    }


}
