package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import org.dimigo.library.Timer;
import org.dimigo.vaiohazard.CustomActions;
import org.dimigo.vaiohazard.GameCoordinate;

/**
 * Created by YuTack on 2015-11-11.
 * in this game actors moves to vertical of horizontal line,
 * define several game animations
 */
public class VaioActor extends Actor {
    enum Direction {
        top,
        bottom,
        left,
        right
    }

    public VaioActor() {
        super();
        this.setOrigin(getWidth()/2, getHeight()/2);
    }

    public VaioActor(int walkSpeed) {
        super();
        this.walkSpeed = walkSpeed;
        this.setOrigin(getWidth()/2, getHeight()/2);
    }

    //dot per second
    protected int walkSpeed = 3; //default
    protected Direction headDirect;

    public void walkTo(int dotX, int dotY, boolean goXFirst) {
        MoveByAction toX = new MoveByAction();

        toX.setAmount(dotX - getX(), 0);
        toX.setDuration(Math.abs(dotX - getX()) / walkSpeed);

        MoveByAction toY = new MoveByAction();

        toY.setAmount(0, dotY - getY());
        toY.setDuration(Math.abs(dotY - getY()) / walkSpeed);

        SequenceAction seq = null;

        if(goXFirst)
            seq = new SequenceAction(toX, toY);
        else
            seq = new SequenceAction(toY, toX);

        this.addAction(seq);
    }


    public static int FOREVER  = -1;
    public void twitch(int count) {

        this.setOrigin(getWidth()/2, getHeight()/2);

        RepeatAction repeat = new RepeatAction();
        repeat.setAction(CustomActions.twinkle());
        if(count > 0)
            repeat.setCount(count);
        else if(count == -1)
            repeat.setCount(RepeatAction.FOREVER);
        else
            assert true : "wtf";

        this.addAction(repeat);
    }

}
