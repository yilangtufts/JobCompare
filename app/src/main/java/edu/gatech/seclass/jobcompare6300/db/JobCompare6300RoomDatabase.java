package edu.gatech.seclass.jobcompare6300.db;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.gatech.seclass.jobcompare6300.db.dao.ComparisonDAO;
import edu.gatech.seclass.jobcompare6300.db.dao.JobDAO;
import edu.gatech.seclass.jobcompare6300.db.entity.Comparison;
import edu.gatech.seclass.jobcompare6300.db.entity.Job;

@Database(entities = {Job.class, Comparison.class}, version = 1, exportSchema = false)
public abstract class JobCompare6300RoomDatabase extends RoomDatabase {

    public abstract JobDAO jobDao();
    public abstract ComparisonDAO comparisonDAO();

    private static volatile JobCompare6300RoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static JobCompare6300RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (JobCompare6300RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JobCompare6300RoomDatabase.class, "jobCompare6300_database.db")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                JobDAO jobDao = INSTANCE.jobDao();
                ComparisonDAO comparisonDAO = INSTANCE.comparisonDAO();
                jobDao.deleteAll();
                comparisonDAO.deleteAll();

                //Init all weights to 1
                Comparison comparison = new Comparison(1,1,1,1,1);
                comparisonDAO.addComparison(comparison);
            });
        }
    };

}
