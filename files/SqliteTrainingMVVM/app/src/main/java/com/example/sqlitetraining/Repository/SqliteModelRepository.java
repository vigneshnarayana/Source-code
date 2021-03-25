package com.example.sqlitetraining.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sqlitetraining.Dao.Database;
import com.example.sqlitetraining.Dao.SqliteModelDao;
import com.example.sqlitetraining.Model.SqliteModel;

import java.util.List;

public class SqliteModelRepository {

    private Database database;
    private SqliteModelDao sqliteModelDao;
    private LiveData<List<SqliteModel>>  studentList;

    public SqliteModelRepository(Application application) {
        database =Database.getDataBase(application);
        sqliteModelDao=database.sqliteModelDao();
        studentList=sqliteModelDao.getAllStudents();
    }

    public LiveData<List<SqliteModel>> getAllStudents(){
        return database.sqliteModelDao().getAllStudents();
    }

    public void insertStudent(final SqliteModel sqliteModel)
    {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.sqliteModelDao().Insert(sqliteModel);
                return null;
            }
        }.execute();
    }

    public void updateStudent(final SqliteModel sqliteModel)
    {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.sqliteModelDao().Update(sqliteModel);
                return null;
            }
        }.execute();
    }

    public void deleteStudent(final SqliteModel sqliteModel)
    {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.sqliteModelDao().Delete(sqliteModel);
                return null;
            }
        }.execute();
    }




}
