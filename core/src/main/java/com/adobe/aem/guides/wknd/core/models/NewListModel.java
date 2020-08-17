package com.adobe.aem.guides.wknd.core.models;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewListModel {

    @Inject
    @Optional
    private Resource myList;



    public Resource getMyList()
    {
        return myList;
    }


    Comparator<ListPropertyModel> compareByRank = new Comparator<ListPropertyModel>() {
        @Override
        public int compare(ListPropertyModel o1, ListPropertyModel o2) {
            return o1.getRank().compareTo(o2.getRank());
        }
    };

    public ArrayList<ListPropertyModel> getSorted() {
        ArrayList sorted= new ArrayList();
        Iterator<Resource> children = myList.listChildren();
        while (children.hasNext()) {
            Resource child=children.next();
            ListPropertyModel model = child.adaptTo(ListPropertyModel.class);
            sorted.add(model);
        }

        Collections.sort(sorted,compareByRank);
        System.out.println(sorted);
        return sorted;

    }
}
