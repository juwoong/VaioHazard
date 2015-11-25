package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import org.dimigo.library.DialogGenerater;

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

    private DialogGenerater generater = new DialogGenerater();
    private String question;
    private Conversation conversation;
    private String status;
    private List<Answer> list = new ArrayList<Answer>();

    public Question(Conversation conversation) {
        this.conversation = conversation;
    }

    public void ask() {
        //dialog.show(stage);
        Dialog dialog = generater.getDialog(conversation.getOwner(), question);

        for(Answer answer : list) {
            dialog.button(answer.getAnswer(), answer, generater.getStyle());
        }

        if(list.size()==0) {
            dialog.button("다음으로", conversation, generater.getStyle());
        }

        dialog.show(conversation.getStage());
    }
}
