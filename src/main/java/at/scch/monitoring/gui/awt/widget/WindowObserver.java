package at.scch.monitoring.gui.awt.widget;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentObserver;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowObserver extends AWTComponentObserver<Window>
{
  private final WindowListener windowListener = new WindowAdapter()
  {
    @Override
    public void windowOpened(WindowEvent e)
    {
      AWTComponentActionEvent event = new AWTComponentActionEvent(WindowObserver.this, e,
          AWTComponentActionEvent.DefaultEventType.WINDOW_OPENED, "Window opened");
      fireActionEvent(event);
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
      AWTComponentActionEvent event = new AWTComponentActionEvent(WindowObserver.this, e,
          AWTComponentActionEvent.DefaultEventType.WINDOW_CLOSED, "Window closed");
      fireActionEvent(event);
    }
  };
  //

  public WindowObserver(Window component)
  {
    super(component);
  }

  @Override
  protected void addListeners()
  {
    getComponent().addWindowListener(windowListener);
  }

  @Override
  protected void removeListeners()
  {
    getComponent().removeWindowListener(windowListener);
  }
}
