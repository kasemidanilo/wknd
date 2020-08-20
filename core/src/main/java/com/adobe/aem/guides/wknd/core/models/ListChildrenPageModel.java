package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListChildrenPageModel {

    @Inject
    private String pathPage;

    @Inject
    private ResourceResolver resourceResolver;

    public List<ListPageChildren> getChildren() {
        ArrayList pageChildren=new ArrayList<>();
        Resource resource = this.resourceResolver.getResource(this.pathPage);


        Page parentPage=resource.adaptTo(Page.class);

        Iterator<Page> listChildPages=parentPage.listChildren();
        while (listChildPages.hasNext()){
            Page childPage=listChildPages.next();
            ListPageChildren children=new ListPageChildren();
            children.setTitle(childPage.getTitle());
            children.setPath(childPage.getPath());
            pageChildren.add(children);


        }
        return pageChildren;


    }
}
