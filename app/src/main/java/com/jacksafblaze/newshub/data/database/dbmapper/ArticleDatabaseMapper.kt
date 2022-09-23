package com.jacksafblaze.newshub.data.database.dbmapper

import com.jacksafblaze.newshub.data.database.model.ArticleDbDto
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.util.EntityMapper

object ArticleDatabaseMapper :
    EntityMapper<ArticleDbDto, Article> {
    override fun entityToDomainModel(entity: ArticleDbDto): Article = Article(
        entity.author ?: "",
        entity.content ?: "",
        entity.description ?: "",
        entity.publishedAt ?: "",
        SourceDatabaseMapper.entityToDomainModel(entity.source),
        entity.title ?: "",
        entity.url,
        entity.urlToImage ?: "",
        true
    )

    override fun domainModelToEntity(domainModel: Article): ArticleDbDto = ArticleDbDto(
        domainModel.author,
        domainModel.content,
        domainModel.description,
        domainModel.publishedAt,
        SourceDatabaseMapper.domainModelToEntity(domainModel.source),
        domainModel.title,
        domainModel.url,
        domainModel.urlToImage
    )

    override fun entityListToDomainModelList(entityList: List<ArticleDbDto>): List<Article> =
        entityList.map { entityToDomainModel(it) }

    override fun domainModelListToEntityList(domainModelList: List<Article>): List<ArticleDbDto> =
        domainModelList.map { domainModelToEntity(it) }
}