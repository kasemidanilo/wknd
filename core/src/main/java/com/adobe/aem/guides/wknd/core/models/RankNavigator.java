package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Model(adaptables = Resource.class)
public class RankNavigator {
    @Inject
    @Optional
    private List<RatingModel> ratingModelList;


    public List<RatingModel> getRatingModelList (){
        Collections.sort(ratingModelList, RatingModel::compareRank);
        return ratingModelList;
    }
}
