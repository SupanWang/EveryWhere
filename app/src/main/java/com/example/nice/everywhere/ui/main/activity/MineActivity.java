package com.example.nice.everywhere.ui.main.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.InfoBean;
import com.example.nice.everywhere.bean.UpDateBean;
import com.example.nice.everywhere.bean.UpLoadBean;
import com.example.nice.everywhere.net.BaseObserver;
import com.example.nice.everywhere.net.RxUtils;
import com.example.nice.everywhere.net.UserService;
import com.example.nice.everywhere.util.Logger;
import com.example.nice.everywhere.util.ToastUtil;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MineActivity extends AppCompatActivity {

    private static final int CAMERA_CODE = 100;
    private static final int ALBUM_CODE = 200;
    private TextView txt_tool_mine;
    private Toolbar toolbar;
    private TextView txt_mine_photo;
    private ImageView img_kaquan;
    private TextView txt_mine_name;
    private TextView txt_user_name;
    private TextView txt_mine_sex;
    private TextView txt_user_sex;
    private TextView txt_mine_qianming;
    private TextView txt_user_qianming;
    private TextView txt_mine_setpsw;
    private TextView txt_mine_bind;
    private String imgUrl = "http://tvax4.sinaimg.cn/crop.0.0.664.664.50/006rTk8Wly8fofptfjs0oj30ig0igt9k.jpg";
    private Button btn_exit;
    private Button btnCamera;
    private Button btnAlbum;
    private Button btnCancel;
    private File mFile;
    private Uri mImageUri;
    private Button btn_man;
    private Button btn_lady;
    private Button btn_sexU;
    private String username;
    private String description;
    private String gender;

    private ArrayList<UpDateBean.ResultsBean> updetList;
    private String dialogName;
    private RelativeLayout rlv_mine_head;
    private RelativeLayout rlv_mine_name;
    private RelativeLayout rlv_mine_sex;
    private RelativeLayout rlv_mine_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        Intent intent1 = getIntent();
        username = intent1.getStringExtra("username");
        description = intent1.getStringExtra("description");
        gender = intent1.getStringExtra("gender");
        initView();

    }


    private void initView() {
        txt_tool_mine = (TextView) findViewById(R.id.txt_tool_mine);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_mine_photo = (TextView) findViewById(R.id.txt_mine_photo);
        img_kaquan = (ImageView) findViewById(R.id.img_kaquan);
        txt_mine_name = (TextView) findViewById(R.id.txt_mine_name);
        txt_user_name = (TextView) findViewById(R.id.txt_user_name);
        txt_mine_sex = (TextView) findViewById(R.id.txt_mine_sex);
        txt_user_sex = (TextView) findViewById(R.id.txt_user_sex);
        txt_mine_qianming = (TextView) findViewById(R.id.txt_mine_qianming);
        txt_user_qianming = (TextView) findViewById(R.id.txt_user_qianming);
        txt_mine_setpsw = (TextView) findViewById(R.id.txt_mine_setpsw);
        txt_mine_bind = (TextView) findViewById(R.id.txt_mine_bind);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        rlv_mine_head = (RelativeLayout) findViewById(R.id.rlv_mine_head);
        rlv_mine_name = (RelativeLayout) findViewById(R.id.rlv_mine_name);
        rlv_mine_sex = (RelativeLayout) findViewById(R.id.rlv_mine_sex);
        rlv_mine_desc = (RelativeLayout) findViewById(R.id.rlv_mine_desc);


        //圆形图片，需要单独写一个实体类，继承extends AppGlideModule，加注解@GlideModule
        RequestOptions options = RequestOptions.circleCropTransform();
        Glide.with(MineActivity.this).load(imgUrl).apply(options).into(img_kaquan);

        //亮色的模式 , 会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        txt_user_sex.setText(gender);
        txt_user_name.setText(username);
        txt_user_qianming.setText(description);

        initListener();

    }

    private void initListener() {

        //用户头像
        rlv_mine_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像拉起popupWindow
                final PopupWindow popupWindow = imgGetPopupWindow();
                //popupWindow按钮监听
                popu_btn_listener(popupWindow);
            }
        });
        //用户性别
        rlv_mine_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //性别拉起popupWindow
                final PopupWindow popupWindow = sexGetPopupWindow();
                //性别拉起popupWindow里的按钮监听
                sexListener(popupWindow);
            }
        });
        //用户昵称
        rlv_mine_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, MineMsgActivity.class);
                intent.putExtra("name", txt_user_name.getText().toString());
                intent.putExtra("type", 30);

                startActivityForResult(intent, 1);

            }
        });

        //用户签名
        rlv_mine_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineActivity.this, MineMsgActivity.class);
                intent.putExtra("name", txt_user_qianming.getText().toString());
                intent.putExtra("type", 20);

                startActivityForResult(intent, 2);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort("退出？");
                SharedPreferences isSuccess = getSharedPreferences("isSuccess", MODE_PRIVATE);
                boolean success = isSuccess.getBoolean("isSuccess", true);
                if (!success) {
                    Intent intent = new Intent(MineActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }


    //性别拉起popupWindow里的按钮监听
    private void sexListener(final PopupWindow popupWindow) {


        btn_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_user_sex.setText(btn_man.getText());
                popupWindow.dismiss();
            }
        });

        btn_lady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_user_sex.setText(btn_lady.getText());
                popupWindow.dismiss();
            }
        });

        btn_sexU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_user_sex.setText(btn_sexU.getText());
                popupWindow.dismiss();
            }
        });



    }

    //性别拉起popupWindow
    @NonNull
    private PopupWindow sexGetPopupWindow() {
        View inflate = LayoutInflater.from(MineActivity.this).inflate(R.layout.popu_sex_item, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置获取焦点
        popupWindow.setFocusable(true);
        //设置可触摸
        popupWindow.setTouchable(true);
        //设置外部可以点击
        popupWindow.setOutsideTouchable(true);
        //设置空背景，必须加上，可以让外部点击事件被触发
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击空白处消失
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(btn_exit, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        btn_man = (Button) inflate.findViewById(R.id.btn_man);
        btn_lady = (Button) inflate.findViewById(R.id.btn_lady);
        btn_sexU = (Button) inflate.findViewById(R.id.btn_sexU);
        return popupWindow;
    }

    @NonNull
    private PopupWindow imgGetPopupWindow() {
        View inflate = LayoutInflater.from(MineActivity.this).inflate(R.layout.popu_item, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置获取焦点
        popupWindow.setFocusable(true);
        //设置可触摸
        popupWindow.setTouchable(true);
        //设置外部可以点击
        popupWindow.setOutsideTouchable(true);
        //设置空背景，必须加上，可以让外部点击事件被触发
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击空白处消失
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(btn_exit, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        btnCamera = (Button) inflate.findViewById(R.id.btn_camera_pop_camera);
        btnAlbum = (Button) inflate.findViewById(R.id.btn_camera_pop_album);
        btnCancel = (Button) inflate.findViewById(R.id.btn_camera_pop_cancel);
        return popupWindow;
    }

    private void popu_btn_listener(final PopupWindow popupWindow) {
        //取消
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //打开相机
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        //打开相册
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePICK();
            }
        });
    }

    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    private void takePICK() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    //打开相册
    private void openAlbum() {
        //启动相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }

    //打开相机
    private void openCamera() {
        //创建文件用于保存图片
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //适配7.0,  等到对应的mImageUri路径地址
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开相机相册上传头像
        openCarmerUpLoad(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 30) {
            dialogName = data.getStringExtra("dialogName");
            txt_user_name.setText(dialogName);


        } else if (requestCode == 2 && resultCode == 20) {
            String dialogQianMing = data.getStringExtra("dialogName");
            txt_user_qianming.setText(dialogQianMing);
        }

    }

    private void openCarmerUpLoad(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {//判断回调成功

            if (requestCode == CAMERA_CODE) {//拍照

                //拍照后的图片上传
                uploadFile(mFile);
            } else if (requestCode == ALBUM_CODE) {//相册

                //获取到相册选中后的图片URI路径
                Uri imageUri = data.getData();

                //文件上传，将Uri路径转换为File对象
                //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                File file = getFileFromUri(imageUri, this);

                if (file.exists()) {
                    uploadFile(file);
                }
            }
        }
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    private void uploadFile(File mFile) {

        String mUrl = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808C")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(mUrl)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(string, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                Toast.makeText(MineActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();

                                Glide.with(MineActivity.this).load(upLoadBean.getData().getUrl()).into(img_kaquan);

                                Logger.println("路径：" + upLoadBean.getData().getUrl());
                            } else {
                                Toast.makeText(MineActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Retrofit infoUser = new Retrofit.Builder()
                .baseUrl(UserService.UserUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        UserService service = infoUser.create(UserService.class);
        Observable<InfoBean> info = service.getInfo();
        info.compose(RxUtils.<InfoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<InfoBean>() {
                    @Override
                    public void onNext(InfoBean infoBean) {
                        ToastUtil.showShort("成功");
                    }

                    @Override
                    public void error(String msg) {
                        ToastUtil.showShort("失败");
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
