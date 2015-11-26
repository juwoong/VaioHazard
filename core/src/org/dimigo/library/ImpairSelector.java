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
            = new HashMap<>();

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

    public List<VaioProblem.Critical> getResult() {
        List<VaioProblem.Critical> list = new ArrayList<>();
        for(int i = 0; i < VaioProblem.Trouble.SIZE; i ++) {
            list.add(selectors.get(VaioProblem.Trouble.getList().get(i)).getSelected());
        }

        return list;
    }
}
