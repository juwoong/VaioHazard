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

        while(impairs.size() == troubleNumber) {
            impairs.put(VaioProblem.Trouble.getTrouble(), VaioProblem.Critical.getCritical());
        }

        brokenVaio.setImpairs(impairs);
        brokenVaio.setDurability(rand.nextInt() % 100);

        return brokenVaio;
    }
}
