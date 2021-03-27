package at.scch.monitoring.gui.awt.widget;

import at.scch.monitoring.gui.awt.AWTComponentActionEvent;
import at.scch.monitoring.gui.awt.AWTComponentObserver;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.TextListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Observes the change of text in a given JTextField
 *
 * @author Stefan Fischer
 */
public class JTextFieldObserver extends AWTComponentObserver<JTextField> {

    private final Set<DocumentEvent.EventType> types;

    private final DocumentListener documentListener = new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
            fireEvent(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fireEvent(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fireEvent(e);
        }

        private void fireEvent(DocumentEvent e){
            if(!types.contains(e.getType())){
                return;
            }
            JTextField textField = getComponent();
            AWTComponentActionEvent event = new AWTComponentActionEvent(JTextFieldObserver.this, null,
                    AWTComponentActionEvent.DefaultEventType.ACTION_PERFORMED, "TextValueChanged",
                    textField.getClass().getName());
            fireActionEvent(event);
        }
    };

    public JTextFieldObserver(JTextField component) {
        this(component, DocumentEvent.EventType.INSERT, DocumentEvent.EventType.REMOVE, DocumentEvent.EventType.CHANGE);
    }

    public JTextFieldObserver(JTextField component, DocumentEvent.EventType... types) {
        super(component);
        this.types = new HashSet<>(Arrays.asList(types));
    }

    @Override
    protected void addListeners() {
        getComponent().getDocument().addDocumentListener(documentListener);
    }

    @Override
    protected void removeListeners() {
        getComponent().getDocument().removeDocumentListener(documentListener);
    }
}
