package at.scch.monitoring.gui.counter.awt;

import at.scch.monitoring.gui.counter.Counter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// An AWT program inherits from the top-level container java.awt.Frame
public class AWTCounter extends Frame {
    // Constructor to setup GUI components and event handlers
    public AWTCounter() {
        setLayout(new FlowLayout());

        Label lblCount = new Label("Counter");
        add(lblCount);

        Counter counter = new Counter();

        TextField tfCount = new TextField(counter.getCount(), 10);
        tfCount.setEditable(false);
        add(tfCount);

        Button btnCount = new Button("Count");
        add(btnCount);

        btnCount.addActionListener(event -> {
            counter.inc();
            tfCount.setText(counter.getCount());
        });

        setTitle("AWT Counter");
        setSize(250, 100);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    dispose();
                }
            }
        );
    }

    public static void main(String[] args) {
        AWTCounter app = new AWTCounter();

        //observer of the app for monitoring
        new AWTCounterObserver(app).startObservation();
    }
}