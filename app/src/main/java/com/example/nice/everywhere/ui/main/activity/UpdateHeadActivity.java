package com.example.nice.everywhere.ui.main.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.nice.everywhere.R;
import com.example.nice.everywhere.bean.UpLoadBean;
import com.example.nice.everywhere.util.Logger;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateHeadActivity extends AppCompatActivity {


    private static final int CAMERA_CODE = 100;
    private static final int ALBUM_CODE = 200;
    private TextView txt_tool_head;
    private Toolbar toolbar_update;
    private ImageView img_update;
    private ImageView img_update_back;
    private String head;
    private Intent intent;
    private Button btnCamera;
    private Button btnAlbum;
    private Button btnCancel;
    private ImageView img_update_op;
    private File mFile;
    private Uri mImageUri;
    private UpLoadBean upLoadBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_head);
        intent = getIntent();
        head = intent.getStringExtra("head");
        initView();
    }

    private void initView() {
        txt_tool_head = (TextView) findViewById(R.id.txt_tool_head);
        toolbar_update = (Toolbar) findViewById(R.id.toolbar_update);
        img_update = (ImageView) findViewById(R.id.img_update);
        img_update_back = (ImageView) findViewById(R.id.img_update_back);
        img_update_op = (ImageView) findViewById(R.id.img_update_op);

        toolbar_update.setTitle("");
        setSupportActionBar(toolbar_update);

        RoundedCorners cornes = new RoundedCorners(15);//数字为圆角度数
        RequestOptions options = new RequestOptions()
                .transforms(cornes)
                .placeholder(R.drawable.zhanweitu_xianlu_jingdian)   //占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(this).load(head).apply(options).into(img_update);

        initListener();
    }

    private void initListener() {
        img_update_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        img_update_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //头像拉起popupWindow
                final PopupWindow popupWindow = imgGetPopupWindow();
                //popupWindow按钮监听
                popu_btn_listener(popupWindow);
            }
        });
    }

    @NonNull
    private PopupWindow imgGetPopupWindow() {
        View inflate = LayoutInflater.from(UpdateHeadActivity.this).inflate(R.layout.popu_item, null);
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

        popupWindow.showAtLocation(img_update, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


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
                upLoadBean = gson.fromJson(string, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                Toast.makeText(UpdateHeadActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();

                                Glide.with(UpdateHeadActivity.this).load(upLoadBean.getData().getUrl()).into(img_update);

                                intent.putExtra("head" , upLoadBean.getData().getUrl());
                                setResult(1001, intent);

                                Logger.println("路径：" + upLoadBean.getData().getUrl());
                            } else {
                                Toast.makeText(UpdateHeadActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
