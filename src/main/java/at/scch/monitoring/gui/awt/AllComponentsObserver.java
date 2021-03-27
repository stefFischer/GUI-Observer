package at.scch.monitoring.gui.awt;

import java.awt.*;

/**
 * Observes component and all its children
 * 
 * @author scch_fischer
 *
 */
public abstract class AllComponentsObserver extends AWTComponentObserver<Component>
{
  public AllComponentsObserver(Component component)
  {
    super(component);
  }

  @Override
  protected void addListeners()
  {
    new AWTComponentVisitor(getComponent())
    {
      @Override
      public void visitComponent(Component component)
      {
        addListeners(component);
      }
    }.visit();
  }

  @Override
  protected void removeListeners()
  {
    new AWTComponentVisitor(getComponent())
    {
      @Override
      public void visitComponent(Component component)
      {
        removeListeners(component);
      }
    }.visit();
  }

  protected abstract void addListeners(Component component);

  protected abstract void removeListeners(Component component);
}
