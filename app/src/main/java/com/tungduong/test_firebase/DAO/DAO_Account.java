package com.tungduong.test_firebase.DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tungduong.test_firebase.Entity.Account;

import java.util.ArrayList;
import java.util.List;

public class DAO_Account {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public interface AccountCallback {
        void onCallback(List<Account> accounts);
    }

    public interface IdLoadCallback {
        void onIdLoad(List<String> idAccount);
    }

    public void showAccount(final AccountCallback callback) {
        List<Account> list = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Account account = dataSnapshot.getValue(Account.class);
                    String id = dataSnapshot.getKey();
                    if (account != null) {
                        account.setId(id);
                        list.add(account);
                    }
                }
                callback.onCallback(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DAL_Account", "Bug database ShowAllAccount: " + error.getMessage());
            }
        });
    }

    public void AddAccount(Account account) {
        String id =  account.getId();
        databaseReference = firebaseDatabase.getReference("Account");
        databaseReference.child(id).setValue(account).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("DAO_Account", "Thêm tài khoản thành công");
            } else {
                Log.e("DAO_Account", "Thêm tài khoản thất bại", task.getException());
            }
        });
    }

    public void DeleteAccount(String id){
        databaseReference = firebaseDatabase.getReference("Account");
        databaseReference.child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("DAO_Account", "Xóa tài khoản thành công");
            } else {
                Log.e("DAO_Account", "Xóa tài khoản thất bại", task.getException());
            }
        });
    }

    public void UpdateAccount(String id, String userName, String passWord,String role){
        Account account = new Account();
        account.setId(id);
        account.setUserName(userName);
        account.setPassWord(passWord);
        account.setRole(role);
        databaseReference = firebaseDatabase.getReference("Account");
        databaseReference.child(id).setValue(account).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("DAO_Account", "Sửa tài khoản thành công");
            } else {
                Log.e("DAO_Account", "Sửa tài khoản thất bại", task.getException());
            }
        });
    }

    public void LoadAllIdAccount(IdLoadCallback callback) {
        databaseReference = firebaseDatabase.getReference("Account");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> idAccount = new ArrayList<>();
                for (DataSnapshot accSnapshot : snapshot.getChildren()) {
                    String id = accSnapshot.getKey();
                    idAccount.add(id);
                }
                Log.d("DAO_Account", "Đã tải tất cả ID tài khoản: " + idAccount);
                callback.onIdLoad(idAccount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DAO_Account", "Lỗi khi tải ID tài khoản", error.toException());
            }
        });
    }
}
