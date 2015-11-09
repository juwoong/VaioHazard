package org.dimigo.vaiohazard;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by YuTack on 2015-11-09.
 */
public class CustomActions {

    public static Action twinkle() {
        RotateByAction action = new RotateByAction();
        action.setAmount(10);
        action.setDuration(0.2f);

        RotateByAction action2 = new RotateByAction();
        action2.setAmount(-20);
        action2.setDuration(0.4f);

        RotateByAction action3 = new RotateByAction();
        action3.setAmount(20);
        action3.setDuration(0.4f);

        RotateByAction action4 = new RotateByAction();
        action4.setAmount(-20);
        action4.setDuration(0.4f);

        RotateByAction action5 = new RotateByAction();
        action5.setAmount(10);
        action5.setDuration(0.2f);

        SequenceAction seq = new SequenceAction(action, action2, action3, action4, action5);

        return seq;
    }
}
