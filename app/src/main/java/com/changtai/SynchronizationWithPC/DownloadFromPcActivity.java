package com.changtai.SynchronizationWithPC;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.changtai.R;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFromPcActivity extends AppCompatActivity {

    //消息显示框
    TextView textView ;

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_from_pc);
        textView=(TextView)findViewById(R.id.tvDownloadFromPc);
    }

    public void onClick(View view) {
        new DownloadFromPcTask().execute("http://192.168.9.192:4000","010101","100","200");
    }


    class DownloadFromPcTask extends AsyncTask<String,String,String>{
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
                    publishProgress(String.format("%d/%d",i,downLoadCreatePackageOut.count));
                }

                DownLoadDeletePackage(path,downLoadCreatePackageOut.packageId);

                String value = stringBuilder.toString();

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
        protected void onProgressUpdate(String... values) {
            textView.setText(values[0]);
            super.onProgressUpdate(values);
        }

        //线程执行结束后运行，该方法在主线程执行
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            Toast.makeText(DownloadFromPcActivity.this,"结束",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
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

        /**
         * 以GET方法请求服务器数据
         * @param path uri
         * @return 数据字符串
         * @throws Exception
         */
        private String getStringByWebMethodGet(String path) throws Exception {
            URL url = new URL(path);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            try{
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setDoInput(true);
                //connection.setDoOutput(true);
                connection.connect();
                //得到响应代码
                int responseCode = connection.getResponseCode();
                if(responseCode==200){
                    InputStream inputStream = connection.getInputStream();
                    byte[] bytes = new byte[10000];
                    int length = inputStream.read(bytes);
                    if(length==-1){
                        return "";
                    }
                    String s =new String(bytes,0,length);
                    return s;
                }
                throw new Exception("服务器返回错误代码");
            }
            finally {
                connection.disconnect();
            }

        }
    }
}
