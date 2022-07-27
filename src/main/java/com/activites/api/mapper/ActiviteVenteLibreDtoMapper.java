package com.activites.api.mapper;

import com.activites.api.dto.ActiviteVenteLibreDto;
import com.activites.api.model.ActiviteVenteLibreModel;
import org.mapstruct.Mapper;

@Mapper
public interface ActiviteVenteLibreDtoMapper {
    ActiviteVenteLibreDto toDestination(ActiviteVenteLibreModel activite);

    ActiviteVenteLibreModel toSource(ActiviteVenteLibreDto activite);

}
