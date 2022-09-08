package com.jacksafblaze.newshub.util

interface EntityMapper<Entity, DomainModel> {
    fun entityToDomainModel(entity: Entity) : DomainModel
    fun domainModelToEntity(domainModel: DomainModel): Entity
    fun entityListToDomainModelList(entityList: List<Entity>): List<DomainModel>
    fun domainModelListToEntityList(domainModelList: List<DomainModel>): List<Entity>

}