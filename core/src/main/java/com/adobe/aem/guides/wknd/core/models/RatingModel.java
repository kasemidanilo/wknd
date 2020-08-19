package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class RatingModel {
    @Inject
    @Optional
    public int Rank;

    @Inject
    @Optional
    public String ValueRank;


    public int compareRank(RatingModel model) {
        return Rank- model.Rank;
    }
}
