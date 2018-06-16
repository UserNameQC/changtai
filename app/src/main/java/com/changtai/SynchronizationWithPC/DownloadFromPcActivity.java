package com.changtai.SynchronizationWithPC;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.changtai.R;
import com.changtai.SynchronizationWithPCModels.DownLoadFromPcModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import static com.utils.WebMethodHelper.getStringByWebMethodGet;
import static com.utils.WebMethodHelper.getStringByWebMethodPost;

/**
 * 从电脑版售水软件下载数据
 */
public class DownloadFromPcActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_download_from_pc);
        textView=(TextView)findViewById(R.id.tvDownloadFromPc);

        //弹出要给ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("工作中，请稍后......");
        //设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //设置ProgressDialog样式为水平的样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

    }

    public void onClick(View view) {
        new DownloadFromPcTask().execute("http://192.168.1.5:4000/DownLoad","010101","100","200");

    }


    /**
     * 下载线程
     */
    class DownloadFromPcTask extends AsyncTask<String,Integer,String>{

        //线程运行前执行，该方法在主线程执行
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("正在下载数据，请稍后......");
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
                final DownLoadCreatePackageOut downLoadCreatePackageOut = downLoadCreatePackage(path, stationNo, startVersion, endVersion);

                for (int i=0;i<downLoadCreatePackageOut.count;i++){
                    String s = DownLoadPackageValue(path, downLoadCreatePackageOut.packageId, i);
                    stringBuilder.append(s);
                    int progress = i * 100 /downLoadCreatePackageOut.count;
                    publishProgress(i,downLoadCreatePackageOut.count);
                }

                DownLoadDeletePackage(path,downLoadCreatePackageOut.packageId);

                String value = stringBuilder.toString();

                DownLoadFromPcModel downLoadFromPcModel = gson.fromJson(value, DownLoadFromPcModel.class);
                Log.i("TEST",String.format("%d",downLoadFromPcModel.Device.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.User.size()));
                Log.i("TEST",String.format("%d",downLoadFromPcModel.Price.size()));

                return value;
                //转换成实体对像然后保存

            } catch (Exception e) {
                Toast.makeText(DownloadFromPcActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                //e.printStackTrace();
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
            //textView.setText(s);
            Toast.makeText(DownloadFromPcActivity.this,"结束",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            //
            new UploadToPcTask().execute("http://192.168.9.192:4000/Upload");
        }

        class DownLoadCreatePackageOut{
            //包ID
            @SerializedName("PackageId")
            private String packageId;
            //包长度
            @SerializedName("Count")
            private Integer count;
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
        private DownLoadCreatePackageOut downLoadCreatePackage(String path, String stationNo, long startVersion, long endVersion) throws Exception {
            path = String.format("%s/DownLoadCreatePackage/%s/%d/%d",path,stationNo,startVersion,endVersion) ;
            String value = getStringByWebMethodGet(path);
            DownLoadCreatePackageOut downLoadCreatePackageOut = gson.fromJson(value, DownLoadCreatePackageOut.class);
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
            path = String.format("%s/DownLoadPackageValue/%s/%d",path,packageId,index) ;
            String value = getStringByWebMethodGet(path);
            return value;
        }

        /**
         * 删除在服务器端生成的下载包
         * @param path uri
         * @param packageId 包ID
         * @throws Exception
         */
        private void DownLoadDeletePackage(String path,String packageId) throws Exception {
            path = String.format("%s/DownLoadDeletePackage/%s",path,packageId) ;
            getStringByWebMethodGet(path);
        }


    }


    /**
     * 上传线程
     */
    class UploadToPcTask extends AsyncTask<String,Integer,String> {

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
                Toast.makeText(DownloadFromPcActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
            Toast.makeText(DownloadFromPcActivity.this, "结束", Toast.LENGTH_LONG).show();
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
}
