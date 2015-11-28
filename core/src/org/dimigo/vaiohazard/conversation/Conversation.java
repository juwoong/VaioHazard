package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.library.DialogGenerator;
import org.dimigo.vaiohazard.Object.Customer;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.conversation.parser.ConversationParser;
import org.dimigo.vaiohazard.conversation.parser.StartConversationParser;

/**
 * Created by juwoong on 15. 11. 25..
 */
public class Conversation {
    //TODO: Negotiating은 다 끝나고.
    enum Status{
        Start, //의뢰 시 Status
        Knowing, //무슨 문제가 있는지 알고 있습니다.
        Estimating, //현재 견적 내는 중
        Negotiating, //협상중
        Accept, //동의
        Deni //거절
    }

    private Status conversationStatus = Status.Start;
    private Stage stage;
    private Customer owner;
    private DialogGenerator generator = new DialogGenerator();

    public Conversation(Stage stage, Customer owner) {
        this.stage = stage;
        this.owner = owner;
    }

    public void start() {
        conversationStatus = Status.Start;

        StartConversationParser parser = new StartConversationParser();
        PixelizedDialog dialog = parser.getGeneratedDialog(owner.getName());
        dialog.button("다음으로 넘기기", this, generator.getTextButtonStyle());
        dialog.show(stage);
    }

    //선택지가 없는 경우
    public void listenAnswer() {
        if(conversationStatus == Status.Start) {
            PixelizedDialog dialog = generator.getDialog(owner.getName(), "증상은 " + owner.sayWhatIKnowAboutMyVaio());
            dialog.button("알겠습니다. 견적을 내보도록 하죠.", this, generator.getTextButtonStyle());
            dialog.show(stage);
            conversationStatus = Status.Knowing;
        }else if(conversationStatus == Status.Knowing) {
            PixelizedDialog dialog = generator.getImpairSelect(owner.getName());
            dialog.show(stage);
            conversationStatus = Status.Estimating;
        }
    }


    //선택지가 있는 경우
    public void listenAnswer(int value) { }

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
