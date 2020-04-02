package futurismo.framework.states;

import java.awt.*;

public abstract class State {
    protected String name; 
    protected StateMachine stateMachine;

    public State(String name, StateMachine stateMachine) {
        this.name = name;
        this.stateMachine = stateMachine;
        this.stateMachine.addState(this);
    }

    public abstract void render(Graphics g);
    public abstract void tick();

    @Override
    public String toString() {
        return name;
    }
}
