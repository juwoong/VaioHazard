package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import org.dimigo.library.DialogGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juwoong on 15. 11. 25..
 */
public class Question {
    //Question Format
    /**
     * Question Format.
     *
     * name(String) : 질문자 이름
     * question(String) : 질문 내용
     * Conversation(String) : 대화 문맥 객체
     *
     */
    private Conversation conversation;
    private DialogGenerator generater = new DialogGenerator(conversation);
    private String question;
    private String status;
    private List<Answer> list = new ArrayList<Answer>();

    public Question(Conversation conversation) {
        this.conversation = conversation;
    }

    public void ask() {
        //dialog.show(stage);
        Dialog dialog = generater.getDialog(conversation.getOwner().getName(), question);

        for(Answer answer : list) {
            dialog.button(answer.getAnswer(), answer, generater.getTextButtonStyle());
        }

        if(list.size()==0) {
            dialog.button("다음으로", conversation, generater.getTextButtonStyle());
        }

        dialog.show(conversation.getStage());
    }
}
