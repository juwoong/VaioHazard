package org.dimigo.vaiohazard.conversation;

/**
 * Created by juwoong on 15. 11. 25..
 */
public class Answer {
    /*Templete*/
    private String answer;
    private int value;
    private Conversation conversation;

    public void reply() {
        conversation.listenAnswer(value);
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
