package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Model(adaptables = Resource.class)
public class Navigator {
    @Inject
    @Optional
    private List<TestModel> items1;

    public List<TestModel> getItems1 (){
        Collections.sort(items1, TestModel::compareToLinkUrl);
        return items1;
    }

}