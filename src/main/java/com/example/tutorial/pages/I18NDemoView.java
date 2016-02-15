package com.example.tutorial.pages;

import com.example.tutorial.form.PersonForm;
import com.example.tutorial.i18n.*;
import com.google.inject.Inject;
import com.vaadin.ui.*;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.view.ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

public class I18NDemoView extends ViewBase {
    @Caption(caption = LabelKey.News, description = DescriptionKey.Interesting_Things)
    private Grid grid;
    @Caption(caption = LabelKey.News, description = DescriptionKey.Interesting_Things)
    @Value(value = DescriptionKey.You_just_asked_for_a_pay_increase)
    private Label label;
    @Caption(caption = LabelKey.News, description = DescriptionKey.Interesting_Things)
    private Table table;
    @Caption(caption = LabelKey.News, description = DescriptionKey.Interesting_Things)
    private TextField textField;
    @Caption(caption = LabelKey.CEO_News_Channel, description = DescriptionKey.Interesting_Things)
    private ButtonBar buttonBar1;
    private ButtonBar buttonBar2;
    private PersonForm personForm;

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
        setupTable();
        setupGrid();
        buttonBar1 = new ButtonBar();
        buttonBar2 = new ButtonBar();

        VerticalLayout layout = new VerticalLayout(personForm.getLayout(), buttonBar1, buttonBar2, textField, label, table, grid);
        Panel panel = new Panel();
        panel.setContent(layout);
        setRootComponent(panel);
    }

    private void setupTable() {
        table.addContainerProperty(LabelKey.First_Name, String.class, null);
        table.addContainerProperty(LabelKey.Last_Name, String.class, null);
        table.setHeight("100px");
        table.setWidth("200px");
    }

    private void setupGrid() {
        grid.addColumn(LabelKey.First_Name, String.class);
        grid.addColumn(LabelKey.Last_Name, Integer.class);
    }
}
