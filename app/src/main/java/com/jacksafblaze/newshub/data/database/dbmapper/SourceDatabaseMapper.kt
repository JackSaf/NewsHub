package com.jacksafblaze.newshub.data.database.dbmapper

import com.jacksafblaze.newshub.data.database.model.SourceDbDto
import com.jacksafblaze.newshub.domain.model.Source
import com.jacksafblaze.newshub.util.EntityMapper

object SourceDatabaseMapper : EntityMapper<SourceDbDto, Source> {
    override fun entityToDomainModel(entity: SourceDbDto): Source =
        Source(entity.id ?: "", entity.name ?: "")

    override fun domainModelToEntity(domainModel: Source): SourceDbDto =
        SourceDbDto(domainModel.id, domainModel.name)

    override fun entityListToDomainModelList(entityList: List<SourceDbDto>): List<Source> =
        entityList.map { entityToDomainModel(it) }

    override fun domainModelListToEntityList(domainModelList: List<Source>): List<SourceDbDto> =
        domainModelList.map { domainModelToEntity(it) }
}