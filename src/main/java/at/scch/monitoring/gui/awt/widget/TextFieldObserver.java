package at.scch.monitoring.gui.awt.widget;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentObserver;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * Observes the change of text in a given TextField
 *
 * @author Stefan Fischer
 */
public class TextFieldObserver extends AWTComponentObserver<TextField> {

    private final TextListener textListener = e -> {
        TextField textField = getComponent();
        AWTComponentActionEvent event = new AWTComponentActionEvent(TextFieldObserver.this, e,
                AWTComponentActionEvent.DefaultEventType.ACTION_PERFORMED, "TextValueChanged",
                textField.getClass().getName());
        fireActionEvent(event);
    };

    public TextFieldObserver(TextField component) {
        super(component);
    }

    @Override
    protected void addListeners() {
        getComponent().addTextListener(textListener);
    }

    @Override
    protected void removeListeners() {
        getComponent().removeTextListener(textListener);
    }
}
