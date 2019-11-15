package com.event.trading_assignment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.event.trading_assignment.R;
import com.event.trading_assignment.networking.APIService;
import com.event.trading_assignment.networking.ApiUtils;
import com.event.trading_assignment.pojo.DataLoginDataSet;
import com.event.trading_assignment.pojo.ResponseLoginDataSet;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoFactor extends AppCompatActivity {

    Button btn_login_userlogin;
    EditText et_email_userlogin;
    EditText et_password_userlogin;

    TextView tv1,tv2;
    String userName,password,qust1,qust2,id1,id2;

    JSONArray allSelectedProductsJsonArrayWithPrice;
    JSONArray qstIds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_factor_login);


        //getting the datbundel from other activity incoming
        Bundle extras = getIntent().getExtras();
         qust1=extras.getString("qust1");
         qust2=extras.getString("qust2");
         id1=extras.getString("id1");
         id2=extras.getString("id2");


        btn_login_userlogin = (Button)findViewById(R.id.btn_login_userlogin);

        et_email_userlogin = (EditText)findViewById(R.id.et_email_userlogin);
        et_password_userlogin = (EditText)findViewById(R.id.et_password_userlogin);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);


        tv1.setText(qust1);

        tv2.setText(qust2);




        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectQstId = new JSONObject();

        try {

            jsonObjectQstId.put("question_ids", qust1);

            jsonObjectQstId.put("question_ids", qust2);


            jsonObject.put("answers", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject.put("answers", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        allSelectedProductsJsonArrayWithPrice.put(jsonObject);
        qstIds.put(jsonObject);


        btn_login_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = et_email_userlogin.getText().toString();
                password = et_password_userlogin.getText().toString();


                login(userName,password,allSelectedProductsJsonArrayWithPrice,qstIds);

//              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

    }

    private void login(String userName, String password, JSONArray allSelectedProductsJsonArrayWithPrice, JSONArray qstIds) {


        APIService mAPIService = ApiUtils.getAPIService();

        Map<String,String> requestBody = new HashMap<>();


        Integer a = 1;

//        {"answers":["a","a"],"count":2,"device":"web","login_id":"ANT123","question_ids":[52,11],qwe123}


        requestBody.put("device", "");
        requestBody.put("login_id", "ANT123");
        requestBody.put("count", String.valueOf(2));
        requestBody.put("question_ids", allSelectedProductsJsonArrayWithPrice.toString());
        requestBody.put("answers", allSelectedProductsJsonArrayWithPrice.toString());


        mAPIService.postchecking(requestBody).enqueue(new Callback<ResponseLoginDataSet>() {
            @Override
            public void onResponse(Call<ResponseLoginDataSet> call, Response<ResponseLoginDataSet> response) {
                if (response.isSuccessful()) {

                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("success")) {

                        Toast.makeText(getBaseContext(), "correct", Toast.LENGTH_SHORT).show();

                        checkingTwoFactor(response.body().getData());

                    } else {

                        Toast.makeText(getBaseContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(getBaseContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseLoginDataSet> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void checkingTwoFactor(DataLoginDataSet data) {




    }


}
