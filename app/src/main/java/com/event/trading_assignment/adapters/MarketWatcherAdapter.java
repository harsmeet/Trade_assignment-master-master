package com.event.trading_assignment.adapters;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.event.trading_assignment.MainActivity;
import com.event.trading_assignment.R;
import com.event.trading_assignment.dbHelper.DBHelper;
import com.event.trading_assignment.utilities.Navigator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketWatcherAdapter extends RecyclerView.Adapter<MarketWatcherAdapter.ViewHolder> {

    Navigator navigator;

//    Data data;

    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    MainActivity mainActivity;
    String response = "send_price";
    private LayoutInflater inflater;
//    JSONArray data;
//private FirebaseDatabase firebaseDatabase;

    ArrayList<HashMap<String, String>> data;
    private DBHelper mydb ;

    String buy_sel,quantityS,nameS,priceS,idS,exchangeS;

    public MarketWatcherAdapter(MainActivity mainActivity, Navigator navigator, ArrayList<HashMap<String, String>> data) {

        inflater = mainActivity.getLayoutInflater();
        this.navigator = navigator;
        this.data = data;
        this.mainActivity = mainActivity;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.watcher_adapter, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


//        final Product_list mdata = data.getProduct_list().get(position);
//
//
        holder.hTextViewShortName.setText(data.get(position).get("name"));
        holder.exchange.setText("Exchange: "+ data.get(position).get("exchange"));
        holder.price.setText("$ "+data.get(position).get("current_price"));
        holder.id.setText(data.get(position).get("id"));
//        holder.tvQuantity.setText(mdata.getOrdered_by());

        holder.button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mainActivity);
                LayoutInflater inflater = mainActivity.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_sel, null);
                dialogBuilder.setView(dialogView);
                EditText enter_price = (EditText) dialogView.findViewById(R.id.enter_qnt);
                EditText buy_sell = (EditText) dialogView.findViewById(R.id.enter_type);

                Button btn_send = (Button) dialogView.findViewById(R.id.btn_send);


                mydb = new DBHelper(mainActivity);

                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        nameS = data.get(position).get("name");
                        priceS = data.get(position).get("current_price");
                        idS = data.get(position).get("id");
                        exchangeS = data.get(position).get("exchange");

                        quantityS = String.valueOf(enter_price.getText());
                        buy_sel = String.valueOf(buy_sell.getText());


                        if (quantityS.equalsIgnoreCase("") || buy_sel.equalsIgnoreCase("")){

                            Toast.makeText(mainActivity,"Please Fill all Details",Toast.LENGTH_SHORT).show();


                        }
                        else {

                            mydb.insertContact(nameS,priceS,idS,exchangeS,quantityS,buy_sel);

                            Toast.makeText(mainActivity,"Buy Sucessfully",Toast.LENGTH_SHORT).show();

                        }


                    }
                });

//                ImageUpload userDetails = new ImageUpload(nameS,priceS,idS,exchangeS,buy_sel,quantityS);
//                String uploadId = databaseReference.push().getKey();
//                databaseReference.child(uploadId).setValue(userDetails);


                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

            }
        });



        holder.button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mainActivity);
                LayoutInflater inflater = mainActivity.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_sel, null);

                dialogBuilder.setView(dialogView);


                EditText enter_price = (EditText) dialogView.findViewById(R.id.enter_qnt);
                EditText buy_sell = (EditText) dialogView.findViewById(R.id.enter_type);

                Button btn_send = (Button) dialogView.findViewById(R.id.btn_send);


                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String price = String.valueOf(enter_price.getText());
                        String text = String.valueOf(buy_sell.getText());

                        if (price.equalsIgnoreCase("") || text.equalsIgnoreCase("")){

                            Toast.makeText(mainActivity,"Please Fill all Details",Toast.LENGTH_SHORT).show();


                        }
                        else {

                            Toast.makeText(mainActivity,"Sell Sucessfully",Toast.LENGTH_SHORT).show();

                        }

                    }
                });



                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hTextViewShortName)
        protected TextView hTextViewShortName;

        @BindView(R.id.hPriceBTC)
        protected TextView price;
        //
        @BindView(R.id.hVolvalue)
        protected TextView id;

        @BindView(R.id.hPriceDoll)
        protected TextView exchange;
        //
        @BindView(R.id.button_buy)
        protected Button button_buy;

        @BindView(R.id.button_sell)
        protected Button button_sell;


//        @BindView(R.id.cv_live_enquiries)
//        protected CardView cv_live_enquiries;
//        @BindView(R.id.tv_enquiry_date)
//        protected TextView tv_enquiry_date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
