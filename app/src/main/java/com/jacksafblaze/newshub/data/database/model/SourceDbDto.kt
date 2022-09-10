package com.jacksafblaze.newshub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SourceDbDto(
                @PrimaryKey(autoGenerate = true) val uid: Int,
                val id: String?,
                val name: String?
)