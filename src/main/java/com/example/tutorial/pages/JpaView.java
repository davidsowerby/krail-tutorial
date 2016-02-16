package com.example.tutorial.pages;

import com.example.tutorial.form.Person;
import com.example.tutorial.i18n.Caption;
import com.example.tutorial.i18n.DescriptionKey;
import com.example.tutorial.i18n.LabelKey;
import com.example.tutorial.jpa.DerbyJpa;
import com.example.tutorial.jpa.HsqlJpa;
import com.google.inject.Inject;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.*;
import org.apache.onami.persist.EntityManagerProvider;
import org.apache.onami.persist.Transactional;
import uk.q3c.krail.core.i18n.Translate;
import uk.q3c.krail.core.persist.cache.i18n.PatternCacheKey;
import uk.q3c.krail.core.persist.common.common.ContainerType;
import uk.q3c.krail.core.persist.common.i18n.PatternDao;
import uk.q3c.krail.core.view.ViewBase;
import uk.q3c.krail.core.view.component.ViewChangeBusMessage;
import uk.q3c.krail.persist.jpa.common.JpaContainerProvider;
import uk.q3c.krail.persist.jpa.common.JpaDao_LongInt;

import javax.persistence.EntityManager;
import java.util.Locale;
import java.util.Random;

public class JpaView extends ViewBase {

    private JpaContainerProvider derbyContainerProvider;
    private JpaContainerProvider hsqlContainerProvider;
    private EntityManagerProvider derbyEntityManagerProvider;
    private EntityManagerProvider hsqlEntityManagerProvider;
    private JpaDao_LongInt derbyDao;
    private JpaDao_LongInt hsqlDao;
    private PatternDao patternDao;
    @Caption(caption = LabelKey.Derby_Table, description = DescriptionKey.Table_connected_to_DerbyDb)
    private Table derbyTable;
    @Caption(caption = LabelKey.HSQL_Table, description = DescriptionKey.Table_connected_to_HsqlDb)
    private Table hsqlTable;
    private JPAContainer<Person> derbyContainer;
    private JPAContainer<Person> hsqlContainer;
    @Caption(caption = LabelKey.Add_with_entity_manager, description = DescriptionKey.Add_with_entity_manager)
    private Button derbyEntityMgrButton;
    @Caption(caption = LabelKey.Add_with_entity_manager, description = DescriptionKey.Add_with_entity_manager)
    private Button hsqlEntityMgrButton;
    @Caption(caption = LabelKey.Add_with_DAO, description = DescriptionKey.Add_with_DAO)
    private Button derbyDaoButton;
    @Caption(caption = LabelKey.Add_with_DAO, description = DescriptionKey.Add_with_DAO)
    private Button hsqlDaoButton;
    @Caption(caption = LabelKey.Insert_Pattern_value, description = DescriptionKey.Insert_Pattern_value)
    private Button derbyPatternButton;

    @Inject
    protected JpaView(Translate translate, @DerbyJpa JpaContainerProvider derbyContainerProvider, @HsqlJpa JpaContainerProvider hsqlContainerProvider,
                      @DerbyJpa EntityManagerProvider derbyEntityManagerProvider, @HsqlJpa EntityManagerProvider hsqlEntityManagerProvider, @DerbyJpa
                      JpaDao_LongInt derbyDao, @HsqlJpa JpaDao_LongInt hsqlDao, @DerbyJpa PatternDao patternDao) {
        super(translate);
        this.derbyContainerProvider = derbyContainerProvider;
        this.hsqlContainerProvider = hsqlContainerProvider;
        this.derbyEntityManagerProvider = derbyEntityManagerProvider;
        this.hsqlEntityManagerProvider = hsqlEntityManagerProvider;
        this.derbyDao = derbyDao;
        this.hsqlDao = hsqlDao;
        this.patternDao = patternDao;
    }

    @Override
    protected void doBuild(ViewChangeBusMessage busMessage) {
        derbyContainer = derbyContainerProvider.get(Person.class, ContainerType.CACHED);
        hsqlContainer = hsqlContainerProvider.get(Person.class, ContainerType.CACHED);
        derbyTable = new Table("", derbyContainer);
        hsqlTable = new Table("", hsqlContainer);

        derbyEntityMgrButton = new Button();
        derbyEntityMgrButton.addClickListener(event -> {
            addWithEntityMgr(derbyEntityManagerProvider);
            derbyContainer.refresh();
        });
        hsqlEntityMgrButton = new Button();
        hsqlEntityMgrButton.addClickListener(event -> {
            addWithEntityMgr(hsqlEntityManagerProvider);
            hsqlContainer.refresh();
        });

        //add with Dao
        derbyDaoButton = new Button();
        derbyDaoButton.addClickListener(event -> {
            derbyDao.save(createPerson());
            derbyContainer.refresh();
        });
        hsqlDaoButton = new Button();
        hsqlDaoButton.addClickListener(event -> {
            hsqlDao.save(createPerson());
            hsqlContainer.refresh();
        });

        derbyPatternButton = new Button();
        derbyPatternButton.addClickListener(event -> {
            patternDao.write(new PatternCacheKey(LabelKey.Derby_Table, Locale.GERMANY), "Tafel aus Derby");
        });


        VerticalLayout derbyLayout = new VerticalLayout(derbyTable, derbyEntityMgrButton, derbyDaoButton, derbyPatternButton);
        VerticalLayout hsqlLayout = new VerticalLayout(hsqlTable, hsqlEntityMgrButton, hsqlDaoButton);

        HorizontalLayout horizontalLayout = new HorizontalLayout(derbyLayout, hsqlLayout);
        Panel panel = new Panel();
        panel.setContent(horizontalLayout);
        setRootComponent(panel);
    }

    private Person createPerson() {
        Person p = new Person();
        int i = new Random().nextInt(5000);
        p.setAge(i % 80);
        p.setFirstName("First name " + i);
        p.setLastName("Last name " + i);
        return p;
    }

    @Transactional
    protected void addWithEntityMgr(EntityManagerProvider entityManagerProvider) {
        final EntityManager entityManager = entityManagerProvider.get();
        entityManager.persist(createPerson());
    }
}
