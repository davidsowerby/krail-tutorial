package com.example.tutorial.form;

import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
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
import uk.q3c.krail.core.option.Option;
import uk.q3c.krail.core.ui.form.BeanFieldGroupBase;
import uk.q3c.krail.core.validation.BeanValidator;

import static com.example.tutorial.i18n.LabelKey.*;


@I18N
public class PersonForm extends BeanFieldGroupBase<Person> {
    @Caption(caption = Submit, description = DescriptionKey.Submit)
    private final Button submitButton;
    private final Person person;
    @Caption(caption = First_Name, description = DescriptionKey.Enter_your_first_name)
    private TextField firstName;

    @Caption(caption = Last_Name, description = DescriptionKey.Enter_your_last_name)
    private TextField lastName;
    @Caption(caption = Age, description = DescriptionKey.Age_of_the_Person)
    private TextField age;
    @Caption(caption = Person_Form, description = DescriptionKey.Person_Details_Form)
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
