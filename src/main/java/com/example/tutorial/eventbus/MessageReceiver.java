package com.example.tutorial.eventbus;


import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import net.engio.mbassy.listener.Handler;

public abstract class MessageReceiver extends Panel {
    private final TextArea textField;

    public MessageReceiver() {
        this.setSizeFull();
        this.textField = new TextArea();
        textField.setSizeFull();
        textField.setRows(8);
        setContent(textField);
    }

    public String getText() {
        return textField.getValue();
    }

    @Handler
    public void addMsg(TutorialMessage tutorialMessage) {
        String s = getText();
        textField.setValue(s + "\n" + tutorialMessage.getMsg());
    }

    public TextArea getTextField() {
        return textField;
    }
}