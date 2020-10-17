package com.felz.cinemaviewer.network

import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.util.EntityMapper
import javax.inject.Inject

class MovieNetworkMapper
@Inject
constructor(): EntityMapper<MovieNetworkEntity,Movie>{
    override fun mapFromEntity(entity: MovieNetworkEntity):Movie{
        return Movie(
            id=entity.id,
            poster = entity.poster,
            title = entity.title,
            genre=entity.genre,
            advisory = entity.advisory,
            posterLandscape = entity.posterLandscape,
            releaseDate = entity.releaseDate,
            runtime = entity.runtime,
            synopsis = entity.synopsis,
            variants = entity.variants,
            theater = entity.theater,
            cast = entity.cast

        )
    }
    override fun mapToEntity(domainModel: Movie):MovieNetworkEntity{
        return MovieNetworkEntity(
            id=domainModel.id,
            poster = domainModel.poster,
            title = domainModel.title,
            genre=domainModel.genre,
            advisory = domainModel.advisory,
            posterLandscape = domainModel.posterLandscape,
            releaseDate = domainModel.releaseDate,
            runtime = domainModel.runtime,
            synopsis = domainModel.synopsis,
            variants = domainModel.variants,
            theater = domainModel.theater,
            cast = domainModel.cast
        )
    }
}