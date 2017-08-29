package com.example.tutorial.pages;

import com.example.tutorial.i18n.TutorialCaption;
import com.example.tutorial.i18n.TutorialDescriptionKey;
import com.example.tutorial.i18n.TutorialLabelKey;
import com.example.tutorial.i18n.TutorialMessageKey;
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
import uk.q3c.krail.core.option.OptionPopup;
import uk.q3c.krail.core.option.VaadinOptionContext;
import uk.q3c.krail.core.shiro.SubjectProvider;
import uk.q3c.krail.core.user.notify.UserNotifier;
import uk.q3c.krail.core.view.Grid3x3ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.eventbus.SubscribeTo;
import uk.q3c.krail.i18n.LocaleChangeBusMessage;
import uk.q3c.krail.i18n.Translate;
import uk.q3c.krail.i18n.persist.PatternCacheKey;
import uk.q3c.krail.i18n.persist.PatternDao;
import uk.q3c.krail.i18n.persist.PatternSource;
import uk.q3c.krail.option.Option;
import uk.q3c.krail.option.OptionKey;
import uk.q3c.krail.persist.InMemory;

import java.util.Locale;
import java.util.Random;

@Listener
@SubscribeTo(SessionBus.class)
public class MyNews extends Grid3x3ViewBase implements VaadinOptionContext {

    public static final OptionKey<Boolean> ceoVisible = new OptionKey<>(true, MyNews.class, TutorialLabelKey.CEO_News_Channel);
    public static final OptionKey<Boolean> itemsForSaleVisible = new OptionKey<>(true, MyNews.class, TutorialLabelKey.Items_For_Sale_Channel);
    public static final OptionKey<Boolean> vacanciesVisible = new OptionKey<>(true, MyNews.class, TutorialLabelKey.Vacancies_Channel);

    private final Option option;
    private final OptionPopup optionPopup;
    private final SubjectProvider subjectProvider;
    private final Provider<PatternDao> patternDaoProvider;
    private final PatternSource patternSource;
    private UserNotifier userNotifier;
    private Label ceoNews;
    private Label itemsForSale;
    private Label vacancies;
    @TutorialCaption(caption = TutorialLabelKey.Options, description = TutorialDescriptionKey.Select_your_options)
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
        nameKey = TutorialLabelKey.My_News;
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
        popupButton.addClickListener(event -> optionPopup.popup(this, TutorialLabelKey.News_Options));
        setBottomCentre(popupButton);

        systemOptionButton = new Button("system option");
        systemOptionButton.addClickListener(event -> {
            option.set(ceoVisible, 1, false);
            optionValueChanged(null);
        });
        setBottomRight(systemOptionButton);

        setMiddleLeft(itemsForSale);
        setCentreCell(ceoNews);
        setMiddleRight(vacancies);


        if (subjectProvider.get().isPermitted("option:edit:SimpleUserHierarchy:*:1:*:*")) {
            systemOptionButton.setVisible(true);
        } else {
            systemOptionButton.setVisible(false);
        }

        payRiseButton = new Button("request a pay rise");
        payRiseButton.addClickListener(event -> requestAPayRise());
        setBottomLeft(payRiseButton);

        bannerLabel = new Label();
        getGridLayout().addComponent(bannerLabel, 0, 0, 1, 0);
        i18NTextBox = new TextField();
        i18NTextBox.setCaption("enter a value for LabelKey.is_selected");
        submitButton = new Button("submit");
        PatternCacheKey cacheKeyUK = new PatternCacheKey(TutorialLabelKey.is_selected, Locale.UK);
        submitButton.addClickListener(event -> {
            patternSource.clearCache();
            patternDaoProvider.get().write(cacheKeyUK, i18NTextBox.getValue());
            populateBanner();
        });
        FormLayout formLayout = new FormLayout(i18NTextBox, submitButton);
        setTopRight(formLayout);

        optionValueChanged(null);

    }

    @RequiresPermissions("pay:request-increase")
    protected void requestAPayRise() {
        userNotifier.notifyInformation(TutorialDescriptionKey.You_just_asked_for_a_pay_increase);
    }

    @Override
    public Option optionInstance() {
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
        TutorialLabelKey selection = (option.get(ceoVisible)) ? TutorialLabelKey.is_selected : TutorialLabelKey.is_not_selected;
        bannerLabel.setValue(getTranslate().from(TutorialMessageKey.Banner, selection, temperature));
    }

    @Handler
    protected void localeChanged(LocaleChangeBusMessage busMessage) {
        populateBanner();
    }
}