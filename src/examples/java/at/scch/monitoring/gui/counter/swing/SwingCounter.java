package at.scch.monitoring.gui.counter.swing;

import at.scch.monitoring.gui.counter.Counter;
import at.scch.monitoring.gui.counter.awt.AWTCounterObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A Swing GUI application inherits from top-level container javax.swing.JFrame
public class SwingCounter extends JFrame {

    // Constructor to setup the GUI components and event handlers
    public SwingCounter() {
        setLayout(new FlowLayout());

        JLabel lblCount = new JLabel("Counter");
        add(lblCount);

        Counter counter = new Counter();

        JTextField tfCount = new JTextField(counter.getCount(), 10);
        tfCount.setEditable(false);
        add(tfCount);

        JButton btnCount = new JButton("Count");
        add(btnCount);

        btnCount.addActionListener(event -> {
                counter.inc();
                tfCount.setText(counter.getCount());
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Swing Counter");
        setSize(250, 100);
        setVisible(true);
    }

    // The entry main() method
    public static void main(String[] args) {
        SwingCounter app = new SwingCounter();

        //observer of the app for monitoring
        new SwingCounterObserver(app).startObservation();
    }
}