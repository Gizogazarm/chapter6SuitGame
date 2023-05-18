package id.rich.challengech5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, GameHistory::class], version = 1)

abstract class GameDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gameHistoryDao(): GameHistoryDao

    companion object{
        private var INSTANCE : GameDatabase? = null

        fun getInstance(context: Context): GameDatabase?{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, GameDatabase::class.java,"suit_game.db").build()
                }
            }
            return INSTANCE
        }

    }

    fun destroyInstance(){
        INSTANCE = null
    }

}