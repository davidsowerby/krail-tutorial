package com.example.tutorial.uac;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

public class TrivialCredentialsStore {
    private Map<String, TrivialUserAccount> store = new HashMap<>();

    @Inject
    protected TrivialCredentialsStore() {
        addAccount("eq", "eq", "page:view:private:*", "option:edit:SimpleUserHierarchy:eq:0:*:*", "pay:request-increase");
        addAccount("fb", "fb", "page:view:private:*", "page:view:finance:*", "option:edit:SimpleUserHierarchy:fb:0:*:*");
        addAccount("admin", "password", "page:view:*", "option:edit:*");
    }

    public TrivialCredentialsStore addAccount(String userId, String password, String... permissions) {
        store.put(userId, new TrivialUserAccount(userId, password, permissions));
        return this;
    }


    public TrivialUserAccount getAccount(String principal) {
        return store.get(principal);
    }
}