package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.library.DialogGenerator;
import org.dimigo.vaiohazard.Object.Customer;
import org.dimigo.vaiohazard.Object.PixelizedDialog;

/**
 * Created by juwoong on 15. 11. 25..
 */
public class Conversation {
    //TODO: Negotiating은 다 끝나고.
    enum Status{
        Start, //의뢰 시 Status
        Estimating, //현재 견적 내는 중
        Negotiating, //협상중
        Accept, //동의
        Deni //거절
    }

    //ENUM으로 바꿉니당
    private Status conversationStatus = Status.Start;
    private Stage stage;
    private Customer owner;
    private DialogGenerator generater = new DialogGenerator();

    public Conversation(Stage stage, Customer owner) {
        this.stage = stage;
        this.owner = owner;
    }

    public void start() {
        PixelizedDialog dialog = generater.getDialog(owner.getName(), "대회를 시작합니다.");
        dialog.button("다음으로 넘기기", this, generater.getTextButtonStyle());
        dialog.show(stage);
    }

    //d선택지가 없는 경우
    public void listenAnswer() {
        if(conversationStatus == Status.Start) {
            PixelizedDialog dialog = generater.getDialog(owner.getName(), "대화를 종료합니다.");
            dialog.button("다음으로 넘기기", this, generater.getTextButtonStyle());
            dialog.show(stage);
            conversationStatus = Status.Accept;
        }
    }

    //선택지가 있는 경우
    public void listenAnswer(int value) {
    }

    public Status getConversationStatus() {
        return conversationStatus;
    }

    public void setConversationStatus(Status conversationStatus) {
        this.conversationStatus = conversationStatus;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Customer getOwner() {
        return owner;
    }

}
