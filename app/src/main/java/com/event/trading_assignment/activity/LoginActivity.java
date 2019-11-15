package com.event.trading_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.event.trading_assignment.R;
import com.event.trading_assignment.networking.APIService;
import com.event.trading_assignment.networking.ApiUtils;
import com.event.trading_assignment.pojo.DataLoginDataSet;
import com.event.trading_assignment.pojo.ResponseLoginDataSet;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_userlogin;
    EditText et_email_userlogin;
    EditText et_password_userlogin;

    String userName,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btn_login_userlogin = (Button)findViewById(R.id.btn_login_userlogin);

        et_email_userlogin = (EditText)findViewById(R.id.et_email_userlogin);
        et_password_userlogin = (EditText)findViewById(R.id.et_password_userlogin);


        btn_login_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = et_email_userlogin.getText().toString();
                password = et_password_userlogin.getText().toString();

                login(userName,password);

//              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

    }

    private void login(String userName, String password) {


        APIService mAPIService = ApiUtils.getAPIService();

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("device", "web");
        requestBody.put("login_id", userName);
        requestBody.put("password", password);

        mAPIService.postlogin(requestBody).enqueue(new Callback<ResponseLoginDataSet>() {
            @Override
            public void onResponse(Call<ResponseLoginDataSet> call, Response<ResponseLoginDataSet> response) {
//                assert response.body() != null;
//                try {
//
//                    response.body();
//
//                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
//
//                } catch (Exception ignored) {
//                }



                if (response.isSuccessful()) {

                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("success")) {

                        Toast.makeText(getBaseContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

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

                      Intent intent = new Intent(LoginActivity.this, TwoFactor.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putString("qust1",data.getQuestions().get(0));
        dataBundle.putString("qust2",data.getQuestions().get(1));
        dataBundle.putString("id1", String.valueOf(data.getQuestionIds().get(0)));
        dataBundle.putString("id2", String.valueOf(data.getQuestionIds().get(1)));
        intent.putExtras(dataBundle);
                startActivity(intent);
                finish();

    }


}
