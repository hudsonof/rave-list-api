package br.com.ravelist.payload.mapper;

import br.com.ravelist.model.*;
import br.com.ravelist.payload.response.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

import java.util.*;

@Mapper
public interface RaveMapper {
    RaveMapper MAPPER = Mappers.getMapper(RaveMapper.class);

    RaveResponse toResponse(Rave rave);
    Rave toModel(RaveResponse raveResponse);

    List<RaveResponse> toListResponse(List<Rave> raves);
}
