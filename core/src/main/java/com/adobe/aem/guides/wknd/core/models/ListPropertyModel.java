package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListPropertyModel {
    @Inject
    private String value;

    @Inject
    private String rank;

    public String getValue() {
        return value;
    }

    public String getRank() {
        return rank;
    }


}
