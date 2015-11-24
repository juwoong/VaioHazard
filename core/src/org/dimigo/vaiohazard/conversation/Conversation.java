package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by juwoong on 15. 11. 12..
 */
public class Conversation {
    //Main Conversation
    //분노 등의 Conversation은 이 Conversation 클래스를 상속받는다.
    //Dialog...

    private Stage stage;
    public Question startQuestion;

    public Conversation(Stage stage){
        this.stage = stage;
    }

    public void setStage() {
        startQuestion.setStage(stage);
    }
    public void setName(String name) { startQuestion.setName(name);}

    public void setQuestion(Question q) {
        startQuestion = q;
    }

    public void start() {
        startQuestion.ask();
    }
}
