package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;


@Model(adaptables = Resource.class)
public class TestModel {
    @Inject
    @Optional
    public String linkUrl;

    @Inject
    @Optional
    public String linkText;

    @Inject
    @Optional
    public String linkClass;



    public int compareToLinkUrl(TestModel o) {
        return this.linkText.toLowerCase().compareTo(o.linkText.toLowerCase());
    }
}