package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import org.dimigo.library.DialogGenerater;
import org.dimigo.vaiohazard.Interface.IAskable;
import org.dimigo.vaiohazard.Question;

/**
 * Created by YuTack on 2015-11-12.
 */
public class Argument {
    private static Argument ourInstance = new Argument();

    public static Argument getInstance() {
        return ourInstance;
    }

    private Argument() {
        generater = new DialogGenerater();
    }

    public int answer = -1;
    private IAskable Q;
    private Dialog currentDialog = null;
    private DialogGenerater generater;
    private Stage stage;

    public void startArgu(IAskable Q, Stage stage) {
        this.stage = stage;
        this.Q = Q;
        quest(Q.ask());
    }

    public void quest(Question q) {
        currentDialog = generater.getDialog3(q.question, q.possibleAnswers);
        currentDialog.show(stage);
    }

    public void act(float dt) {
        if(answer != -1) {
            Q.hear(answer);
            quest(Q.ask());
            answer = -1;
        }
    }
}
