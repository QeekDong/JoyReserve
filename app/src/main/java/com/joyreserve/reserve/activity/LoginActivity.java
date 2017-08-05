package com.joyreserve.reserve.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joyreserve.reserve.APIUtils;
import com.joyreserve.reserve.R;
import com.joyreserve.reserve.model.Room;
import com.joyreserve.reserve.model.RoomResult;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etReserveId;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnDeleteId;
    private Button btnDeletePassword;
    private APIUtils service;
    String room_id;
    String auth_code;

    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        decorView.setSystemUiVisibility(option);

        initViews();

    }

    private void initViews() {
        etReserveId = (EditText) findViewById(R.id.et_person_id);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnDeleteId = (Button) findViewById(R.id.btn_delete_reserve_id);
        btnDeletePassword = (Button) findViewById(R.id.btn_delete_password);
        btnLogin.setOnClickListener(this);
        btnDeleteId.setOnClickListener(this);
        btnDeletePassword.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("Loginconfig", MODE_PRIVATE);
        String roomid = sharedPreferences.getString("roomid", "");
        String authcode = sharedPreferences.getString("authcode", "");
        if (!roomid.equals("")) {
            etReserveId.setText(roomid);
            etPassword.setText(authcode);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                room_id = etReserveId.getText().toString();
                auth_code = etPassword.getText().toString();
                saveData(room_id, auth_code);

                service = new Retrofit.Builder()
                        .baseUrl("http://www.joyreserve.com/index.php/API/Screen/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(APIUtils.class);

                service.getRoomInfo(room_id, auth_code)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RoomResult>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RoomResult result) {
                                switch (result.getErrcode()) {
                                    case 0:
                                        room = result.getRoom();
                                        Intent intent = new Intent(LoginActivity.this, ReserveActivity.class);
                                        intent.putExtra("room", room);
                                        intent.putExtra("room_id",room_id);
                                        intent.putExtra("auth_code", auth_code);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 1:
                                        String msg = result.getMsg();
                                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        String msg1 = result.getMsg();
                                        Toast.makeText(LoginActivity.this,msg1,Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.btn_delete_reserve_id:
                etReserveId.setText("");
                break;
            case R.id.btn_delete_password:
                etPassword.setText("");
                break;
            default:
                break;
        }
    }

    public void saveData(String roomid, String authcode){
        SharedPreferences sharedPreferences = this.getSharedPreferences("Loginconfig", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("roomid", roomid);
        editor.putString("authcode", authcode);
        editor.apply();
    }


}
