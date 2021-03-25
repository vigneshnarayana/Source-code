package com.example.mvvmpartenapplication.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;

import com.example.mvvmpartenapplication.Dao.DataBase;
import com.example.mvvmpartenapplication.Dao.EmployeeDao;
import com.example.mvvmpartenapplication.Model.Employee;
import com.example.mvvmpartenapplication.Service.EmployeeService;
import com.example.mvvmpartenapplication.Service.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {
    private DataBase dataBase;

    private EmployeeDao employeeDao;
    private EmployeeService employeeService;

    public EmployeeRepository(Application application) {

      dataBase=DataBase.getDataBase(application);
      employeeDao=dataBase.employeeDao();
      employeeService = RetrofitClientInstance.getClient().create(EmployeeService.class);
    }

    //////////////////////Room/////////////////////
    public void insertdata(Employee employee){
        new insertdata(employeeDao).execute(employee);
    }

    private static  class insertdata extends AsyncTask<Employee,Void,Void>{
        private EmployeeDao employeeDao;

        public insertdata(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }


        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.insertdata(employees[0]);
            return null;
        }
    }



    /////////////////////////////////////////////////service////////////////

    public  void getemployeeservicedetails(){
        Call<List<Employee>> call=employeeService.getemployeeservicedetails();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                List<Employee> employees=response.body();

                  for(Employee i:employees){

                      Employee employee=new Employee(i.getEmpID(),
                              i.getEmpName(),
                              i.getPassword(),
                              i.getLocation());
                      insertdata(employee);


                      Log.d("data", i.getEmpID());

                    }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });
    }


}
