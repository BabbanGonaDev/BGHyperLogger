package com.babbangona.hyperlogger.data.realm;

import android.content.Context;

import com.babbangona.hyperlogger.data.realm.model.Logs;
import com.babbangona.hyperlogger.data.realm.model.Sessions;
import com.babbangona.hyperlogger.data.realm.module.AllTablesModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Library projects can also use Realms, but some configuration options are mandatory to avoid clashing with Realms used
 * in the app code.
 */
public class HyperloggerDb {

    private final RealmConfiguration realmConfig;
    private static Realm realm;

    public HyperloggerDb(Context context) {
        Realm.init(context);

        realmConfig = new RealmConfiguration.Builder()
                .name("hyperlogger.realm")
                .modules(new AllTablesModule())
                .build();
    }

    public void open() {
        // Don't use Realm.setDefaultInstance() in library projects. It is unsafe as app developers can override the
        // default configuration. So always use explicit configurations in library projects.

        if (realm == null) {
            realm = Realm.getInstance(realmConfig);
        }

    }

    public void close() {
        realm.close();
    }

    public RealmResults<Logs> getUnSyncedLogs() {
        return realm.where(Logs.class).equalTo("sync_flag", 0).findAllAsync();
    }

    public RealmResults<Sessions> getUnSyncedSessions() {
        return realm.where(Sessions.class).equalTo("sync_flag", 0).findAllAsync();
    }

    public void addLogs(Logs userLog) {
        realm.executeTransactionAsync(realm -> {

            realm.copyToRealm(userLog);

        });
    }
}
