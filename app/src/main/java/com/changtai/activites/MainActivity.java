package com.changtai.activites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.changtai.R;
import com.changtai.SynchronizationWithPCModels.DownLoadFromPcModel;
import com.changtai.SynchronizationWithPCModels.SwpDeviceModel;
import com.changtai.SynchronizationWithPCModels.SwpPriceModel;
import com.changtai.SynchronizationWithPCModels.SwpPurchaseRecordModel;
import com.changtai.SynchronizationWithPCModels.SwpUserModel;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SpUtils;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityMainBinding;
import com.changtai.fragment.FragmentAdapter;
import com.changtai.fragment.FragmentMain;
import com.changtai.fragment.FragmentMine;
import com.changtai.sqlModel.ConfigModel;
import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.PriceModel;
import com.changtai.sqlModel.PurchaseRecordModel;
import com.changtai.sqlModel.UserModel;
import com.changtai.sqlModelDao.ConfigModelDao;
import com.changtai.sqlModelDao.PurchaseRecordModelDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import static com.utils.WebMethodHelper.getStringByWebMethodGet;
import static com.utils.WebMethodHelper.getStringByWebMethodPost;

@SuppressWarnings("all")
public class MainActivity extends BaseActivity {

    public ActivityMainBinding mainBinding;
    public List<ImageView> imageViews;
    public List<TextView> textViews;
    public List<Fragment> fragments;
    public FragmentAdapter fragmentAdapter;

    public SpUtils spUtils;

    //消息显示框
    TextView textView ;

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //进度条
    private ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        //initView();
        spUtils = new SpUtils();
        checkBaseId();
        initBottomMenu();
        initViewPager();
    }

    public void initViewPager(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new FragmentMain());
        fragments.add(new FragmentMine());
        fragmentAdapter = new FragmentAdapter(fragmentManager, fragments);
        mainBinding.mainViewPager.setAdapter(fragmentAdapter);
        mainBinding.mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setMenuSelector(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setMenuSelector(0);

        mainBinding.mainFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuSelector(0);
            }
        });

        mainBinding.mineFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuSelector(1);
            }
        });
    }

    /**
     * 选中指定的菜单项并显示对应的Fragment
     *
     * @param index
     */
    private void setMenuSelector(int index) {
        reSetSelected();
        textViews.get(index).setSelected(true);
        imageViews.get(index).setSelected(true);
        mainBinding.mainViewPager.setCurrentItem(index, false);
    }

    /**
     * 重置底部菜单所有ImageView和TextView为未选中状态
     */
    private void reSetSelected() {
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setSelected(false);
            imageViews.get(i).setSelected(false);
        }
    }



    public void initBottomMenu(){
        textViews = new ArrayList<>();
        textViews.add(mainBinding.mainBottomTv);
        textViews.add(mainBinding.mineBottomTv);
        imageViews = new ArrayList<>();
        imageViews.add(mainBinding.mainBottomImage);
        imageViews.add(mainBinding.mineBottomImage);
    }

    public void checkBaseId(){
        ConfigModelDao configModelDao = MyApplication.getInstance().getDaoSession().getConfigModelDao();
        List<ConfigModel> configModels = configModelDao.queryBuilder().list();
        if (configModels == null || configModels.size() == 0){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("未检测到地区号，请设置地区号");
            builder.setTitle("提示");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }


    public void downLoadFromWeb(){

        //弹出要给ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("工作中，请稍后......");
        //设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //设置ProgressDialog样式为水平的样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        String url = spUtils.getString(Entity.SERVER);
        if (!TextUtils.isEmpty(url)){
            Entity.MAIN_URL = url;
        }
        new DownloadFromWebTask().execute(Entity.MAIN_URL + "/PdaDownLoadFromWeb","010101","100","200");
    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }

    /**
     * 下载线程
     */
    class DownloadFromWebTask extends AsyncTask<String,Integer,String> {

        //线程运行前执行，该方法在主线程执行
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("正在下载数据，请稍后......");
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            //在onPreExecute()中我们让ProgressDialog显示出来
            progressDialog.show();
        }

        //线程体，该方法在子线程执行
        @Override
        protected String doInBackground(String... strings) {
            String path = strings[0];
            String stationNo = strings[1];
            Integer startVersion = Integer.parseInt(strings[2]);
            Integer endVersion = Integer.parseInt(strings[3]);

            StringBuilder stringBuilder= new StringBuilder();

            try {
                MainActivity.DownloadFromWebTask.DownLoadCreatePackageOut downLoadCreatePackageOut = downLoadCreatePackage(path, stationNo, startVersion, endVersion);
                for (int i=0;i<downLoadCreatePackageOut.count;i++){
                    String s = DownLoadPackageValue(path, downLoadCreatePackageOut.packageId, i);
                    Log.i("TEST",String.format("s的长度是%d",s.length()));
                    Log.i("TEST",String.format("s是%s",s));
                    String s2 = gson.fromJson(s,String.class);
                    stringBuilder.append(s2);

                    Log.i("TEST",String.format("s2的长度是%d",s2.length()));
                    Log.i("TEST",String.format("s2是%s",s2));
                    publishProgress(i+1,downLoadCreatePackageOut.count);
                }

                DownLoadDeletePackage(path,downLoadCreatePackageOut.packageId);

                String value = stringBuilder.toString();

                if(downLoadCreatePackageOut.jsonLength!=value.length()){
                    //throw new Exception("接收的数据长度与服务器不一致");
                }

                DownLoadFromPcModel downLoadFromPcModel = gson.fromJson(value, DownLoadFromPcModel.class);
                Log.i("TEST",String.format("%d",downLoadFromPcModel.Device.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.User.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.Price.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.PurchaseRecord.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.CardReplacement.size()));
                for(SwpDeviceModel model:downLoadFromPcModel.Device){
                    DeviceModel model1= MappingObject(model,DeviceModel.class);
                    model1.DeviceId= Long.parseLong(model.DeviceNo);
                    MyApplication.myApplication.getDaoSession().getDeviceModelDao().insertOrReplace(model1);
                }
                for(SwpUserModel model:downLoadFromPcModel.User){
                    UserModel model1= MappingObject(model,UserModel.class);
                    model1.Id= Long.parseLong(model.UserNo);
                    MyApplication.myApplication.getDaoSession().getUserModelDao().insertOrReplace(model1);
                }
                for(SwpPriceModel model:downLoadFromPcModel.Price){
                    PriceModel model1= MappingObject(model,PriceModel.class);
                    model1.Id= Long.parseLong(String.format("%s%03d",model1.stationNo,model1.sjId));
                    MyApplication.myApplication.getDaoSession().getPriceModelDao().insertOrReplace(model1);
                }
                for(SwpPurchaseRecordModel model:downLoadFromPcModel.PurchaseRecord){
                    PurchaseRecordModel model1= MappingObject(model,PurchaseRecordModel.class);
                    QueryBuilder<PurchaseRecordModel> qb =MyApplication.myApplication.getDaoSession().getPurchaseRecordModelDao().queryBuilder();
                    qb.where(PurchaseRecordModelDao.Properties.PurchaseRecordId.eq(model1.purchaseRecordId));
                    List<PurchaseRecordModel> list = qb.list();
                    if(list.size()>0){
                        model1.Id=list.get(0).Id;
                    }
                    MyApplication.myApplication.getDaoSession().getPurchaseRecordModelDao().insertOrReplace(model1);
                }


                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
            //转换成实体对像然后保存
        }

        /**
         * 把一个对像的值复制到另一个对象
         * @param object 原对象
         * @param classOfT 生成的对象类名
         * @param <T> 生成的对象类名
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */

        private <T> T MappingObject(Object object, Class<T> classOfT) throws Exception {
            T newObject = classOfT.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for(Field field : fields){
                String fieldName = field.getName();
                try {
                    Field[] newFields = classOfT.getDeclaredFields();
                    for (Field newField : newFields) {
                        String newFieldName = newField.getName();
                        if (fieldName.toLowerCase().equals(newFieldName.toLowerCase())) {
                            newField.set(newObject, field.get(object));
                            break;
                        }
                    }
                }catch (Exception e){
                    throw new Exception(String.format("%s实体,%s属性,%s",classOfT.getName(),fieldName,e.getMessage()));
                }
            }
            return newObject;
        }

        //线程执行进度，该方法在主线程执行
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
            progressDialog.setMax(values[1]);
            super.onProgressUpdate(values);
        }

        //线程执行结束后运行，该方法在主线程执行
        @Override
        protected void onPostExecute(String s) {
            //使ProgressDialog框消失
            progressDialog.dismiss();
            //textView.setText(s);
            Toast.makeText(MainActivity.this,"结束",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            //
            new  MainActivity.UploadToWebTask().execute("http://tzctdz.51mypc.cn:8000/PdaUploadToWeb");
        }

        class DownLoadCreatePackageOut{
            //包ID
            @SerializedName("PackageId")
            private String packageId;
            //包长度
            @SerializedName("Count")
            private Integer count;
            //所有了包拼接后字符串的长度
            @SerializedName("JsonLength")
            private long jsonLength;
        }

        /**
         * 下载数据,在服务器端生成下载包
         * @param path uri
         * @param stationNo 售水站号
         * @param startVersion 起始版本号
         * @param endVersion 结束版本号
         * @return 返回包ID，包长度
         * @throws Exception
         */
        private MainActivity.DownloadFromWebTask.DownLoadCreatePackageOut downLoadCreatePackage(String path, String stationNo, long startVersion, long endVersion) throws Exception {
            path = String.format("%s/DownLoadCreatePackage", path);
            Map<String, String> params = new HashMap<String, String>();
            params.put("stationNo", stationNo);
            params.put("startVersion", String.format("%d", startVersion));
            params.put("endVersion", String.format("%d", endVersion));
            String value = getStringByWebMethodPost(path, params);
            MainActivity.DownloadFromWebTask.DownLoadCreatePackageOut downLoadCreatePackageOut = gson.fromJson(value, MainActivity.DownloadFromWebTask.DownLoadCreatePackageOut.class);
            return downLoadCreatePackageOut;
        }

        /**
         * 下载子包
         * @param path uri
         * @param packageId 包ID
         * @param index 子包序号
         * @return 子包内容
         * @throws Exception
         */
        private String DownLoadPackageValue(String path,String packageId, int index) throws Exception {
            path = String.format("%s/DownLoadPackageValue",path) ;
            Map<String, String> params = new HashMap<String, String>();
            params.put("packageId", String.format("%s", packageId));
            params.put("index", String.format("%d", index));
            String value = getStringByWebMethodPost(path, params);
            return value;
        }

        /**
         * 删除在服务器端生成的下载包
         * @param path uri
         * @param packageId 包ID
         * @throws Exception
         */
        private void DownLoadDeletePackage(String path,String packageId) throws Exception {
            path = String.format("%s/DownLoadDeletePackage",path) ;
            Map<String, String> params = new HashMap<String, String>();
            params.put("packageId", packageId);
            getStringByWebMethodPost(path, params);
        }


    }


    /**
     * 上传线程
     */
    class UploadToWebTask extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //在onPreExecute()中我们让ProgressDialog显示出来
            progressDialog.setMessage("正在上传数据，请稍后......");
            progressDialog.show();
        }

        //线程
        @Override
        protected String doInBackground(String... strings) {
            String path = strings[0];
            String value = "qwertyuiop[]asdfghjkl;'zxcvbnm,./" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "asdfghjkl;'sfghjkl;ertyuiop[]xcvbnm,./34567890-1234567890-wertyuiop[]sdfghjkl" +
                    "";
            Integer stepLength = 1;
            Integer stepCount = value.length() / stepLength + 1;

            try {
                String packageId = UpLoadCreatePackage(path);
                for (int i = 0; i < stepCount; i++) {
                    Integer beginIndex = i * stepLength;
                    if (beginIndex >= value.length()) {
                        //已经结束了
                        break;
                    }
                    Integer endIndex = beginIndex + stepLength;
                    if (endIndex < value.length()) {
                        String subString = value.substring(beginIndex, endIndex);
                        UpLoadPackage(path, packageId, i, subString);
                    } else {
                        String subString = value.substring(beginIndex, value.length() - 1);
                        UpLoadPackage(path, packageId, i, subString);
                    }

                    int progress = i * 100 / stepCount;
                    publishProgress(i,stepCount);
                }
                UpLoadSavePackage(path, packageId);
            } catch (Exception e) {
                //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            return null;
        }

        //线程执行进度，该方法在主线程执行
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
            progressDialog.setMax(values[1]);
            super.onProgressUpdate(values);
        }


        //线程执行结束后运行，该方法在主线程执行
        @Override
        protected void onPostExecute(String s) {
            //使ProgressDialog框消失
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "结束", Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        /**
         * 在服务器创建空包
         *
         * @param path
         * @return
         * @throws Exception
         */
        private String UpLoadCreatePackage(String path) throws Exception {
            path = String.format("%s/UpLoadCreatePackage", path);
            String value = getStringByWebMethodGet(path);
            return value;
        }

        /**
         * 把本地数据上传到服务器空包中
         *
         * @param packageId
         * @param index
         * @param value
         */
        private void UpLoadPackage(String path, String packageId, int index, String value) throws Exception {
            path = String.format("%s/UpLoadPackage", path);
            Map<String, String> params = new HashMap<String, String>();
            params.put("packageId", packageId);
            params.put("index", String.format("%d", index));
            params.put("value", value);
            getStringByWebMethodPost(path, params);
        }

        /**
         * 通知服务器保存数据并删除包
         *
         * @param path
         * @param packageId
         * @throws Exception
         */
        private void UpLoadSavePackage(String path, String packageId) throws Exception {
            path = String.format("%s/UpLoadSavePackage/%s", path, packageId);
            getStringByWebMethodGet(path);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否退出应用？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Entity.loginModel != null){
                    Entity.loginModel.setPassword("");
                    Entity.loginModel.setUserName("");
                    Entity.loginModel.setLoginName("");
                    Entity.loginModel.setQxString("");
                    dialog.dismiss();
                    MyApplication.getInstance().exit();
                }
            }
        });

        builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
