package com.tungduong.test_firebase.BLL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tungduong.test_firebase.Entity.Account;
import com.tungduong.test_firebase.GUI.MainActivity;
import com.tungduong.test_firebase.R;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {
    private MainActivity main;
    private Context context;
    private List<Account> accountList;

    public MyAdaptor(MainActivity main, List<Account> accountList) {
        this.main = main;
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         Account account = accountList.get(position);
        holder.txt_passWord.setText(account.getPassWord());
        holder.txt_role.setText(account.getRole());
        holder.txt_userName.setText(account.getUserName());
        holder.txt_id.setText(account.getId());

        holder.itemView.setOnClickListener(v -> {
           main.SetText(
                   String.valueOf(account.getId()),
                   String.valueOf(account.getUserName()),
                   String.valueOf(account.getPassWord()),
                   String.valueOf(account.getRole())
           );
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView txt_userName,txt_passWord,txt_role,txt_id;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_userName = itemView.findViewById(R.id.txt_userName);
        txt_passWord = itemView.findViewById(R.id.txt_passWord);
        txt_role = itemView.findViewById(R.id.txt_role);
        txt_id = itemView.findViewById(R.id.edt_id);
    }
}