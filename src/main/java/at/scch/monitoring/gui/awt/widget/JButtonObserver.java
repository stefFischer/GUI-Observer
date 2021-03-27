package at.scch.monitoring.gui.awt.widget;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Observes the click of a given JButton
 * 
 * @author Stefan Fischer
 */
public class JButtonObserver extends AWTComponentObserver<JButton>
{
  private final ActionListener actionListener = e -> {
    JButton button = getComponent();
    AWTComponentActionEvent event = new AWTComponentActionEvent(JButtonObserver.this, e,
        AWTComponentActionEvent.DefaultEventType.ACTION_PERFORMED, "Button",
        button.getClass().getName());
    fireActionEvent(event);
  };

  public JButtonObserver(JButton component)
  {
    super(component);
  }

  @Override
  protected void addListeners(){
     getComponent().addActionListener(actionListener);
  }

  @Override
  protected void removeListeners(){
    getComponent().removeActionListener(actionListener);
  }
}
