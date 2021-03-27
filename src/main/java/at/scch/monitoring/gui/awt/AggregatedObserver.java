package at.scch.monitoring.gui.awt;

import java.awt.*;

/**
 * Aggregated observer to combine different observers on a single component.
 * e.g. use a ComponentObserver and a ContainerObserver on one component.
 * 
 * @author scch_fischer
 *
 */
public class AggregatedObserver extends AWTComponentObserver<Component>
{
  private final AWTComponentObserver<? extends Component>[] observers;

  @SafeVarargs
  public AggregatedObserver(Component component, AWTComponentObserver<? extends Component>... awtComponentObservers)
  {
    super(component);
    this.observers = awtComponentObservers;
  }

  @Override
  protected void addListeners()
  {
    for (AWTComponentObserver<? extends Component> observer : this.observers)
    {
      observer.addListeners();
    }
  }

  @Override
  protected void removeListeners()
  {
    for (AWTComponentObserver<? extends Component> observer : this.observers)
    {
      observer.removeListeners();
    }
  }

  @Override
  public void addAWTComponentActionListener(AWTComponentActionListener actionListener)
  {
    for (AWTComponentObserver<? extends Component> observer : this.observers)
    {
      observer.addAWTComponentActionListener(actionListener);
    }
  }

  @Override
  public void removeAWTComponentActionListener(AWTComponentActionListener actionListener)
  {
    for (AWTComponentObserver<? extends Component> observer : this.observers)
    {
      observer.removeAWTComponentActionListener(actionListener);
    }
  }
}
