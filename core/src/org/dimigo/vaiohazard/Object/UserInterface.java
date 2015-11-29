package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by juwoong on 15. 11. 28..
 */
public class UserInterface extends Table{
    private static BitmapFont textFont;
    private static Label.LabelStyle labelStyle;
    static {
        textFont = new BitmapFont(Gdx.files.internal("resources/font/font.fnt"));
        labelStyle = new Label.LabelStyle(textFont, Color.BLACK);
    }

    private ServiceCenter center;

    private Label reputationLabel;
    private Label credit;
    private Label checkedPerson;
    private Label waitPerson;
    private Label money;
    private Label time, date;

    private int i = 0;
    public UserInterface(){
        center = ServiceCenter.getInstance();
        init();
    }

    //필요한 컨텐츠 라벨 등을 초기화한다.
    public void init() {
        Table table = this;
        Table leftTable = new Table();
        Table rightTable = new Table();
        table.setFillParent(true);
        table.top();
        leftTable.top().padTop(10).padBottom(10);
        rightTable.top().padTop(10).padBottom(10);

        table.setColor(92 / 255f, 167 / 255f, 244 / 255f, 1);

        reputationLabel = new Label("0", labelStyle);
        credit = new Label("29", labelStyle);
        checkedPerson = new Label("12명", labelStyle);
        money = new Label("000,000,000원", labelStyle);
        waitPerson = new Label("0명", labelStyle);

        leftTable.add(new Label("가게 평판 : ", labelStyle));
        leftTable.add(reputationLabel).padRight(70);
        leftTable.add(new Label("신뢰도 : ", labelStyle));
        leftTable.add(credit).padRight(70);
        leftTable.add(new Label("맡긴 사람 : ", labelStyle));
        leftTable.add(checkedPerson);

        leftTable.row().padTop(10);
        leftTable.add(new Label("가게 자금 : ", labelStyle));
        leftTable.add(money).padRight(15);
        leftTable.add(new Label("대기중 : ", labelStyle));
        leftTable.add(waitPerson);
        leftTable.debug();

        table.add(leftTable).expandX().left().padLeft(50);

        time = new Label("2015년 01월 01일", labelStyle);
        date = new Label("09:00", labelStyle);

        rightTable.add(time).padRight(15);
        rightTable.add(date);

        table.add(rightTable).padRight(100);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        reputationLabel.setText(Integer.toString((int) center.getReputaionPercent() * 100));
        money.setText(String.format("%,d원", center.getMoney()));

        time.setText(String.format("%d년 %02d월 %02d일", center.getYear(), center.getMonth(), center.getDay()));
        date.setText(String.format("%02d:%02d", center.getMinutes()/60, center.getMinutes()%60));

        waitPerson.setText(Integer.toString(ServiceCenter.getInstance().getWaitingNumber() - ServiceCenter.getInstance().getCountNumber()));
        checkedPerson.setText(Integer.toString(ServiceCenter.getInstance().getOrders().size()));
    }

}
