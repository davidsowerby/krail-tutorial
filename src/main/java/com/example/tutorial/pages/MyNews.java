package com.example.tutorial.pages;

import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.google.inject.Inject;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.option.Option;
import uk.q3c.krail.core.option.OptionContext;
import uk.q3c.krail.core.option.OptionKey;
import uk.q3c.krail.core.option.OptionPopup;
import uk.q3c.krail.core.shiro.SubjectProvider;
import uk.q3c.krail.core.user.notify.UserNotifier;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

import javax.annotation.Nonnull;

public class MyNews extends Grid3x3ViewBase implements OptionContext {

    public static final OptionKey<Boolean> ceoVisible = new OptionKey<>(true, MyNews.class, LabelKey.CEO_News_Channel);
    public static final OptionKey<Boolean> itemsForSaleVisible = new OptionKey<>(true, MyNews.class, LabelKey.Items_For_Sale_Channel);
    public static final OptionKey<Boolean> vacanciesVisible = new OptionKey<>(true, MyNews.class, LabelKey.Vacancies_Channel);

    private final Option option;
    private final OptionPopup optionPopup;
    private SubjectProvider subjectProvider;
    private UserNotifier userNotifier;
    private Label ceoNews;
    private Label itemsForSale;
    private Label vacancies;
    private Button popupButton;
    private Button systemOptionButton;
    private Button payRiseButton;

    @Inject
    protected MyNews(Translate translate, Option option, OptionPopup optionPopup, SubjectProvider subjectProvider, UserNotifier userNotifier) {
        super(translate);
        this.option = option;
        this.optionPopup = optionPopup;
        this.subjectProvider = subjectProvider;
        this.userNotifier = userNotifier;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        super.doBuild(busMessage);
        ceoNews = new Label("CEO News");
        itemsForSale = new Label("Items for Sale");
        vacancies = new Label("Vacancies");
        ceoNews.setSizeFull();
        itemsForSale.setSizeFull();
        vacancies.setSizeFull();

        popupButton = new Button("options");
        popupButton.addClickListener(event -> optionPopup.popup(this, LabelKey.News_Options));
        setBottomCentre(popupButton);

        systemOptionButton = new Button("system option");
        systemOptionButton.addClickListener(event -> {
            option.set(ceoVisible, 1, false);
            optionValueChanged(null);
        });
        if (subjectProvider.get()
                           .isPermitted("option:edit:SimpleUserHierarchy:*:1:*:*")) {
            systemOptionButton.setVisible(true);
        } else {
            systemOptionButton.setVisible(false);
        }
        setBottomRight(systemOptionButton);

        payRiseButton = new Button("request a pay rise");
        payRiseButton.addClickListener(event -> requestAPayRise());
        setBottomLeft(payRiseButton);

        setMiddleLeft(itemsForSale);
        setCentreCell(ceoNews);
        setMiddleRight(vacancies);
        optionValueChanged(null);


    }

    @RequiresPermissions("pay:request-increase")
    protected void requestAPayRise() {
        userNotifier.notifyInformation(DescriptionKey.You_just_asked_for_a_pay_increase);
    }

    @Nonnull
    @Override
    public Option getOption() {
        return option;
    }

    @Override
    public void optionValueChanged(Property.ValueChangeEvent event) {
        ceoNews.setVisible(option.get(ceoVisible));
        itemsForSale.setVisible(option.get(itemsForSaleVisible));
        vacancies.setVisible(option.get(vacanciesVisible));
    }
}