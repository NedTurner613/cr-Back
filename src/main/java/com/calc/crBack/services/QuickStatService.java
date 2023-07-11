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
        monsterStats.setDefCR(calculateDefCR(monsterStats.getHP(), monsterStats.getAC()));
//        calculate and set offensive CR
        monsterStats.setOffCR(calculateOffCR(monsterStats.getDmgRnd(), monsterStats.getAtk()));

        int CR = (int)Math.round(((double)monsterStats.getDefCR()+(double)monsterStats.getOffCR())/2);

        monsterStats.setCR(CR);

        return Optional.of(monsterStats);
    }


    public int calculateDefCR(int HP, int AC){
//        determine expected CR by HP
        int expectedCR = reference.hpToExpectedCR(HP);
//        modify CR by diff b/w expected AC (by expected CR) and actual AC
        expectedCR += (AC-reference.crToExpectedAC(expectedCR))/2;

        return expectedCR;
    }


    public int calculateOffCR(int DmgRnd, int Atk){
//        determine expected CR by dmg per round
        int expectedCR = reference.dmgToExpectedCR(DmgRnd);
//        modify CR by diff b/w expected Atk and actual attack bonus
        expectedCR += (Atk-reference.crToExpectedAtkBonus(expectedCR));

        return expectedCR;
    }



}
