package futurismo.framework.states;

import java.util.ArrayList;

public class StateMachine {
    private ArrayList<State> states; 
    private State currentState;

    public StateMachine(){
        states = new ArrayList<>();
        currentState = null;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public State getCurrentState() {return currentState;}
 
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
