package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by juwoong on 15. 11. 25..
 */
public class Conversation {
    enum Status{

    }

    //ENUM으로 바꿉니당
    private String conversationStatus;
    private Stage stage;
    private String owner;

    //선택지가 없는 경우
    public void listenAnswer() {
    }

    //선택지가 있는 경우
    public void listenAnswer(int value) {
    }

    public String getConversationStatus() {
        return conversationStatus;
    }

    public void setConversationStatus(String conversationStatus) {
        this.conversationStatus = conversationStatus;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
