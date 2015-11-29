package org.dimigo.vaiohazard.conversation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.library.DialogGenerator;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.Object.Customer;
import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.Object.ServiceCenter;
import org.dimigo.vaiohazard.conversation.parser.ConversationParser;
import org.dimigo.vaiohazard.conversation.parser.StartConversationParser;

import java.util.Map;

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

    private static final String TAG = "Conversation";
    private Status conversationStatus = Status.Start;
    private Stage stage;
    private Customer owner;
    private DialogGenerator generator = new DialogGenerator(this);

    public Conversation(Stage stage, Customer owner) {
        this.stage = stage;
        this.owner = owner;
    }

    public void start() {
        conversationStatus = Status.Start;

        StartConversationParser parser = new StartConversationParser(generator);
        PixelizedDialog dialog = parser.getGeneratedDialog(owner.getName());
        dialog.button("다음으로 넘기기", null, generator.getTextButtonStyle());
        dialog.show(stage);
    }

    //선택지가 없는 경우
    public void listenAnswer(Object object) {
        if(conversationStatus == Status.Start) {
            PixelizedDialog dialog = generator.getDialog(owner.getName(), "증상은 " + owner.sayWhatIKnowAboutMyVaio());
            dialog.button("알겠습니다. 견적을 내보도록 하죠.", null, generator.getTextButtonStyle());
            dialog.show(stage);
            conversationStatus = Status.Knowing;
        }else if(conversationStatus == Status.Knowing) {
            ServiceCenter center = ServiceCenter.getInstance();
            PixelizedDialog dialog = generator.getImpairSelect(owner.getName(), center.inspectVaio(owner.getVaio()));
            dialog.show(stage);
            conversationStatus = Status.Estimating;
        }else if(conversationStatus == Status.Estimating) {
            Map<VaioProblem.Trouble,VaioProblem.Critical> map = (Map<VaioProblem.Trouble,VaioProblem.Critical>) object;
            owner.listenInspectResult(map);

            if(owner.getCustomerState() == Customer.CustomerState.overNegotiation) conversationStatus=Status.Accept;
            else conversationStatus = Status.Estimating;
        }
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
