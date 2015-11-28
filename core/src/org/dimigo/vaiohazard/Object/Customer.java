
package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import org.dimigo.library.BrokenVaioGenerator;
import org.dimigo.library.Rand;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.conversation.Conversation;

import java.util.*;

/**
 * Created by YuTack on 2015-11-11.
 */

/*
    0 ~ 20
    20 ~ 40
    40 ~ 60
    60 ~ 80
    80 ~ 100
*/

public class Customer extends VaioActor {
    public boolean getPurpose() {
        return purpose;
    }

    public CumstomerState getCumstomerState() {
        return cumstomerState;
    }

    public enum CumstomerState {
        //자신의 차례까지 기다림 ~ 점원에게 이동할 때 까지
        waitForTurn,
        //협상 준비 완료, 이 상태가 된 객체를 서치해서 협상으로 들어감
        readyToNegotiate,
        //협상 중
        onNegotiation,
        //협상 실패, 화난 상황 (관련 상태가 더 필요할 수 있음 현재 분노 미완성)
        Angry,
        //협상 종료 자동으로 화면 밖으로 퇴장
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
    private ArrayList<VaioProblem.Trouble> whatIKnowAboutMyVaio;

    // 0 ~ 100
    // 이 퍼센트에 따라 다이얼로그 내용이 바뀜, 가게 평판에 따라 초기값 결정
    float doubtPercent;
    //고객의 호갱도, 고객의 난이도와 관련
    float hogangPercent;

    public Customer(String name, String image, int cols, int rows, String uuid) {
        this.name = name;
        setAnimation(image, cols, rows);
        this.uuid = uuid;
    }

    public Customer(String name) {
        this(name, "resources/Actor/Creeper.png", 1, 1);
    }

    public Customer(String name, boolean tester) {
        this.name = name;
        setAnimation("mario.png", 4, 1);
        uuid="난 시발 테스터다";
        init();
    }

    public Customer(String name, String image, int cols, int rows) {
        this.name = name;
        setAnimation(image, cols, rows);
        uuid = UUID.randomUUID().toString().replace("-", "");
        init();
    }

    private void init() {
        Random rand = new Random();

        //testCode
        hogangPercent = rand.nextFloat();

        //초기 의심도 로직 (1 - 가계 명망 + 랜덤) * 0.5 , 첨부터 너무 의심도가 높으면 골때리기 때문에 0.5 곱하기
        doubtPercent = ((1 - ServiceCenter.getInstance().getReputaionPercent()) + (rand.nextInt(30) / 100)) * 0.5f;

        vaio = BrokenVaioGenerator.getBrokenVaio();

        whatIKnowAboutMyVaio = setMemory(hogangPercent, vaio);
    }

    private static ArrayList<VaioProblem.Trouble> setMemory(float hogang, Vaio myVaio) {
        Map<VaioProblem.Trouble, VaioProblem.Critical> myVaioImpairs
                = new HashMap<VaioProblem.Trouble, VaioProblem.Critical>(myVaio.getImpairs());

        VaioProblem.Trouble[] troubles = myVaioImpairs.keySet().toArray(new VaioProblem.Trouble[0]);

        ArrayList<VaioProblem.Trouble> memory = new ArrayList<VaioProblem.Trouble>();

        Random rand = new Random();

        for (VaioProblem.Trouble trouble : myVaioImpairs.keySet()) {
            //case Fine: 은 고려안함, 심각한 문제일 수록 발견확률 올라감.
            switch (myVaioImpairs.get(trouble)) {
                case Little:
                    if(Rand.get((0.3f * (1 - hogang)))) { memory.add(trouble); }
                    break;
                case Soso:
                    if(Rand.get((0.5f * (1 - hogang)))) { memory.add(trouble); }
                    break;
                case Bad:
                    if(Rand.get((0.8f * (1 - hogang)))) { memory.add(trouble); }
                    break;
                case Serious:
                    if(Rand.get((0.9f * (1 - hogang)))) { memory.add(trouble); }
                    break;
                case Died:
                    if(Rand.get((1.0f * (1 - hogang)))) { memory.add(trouble); }
                    break;
            }
        }
        return memory;
    }
    //가게 들어와서 처음으로 하는 말
    public void speakWhatINeed() {
        Gdx.app.log("Customer", "speakWhatINeed");

        //다이얼로그 호출, 바이오 넘기기, 조사
    }

    //조사 결과를 들음, 일반적으로 구라를 쳐서 넘김
    public void listenInspectResult(Map<VaioProblem.Trouble, VaioProblem.Critical> result) {
        for(VaioProblem.Trouble trouble : VaioProblem.Trouble.getList()) {
            VaioProblem.Critical fakeCritical = result.get(trouble);
            VaioProblem.Critical realCritical = vaio.getImpairs().get(trouble);

            float doubtPlus = 0;

            //의심도가 높으면 더 의심할 확률도 증가함

            //이미 알고 있는 문제에 대한 경우
            if(whatIKnowAboutMyVaio.contains(trouble) == true) {
                int differece = VaioProblem.Critical.valueOf(fakeCritical) - VaioProblem.Critical.valueOf(realCritical);

                //이미 의심도가 높으면 배로 높아짐

                if(differece > 0) {
                    //실제 심각성 보다 더 부숴졌다고 말한 경우
                    doubtPlus += (1 - hogangPercent) * (differece * 0.1f);

                    //정상인데 구라치면 더 높아지는 의심도
                    if(realCritical == VaioProblem.Critical.Fine && Rand.get(1 - hogangPercent)) doubtPlus += 1.2f;

                } else {

                    //실제 심각성 보다 더 낫다고 말한 경우 ( * 의심도 내려감 )
                    doubtPlus += (1 - hogangPercent) * (differece * 0.1f);
                }

                //상황이 정상인데 구라친 경우
            } else {
                //모르는 문제에 대한 경우

                //true일 확률이 좀 높음 이 경우 실제 바이오의 고장상태와는 무관함
                if(Rand.get(1 - hogangPercent) || Rand.get(1 - hogangPercent)) {
                    doubtPlus += 0.1f * VaioProblem.Critical.valueOf(fakeCritical);
                }
            }

            doubtPercent += doubtPlus;
        }

        if(angryCheck()) {
            cumstomerState = CumstomerState.Angry;
        } else {
            //정상적으로 계약이 끝남
            cumstomerState = CumstomerState.overNegotiation;
        }
    }

    //TODO: 물건 받으로 돌아올 경우 구현

    public void appearToGetVaio() {
        purpose = REGAIN;
        cumstomerState = CumstomerState.waitForTurn;
        angryCheck();
    }

    public boolean receiveVaio(Vaio vaioRepaired) {
        //고객 자신의 지식과 호갱도를 기준으로 수리된 바이오랑 비교함

        return true;
    }

    //매 대화마다 체크함 여기서 분노상태로 돌입
    public boolean angryCheck() {
        return false;
    }

    @Override
    public void act(float deltaTime) {
        super.act(deltaTime);
        //입장하고 움직임 (대기는 아직)
        if(cumstomerState == CumstomerState.waitForTurn) {
            if(moveState == MovingState.wating) {

                //고객이 꽉차있으면 기다려야함.. 지금은 그런거 할시간 없으니 그냥 이동시킴

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

    public String sayWhatIKnowAboutMyVaio() {
        StringBuilder builder = new StringBuilder();
        System.out.println(whatIKnowAboutMyVaio);
        System.out.println(vaio.getImpairs());
        System.out.println(hogangPercent);
        System.out.println(doubtPercent);
        for(int i=0; i<whatIKnowAboutMyVaio.size(); i++){
            String convWord = null;
            switch (whatIKnowAboutMyVaio.get(i)) {
                case LiquidDisplayTrouble:
                    convWord = "화면에 제대로 표시를 못합니다.";
                    break;
                case BootTrouble:
                    convWord = "부팅이 안됩니다.";
                    break;
                case PowerTrouble:
                    convWord = "켜지지를 않습니다.";
                    break;
                case LCDTrouble:
                    convWord = "액정이 나간것 같습니다.";
                    break;
                case FunctionTrouble:
                    convWord = "쒸쁘뜨끼까 안빠찌ㅃ니다.";
                    break;
                case Cleaning:
                    convWord = "팬 소리가 너무 시끄럽습니다.";
                    break;
                case BatteryTrouble:
                    convWord = "배터리가 너무 빨리 닳습니다.";
            }

            if(i==whatIKnowAboutMyVaio.size()-1) builder.append(String.format("%s", convWord));
            else builder.append(String.format("%s%s", convWord.substring(0, convWord.length()-4), "고, "));
            if((i+1)%2==0) builder.append("\n");

        }
        if(whatIKnowAboutMyVaio.size()==0) {
            builder.append("뭔지 모르겠으나 매우 이상합니다");
        }
        return builder.toString();
    }

    public String getName(){ return name; }

    //TODO: 기록 저장 시 User Save 만들 것.
}