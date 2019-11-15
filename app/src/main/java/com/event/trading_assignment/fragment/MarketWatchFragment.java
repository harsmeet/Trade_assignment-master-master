package com.event.trading_assignment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.event.trading_assignment.BaseFragment;
import com.event.trading_assignment.R;
import com.event.trading_assignment.adapters.MarketWatcherAdapter;
import com.event.trading_assignment.utilities.Navigator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MarketWatchFragment extends BaseFragment {


   private RecyclerView rv;


     MarketWatcherAdapter marketWatcherAdapter;

    ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
    Navigator navigator;

    private View root_view;


    @Override
    protected int getLayout() {


        return R.layout.market_watch_fragment;


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.market_watch_fragment, null);
        navigator = getMainActivity().getNavigator();

        //Initializing viewPager
        rv = (RecyclerView) root_view.findViewById(R.id.rv);

//        loadJSONFromAsset();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray m_jArry = obj.getJSONArray("stocks");

            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                try {

                    JSONObject jo_inside = m_jArry.getJSONObject(i);


//                Log.d("Details-->", jo_inside.getString("formule"));

                    String name = jo_inside.getString("name");
                    String id = jo_inside.getString("id");
                    String exchange = jo_inside.getString("exchange");
                    String price = jo_inside.getString("current_price");

                    //Add your values in your `ArrayList` as below:

                    m_li = new HashMap<String, String>();
                    m_li.put("name", name);
                    m_li.put("id", id);
                    m_li.put("exchange", exchange);
                    m_li.put("current_price", price);

                    formList.add(m_li);

                }catch (Exception e){

                    e.printStackTrace();
                }

            }

            sendData(formList);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        return root_view;

    }


    private void sendData(ArrayList<HashMap<String, String>> formList) {

        marketWatcherAdapter = new MarketWatcherAdapter(getMainActivity(), navigator, formList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(marketWatcherAdapter);


    }


    public String loadJSONFromAsset() {

        String json = null;

        try {

            InputStream is = getActivity().getAssets().open("marketwatch.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {

            ex.printStackTrace();
            return null;

        }

        return json;

    }



}