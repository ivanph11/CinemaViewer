package com.felz.cinemaviewer.network

import com.felz.cinemaviewer.model.Schedule
import com.felz.cinemaviewer.util.EntityMapper
import javax.inject.Inject

class ScheduleNetworkMapper
@Inject
constructor():EntityMapper<ScheduleNetworkEntity,Schedule>{
    override fun mapFromEntity(entity: ScheduleNetworkEntity): Schedule {
        return Schedule(
            dates = entity.dates,
            cinemas = entity.cinemas,
            times = entity.times
        )
    }

    override fun mapToEntity(domainModel: Schedule): ScheduleNetworkEntity {
        return ScheduleNetworkEntity(
            dates = domainModel.dates,
            cinemas = domainModel.cinemas,
            times = domainModel.times
        )
    }
}