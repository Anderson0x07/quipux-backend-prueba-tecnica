package com.quipux.infraestructure.adapters.in.rest.mappers;

import com.quipux.domain.Playlist;
import com.quipux.infraestructure.adapters.in.rest.controller.request.PlaylistRequest;
import com.quipux.infraestructure.adapters.in.rest.controller.response.DescriptionResponse;
import com.quipux.infraestructure.adapters.in.rest.controller.response.PlaylistResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistRestMapper {
    PlaylistResponse domainToResponse(Playlist domain);
    DescriptionResponse domainToResponseDescription(Playlist domain);
    List<PlaylistResponse> domainsToResponses(List<Playlist> domain);
    Playlist requestToDomain(PlaylistRequest request);
}
