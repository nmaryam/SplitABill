package com.example.dell.splitabill;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by DELL on 11/28/2017.
 */

public class SplitBillModel extends BaseObservable {
    private String amountTextLabel;
    private String userBillLabel;
    private String percentLabel;

    private String peopleDropdownSpinner;
    private String tipEditTextHint;

    private String amountTextValue;
    private int numberOfPeopleValue;
    private int tipSeekbarValue;
    private String tipEditTextValue;
    private String userBillValue;

    public SplitBillModel(String amountTextLabel, String userBillLabel, String percentLabel, String peopleDropdownSpinner, String tipEditTextHint, String amountTextValue, int numberOfPeopleValue,int tipSeekbarValue, String tipEditTextValue) {
        this.amountTextLabel = amountTextLabel;
        this.userBillLabel = userBillLabel;
        this.percentLabel = percentLabel;

        this.peopleDropdownSpinner = peopleDropdownSpinner;
        this.tipEditTextHint = tipEditTextHint;

        this.amountTextValue = amountTextValue;
        this.numberOfPeopleValue = numberOfPeopleValue;
        this.tipSeekbarValue = tipSeekbarValue;
        this.tipEditTextValue = tipEditTextValue;
    }
    //amountTextLabel
    public String getAmountTextLabel() {
        return amountTextLabel;
    }
    @Bindable
    public void setAmountTextLabel(String amountTextLabel) {
        this.amountTextLabel = amountTextLabel;
    }
    //userBillLabel
    public String getUserBillLabel(){
        return userBillLabel;
    }
    @Bindable
    public void setUserBillLabel(String userBillLabel){
        this.userBillLabel = userBillLabel;
    }
    //percentLabel
    public String getPercentLabel(){
        return percentLabel;
    }
    @Bindable
    public void setPercentLabel(String percentLabel){
        this.percentLabel = percentLabel;
    }
    //peopleDropdownSpinner
    public String getPeopleDropdownSpinner(){
        return peopleDropdownSpinner;
    }
    @Bindable
    public void setPeopleDropdownSpinner(String peopleDropdownSpinner){
        this.peopleDropdownSpinner = peopleDropdownSpinner;
    }
    //tipEditTextHint
    public String getTipEditTextHint(){
        return tipEditTextHint;
    }
    @Bindable
    public void setTipEditTextHint(String tipEditTextHint){
        this.tipEditTextHint = tipEditTextHint;

    }
    //amountTextValue
    public String getAmountTextValue(){
        return amountTextValue;
    }
    @Bindable
    public void setAmountTextValue(String amountTextValue){
        this.amountTextValue = amountTextValue;
    }
    //numberOfPeopleValue
    public int getNumberOfPeopleValue(){
        return numberOfPeopleValue;
    }
    @Bindable
    public void setNumberOfPeopleValue(int numberOfPeopleValue){
        this.numberOfPeopleValue = numberOfPeopleValue;
        notifyPropertyChanged(R.id.NumberOfPeople);
        notifyPropertyChanged(BR.numberOfPeopleValue);
    }
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        // your code here
        Toast.makeText(parentView.getContext(), "No of persons are " + parentView.getItemAtPosition(position).toString() + "and tip is "+ tipSeekbarValue, Toast.LENGTH_LONG).show();
       String s = parentView.getItemAtPosition(position).toString();
        if(s != "") {
            this.setNumberOfPeopleValue(Integer.parseInt(parentView.getItemAtPosition(position).toString()));
            this.CalculateTotalPayment();
        }
    }
    //tipSeekbarValue
    public int getTipSeekbarValue(){
        return tipSeekbarValue;
    }
    @Bindable
    public void setTipSeekbarValue(int tipSeekbarValue){
        this.tipSeekbarValue = tipSeekbarValue;
        notifyPropertyChanged(R.id.TipSeekbar);
        notifyPropertyChanged(BR.tipSeekbarValue);
    }
//
//    public void onValueChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        this.setTipSeekbarValue(progress);
//        int seekbarValue = this.getTipSeekbarValue();
//        int tipeditValue = Integer.parseInt(this.getTipEditTextValue());
////        AdapterView<?> ParentView =;
////        Toast.makeText( "No of persons are ", Toast.LENGTH_LONG).show();
//        if(seekbarValue != tipeditValue) {
//            this.setTipEditTextValue(String.valueOf(seekbarValue));
////            this.tipEditTextValue = String.valueOf(seekbarValue);
//            this.CalculateTotalPayment();
//        }
//    }

    //tipEditTextValue
    public String getTipEditTextValue(){
        return tipEditTextValue;
    }
    @Bindable
    public void setTipEditTextValue(String tipEditTextValue){
        this.tipEditTextValue = tipEditTextValue;
        notifyPropertyChanged(R.id.TipEditText);
        notifyPropertyChanged(BR.tipEditTextValue);
    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int seekbarValue = this.getTipSeekbarValue();
        int tipeditValue = Integer.parseInt(String.valueOf(s));
        if(seekbarValue != tipeditValue) {
            this.setTipSeekbarValue(tipeditValue);
            this.CalculateTotalPayment();
        }
    }
    //userBillValue
    public String getUserBillValue(){
        return userBillValue;
    }
    @Bindable
    public void setUserBillValue(String userBillValue){
        this.userBillValue = userBillValue;
        notifyPropertyChanged(R.id.BillPerPerson);
        notifyPropertyChanged(BR.userBillValue);
    }

    public void CalculateTotalPayment(){
        double amountValue = Integer.parseInt(this.getAmountTextValue());
        double percentageOfTip = ( amountValue * this.getTipSeekbarValue()) / 100;
        double totalAmountForTheBill = amountValue + percentageOfTip;
        double tipPerEachPerson = percentageOfTip / this.getNumberOfPeopleValue();
        double billPerEachPerson = totalAmountForTheBill / this.getNumberOfPeopleValue();

        this.setUserBillValue(String.format("%.0f",billPerEachPerson));
        String s = this.getUserBillValue();

    }

}
