package at.scch.monitoring.gui.counter.awt;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentActionListener;
import at.scch.monitoring.gui.awt.AWTComponentObserver;
import at.scch.monitoring.gui.awt.AWTObserver;
import at.scch.monitoring.gui.awt.widget.ButtonObserver;
import at.scch.monitoring.gui.awt.widget.TextFieldObserver;
import at.scch.monitoring.gui.awt.widget.WindowObserver;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AWTCounterObserver extends AWTObserver {

    private AWTComponentActionEvent action;
    private long actionTime;

    private final static Logger LOGGER = Logger.getLogger(AWTCounterObserver.class.getName());

    private final AWTComponentActionListener buttonListener = event -> {
        action = event;
        actionTime = System.currentTimeMillis();
    };

    private final AWTComponentActionListener textListener = event -> {
        String actionString = "Unknown";
        if(action != null && System.currentTimeMillis() - actionTime <= 1000){
            actionString = action.getDescription();
        }
        action = null;
        LOGGER.log(Level.INFO, "Counter changed, by: " + actionString);
    };

    private final AWTComponentActionListener windowListener = event -> {
        LOGGER.log(Level.INFO, event.getDescription());
    };

    public AWTCounterObserver(AWTCounter root) {
        super(root);
    }

    @Override
    public <T extends Component> AWTComponentObserver getObserver(T component) {
        if(component instanceof Button){
            ButtonObserver observer = new ButtonObserver((Button) component);
            observer.addAWTComponentActionListener(buttonListener);
            return observer;
        }
        if(component instanceof TextField){
            TextFieldObserver observer = new TextFieldObserver((TextField) component);
            observer.addAWTComponentActionListener(textListener);
            return observer;
        }
        if(component instanceof Window){
            WindowObserver observer = new WindowObserver((Window) component);
            observer.addAWTComponentActionListener(windowListener);
            return observer;
        }
        return null;
    }
}
