package org.dimigo.vaiohazard.Object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.dimigo.library.Rand;
import org.dimigo.vaiohazard.Device.Components;
import org.dimigo.vaiohazard.Device.Vaio;
import org.dimigo.vaiohazard.Device.VaioProblem;
import org.dimigo.vaiohazard.control.Scheduler;
import org.dimigo.vaiohazard.conversation.Conversation;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Created by YuTack on 2015-11-11.
 */

public class ServiceCenter {
    private static ServiceCenter center = new ServiceCenter();
    public static ServiceCenter getInstance() {
        return center;
    }

    private ServiceCenter() {}

    private int money;
    private float reputaionPercent;
    private List<RepairOrder> orders = new ArrayList<RepairOrder>();

    private int month;
    private int day;
    private int hour;
    private int minutes;
    private DayOfWeek dayOfWeek;

    //0 에서 1사이의 값
    private float inspectSkill = 0.5f;

    private Stage currentStage;
    //repairOrders에서 오늘날짜 고객들을 서치해서 customers에 넣어 줌.
    private Scheduler todaySchedule;

    //지금 가게에 렌더링되고 있는 고객만 있음
    private ArrayList<Customer> customers;
    private int waitingNumber = 0;

    public static final int MAX_COUNTER_NUM = 1;
    public static final int MAX_WAITING_NUM = 5;

    private Components components;
//
    public static void newCenter() {
        center = new ServiceCenter();
        center.money = 10000;
        center.reputaionPercent = 30 / 100;
        center.month = 1;
        center.day= 1;
        center.hour = 9;
        center.minutes = 0;
        center.dayOfWeek = DayOfWeek.MONDAY;
        center.customers = new ArrayList<Customer>();
    }

    /*public static ServiceCenter loadCenter() {

    }*/

    public void updateDate() {
        todaySchedule = new Scheduler(month, day, orders, customers);
    }

    public void updateWaitingState(Customer orderer) {
        customers.remove(orderer);
        for(Customer customer : customers) {
            if(customer.getCustomerState() == Customer.CustomerState.waitForTurn) {
                //대기번호 하나씩 줄어듬, 대기번호 0번되면 자동으로 카운터로 이동하고 협상 대기 상태가 됨
                customer.updateWaitingNumber();
            }
        }
    }

    public void update(float deltaTime) {
        todaySchedule.update(deltaTime);

        int currentWaitingNum = 0;

        for(Customer customer : customers) {

            Customer.CustomerState state = customer.getCustomerState();

            if(state == Customer.CustomerState.readyToNegotiate) {
                (new Conversation(currentStage, customer)).start();
            } else if(state == Customer.CustomerState.waitForTurn) {
                currentWaitingNum++;
            }
        }

        waitingNumber = currentWaitingNum;
    }

    static public class InspectResult {
        public Map<VaioProblem.Trouble, VaioProblem.Critical> impairs;
        public int failCount;
    }

    //Fine인 문제를 착각할 정도로 멍청하진 않음, 그러나 스킬에 따라 있는 문제를 모르거나(가벼운 경우만) 심각성을 잘못 판단할 수 있음
    public InspectResult inspectVaio(Vaio vaio) {
        Map<VaioProblem.Trouble, VaioProblem.Critical> inspectResult = new HashMap<VaioProblem.Trouble, VaioProblem.Critical>();
        int failCount = 0;

        for (VaioProblem.Trouble realTrouble : vaio.getImpairs().keySet()) {
            if (vaio.getImpairs().get(realTrouble) == VaioProblem.Critical.Fine) {

                inspectResult.put(realTrouble, VaioProblem.Critical.Fine);

            } else if (vaio.getImpairs().get(realTrouble) == VaioProblem.Critical.Little) {

                if (Rand.get(inspectSkill)) inspectResult.put(realTrouble, VaioProblem.Critical.Little);
                else {
                    inspectResult.put(realTrouble, VaioProblem.Critical.Fine);
                    failCount++;
                }

            } else {

                if (Rand.get(inspectSkill)) {
                    inspectResult.put(realTrouble, vaio.getImpairs().get(realTrouble));
                } else {
                    Random rand = new Random();
                    inspectResult.put(realTrouble, VaioProblem.Critical.values()[2 + rand.nextInt(3)]);
                    failCount++;
                }

            }
        }

        InspectResult result = new InspectResult();
        result.impairs = inspectResult;
        result.failCount = failCount;
        return result;
    }

    public void addRepairOrder(RepairOrder order) {
        updateWaitingState(order.getOrderer());
        orders.add(order);
    }

    public int getMoney() {
        return money;
    }

    public float getReputaionPercent() {
        return reputaionPercent;
    }

    public float getInspectSkill() { return inspectSkill; }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setCurrentStage(Stage stage) { this.currentStage = stage; }

    public int getWaitingNumber() { return waitingNumber; }
}