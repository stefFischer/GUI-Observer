package at.scch.monitoring.gui.awt.utils;

import java.awt.*;

/**
 * 
 * Collection of helper methods.
 * 
 * @author scch_fischer
 *
 */
public abstract class ComponentUtils
{

  private ComponentUtils()
  {
    // do nothing
  }

  /**
   * Go up hierarchy of components until root is reached
   * 
   * @param component
   * @return root component
   */
  public static Component getComponentRoot(Component component)
  {
    // go up hierarchy first
    Component root = component;
    while (root.getParent() != null)
    {
      root = root.getParent();
    }
    return root;
  }

  /**
   * Checks if component is contained in container.
   * 
   * @param container
   * @param component
   * @return
   */
  public static boolean isContainedIn(Container container, Component component)
  {
    while (component != null)
    {
      if (component == container)
      {
        return true;
      }
      component = component.getParent();
    }
    return false;
  }
}
