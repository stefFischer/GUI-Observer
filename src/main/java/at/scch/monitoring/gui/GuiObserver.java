package at.scch.monitoring.gui;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * Abstract super class for all observers.
 * 
 * @author scch_fischer
 *
 */
public abstract class GuiObserver implements IGuiObserver
{
  private final Set<GuiObserverListener> actionListeners;

  public GuiObserver()
  {
    super();
    this.actionListeners = new LinkedHashSet<>();
  }

  /**
   * add a GuiObserverListener
   * 
   * @param actionListener
   */
  public void addGuiObserverListener(GuiObserverListener actionListener)
  {
    if (actionListener != null)
    {
      this.actionListeners.add(actionListener);
    }
  }

  /**
   * remove GuiObserverListener
   * 
   * @param actionListener
   */
  public void removeGuiObserverListener(GuiObserverListener actionListener)
  {
    this.actionListeners.remove(actionListener);
  }

  /**
   * Fire the event to all registered listeners.
   * 
   * @param event
   */
  protected void fireActionEvent(GuiObserverEvent event)
  {
    for (GuiObserverListener actionListener : this.actionListeners)
    {
      actionListener.actionPerformed(event);
    }
  }
}
