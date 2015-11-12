package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import org.dimigo.vaiohazard.Interface.IAskable;
import org.dimigo.vaiohazard.Question;

/**
 * Created by YuTack on 2015-11-11. fuck you
 */
public class Customer implements IAskable{
    private Question quest1;
    private Question quest1_1;

    private boolean answered = false;

    public Customer() {
        init();
    }

    private void init() {
        quest1 = new Question();
        quest1.possibleAnswers.add("대답1");
        quest1.possibleAnswers.add("대답2");
        quest1.possibleAnswers.add("대답3");
        quest1.question = "어디한번 대답해 보시지?";

        quest1_1 = new Question();
        quest1_1.question = "그거 정말 느메운";
        quest1_1.possibleAnswers.add("대답 불가함");
    }

    @Override
    public Question ask() {
        if(answered) return quest1_1;
        else return quest1;
    }

    @Override
    public void hear(int answer) {
            answered = true;
    }
}
