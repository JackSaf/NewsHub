package com.jacksafblaze.newshub.data.network.networkmapper

import com.jacksafblaze.newshub.data.network.model.SourceNetworkEntity
import com.jacksafblaze.newshub.domain.model.Source
import com.jacksafblaze.newshub.util.EntityMapper

object SourceNetworkEntityMapper : EntityMapper<SourceNetworkEntity, Source> {
    override fun entityToDomainModel(entity: SourceNetworkEntity): Source =
        Source(entity.id ?: "", entity.name ?: "")

    override fun domainModelToEntity(domainModel: Source): SourceNetworkEntity =
        SourceNetworkEntity(domainModel.id, domainModel.name)

    override fun entityListToDomainModelList(entityList: List<SourceNetworkEntity>): List<Source> =
        entityList.map { entityToDomainModel(it) }

    override fun domainModelListToEntityList(domainModelList: List<Source>): List<SourceNetworkEntity> =
        domainModelList.map { domainModelToEntity(it) }
}