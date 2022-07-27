package com.activites.api.mapper;

import com.activites.api.data.entity.ActiviteVenteLibre;
import com.activites.api.data.entity.JoursOuvertureActiviteVenteLibre;
import com.activites.api.model.ActiviteVenteLibreModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ActiviteVenteLibreModelMapper {

    @Mapping(source = "activite", target = "joursOuverture", qualifiedByName = "joursOuvertureToModel")
    ActiviteVenteLibreModel toDestination(ActiviteVenteLibre activite);

    @Mapping(target = "joursOuverture", ignore = true)
    ActiviteVenteLibre toSource(ActiviteVenteLibreModel activite);


    @Named("joursOuvertureToModel")
    default List<DayOfWeek> locationToLocationDto(ActiviteVenteLibre activite) {
        List<DayOfWeek> dates = new ArrayList<>();
        if (activite.getJoursOuverture() != null && !activite.getJoursOuverture().isEmpty()) {
            dates.addAll(activite.getJoursOuverture()
                    .stream()
                    .map(JoursOuvertureActiviteVenteLibre::getJourOuverture).toList());
        }
        return dates;
    }
}
