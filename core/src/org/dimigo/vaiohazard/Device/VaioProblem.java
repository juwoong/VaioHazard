package org.dimigo.vaiohazard.Device;

import java.util.*;

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
    public enum Trouble{
        LiquidDisplayTrouble,
        BootTrouble,
        PowerTrouble,
        LCDTrouble,
        FunctionTrouble,
        Cleaning,
        BatteryTrouble;

        private static final List<Trouble> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        public static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Trouble getTrouble() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
        public static List<Trouble> getList() { return VALUES; }
    }

    public static String[] TroubleStrings = new String[] {
            "액정문제",
            "부팅문제",
            "전원문제",
            "액정파손",
            "기능문제",
            "더러움",
            "배터리문제"
    };

    //문제가 심각한 정도, 수리의 난이도에 영향
    /*
    Little : 5
    Soso : 4
    Bad : 3
    Serious : 2
    Died : 1
    Fine : 0 (문제 없음)
     */
    public enum Critical{
        Fine,
        Little,
        Soso,
        Bad,
        Serious,
        Died;

        private static final List<Critical> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Critical getCritical() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
        public static int valueOf(Critical critical) {
            for(Critical val : VALUES)
                if(val == critical) return VALUES.indexOf(val);

            //error case
            return -1;
        }
    }

    //필요한 개수임 ( 임시 )
    public static final Map<Trouble, Components> requireComponents = new HashMap<Trouble, Components>(){{
        put(Trouble.LiquidDisplayTrouble, new Components(2, 0, 1, 0, 0, 0, 2, 0 ,0));
        put(Trouble.BootTrouble, new Components(2, 0, 1, 0, 0, 0, 2, 0 ,0));
        put(Trouble.PowerTrouble, new Components(2, 0, 2, 0, 0, 0, 0, 0, 2));
        put(Trouble.LCDTrouble, new Components(2, 0, 0, 0, 0, 0, 2, 0, 0));
        put(Trouble.FunctionTrouble, new Components(2, 2, 2, 2, 2, 2, 0, 2, 2));
        put(Trouble.Cleaning, new Components(2, 0, 0, 0, 0, 0, 0, 0, 0));
        put(Trouble.BatteryTrouble, new Components(0, 0, 0, 0, 0, 0, 0, 0, 2));
    }};

    //다음과 같은 문제가 발생했을 때, 어떤 부품의 문제가 생길수 있는가? 에 대한 배열
    public static final Map<Trouble, boolean[]> causeAbleReason = new HashMap<Trouble, boolean[]>(){{
        put(Trouble.LiquidDisplayTrouble, new boolean[]{true, false, true, false, false, false, true, false, false});
        put(Trouble.BootTrouble, new boolean[]{true, false, true, true, true, true, false, true, true});
        put(Trouble.PowerTrouble, new boolean[]{true, false, true, false, false, false, false, false, true});
        put(Trouble.LCDTrouble, new boolean[]{true, false, false, false, false, false, true, false, false});
        put(Trouble.FunctionTrouble, new boolean[]{true, true, true, true, true, true, false, true, true});
        put(Trouble.Cleaning, new boolean[]{true, false, false, false, false, false, false, false, false});
        put(Trouble.BatteryTrouble, new boolean[]{false, false, false, false, false,  false, false, false, true});
    }};
}
