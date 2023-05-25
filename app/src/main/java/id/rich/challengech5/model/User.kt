package id.rich.challengech5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey var username: String,
    @ColumnInfo("name") var name: String,
    @ColumnInfo("password") var password: String,
    @ColumnInfo("gender") var gender: Gender
)

enum class Gender {FEMALE, MALE}