package com.example.tutorial.pages;

import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.example.tutorial.jpa.DerbyJpa;
import com.google.inject.Inject;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.option.*;
import uk.q3c.krail.core.persist.common.common.ContainerType;
import uk.q3c.krail.core.persist.common.common.VaadinContainerProvider;
import uk.q3c.krail.core.persist.common.option.OptionEntity;
import uk.q3c.krail.core.persist.inmemory.common.InMemoryContainer;
import uk.q3c.krail.core.view.ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.persist.jpa.common.JpaContainerProvider;
import uk.q3c.krail.persist.jpa.option.JpaOptionEntity;

import javax.annotation.Nonnull;

public class JpaOptionView extends ViewBase implements OptionContext {


    public static final OptionKey<String> anyOldText = new OptionKey<>("default text", MyNews.class, LabelKey.Age, DescriptionKey.Age_of_the_Person);
    private final VaadinContainerProvider inMemoryContainerProvider;
    private final JpaContainerProvider derbyContainerProvider;
    private JPAContainer<JpaOptionEntity> derbyContainer;
    private InMemoryContainer inMemoryContainer;

    @Caption(caption = LabelKey.In_Memory, description = DescriptionKey.Interesting_Things)
    private Table inMemoryTable;
    @Caption(caption = LabelKey.Derby, description = DescriptionKey.Interesting_Things)
    private Table derbyTable;
    private Option option;
    private OptionPopup optionPopup;

    @Caption(caption = LabelKey.Options, description = DescriptionKey.Interesting_Things)
    private Button optionPopupButton;

    @Inject
    protected JpaOptionView(Translate translate, @InMemory VaadinContainerProvider inMemoryContainerProvider, @DerbyJpa JpaContainerProvider
            derbyContainerProvider, OptionPopup
                                    optionPopup, Option option) {
        super(translate);
        this.inMemoryContainerProvider = inMemoryContainerProvider;
        this.derbyContainerProvider = derbyContainerProvider;
        this.optionPopup = optionPopup;
        this.option = option;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        optionPopupButton = new Button();
        optionPopupButton.addClickListener(event -> optionPopup.popup(this, LabelKey.Options));
        inMemoryTable = new Table();
        derbyTable = new Table();
        inMemoryContainer = (InMemoryContainer) inMemoryContainerProvider.get(OptionEntity.class, ContainerType.CACHED);
        derbyContainer = derbyContainerProvider.get(JpaOptionEntity.class, ContainerType.CACHED);
        inMemoryTable.setContainerDataSource(inMemoryContainer);
        derbyTable.setContainerDataSource(derbyContainer);

        HorizontalLayout horizontalLayout = new HorizontalLayout(optionPopupButton, inMemoryTable, derbyTable);
        setRootComponent(new Panel(horizontalLayout));

    }

    @Nonnull
    @Override
    public Option getOption() {
        return option;
    }

    @Override
    public void optionValueChanged(Property.ValueChangeEvent event) {
        inMemoryContainer.refresh();
        derbyContainer.refresh();
    }
}