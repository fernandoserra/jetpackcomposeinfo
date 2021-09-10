package com.example.jetpackcomposeinfo.data.local.team

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "team_local", indices = [Index(value = ["id"], unique = true)])
data class TeamLocal(
    @PrimaryKey(autoGenerate = true) val id_team_images: Int,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "abbreviation") val abbreviation: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "conference") val conference: String?,
    @ColumnInfo(name = "division") val division: String?,
    @ColumnInfo(name = "full_name") val full_name: String?,
    @ColumnInfo(name = "name") val name: String?

): Parcelable