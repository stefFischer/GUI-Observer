package at.scch.monitoring.gui;

/**
 * Interface that all GuiObservers should implement.
 * 
 * @author scch_fischer
 *
 */
public interface IGuiObserver
{
  /**
   * Start monitoring.
   */
  void startObservation();

  /**
   * Stop monitoring.
   */
  void stopObservation();
}
