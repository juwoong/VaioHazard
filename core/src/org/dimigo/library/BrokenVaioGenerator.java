package org.dimigo.library;

import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;

import java.util.*;

/**
 * Created by YuTack on 2015-11-25.
 */
public class BrokenVaioGenerator {

    //완전 랜덤히 망가진 바이오를 얻습니다.
    public static Vaio getBrokenVaio() {
        Random rand = new Random();

        Vaio brokenVaio = new Vaio();

        int troubleNumber = rand.nextInt() % VaioProblem.Trouble.SIZE;

        Map<VaioProblem.Trouble, VaioProblem.Critical> impairs = new HashMap<VaioProblem.Trouble, VaioProblem.Critical>();

        //문제 개수만큼 바이오를 부순다.
        while(impairs.size() == troubleNumber) {
            VaioProblem.Critical critical  = VaioProblem.Critical.getCritical();
            if(critical != VaioProblem.Critical.Fine)
                impairs.put(VaioProblem.Trouble.getTrouble(), VaioProblem.Critical.getCritical());
        }

        //나머지 부분은 Fine 상태로 바꿈
        for(int i=0; i < VaioProblem.Trouble.SIZE; i++) {
            if(impairs.keySet().contains(VaioProblem.Trouble.getList().get(i)) == false) {
                impairs.put(VaioProblem.Trouble.getList().get(i), VaioProblem.Critical.Fine);
            }
        }

        brokenVaio.setImpairs(impairs);
        brokenVaio.setDurability(rand.nextInt() % 100);

        return brokenVaio;
    }
}
