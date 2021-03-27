package at.scch.monitoring.gui.awt;

import at.scch.monitoring.gui.IGuiObserver;

import java.awt.Component;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AWTComponentObserver<T extends Component> implements IGuiObserver
{
  /**
   * Component that should be observed
   */
  private final T component;
  private final Set<AWTComponentActionListener> actionListeners;

  public AWTComponentObserver(T component)
  {
    super();
    this.component = component;
    this.actionListeners = new LinkedHashSet<>();
  }

  public T getComponent()
  {
    return component;
  }

  @Override
  public void startObservation()
  {
    addListeners();
  }

  @Override
  public void stopObservation()
  {
    removeListeners();
  }

  public void addAWTComponentActionListener(AWTComponentActionListener actionListener)
  {
    if (actionListener != null)
    {
      this.actionListeners.add(actionListener);
    }
  }

  public void removeAWTComponentActionListener(AWTComponentActionListener actionListener)
  {
    this.actionListeners.remove(actionListener);
  }

  protected void fireActionEvent(AWTComponentActionEvent event)
  {
    for (AWTComponentActionListener actionListener : this.actionListeners)
    {
      actionListener.actionPerformed(event);
    }
  }

  protected abstract void addListeners();

  protected abstract void removeListeners();
}
