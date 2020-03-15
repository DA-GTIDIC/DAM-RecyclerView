package cat.udl.tidic.amd.dam_recyclerview.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import cat.udl.tidic.amd.dam_recyclerview.dao.EventDAOI;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase instance;

    public abstract EventDAOI eventDAO();

    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class, "event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDAOI eventDAO;

        private PopulateDbAsyncTask(EventDatabase db) {
            eventDAO = db.eventDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Event e1 = new Event (1, "20/01/2020", "22/01/2020",
                    "Event 0", "Wow this is event 0", (float) 3.0);

            Event e2 = new Event(1, "21/01/2020", "22/01/2020",
                    "Event 1", "Wow this is event 1",(float) 4.0);
            Event e3 = new Event( 1, "22/01/2020", "24/01/2020",
                    "Event 2", "Wow this is event 2",(float) 1.0);
            Event e4 = new Event(1, "23/01/2020", "25/01/2020",
                    "Event 3", "Wow this is event 3",(float) 2.0);
            Event e5 = new Event( 2, "20/01/2020", "22/01/2020",
                    "Event 4", "Wow this is event 4",(float) 4.0);
            Event e6 = new Event(2, "21/01/2020", "22/01/2020",
                    "Event 5", "Wow this is event 5",(float) 5.0);

            eventDAO.insert(e1);
            eventDAO.insert(e2);
            eventDAO.insert(e3);
            eventDAO.insert(e4);
            eventDAO.insert(e5);
            eventDAO.insert(e6);
            return null;
        }
    }
}