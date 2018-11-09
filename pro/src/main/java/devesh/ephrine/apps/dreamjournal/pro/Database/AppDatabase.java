package devesh.ephrine.apps.dreamjournal.pro.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import devesh.ephrine.apps.dreamjournal.pro.Data.Dream;

@Database(entities = Dream.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DreamDao dreamDao();
}
