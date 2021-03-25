package com.example.sqlitetraining.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sqlitetraining.Model.SqliteModel;
import com.example.sqlitetraining.Repository.SqliteModelRepository;

import java.util.List;

public class SqliteViewModel extends AndroidViewModel {

    private SqliteModelRepository sqliteModelRepository;
    private LiveData<List<SqliteModel>> studentList;

    public SqliteViewModel(@NonNull Application application) {
        super(application);
        sqliteModelRepository = new SqliteModelRepository(application);
        studentList = sqliteModelRepository.getAllStudents();
    }

    public LiveData<List<SqliteModel>> getAllStudent(){
        return sqliteModelRepository.getAllStudents();
    }

    public void insert(SqliteModel sqliteModel){
        sqliteModelRepository.insertStudent(sqliteModel);
    }

    public void update(SqliteModel sqliteModel){
        sqliteModelRepository.updateStudent(sqliteModel);
    }

    public void delete(SqliteModel sqliteModel){
        sqliteModelRepository.deleteStudent(sqliteModel);
    }

}
