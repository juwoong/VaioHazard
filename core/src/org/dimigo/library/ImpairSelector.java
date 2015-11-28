package org.dimigo.library;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import org.dimigo.vaiohazard.Device.VaioProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuTack on 2015-11-26.
 */
public class ImpairSelector {

    private Map<VaioProblem.Trouble, SelectBox<VaioProblem.Critical>> selectors
            = new HashMap<VaioProblem.Trouble, SelectBox<VaioProblem.Critical>>();

    public ImpairSelector (SelectBox.SelectBoxStyle style) {
        for(int i=0; i< VaioProblem.Trouble.SIZE; i++) {
            SelectBox<VaioProblem.Critical> box = new SelectBox<VaioProblem.Critical>(style);
            box.setItems(new Array<VaioProblem.Critical>(VaioProblem.Critical.values()));
            box.setSelectedIndex(0);
            selectors.put(VaioProblem.Trouble.getList().get(i), box);
        }
    }

    public Object[] getSelectBoxes () {
        return selectors.values().toArray();
    }

    //나중에 이 겁나 긴 map을 따로 클레스로 만들어서 처리 하는게 훨씬 좋은듯 시간없어서 못할거같긴한데;
    public Map<VaioProblem.Trouble, VaioProblem.Critical> getResult() {
        Map<VaioProblem.Trouble,VaioProblem.Critical> map = new HashMap<VaioProblem.Trouble,VaioProblem.Critical>();
        for(int i = 0; i < VaioProblem.Trouble.SIZE; i ++) {
            map.put(VaioProblem.Trouble.getList().get(i), selectors.get(VaioProblem.Trouble.getList().get(i)).getSelected());
        }

        return map;
    }
}
