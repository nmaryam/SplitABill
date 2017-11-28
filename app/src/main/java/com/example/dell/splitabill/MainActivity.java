package com.example.dell.splitabill;

import android.databinding.DataBindingUtil;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//import android.cont

import com.example.dell.splitabill.ClickEvent.ClickListener;
import com.example.dell.splitabill.databinding.TotalAmountBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TOTALAMOUNT = "";
    public static int _bindTotalAmountValue = 232;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TotalAmountBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        final TotalAmountModel viewModel=new TotalAmountModel("Enter Email","Enter Password","00");
        binding.setTotalAmountVariable(viewModel);

        binding.setModelClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this ,"No of persons are and tip is ", Toast.LENGTH_LONG).show();
//                viewModel.setTotalAmount("900");
//                binding.setTotalAmountVariable(viewModel);

                Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                _bindTotalAmountValue = Integer.parseInt(binding.getTotalAmountVariable().getTotalAmount());
                myIntent.putExtra(TOTALAMOUNT,String.valueOf(_bindTotalAmountValue)); //Optional parameters
                MainActivity.this.startActivity(myIntent);

                // On Click It will display all the values
            }
        });
    }
//    public void sendMessage(View view) {
//        // Do something in response to button
//        Toast.makeText(MainActivity.this ,"No of persons are and tip is ", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, Main2Activity.class);
//        intent.putExtra(EXTRA_MESSAGE, ss);
//        startActivity(intent);
//    }
}
