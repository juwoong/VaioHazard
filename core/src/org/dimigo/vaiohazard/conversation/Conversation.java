package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by juwoong on 15. 11. 12..
 */
public class Conversation {
    private Stage stage;
    public Question startQuestion;

    public Conversation(Stage stage){
        this.stage = stage;
    }

    public void setStage() {
        startQuestion.setStage(stage);
    }

    public void setQuestion(Question q) {
        startQuestion = q;
    }

    public void start() {
        startQuestion.ask();
    }
}
