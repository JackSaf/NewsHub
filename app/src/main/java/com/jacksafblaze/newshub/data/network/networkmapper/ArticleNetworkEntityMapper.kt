package com.jacksafblaze.newshub.data.network.networkmapper

import com.jacksafblaze.newshub.data.network.model.ArticleNetworkEntity
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.util.EntityMapper

class ArticleNetworkEntityMapper(private val sourceNetworkEntityMapper: SourceNetworkEntityMapper): EntityMapper<ArticleNetworkEntity, Article> {
    override fun entityToDomainModel(entity: ArticleNetworkEntity): Article = Article(entity.author,
        entity.content,
        entity.description,
        entity.publishedAt,
        sourceNetworkEntityMapper.entityToDomainModel(entity.sourceNetworkEntity),
        entity.title,
        entity.url,
        entity.urlToImage,
        false
    )

    override fun domainModelToEntity(domainModel: Article): ArticleNetworkEntity = ArticleNetworkEntity(domainModel.author,
        domainModel.content,
        domainModel.description,
        domainModel.publishedAt,
        sourceNetworkEntityMapper.domainModelToEntity(domainModel.source),
        domainModel.title,
        domainModel.url,
        domainModel.urlToImage)

    override fun entityListToDomainModelList(entityList: List<ArticleNetworkEntity>): List<Article> = entityList.map { entityToDomainModel(it) }

    override fun domainModelListToEntityList(domainModelList: List<Article>): List<ArticleNetworkEntity> = domainModelList.map { domainModelToEntity(it) }
}