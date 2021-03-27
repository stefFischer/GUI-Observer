package at.scch.monitoring.gui.awt;

import java.awt.Component;
import java.awt.Container;

public abstract class AWTComponentVisitor
{
  private final Component component;

  public AWTComponentVisitor(Component component)
  {
    super();
    this.component = component;
  }

  public abstract void visitComponent(Component component);

  public void visit()
  {
    visit(this.component);
  }

  private void visit(Component component)
  {
    visitComponent(component);
    if (component instanceof Container)
    {
      for (Component child : ((Container) component).getComponents())
      {
        visit(child);
      }
    }
  }
}
