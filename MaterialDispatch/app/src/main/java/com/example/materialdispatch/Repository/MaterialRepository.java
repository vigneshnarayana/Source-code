package com.example.materialdispatch.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.materialdispatch.Dao.DataBase;
import com.example.materialdispatch.Dao.MaterialDao;
import com.example.materialdispatch.Model.Material;

import java.util.List;

public class MaterialRepository {
    private DataBase dataBase;
    private MaterialDao materialDao;
    LiveData<List<Material>> getMaterialData;

    public MaterialRepository(Application application) {

        dataBase= DataBase.getDataBase(application);
        materialDao=dataBase.materialDao();
        getMaterialData=materialDao.getMaterialData();
    }

    public LiveData<List<Material>> getMaterialData(){
        return dataBase.materialDao().getMaterialData();
    }
    public LiveData<List<Material>> getMaterialDataSa(String customer){
        return materialDao.getMaterialDataSa(customer);
    }

    public void  insertMaterial(Material material){
        new insertMaterial(materialDao).execute(material);
    }
    private  static class insertMaterial extends AsyncTask<Material,Void,Void>{
        private MaterialDao materialDao;

        public insertMaterial(MaterialDao materialDao) {
            this.materialDao = materialDao;
        }

        @Override
        protected Void doInBackground(Material... materials) {
            materialDao.insertMaterial(materials[0]);
            return null;
        }
    }
}
