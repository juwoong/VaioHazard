package org.dimigo.vaiohazard.conversation;

import org.dimigo.vaiohazard.Object.PixelizedDialog;
import org.dimigo.vaiohazard.Object.ServiceCenter;

/**
 * Created by juwoong on 15. 11. 13..
 */
public class Effect extends Question {
    private int reputation;
    private int doubt;
    private int money;

    public Effect(String question, String requiredAnswer, int reputation, int doubt, int money) {
        super(question, requiredAnswer);
        this.reputation = reputation;
        this.doubt = doubt;
        this.money = money;
        this.isFinal = true;
    }

    public Effect(String question, String requiredAnswer) {
        this(question, requiredAnswer, 0, 0, 0);
    }

    public void setValue(int reputation, int doubt, int money) {
        this.reputation = reputation;
        this.doubt = doubt;
        this.money = money;
    }

    @Override
    public void ask() {
        PixelizedDialog dialog = generater.getDialog("temp", question);
        ServiceCenter center = ServiceCenter.getInstance();

        center.changeDoubt(doubt);
        center.changeMoney(money);
        center.changeReputaion(reputation);

        dialog.button("대화 끝마치기.", "end", generater.getStyle());

        dialog.show(stage);
    }

}
