package com.example.tutorial.pages;

import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.example.tutorial.i18n.MessageKey;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import uk.q3c.krail.core.eventbus.SessionBus;
import uk.q3c.krail.core.eventbus.SubscribeTo;
import uk.q3c.krail.core.i18n.LocaleChangeBusMessage;
import uk.q3c.krail.core.i18n.PatternSource;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.option.*;
import uk.q3c.krail.core.persist.cache.i18n.PatternCacheKey;
import uk.q3c.krail.core.persist.common.i18n.PatternDao;
import uk.q3c.krail.core.shiro.SubjectProvider;
import uk.q3c.krail.core.user.notify.UserNotifier;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.Random;

@Listener
@SubscribeTo(SessionBus.class)
public class MyNews extends Grid3x3ViewBase implements OptionContext {

    public static final OptionKey<Boolean> ceoVisible = new OptionKey<>(true, MyNews.class, LabelKey.CEO_News_Channel);
    public static final OptionKey<Boolean> itemsForSaleVisible = new OptionKey<>(true, MyNews.class, LabelKey.Items_For_Sale_Channel);
    public static final OptionKey<Boolean> vacanciesVisible = new OptionKey<>(true, MyNews.class, LabelKey.Vacancies_Channel);

    private final Option option;
    private final OptionPopup optionPopup;
    private SubjectProvider subjectProvider;
    private UserNotifier userNotifier;
    private Provider<PatternDao> patternDaoProvider;
    private PatternSource patternSource;
    private Label ceoNews;
    private Label itemsForSale;
    private Label vacancies;
    @Caption(caption = LabelKey.Options, description = DescriptionKey.Select_your_options)
    private Button popupButton;
    private Button systemOptionButton;
    private Button payRiseButton;
    private Label bannerLabel;
    private TextField i18NTextBox;
    private Button submitButton;

    @Inject
    protected MyNews(Translate translate, Option option, OptionPopup optionPopup, SubjectProvider subjectProvider, UserNotifier userNotifier, @InMemory
    Provider<PatternDao> patternDaoProvider, PatternSource patternSource) {
        super(translate);
        this.option = option;
        this.optionPopup = optionPopup;
        this.subjectProvider = subjectProvider;
        this.userNotifier = userNotifier;
        this.patternDaoProvider = patternDaoProvider;
        this.patternSource = patternSource;
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

        popupButton = new Button();
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


        int temperature = (new Random().nextInt(40)) - 10;
        LabelKey selection = (option.get(ceoVisible)) ? LabelKey.is_selected : LabelKey.is_not_selected;

        bannerLabel = new Label();
        getGridLayout().addComponent(bannerLabel, 0, 0, 1, 0);

        i18NTextBox = new TextField();
        i18NTextBox.setCaption("enter a value for LabelKey.is_selected");
        submitButton = new Button("submit");
        PatternCacheKey cacheKeyUK = new PatternCacheKey(LabelKey.is_selected, Locale.UK);
        submitButton.addClickListener(event -> {
            patternSource.clearCache();
            patternDaoProvider.get()
                              .write(cacheKeyUK, i18NTextBox.getValue());
            populateBanner();
        });
        FormLayout formLayout = new FormLayout(i18NTextBox, submitButton);
        setTopRight(formLayout);


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
        populateBanner();
    }

    private void populateBanner() {
        int temperature = (new Random().nextInt(40)) - 10;
        LabelKey selection = (option.get(ceoVisible)) ? LabelKey.is_selected : LabelKey.is_not_selected;
        bannerLabel.setValue(getTranslate().from(MessageKey.Banner, selection, temperature));
    }

    @Handler
    protected void localeChanged(LocaleChangeBusMessage busMessage) {
        populateBanner();
    }
}