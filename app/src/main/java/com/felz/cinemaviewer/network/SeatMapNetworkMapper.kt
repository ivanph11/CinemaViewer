package com.felz.cinemaviewer.network

import com.felz.cinemaviewer.model.SeatMap
import com.felz.cinemaviewer.util.EntityMapper
import javax.inject.Inject

class SeatMapNetworkMapper
@Inject
constructor():EntityMapper<SeatMapNetworkEntity,SeatMap>{
    override fun mapFromEntity(entity: SeatMapNetworkEntity): SeatMap {
        return SeatMap(
            seatMap = entity.seatMap
        )
    }

    override fun mapToEntity(domainModel: SeatMap): SeatMapNetworkEntity {
        return SeatMapNetworkEntity(
            seatMap = domainModel.seatMap
        )
    }
}