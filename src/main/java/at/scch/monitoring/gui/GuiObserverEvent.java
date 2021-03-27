package at.scch.monitoring.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Event for Observers.
 * 
 * @author scch_fischer
 *
 */
public class GuiObserverEvent
{
  /**
   * EventType to distinguish different types of events.
   * 
   * @author scch_fischer
   *
   */
  public interface EventType
  {
    public String name();
  }

  private final EventType type;

  // description of the event, is used to pass action string that is stored
  private final String description;

  // meta information to add to events, used for additional information in
  // actions
  private final String metaInformation;

  // arbitrary properties that can be added to an event
  private Map<String, Object> properties;

  public GuiObserverEvent(EventType type, String description)
  {
    this(type, description, null);
  }

  public GuiObserverEvent(EventType type, String description, String metaInformation)
  {
    super();
    this.type = type;
    this.description = description;
    this.metaInformation = metaInformation;
    this.properties = new HashMap<>();
  }

  public EventType getType()
  {
    return type;
  }

  public String getDescription()
  {
    return description;
  }

  public String getMetaInformation()
  {
    return metaInformation;
  }

  public <T> Optional<T> getProperty(final String name)
  {
    Optional<T> result = Optional.empty();
    if (properties.containsKey(name))
    {
      final Object obj = properties.get(name);
      try
      {
        @SuppressWarnings("unchecked")
        final T item = (T) obj;
        result = Optional.of(item);
      }
      catch (final ClassCastException e)
      {
        Logger.getLogger(GuiObserverEvent.class.getName()).log(Level.WARNING,
            "Expected a different type of the property.");
      }
    }
    return result;
  }

  public <T> T getPropertyValue(final String name)
  {
    Optional<T> result = getProperty(name);
    if (result.isPresent())
    {
      return result.get();
    }
    return null;
  }

  public <T> void putProperty(final String name, final T property)
  {
    if (name != null && !name.isEmpty() && property != null)
    {
      properties.put(name, property);
    }
  }

  public void removeProperty(String name)
  {
    properties.remove(name);
  }

  public boolean hasProperty(final String name)
  {
    return properties.containsKey(name);
  }

  public Map<String, Object> getProperties()
  {
    return new HashMap<>(properties);
  }

  @Override
  public String toString()
  {
    return "GuiObserverEvent [type=" + type + ", description=" + description + ", properties=" + properties + "]";
  }
}
