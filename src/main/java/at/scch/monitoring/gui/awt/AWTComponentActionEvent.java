package at.scch.monitoring.gui.awt;

import at.scch.monitoring.gui.GuiObserverEvent;

import java.awt.*;

public class AWTComponentActionEvent extends GuiObserverEvent
{
  public enum DefaultEventType implements EventType
  {
    ACTION_PERFORMED, DIALOG_OPENED, COMPONENT_ADDED, COMPONENT_REMOVED, WINDOW_OPENED, WINDOW_CLOSED, MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_CLICKED, KEY_PRESSED, KEY_RELEASED, KEY_TYPED
  }
  private final AWTComponentObserver<?> observer;
  private final AWTEvent event;

  public AWTComponentActionEvent(AWTComponentObserver<?> observer, EventType type, String description)
  {
    this(observer, null, type, description);
  }

  public AWTComponentActionEvent(AWTComponentObserver<?> observer, AWTEvent event, EventType type, String description)
  {
    super(type, description);
    this.observer = observer;
    this.event = event;
  }

  public AWTComponentActionEvent(AWTComponentObserver<?> observer, EventType type, String description,
      String metaInformation)
  {
    this(observer, null, type, description, metaInformation);
  }

  public AWTComponentActionEvent(AWTComponentObserver<?> observer, AWTEvent event, EventType type, String description,
      String metaInformation)
  {
    super(type, description, metaInformation);
    this.observer = observer;
    this.event = event;
  }

  public AWTComponentObserver<?> getObserver()
  {
    return observer;
  }

  public AWTEvent getEvent()
  {
    return event;
  }

  @Override
  public String toString()
  {
    return "AWTComponentActionEvent [observer=" + observer + ", event=" + event + ", getType()=" + getType()
        + ", getDescription()=" + getDescription() + ", getProperties()=" + getProperties() + "]";
  }
}
