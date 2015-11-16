package org.dimigo.vaiohazard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juwoong on 15. 11. 16..
 */
public class Trouble {
    private static Map<String, int[]> map = null;

    static {
        map = new HashMap<String, int[]>();
        map.put("display", new int[]{3,1,2,1,1,1,2,1,1});
        map.put("boot", new int[]{3,1,2,2,2,2,1,2,2});
        map.put("power", new int[]{3,1,3,1,1,1,1,1,3});
        map.put("lcd", new int[]{3,1,1,1,1,1,3,1,1});
        map.put("func", new int[]{3,3,3,3,3,3,1,3,3});
        map.put("clean", new int[]{3,1,1,1,1,1,1,1,1});
        map.put("battery", new int[]{1,1,1,1,1,1,1,1,3});
    }

    public static int[] get(String key) {
        return map.get(key);
    }


}
