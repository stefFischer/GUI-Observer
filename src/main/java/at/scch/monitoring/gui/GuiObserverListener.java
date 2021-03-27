package at.scch.monitoring.gui;

/**
 * Listener for events from an Observer
 * 
 * @author scch_fischer
 *
 */
public interface GuiObserverListener
{
  /**
   * @param event
   *          GuiObserverEvent that occured
   */
  public void actionPerformed(GuiObserverEvent event);
}
