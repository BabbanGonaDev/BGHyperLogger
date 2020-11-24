package com.example.hyperlogger.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hyperlogger.Database.Dao.AppLogsDao;
import com.example.hyperlogger.Database.Dao.HyperLoggerDao;
import com.example.hyperlogger.Database.Entities.AppLogs;
import com.example.hyperlogger.Database.Entities.HyperLoggerTable;


@Database(entities = {HyperLoggerTable.class, AppLogs.class},
        version = DatabaseStringConstants.MS_PLAYBOOK_DATABASE_VERSION, exportSchema = false)


public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase appDatabase;
    public abstract HyperLoggerDao hyperLoggerDao();
    public abstract AppLogsDao appLogsDao();

    /**
     * Return instance of database creation
     * */
    public static AppDatabase getInstance(Context context) {
        if (null == appDatabase) {
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(
                context, AppDatabase.class, DatabaseStringConstants.MS_PLAYBOOK_DATABASE_NAME)
                .allowMainThreadQueries()
                //.addMigrations(MIGRATION_1_2)
                //.fallbackToDestructiveMigration()
                .build();
    }

    public void cleanUp() {
        appDatabase = null;
    }
}
