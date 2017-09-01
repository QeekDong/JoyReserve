package com.joyreserve.reserve.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.bumptech.glide.Glide;
import com.joyreserve.reserve.APIUtils;
import com.joyreserve.reserve.adapter.MyAdapter;
import com.joyreserve.reserve.R;
import com.joyreserve.reserve.model.ReserveInfo;
import com.joyreserve.reserve.model.ReserveResult;
import com.joyreserve.reserve.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/*
   待解决问题：
1. 时间动态更新
调用线程方法
2. 实时更新数据
参照例子，先将数据取出来，序列化，再用Bundle传输
3. 如何给服务器传送特定Id
*/

public class ReserveActivity extends AppCompatActivity implements View.OnClickListener{

    Room room;
    TextView tvRoomName;
    TextView remarks;
    ListView listView;
    ImageView imageView;
    APIUtils service;
    List<ReserveInfo> reserveInfos = new ArrayList<>();
    String room_id;
    String auth_code;
    String date;
    Button btnLastDay;
    Button btnNextDay;

    private MyAdapter adapter;
    public static boolean isToday;
    int year;
    int month;
    int day;
    int today;


//    定时刷新显示界面
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            adapter.notifyDataSetChanged();
            handler.postDelayed(this, 1000*60*10);// 间隔120秒
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        // 启动百度push
        PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,
                "yIYC6nzNbCYtFpnfdd1gFWhC");
        //初始化界面
        initView();
        
//        获取当前时间
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        year=c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        today = day;
        changeDateToString(year,month,day);
        initData();

//        Bitmap bitmap = null;
//        AssetManager am = getResources().getAssets();
//        try {
//            InputStream is = am.open("get_one_room_qcode.png");
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        imageButton.setImageBitmap(bitmap);

        service = new Retrofit.Builder()
                .baseUrl("http://www.joyreserve.com/index.php/API/Screen/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(APIUtils.class);

        updateReserveInfo(date);
        //定时刷新界面
        handler.postDelayed(runnable, 1000*60);


    }
//    更新预定信息并用list加载出来
    private void updateReserveInfo(String s) {
        service.getReserveInfo(room_id, auth_code,s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReserveResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReserveResult result) {
                        switch (result.getErrcode()) {
                            case 0:
                                reserveInfos = result.getReserveInfoList();
                                break;
                            case 1:
                                String msg = result.getMsg();
                                Toast.makeText(ReserveActivity.this,msg,Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                String msg1 = result.getMsg();
                                Toast.makeText(ReserveActivity.this,msg1,Toast.LENGTH_SHORT).show();
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
                        adapter = new MyAdapter(ReserveActivity.this, reserveInfos, R.layout.item);
                        listView.setAdapter(adapter);
                    }
                });
    }

    //将日期转化为字符串
    private void changeDateToString(int year,int month,int day) {
        if (month <= 9) {
            if (day <= 9) {
                date=""+year+"0"+month+"0"+day;
            }else{
                date=""+year+"0"+month+day;
            }
        }else{
            if (day <= 9) {
                date=""+year+month+"0"+day;
            }else{
                date=""+year+month+day;
            }
        }

        //判断是否在当天
        if (today == day){
            isToday = true;
        }else {
            isToday = false;
        }
    }
    
    private void initView(){
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);

        tvRoomName = (TextView) findViewById(R.id.tv_room_name);
        remarks = (TextView) findViewById(R.id.tv_remarks);
        listView = (ListView) findViewById(R.id.list);
        imageView = (ImageView) findViewById(R.id.img);
        btnLastDay = (Button) findViewById(R.id.btn_left);
        btnNextDay = (Button) findViewById(R.id.btn_right);
        remarks.setMovementMethod(new ScrollingMovementMethod());
        btnLastDay.setOnClickListener(this);
        btnNextDay.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    //设置房间主题和标记
    private void initData() {
        room = getIntent().getParcelableExtra("room");
        room_id = getIntent().getStringExtra("room_id");
        auth_code = getIntent().getStringExtra("auth_code");
        tvRoomName.setText(room.getRoomName()+" "+date);
        remarks.setText("1.最大容纳人数：" + room.getSeats() + "人\n" + "2.会议室设备: " + room.getDevices());
        Glide.with(this).load("http://"+room.getSign_qcode()).into(imageView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                day--;
                changeDateToString(year,month,day);
                tvRoomName.setText(room.getRoomName()+" "+date);
                updateReserveInfo(date);
                Toast.makeText(this, "当前日期"+date, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_right:
                day++;
                changeDateToString(year,month,day);
                tvRoomName.setText(room.getRoomName()+" "+date);
                updateReserveInfo(date);
                Toast.makeText(this, "当前日期"+date, Toast.LENGTH_SHORT).show();
                break;
            case R.id.img:
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                year=c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH) + 1;
                day = c.get(Calendar.DAY_OF_MONTH);
                changeDateToString(year,month,day);
                tvRoomName.setText(room.getRoomName()+" "+date);
                updateReserveInfo(date);
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable); //停止刷新
        super.onDestroy();
    }
}

