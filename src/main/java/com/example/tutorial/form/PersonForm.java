package com.example.tutorial.form;

import com.example.tutorial.i18n.TutorialCaption;
import com.example.tutorial.i18n.TutorialDescriptionKey;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import uk.q3c.krail.core.i18n.I18N;
import uk.q3c.krail.core.i18n.I18NProcessor;
import uk.q3c.krail.core.ui.form.BeanFieldGroupBase;
import uk.q3c.krail.core.validation.BeanValidator;
import uk.q3c.krail.option.Option;

import static com.example.tutorial.i18n.TutorialLabelKey.*;


@I18N
public class PersonForm extends BeanFieldGroupBase<Person> {
    @TutorialCaption(caption = Submit, description = TutorialDescriptionKey.Submit)
    private final Button submitButton;
    private final Person person;
    @TutorialCaption(caption = First_Name, description = TutorialDescriptionKey.Enter_your_first_name)
    private TextField firstName;

    @TutorialCaption(caption = Last_Name, description = TutorialDescriptionKey.Enter_your_last_name)
    private TextField lastName;
    @TutorialCaption(caption = Age, description = TutorialDescriptionKey.Age_of_the_Person)
    private TextField age;
    @TutorialCaption(caption = Person_Form, description = TutorialDescriptionKey.Person_Details_Form)
    private Panel layout;


    @Inject
    public PersonForm(I18NProcessor i18NProcessor, Provider<BeanValidator> beanValidatorProvider, Option option) {
        super(i18NProcessor, beanValidatorProvider, option);
        firstName = new TextField();
        lastName = new TextField();
        age = new TextField();


        person = new Person();
        person.setAge(44);
        person.setFirstName("Mango");
        person.setLastName("Chutney");
        submitButton = new Button();
        submitButton.addClickListener(event -> {
            try {
                this.commit();
            } catch (CommitException e) {
                e.printStackTrace();
            }
        });
        layout = new Panel(new VerticalLayout(firstName, lastName, age, submitButton));
        layout.setStyleName(ValoTheme.PANEL_WELL);
        setBean(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void optionValueChanged(Property.ValueChangeEvent event) {

    }

    public Panel getLayout() {
        return this.layout;
    }
}