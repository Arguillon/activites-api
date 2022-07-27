package com.activites.api.mapper;

import com.activites.api.data.entity.ActiviteSurDemande;
import com.activites.api.data.entity.JoursOuvertureActiviteSurDemande;
import com.activites.api.model.ActiviteSurDemandeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ActiviteSurDemandeModelMapper {
    @Mapping(source = "activite", target = "joursOuverture", qualifiedByName = "joursOuvertureToModel")
    ActiviteSurDemandeModel toDestination(ActiviteSurDemande activite);

    @Mapping(target = "joursOuverture", ignore = true)
    ActiviteSurDemande toSource(ActiviteSurDemandeModel activite);


    @Named("joursOuvertureToModel")
    default List<DayOfWeek> locationToLocationDto(ActiviteSurDemande activite) {
        List<DayOfWeek> dates = new ArrayList<>();
        if (activite.getJoursOuverture() != null && !activite.getJoursOuverture().isEmpty()) {
            dates.addAll(activite.getJoursOuverture()
                    .stream()
                    .map(JoursOuvertureActiviteSurDemande::getJourOuverture).toList());
        }
        return dates;
    }
}
