package com.activites.api.mapper;

import com.activites.api.dto.ActiviteSurDemandeDto;
import com.activites.api.model.ActiviteSurDemandeModel;
import org.mapstruct.Mapper;

@Mapper
public interface ActiviteSurDemandeDtoMapper {
    ActiviteSurDemandeDto toDestination(ActiviteSurDemandeModel activite);

    ActiviteSurDemandeModel toSource(ActiviteSurDemandeDto activite);

}
