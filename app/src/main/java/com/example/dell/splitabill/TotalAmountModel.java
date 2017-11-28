package com.example.dell.splitabill;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
/**
 * Created by DELL on 11/27/2017.
 */

public class TotalAmountModel extends BaseObservable{
    private String totalAmountHint;
    private String totalAmount;
    private String continueButtonText;

    public TotalAmountModel(String totalAmountHint, String continueButtonText, String totalAmount) {
        this.totalAmountHint = totalAmountHint;
        this.continueButtonText = continueButtonText;
        this.totalAmount = totalAmount;
    }
    public String getTotalAmountHint() {
        return totalAmountHint;
    }
    @Bindable
    public void setTotalAmountHint(String totalAmountHint) {
        this.totalAmountHint = totalAmountHint;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        /** To get value of edittext enterd by user, This Updates the value of userEmail on Every LEtter Entered by User*/
        notifyPropertyChanged(R.id.TotalAmount);
        /**to check Email for validation on every character inserted by user*/
        //Similarly This
//        notifyPropertyChanged(BR.errorEmail);
    }
    public String getContinueButtonText() {
        return continueButtonText;
    }

    public void setContinueButtonText(String continueButtonText) {
        this.continueButtonText = continueButtonText;
    }
}
