package org.dimigo.vaiohazard.Device;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YuTack on 2015-11-14.
 * 문제\부품 	나사 	서멀구리스 	메인보드 	CPU 	램 	SATA 케이블 	액정 	하드 디스크 	배터리
    액정 문제 	O 	X 	△ 	X 	X 	X 	O 	X 	X
    부팅 불량 	O 	X 	O 	O 	O 	O 	X 	O 	O
    전원 불량 	O 	X 	O 	X 	X 	X 	X 	X 	O
    LCD파손 	    O 	X 	X 	X 	X 	X 	O 	X 	X
    기능불량     	O 	O 	O 	O 	O 	O 	X 	O 	O
    놋북청소     	O 	X 	X 	X 	X 	X 	X 	X 	X
    배터리교체 	X 	X 	X 	X 	X 	X 	X 	X 	O

 */
public class VaioProblem {
    enum Trouble{
        LiquidDisplayTrouble,
        BootTrouble,
        PowerTrouble,
        LCDTrouble,
        FunctionTrouble,
        Cleaning,
        BatteryTrouble
    }

    //문제가 심각한 정도, 수리의 난이도에 영향
    enum Critical{
        NotBad,
        Soso,
        Bad,
        Serious,
        //Raped,
        Died
    }

    //look at this trick!
    //필요한 개수임 ( 임시 )
    public static final Map<Trouble, Components> TroubleRequireComponets = new HashMap<Trouble, Components>(){{
        put(Trouble.LiquidDisplayTrouble, new Components(2, 0, 1, 0, 0, 0, 2, 0 ,0));
        put(Trouble.BootTrouble, new Components(2, 0, 1, 0, 0, 0, 2, 0 ,0));
        put(Trouble.PowerTrouble, new Components(2, 0, 2, 0, 0, 0, 0, 0, 2));
        put(Trouble.LCDTrouble, new Components(2, 0, 0, 0, 0, 0, 2, 0, 0));
        put(Trouble.FunctionTrouble, new Components(2, 2, 2, 2, 2, 2, 0, 2, 2));
        put(Trouble.Cleaning, new Components(2, 0, 0, 0, 0, 0, 0, 0, 0));
        put(Trouble.BatteryTrouble, new Components(0, 0, 0, 0, 0, 0, 0, 0, 2));
    }};


}
