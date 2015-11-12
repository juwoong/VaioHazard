package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import org.dimigo.library.DialogGenerater;
import org.dimigo.vaiohazard.Answer;
import org.dimigo.vaiohazard.Interface.IAnswerable;
import org.dimigo.vaiohazard.Interface.IAskable;
import org.dimigo.vaiohazard.Question;

/**
 * Created by YuTack on 2015-11-11.
 */
public class Conversation {

    public int answer = -1;
    private IAskable Q;
    private Dialog currentDialog;
    private DialogGenerater generater;

    public Conversation(IAskable Q) {
        super();
        generater = new DialogGenerater();
        this.Q = Q;
        quest(Q.ask());
    }

    public void quest(Question q) {
        currentDialog = generater.getDialog3(q.question, q.possibleAnswers);
    }

    public Dialog getDialog() {
        return currentDialog;
    }

    public void act(float dt) {
        if(answer != -1) {
            Q.hear(answer);
            quest(Q.ask());
            answer = -1;
        }
    }
}
