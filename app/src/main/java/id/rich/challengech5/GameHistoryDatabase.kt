package id.rich.challengech5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameHistory::class], version = 1)
abstract class GameHistoryDatabase:RoomDatabase() {
    abstract fun gameHistoryDao(): GameHistoryDao

    companion object{
        private var INSTANCE: GameHistoryDatabase? = null

        fun getInstance(context: Context): GameHistoryDatabase?{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, GameHistoryDatabase::class.java,"GameHistory.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
           INSTANCE = null
        }
    }
}