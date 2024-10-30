package com.tungduong.test_firebase.GUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tungduong.test_firebase.DAO.DAO_Account;
import com.tungduong.test_firebase.Entity.Account;
import com.tungduong.test_firebase.BLL.MyAdaptor;
import com.tungduong.test_firebase.R;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.CollectionToArray;

public class MainActivity extends AppCompatActivity {

    EditText txt_userName,txt_passWord,txt_id,txt_role;
    Button btn_them,btn_sua,btn_xoa,btn_clear;
    RecyclerView recyclerView;
    List<Account> accountList;
    MyAdaptor adaptor;
    DAO_Account dao_account;
    List<String> IdAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnhXa();
        accountList = new ArrayList<>();

        DividerItemDecoration decoration = new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        dao_account = new DAO_Account();
        LoadAllAccount();

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_id.setText("");
                txt_userName.setText("");
                txt_passWord.setText("");
                txt_role.setText("");
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAccount();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString().trim();
                if (id.isEmpty()){
                    Toast.makeText(MainActivity.this,"Không tìm thấy ID của tài khoản",Toast.LENGTH_SHORT).show();
                }
                else {
                    dao_account.DeleteAccount(id);
                    txt_id.setText("");
                    txt_userName.setText("");
                    txt_passWord.setText("");
                    txt_role.setText("");
                }
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString().trim();
                String userName = txt_userName.getText().toString().trim();
                String passWord = txt_passWord.getText().toString().trim();
                String role = txt_role.getText().toString().trim();
                if (userName.isEmpty() || passWord.isEmpty() || role.isEmpty()){
                    Toast.makeText(MainActivity.this,"Vui lòng điền đầy đủ thông tin sản phẩm",Toast.LENGTH_SHORT).show();
                    return;
                }
                dao_account.UpdateAccount(id ,userName,passWord,role);
                Toast.makeText(MainActivity.this,"Cập nhật Account thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddAccount() {
        String id = txt_id.getText().toString();
        String userName = txt_userName.getText().toString();
        String passWord = txt_passWord.getText().toString();
        String role = txt_role.getText().toString();

        dao_account.LoadAllIdAccount(new DAO_Account.IdLoadCallback() {
            @Override
            public void onIdLoad(List<String> idAccount) {
                if (idAccount.contains(id)) {
                    Toast.makeText(MainActivity.this, "ID đã tồn tại, vui lòng nhập ID khác", Toast.LENGTH_SHORT).show();
                } else {
                    Account account = new Account(id, userName, passWord, role);
                    dao_account.AddAccount(account);
                    Toast.makeText(MainActivity.this, "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                    txt_id.setText("");
                    txt_userName.setText("");
                    txt_passWord.setText("");
                    txt_role.setText("");
                }
            }
        });
    }

    public void LoadAllAccount() {
        dao_account.showAccount(new DAO_Account.AccountCallback() {
            @Override
            public void onCallback(List<Account> accounts) {
                accountList.clear();
                accountList.addAll(accounts);
                adaptor = new MyAdaptor(MainActivity.this, accountList);
                recyclerView.setAdapter(adaptor);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            }
        });
    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.list_account);

        txt_id = findViewById(R.id.edt_id);
        txt_userName = findViewById(R.id.edt_userName);
        txt_passWord = findViewById(R.id.edt_passWord);
        txt_role = findViewById(R.id.edt_role);

        btn_them = findViewById(R.id.btn_them);
        btn_sua = findViewById(R.id.btn_sua);
        btn_xoa = findViewById(R.id.btn_xoa);
        btn_clear = findViewById(R.id.btn_clear);
    }

    public void SetText(String id,String userName,String passWord,String role){
        txt_id.setText(id);
        txt_userName.setText(userName);
        txt_passWord.setText(passWord);
        txt_role.setText(role);
    }
}
