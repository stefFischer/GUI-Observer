package at.scch.monitoring.gui.awt.widget;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentObserver;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Observes the click of a given Button
 * 
 * @author Stefan Fischer
 */
public class ButtonObserver extends AWTComponentObserver<Button>
{
  private final ActionListener actionListener = e -> {
    Button button = getComponent();
    AWTComponentActionEvent event = new AWTComponentActionEvent(ButtonObserver.this, e,
        AWTComponentActionEvent.DefaultEventType.ACTION_PERFORMED, "Button",
        button.getClass().getName());
    fireActionEvent(event);
  };

  public ButtonObserver(Button component)
  {
    super(component);
  }

  @Override
  protected void addListeners()
  {
     getComponent().addActionListener(actionListener);
  }

  @Override
  protected void removeListeners()
  {
    getComponent().removeActionListener(actionListener);
  }
}
