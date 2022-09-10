package com.jacksafblaze.newshub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
class SourceDbDto(
                @PrimaryKey val id: String,
                val name: String?
)