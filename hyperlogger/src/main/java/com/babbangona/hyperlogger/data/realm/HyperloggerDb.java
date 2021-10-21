package com.babbangona.hyperlogger.data.realm;

import com.babbangona.hyperlogger.data.realm.model.Logs;
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
    private Realm realm;

    public HyperloggerDb() {
        realmConfig = new RealmConfiguration.Builder()
                .name("hyperlogger.realm")
                .modules(new AllTablesModule())
                .build();
    }

    public void open(){
        // Don't use Realm.setDefaultInstance() in library projects. It is unsafe as app developers can override the
        // default configuration. So always use explicit configurations in library projects.
        realm = Realm.getInstance(realmConfig);
    }

    public void close(){
        realm.close();
    }

    public RealmResults<Logs> getUnSyncedLogs(){
        return realm.where(Logs.class).equalTo("sync_flag", 0).findAll();
    }

    public void addLogs(Logs log){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }
}
