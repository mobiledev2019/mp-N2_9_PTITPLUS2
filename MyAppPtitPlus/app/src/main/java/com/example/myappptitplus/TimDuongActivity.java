package com.example.myappptitplus;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.internal.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;

public class TimDuongActivity extends AppCompatActivity {
    Button btn_timbus ,btn_showmap;
    TextView txt_ketquatimkiem , txt_ketquabus;
    EditText edt_diembatdau , edt_diemden ;
    String diemdi , diemden ;
    String urlApi = "";
    ArrayList<String> bus = new ArrayList();
    ArrayList<String> stop = new ArrayList();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_duong);
        addcontrols();
        addevents();
    }


    private void addcontrols() {
        btn_timbus = findViewById(R.id.btn_timbus);
        txt_ketquatimkiem = findViewById(R.id.txt_ketquatimkiem);
        txt_ketquabus = findViewById(R.id.txt_ketquabus);
        edt_diembatdau = findViewById(R.id.edt_diembatdau);
        edt_diemden = findViewById(R.id.edt_diemden);

    }

    private void addevents() {
        btn_timbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diemdi = edt_diembatdau.getText().toString();
                diemden = edt_diemden.getText().toString();

                diemdi = diemdi.replace(" ","%20");
                System.out.println(diemdi);
                diemden = diemden.replaceAll(" ","%20");
                System.out.println(diemden);
                urlApi = "https://maps.googleapis.com/maps/api/directions/json?origin=" +diemdi+ "&destination=" +diemden+"&mode=transit&key=AIzaSyDk4HCaLsS9c9EUs2yBW5c-zIQDA2d49j0";
                LoadBusTask placeService = new LoadBusTask(TimDuongActivity.this);
                placeService.execute();
            }
        });


    }

//    public String biendoiString(String a){
//        String[] output = a.split(" ");
//        a = output[0];
//        for(int i = 1 ;i<output.length ; i++){
//            a = a+"%20"
//        }
//        return a;
//    }

    public class LoadBusTask extends AsyncTask<Void, Void, ArrayList<String>> {

        private JSONArray jsonArrayResult , jsonArrayroutes , jsonArray01 , jsonArraylegs , jsonArray02 , jsonArraysteps ;
        private ArrayList<String> bus;

        private Context context;
        private Constants constant;

        public LoadBusTask(Context context) {
            this.context = context;
        }


        @Override
        protected void onPreExecute() {
            if (bus != null) {
                bus.clear();
                Log.d("HanoiFood", "Clear arrayList");
            }
            progressDialog = new ProgressDialog(TimDuongActivity.this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Đang tải dữ liệu");
            progressDialog.show();
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            String strValuesJson = connectionUrl();
            bus = getDataFromJson(strValuesJson);
            return bus;
        }

        @Override
        protected void onPostExecute(ArrayList<String> bus) {
            showResult();
            super.onPostExecute(bus);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

        /**
         * Function to connect to server
         *
         * @return
         */
        private String connectionUrl() {
            String strValuesJson = "";
            try {
                String urlParser = "https://maps.googleapis.com/maps/api/directions/json?origin="+diemdi+"&destination="+diemden+"&mode=transit&key=AIzaSyDk4HCaLsS9c9EUs2yBW5c-zIQDA2d49j0";
                System.out.println(urlParser);
                HttpParams httpParams = new BasicHttpParams();
                DefaultHttpClient httpClient = new DefaultHttpClient();
                httpClient.setParams(httpParams);

                HttpGet httpGet = new HttpGet(urlParser);

                HttpResponse httpRespone = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpRespone.getEntity();
                InputStream is = httpEntity.getContent();
                InputStreamReader isReader = new InputStreamReader(is, "utf-8");
                BufferedReader bfreader = new BufferedReader(isReader);
                StringBuilder strBuilder = new StringBuilder();
                String line = null;
                while ((line = bfreader.readLine()) != null) {
                    strBuilder.append(line + "\n");
                }
                is.close();
                isReader.close();
                bfreader.close();
                strValuesJson = strBuilder.toString();
            } catch (ClientProtocolException e) {
                Log.d("HanoiFood", "ClientProtocolException ");
            } catch (ConnectTimeoutException e) {
                Log.d("HanoiFood", "ConnectTimeout Exception");
            } catch (SocketTimeoutException e) {
                Log.d("HanoiFood", "SocketTimeout Exception");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return strValuesJson;
        }

        /**
         * Function to parser file json
         *
         * @param strJson
         * @return
         */
        @TargetApi(Build.VERSION_CODES.KITKAT)
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private ArrayList<String> getDataFromJson(String strJson) {
            System.out.println("du lieu lay ve : "+strJson);
            ArrayList<String> bus = new ArrayList<>();
            try {
                if (strJson != null) {
                    JSONObject jsonArrayResult = new JSONObject(strJson);
                    JSONArray a = jsonArrayResult.getJSONArray("routes");
                    System.out.println("day la a:"+a);
                    for (int i = 0 ;i<a.length();i++){
                        JSONObject jsonObjectroutes = a.getJSONObject(i);
                        JSONArray jsona = jsonObjectroutes.getJSONArray("legs");
                        for (int j = 0 ;j<jsona.length() ;j++){
                            JSONObject jsonObjeclegs= jsona.getJSONObject(j);
                            JSONArray jsonb = jsonObjeclegs.getJSONArray("steps");
                            for (int k = 0 ; k<jsonb.length() ; k++){
                                JSONObject jsonObjectmode= jsonb.getJSONObject(k);
                                String mode = jsonObjectmode.getString("travel_mode");
                                if(mode.equals("TRANSIT")){
//                                    JSONArray jsonc = jsonObjectmode.getJSONArray("transit_details");
                                    JSONObject jsontransit = jsonObjectmode.getJSONObject("transit_details");
                                    JSONObject jsonarrivalstop = jsontransit.getJSONObject("arrival_stop");
                                    JSONObject jsonline = jsontransit.getJSONObject("line");
                                    JSONArray jsond = jsonline.getJSONArray("agencies");
                                    String id_bus = jsonline.getString("name");
                                    String arrival_stop = jsonarrivalstop.getString("name");
                                    if(!id_bus.equals("")){
                                            bus.add(id_bus);
                                            stop.add(arrival_stop);
                                        }
//                                    for (int h = 0 ; h < jsond.length() ; h++){
//                                        JSONObject jsonObjectagencis= jsond.getJSONObject(h);
//                                        String id_bus = jsonObjectagencis.getString("name");
//                                        if(!id_bus.equals("")){
//                                            bus.add(id_bus);
//                                        }
//                                    }
   //

                                }
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return bus;
        }

            /**
         * Function to show result
         *
         * @param
         */
        private void showResult() {
            if (bus != null) {
                txt_ketquatimkiem.setText("Kết quả tìm kiếm :");
                txt_ketquabus.clearComposingText();
                txt_ketquabus.setText("");
//                btn_showmap.setVisibility(View.VISIBLE);
                for (int i = 0 ;i<bus.size() ;i++){
                    txt_ketquabus.append(bus.get(i)+"\n\n" +">>> Điểm Dừng : "+stop.get(i)+"\n\n"+">>> Giá Vé : 7000đ"+"\n\n");

            }

        }

    }
}

}
