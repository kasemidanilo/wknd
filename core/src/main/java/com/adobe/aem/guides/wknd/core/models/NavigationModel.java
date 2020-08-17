package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Model(adaptables = Resource.class)
public class NavigationModel {

    @Inject
    @Optional
    public String linkText;

    @Inject
    @Optional
    private List<NavigationModel> menuItems;

    public int compare(NavigationModel cmp) {
        return this.linkText.compareToIgnoreCase(cmp.linkText);
    }

    public List<NavigationModel> getMenuItems (){
        Collections.sort(menuItems, NavigationModel::compare);
        return menuItems;
    }
}
