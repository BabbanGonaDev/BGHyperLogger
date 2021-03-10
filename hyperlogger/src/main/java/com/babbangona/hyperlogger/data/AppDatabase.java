package com.babbangona.hyperlogger.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.babbangona.hyperlogger.data.room.dao.GeneralLogsDao;
import com.babbangona.hyperlogger.data.room.dao.LogsDao;
import com.babbangona.hyperlogger.data.room.entities.GeneralLogsTable;
import com.babbangona.hyperlogger.data.room.entities.LogsTable;

@Database(entities = {LogsTable.class, GeneralLogsTable.class}, version = 2, exportSchema = false)
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

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE logs_table ADD COLUMN 'ram_utilization' TEXT DEFAULT ''");
            database.execSQL("ALTER TABLE logs_table ADD COLUMN 'memory_usage' TEXT DEFAULT ''");
        }
    };

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(
                context, AppDatabase.class, "hyper_logger.db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    public void cleanUp() {
        appDatabase = null;
    }
}
