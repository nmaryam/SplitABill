package com.example.dell.splitabill;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import android.text.TextWatcher;
import android.text.Editable;

public class Main2Activity extends Activity {
    private SeekBar tipSeekbar;
    private Spinner noOfPersonSpinner;
    private int totalBillValue;
    private int tipSeekbarValue = 0;
    private int tipEditTextValue = 0;
    private int noOfPersonValue = 1;
    private EditText tipEditText;
    private TextView BillPerPerson;
    private int BillPerPersonValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int message = Integer.parseInt(intent.getStringExtra(MainActivity.TOTALAMOUNT));
        totalBillValue = message;
        TextView textView = findViewById(R.id.AmountText);
        noOfPersonSpinner = (Spinner)findViewById(R.id.NumberOfPeople);
        tipSeekbar = (SeekBar)findViewById(R.id.TipSeekbar);
        tipEditText = (EditText) findViewById(R.id.TipEditText);
        tipSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipSeekbarValue = progress;
                if(tipSeekbarValue != tipEditTextValue) {
                    tipEditTextValue = tipSeekbarValue;
                    tipEditText.setText(String.valueOf(tipSeekbarValue));
                    CalculateTotalPayment();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                tipPercentLabel.setText("Tip Percent - " + seekBar.getProgress());
            }

        });
        tipEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tipEditTextValue = Integer.parseInt(tipEditText.getText().toString());
                if(tipSeekbarValue != tipEditTextValue) {
                    tipSeekbarValue = tipEditTextValue;
                    tipSeekbar.setProgress(tipEditTextValue);
                    CalculateTotalPayment();
                }
            }
        });

        noOfPersonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Toast.makeText(parentView.getContext(), "No of persons are " +
                        parentView.getItemAtPosition(position).toString() + "and tip is "+ tipSeekbarValue, Toast.LENGTH_LONG).show();
                noOfPersonValue = Integer.parseInt(parentView.getItemAtPosition(position).toString());
                CalculateTotalPayment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        textView.setText(String.valueOf(totalBillValue));
//        textView.setText(message);

    }

    void CalculateTotalPayment(){
        BillPerPerson = findViewById(R.id.BillPerPerson);
        double percentageOfTip = (totalBillValue * tipSeekbarValue) / 100;
        double totalAmountForTheBill = totalBillValue + percentageOfTip;
        double tipPerEachPerson = percentageOfTip / noOfPersonValue;
        double billPerEachPerson = totalAmountForTheBill / noOfPersonValue;
        BillPerPerson.setText(String.format("%.0f",billPerEachPerson));

    }
}
