
package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;

import javax.xml.crypto.KeySelector;
import java.util.*;

/**
 * Created by YuTack on 2015-11-11.
 */

public class Customer extends VaioActor {
    public boolean getPurpose() {
        return purpose;
    }

    public CumstomerState getCumstomerState() {
        return cumstomerState;
    }

    public enum CumstomerState {
        //자신의 차례까지 기다림
        waitForTurn,
        //협상 준비 완료, 이 상태가 된 객체를 서치해서 협상으로 들어감
        readyToNegotiate,
        //협상 중
        onNegotiation,
        //협상 실패, 화난 상황 (관련 상태가 더 필요할 수 있음 현재 분노 미완성)
        Angry,
        //협상 종료
        overNegotiation,
        //가게밖, 화면에 없음
        OutOfStore
    }

    private CumstomerState cumstomerState = CumstomerState.waitForTurn;

    public static final boolean REPAIR = false;
    public static final boolean REGAIN = true;

    //가게에 온 목적, repair에서 regain으로 넘어감, 이 불린값과 스테이트 값을 통해 고객의 상태 결정
    private boolean purpose = REPAIR;


    private String name; //고객의 이름
    private final String uuid; //수리 맡긴 고객을 다시 등장시키기 위한 UUID - 중복 안 됨.

    private Vaio vaio;
    private List<VaioProblem.Trouble> whatIKnowMyVaioTrouble = new ArrayList<VaioProblem.Trouble>();

    // 0 ~ 100
    // 이 퍼센트에 따라 다이얼로그 내용이 바뀜, 가게 평판에 따라 초기값 결정
    int doubtPercent;

    //고객의 호갱도, 고객의 난이도와 관련
    int hogangPercent;

    public Customer(String name, String image, int cols, int rows, String uuid) {
        this.name = name;
        setAnimation(image, cols, rows);
        this.uuid = uuid;
    }

    public Customer(String name, String image, int cols, int rows) {
        this.name = name;
        setAnimation(image, cols, rows);
        uuid = UUID.randomUUID().toString().replace("-", "");
        //TODO: 고객의 의심도 설정 - 이 고객의 난이도와 연관되어 있음.

        //testCode
        hogangPercent = 30;

        //TODO: 랜덤하게 파손된 바이오

        //testCode
        vaio = new Vaio();

        //TODO: 고객의 자신의 바이오에 대한 지식, 뭐가 고장났는지
    }

    //TODO: * 다이얼로그와 고객 내부로직 분리할거다 *

    //가게 들어와서 처음으로 하는 말
    public void speakWhatINeed() {

        Gdx.app.log("Customer", "speakWhatINeed");

        //다이얼로그 호출, 바이오 넘기기, 조사
    }

    //조사 결과를 들음, 일반적으로 구라를 쳐서 넘김
    public boolean listenInspectResult(Components components) {
        //TODO: 의심도 상승 여기서 의심도로 인한 분기 화나냐 안 화나냐, true일 경우 애가 빡친거고 false 인 경우 구라 성공

        //구라 결과를 받고 의심도 계산이후 분기, 대답 다이얼로그 출력 이후 퇴장

        //정상적으로 계약이 끝남
        //positiveAnswer();
        cumstomerState = CumstomerState.overNegotiation;
        return false;
    }

    //TODO: 물건 받으로 돌아올 경우 구현

    public void appearToGetVaio() {
        purpose = REGAIN;

    }

    public boolean receiveVaio(Vaio vaioRepaired) {
        //여기서도 의심도 계산, 분노로 분기 가능
        return true;
    }

    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        //입장하고 움직임 (대기는 아직)
        if(cumstomerState == CumstomerState.waitForTurn) {
            if(moveState == MovingState.wating) {

                super.walkTo(100, 100, true); //점원 있는 곳으로 이동

            } else if(moveState == MovingState.walkingOver) {
                moveState = MovingState.wating;

                cumstomerState = CumstomerState.readyToNegotiate;

                //외부에서 Negotiation객체로 협상 시작
            }
        } else if(cumstomerState == CumstomerState.overNegotiation) {
            if(moveState == MovingState.wating) {

                super.walkTo(0, 0, false); //화면 밖으로 이동

            } else if(moveState == MovingState.walkingOver) {
                //손님이 물건 맏기고 떠남 안보이게 해줌
                cumstomerState = CumstomerState.OutOfStore;
            }
        }

        //TODO: 수령하는 부분 구현

    }

    public Customer(String name) {
        this(name, "resources/Actor/Creeper.png", 1, 1);
    }

    public Customer(String name, boolean tester) {
        this.name = name;
        setAnimation("mario.png", 4, 1);
        uuid="난 시발 테스터다";
    }

    public int getDoubtPercent() { return this.doubtPercent; }

    public void setVaio(Vaio vaio) {
        this.vaio = vaio;
    }

    public Vaio getVaio() {
        return vaio;
    }


    //TODO: 기록 저장 시 User Save 만들 것.
}