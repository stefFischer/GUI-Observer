package at.scch.monitoring.gui.awt;

import at.scch.monitoring.gui.IGuiObserver;
import at.scch.monitoring.gui.awt.utils.ComponentUtils;

import java.awt.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * IGuiObserver for AWT applications
 * 
 * @author scch_fischer
 *
 */
public abstract class AWTObserver implements IGuiObserver
{
  /**
   * Component that should be observed
   */
  private final Component root;

  private static final long COLLECTION_THRESHOLD = 1000;

  private long addCount = 0;

  /**
   * 
   */
  private final Map<Component, AWTComponentObserver<Component>> components;

  private ContainerListener containerListener = new ContainerAdapter()
  {
    @Override
    public void componentAdded(ContainerEvent event)
    {
      startObservation(event.getChild());
      if (addCount > COLLECTION_THRESHOLD)
      {
        collectGrabage();
        addCount = 0;
      }
      addCount++;
    }

    @Override
    public void componentRemoved(ContainerEvent event)
    {
      stopObservation(event.getChild());
    }
  };

  public AWTObserver(Component root)
  {
    this.root = ComponentUtils.getComponentRoot(root);
    this.components = new IdentityHashMap<>();
  }

  private void collectGrabage()
  {
    Set<Component> watchedComponents = getWatchedComponents();
    AWTComponentVisitor visitor = new AWTComponentVisitor(root)
    {
      @Override
      public void visitComponent(Component component)
      {
        watchedComponents.remove(component);
      }
    };
    visitor.visit();

    synchronized (this.components)
    {
      for (Component toCollect : watchedComponents)
      {
        this.components.remove(toCollect);
      }
    }
  }

  public abstract <T extends Component> AWTComponentObserver<T> getObserver(T component);

  /**
   * Start monitoring.
   */
  @Override
  public void startObservation()
  {
    startObservation(this.root);
  }

  private void startObservation(Component component)
  {
    new AWTComponentVisitor(component)
    {
      @Override
      public void visitComponent(Component component)
      {
        synchronized (AWTObserver.this.components)
        {
          if (AWTObserver.this.components.containsKey(component))
          {
            return;
          }
          AWTComponentObserver<Component> componentObserver = AWTObserver.this.getObserver(component);
          if (componentObserver != null)
          {
            AWTObserver.this.components.put(component, componentObserver);
            componentObserver.startObservation();
            if (component instanceof Container)
            {
              ((Container)component).addContainerListener(containerListener);
            }
          }
        }
      }
    }.visit();
  }

  /**
   * Stop monitoring.
   */
  @Override
  public void stopObservation()
  {
    synchronized (AWTObserver.this.components)
    {
      for (AWTComponentObserver<? extends Component> componentObserver : this.components.values())
      {
        if (componentObserver != null)
        {
          componentObserver.stopObservation();
        }
      }
      this.components.clear();
    }
  }

  private void stopObservation(Component component)
  {
    new AWTComponentVisitor(component)
    {
      @Override
      public void visitComponent(Component component)
      {
        synchronized (AWTObserver.this.components)
        {
          if (AWTObserver.this.components.containsKey(component))
          {
            AWTComponentObserver<? extends Component> componentObserver = AWTObserver.this.components.get(component);
            if (componentObserver != null)
            {
              componentObserver.stopObservation();
            }

            AWTObserver.this.components.remove(component);
          }
          if (component instanceof Container)
          {
            ((Container)component).removeContainerListener(containerListener);
          }
        }
      }
    }.visit();
  }

  public AWTComponentObserver<Component> getComponentObserver(Component component)
  {
    synchronized (AWTObserver.this.components)
    {
      return this.components.get(component);
    }
  }

  @SuppressWarnings("unchecked")
  public <T extends Component> Set<T> getComponentsOfType(Class<T> type)
  {
    Set<T> componentSet = new HashSet<>();
    synchronized (this.components)
    {
      for (Component c : this.components.keySet())
      {
        if (type.isAssignableFrom(c.getClass()))
        {
          componentSet.add((T) c);
        }
      }
    }
    return componentSet;
  }

  public static boolean componentIsAncestorOf(Component ancestor, Component component)
  {
    Component parent = component;
    while (parent != null)
    {
      if (parent == ancestor)
      {
        return true;
      }
      parent = parent.getParent();
    }
    return false;
  }

  public Set<Component> getWatchedComponents()
  {
    synchronized (this.components)
    {
      return new HashSet<>(this.components.keySet());
    }
  }

  public Component getRoot()
  {
    return root;
  }
}
