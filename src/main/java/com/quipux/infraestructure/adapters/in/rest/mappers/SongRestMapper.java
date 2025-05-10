package com.quipux.infraestructure.adapters.in.rest.mappers;

import com.quipux.domain.Song;
import com.quipux.infraestructure.adapters.in.rest.controller.request.SongRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.SongResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongRestMapper {

    SongResponse domainToResponse(Song domain);
    List<SongResponse> domainsToResponses(List<Song> domain);
    Song requestToDomain(SongRequest request);
}
