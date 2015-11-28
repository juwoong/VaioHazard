
package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.Gdx;
import org.dimigo.library.BrokenVaioGenerator;
import org.dimigo.library.Rand;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;

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
        hogangPercent = (float) ((rand.nextInt(100)+1) / 100.0);

        //초기 의심도 로직
        doubtPercent = (1 - ServiceCenter.getInstance().getReputaionPercent())
                * (1 - hogangPercent)
                + ((rand.nextInt() % 10) / 100);

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
            //case Fine: 은 고려안함
            switch (myVaioImpairs.get(trouble)) {
                case Little:
                    if(Rand.get(30 * (1 - hogang))) { memory.add(trouble); }
                    break;
                case Soso:
                    if(Rand.get(50 * (1 - hogang))) { memory.add(trouble); }
                    break;
                case Bad:
                    if(Rand.get(60 * (1 - hogang))) { memory.add(trouble); }
                    break;
                case Serious:
                    if(Rand.get(80 * (1 - hogang))) { memory.add(trouble); }
                    break;
                case Died:
                    if(Rand.get(100 * (1 - hogang))) { memory.add(trouble); }
                    break;
            }
        }
        return memory;
    }

    //TODO: * 다이얼로그랑 고객 내부로직 분리할거다 *

    //가게 들어와서 처음으로 하는 말
    public void speakWhatINeed() {

        Gdx.app.log("Customer", "speakWhatINeed");

        //다이얼로그 호출, 바이오 넘기기, 조사
    }

    //조사 결과를 들음, 일반적으로 구라를 쳐서 넘김
    public boolean listenInspectResult(Components components) {
        //TODO: 의심도 상승 여기서 의심도로 인한 분기 화나냐 안 화나냐, true일 경우 애가 빡친거고 false 인 경우 구라 성공
        //고객 자신의 지식과 호갱도를 기준으로 말한 재료와 비교함









        //구라 결과를 받고 의심도 계산이후 분기, 대답 다이얼로그 출력 이후 퇴장

        //정상적으로 계약이 끝남
        cumstomerState = CumstomerState.overNegotiation;
        return false;
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

    public void setVaio(Vaio vaio) {
        this.vaio = vaio;
    }

    public Vaio getVaio() {
        return vaio;
    }

    public String getName() { return name; }

    //TODO: 기록 저장 시 User Save 만들 것.
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

}