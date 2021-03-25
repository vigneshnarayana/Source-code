package com.example.mvvmdemo.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.model.Users;

import java.util.List;

public class UsersAdapter  extends RecyclerView.Adapter<UsersAdapter.UserAdaptorVH> {
   private List<Users> userList;
   private Context context;
   private ClickListener clickListener;

    public UsersAdapter(List<Users> userList) {
        this.userList=userList;
        notifyDataSetChanged();
    }
    public void setData(List<Users> userList) {
        this.userList=userList;
        notifyDataSetChanged();
    }


    public UsersAdapter(ClickListener clickListener) {
        this.clickListener=clickListener;
    }

    @NonNull
    @Override
    public UserAdaptorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context=parent.getContext();
        return new UserAdaptorVH(
                LayoutInflater.from(context).inflate(R.layout.row_iteam,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdaptorVH holder, int position) {
       Users users=userList.get(position);
        String username= users.getUsername();

       holder.tv_name.setText(username);

       holder.imageOptions.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showPopUp(v,users);

           }
       });

    }

    public  void  showPopUp(View view,Users users){
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
              int id=item.getItemId();
              switch (id){
                  case R.id.itupdate:
                      clickListener.updateClicked(users);
                      break;
                  case R.id.itdelete:
                      clickListener.deleteClicked(users);
                      break;
              }

                return false;
            }
        });

        popupMenu.show();
    }
  public interface  ClickListener{
        void updateClicked(Users users);
        void deleteClicked(Users users);
  }
    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class UserAdaptorVH extends RecyclerView.ViewHolder {
        ImageView imageOptions;
        TextView tv_name;
        public UserAdaptorVH(@NonNull View itemView) {
            super(itemView);
            imageOptions=itemView.findViewById(R.id.imageOption);
            tv_name=itemView.findViewById(R.id.tv_new);
        }
    }
}
