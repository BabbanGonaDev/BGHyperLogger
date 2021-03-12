package com.babbangona.hyperlogger.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.babbangona.hyperlogger.data.room.dao.GeneralLogsDao;
import com.babbangona.hyperlogger.data.room.dao.LogsDao;
import com.babbangona.hyperlogger.data.room.entities.GeneralLogsTable;
import com.babbangona.hyperlogger.data.room.entities.LogsTable;

@Database(entities = {LogsTable.class, GeneralLogsTable.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract LogsDao getLogsDao();

    public abstract GeneralLogsDao getGeneralLogsDao();

    /**
     * Return instance of database creation
     **/
    public static AppDatabase getInstance(Context context) {
        if (null == appDatabase) {
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(
                context, AppDatabase.class, "hyper_logger.db")
                .allowMainThreadQueries()
                .build();
    }

    public void cleanUp() {
        appDatabase = null;
    }
}
