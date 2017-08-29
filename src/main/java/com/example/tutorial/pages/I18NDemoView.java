package com.example.tutorial.pages;

import com.example.tutorial.form.PersonForm;
import com.example.tutorial.i18n.*;
import com.google.inject.Inject;
import com.vaadin.ui.*;
import uk.q3c.krail.core.view.ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.i18n.Translate;

public class I18NDemoView extends ViewBase {
    private final PersonForm personForm;
    @TutorialCaption(caption = TutorialLabelKey.News, description = TutorialDescriptionKey.Interesting_Things)
    private Grid grid;
    @TutorialCaption(caption = TutorialLabelKey.News, description = TutorialDescriptionKey.Interesting_Things)
    @TutorialValue(value = TutorialDescriptionKey.You_just_asked_for_a_pay_increase)
    private Label label;
    @TutorialCaption(caption = TutorialLabelKey.News, description = TutorialDescriptionKey.Interesting_Things)
    private Table table;
    @TutorialCaption(caption = TutorialLabelKey.News, description = TutorialDescriptionKey.Interesting_Things)
    private TextField textField;
    @TutorialCaption(caption = TutorialLabelKey.CEO_News_Channel, description = TutorialDescriptionKey.Interesting_Things)
    private ButtonBar buttonBar1;
    private ButtonBar buttonBar2;

    @Inject
    protected I18NDemoView(Translate translate, PersonForm personForm) {
        super(translate);
        this.personForm = personForm;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        textField = new TextField();
        label = new Label();
        table = new Table();
        grid = new Grid();
        buttonBar1 = new ButtonBar();
        buttonBar2 = new ButtonBar();
        setupTable();
        setupGrid();
        VerticalLayout layout = new VerticalLayout(personForm.getLayout(), buttonBar1, buttonBar2, textField, label, table, grid);
        Panel panel = new Panel();
        panel.setContent(layout);
        setRootComponent(panel);

    }

    private void setupTable() {
        table.addContainerProperty(TutorialLabelKey.First_Name, String.class, null);
        table.addContainerProperty(TutorialLabelKey.Last_Name, String.class, null);
        table.setHeight("100px");
        table.setWidth("200px");
    }

    private void setupGrid() {
        grid.addColumn(TutorialLabelKey.First_Name, String.class);
        grid.addColumn(TutorialLabelKey.Last_Name, Integer.class);
    }
}
