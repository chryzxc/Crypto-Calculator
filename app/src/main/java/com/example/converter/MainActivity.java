package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    EditText capitalP,capitalD, buyP,buyD, sellP,sellD, totalDollar, grossProfit, netProfit,phpR,amount,interest,numDays,cumulativeInterest,phpValue;
    TextView reset;
    LinearLayout profitTable,interestTable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profitTable = (LinearLayout) findViewById(R.id.profitTable);
        interestTable = (LinearLayout) findViewById(R.id.interestTable);

        String[] arraySpinner = new String[] {
                "Profit", "Interest"
        };
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Profit"))
                {
                    hideTables();
                    profitTable.setVisibility(View.VISIBLE);
                }

                if(selectedItem.equals("Interest"))
                {
                    hideTables();
                    interestTable.setVisibility(View.VISIBLE);
                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        ImageView swapCurrency = (ImageView) findViewById(R.id.swapCurrency);

        reset = findViewById(R.id.reset);

        capitalP = findViewById(R.id.capitalP);
        capitalD = findViewById(R.id.capitalD);
        buyP = findViewById(R.id.buyP);
        buyD = findViewById(R.id.buyD);
        sellP = findViewById(R.id.sellP);
        sellD = findViewById(R.id.sellD);
        totalDollar = findViewById(R.id.tD);
        netProfit = findViewById(R.id.netProfit);
        grossProfit = findViewById(R.id.grossProfit);

        amount = findViewById(R.id.amount);
        interest = findViewById(R.id.interest);
        numDays = findViewById(R.id.numDays);
        cumulativeInterest = findViewById(R.id.cumulativeInterest);
        phpValue = findViewById(R.id.phpValue);

        phpR = findViewById(R.id.phpR);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        phpR.setText(preferences.getString("phpRate", ""));

        reset.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {

                capitalP.setText(null);
                capitalD.setText(null);
                buyP.setText(null);
                buyD.setText(null);
                sellP.setText(null);
                sellD.setText(null);
                totalDollar.setText(null);
                grossProfit.setText(null);
                netProfit.setText(null);

                amount.setText(null);
                interest.setText(null);
                numDays.setText(null);
                cumulativeInterest.setText(null);
            }
        });



        phpR.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phpRate", phpR.getText().toString());
                editor.apply();


            }
        });





        capitalP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String string = capitalP.getText().toString();
                if (!string.isEmpty()){
                    getProfit("capitalP");
                }else{
                    clear("capitalP");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        capitalD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String string = capitalD.getText().toString();
                if (!string.isEmpty()){
                    getProfit("capitalD");
                }else{
                    clear("capitalD");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buyP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = buyP.getText().toString();
                if (!string.isEmpty()){
                    getProfit("buyP");
                }else{
                    clear("buyP");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buyD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = buyD.getText().toString();
                if (!string.isEmpty()){
                    getProfit("buyD");
                }else{
                    clear("buyD");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sellP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mPHP = sellP.getText().toString();
                if (!mPHP.isEmpty()){
                    getProfit("sellP");
                }else{
                    clear("sellP");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        sellD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mD = sellD.getText().toString();
                if (!mD.isEmpty()){
                    getProfit("sellD");
                }else{
                    clear("sellD");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = amount.getText().toString();
                if (!string.isEmpty()){
                   getInterest();
                }else{
                    clearInterest();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        interest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = interest.getText().toString();
                if (!string.isEmpty()){
                    getInterest();
                }else{
                    clearInterest();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        numDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              getInterest();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        swapCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String b = buyP.getText().toString();
                String s = sellP.getText().toString();
                buyP.requestFocus();
                buyP.setText(s);
                sellP.requestFocus();
                sellP.setText(b);
            }
        });


        spinner.clearFocus();
    }

    public void hideTables(){
        profitTable.setVisibility(View.GONE);
        interestTable.setVisibility(View.GONE);
    }

    public void clearInterest(){
        cumulativeInterest.setText(null);
        phpValue.setText(null);

    }

    public void getInterest(){

        String amountx = amount.getText().toString();
        String interestx = interest.getText().toString();
        String numDaysx = numDays.getText().toString();
        String phpx = phpR.getText().toString();

        if (!interestx.isEmpty() && !amountx.isEmpty() && numDaysx.isEmpty()){
            BigDecimal finalInterest = new BigDecimal(interestx);
            BigDecimal hundred = new BigDecimal(100);
            BigDecimal year = new BigDecimal(365);
            BigDecimal a = new BigDecimal(amountx);
            BigDecimal i = finalInterest.divide(hundred,15,RoundingMode.DOWN);
            BigDecimal perYear = a.multiply(i);
            BigDecimal perDay = perYear.divide(year,8,RoundingMode.DOWN);
            BigDecimal phpVal = perDay.multiply(new BigDecimal(phpx));
            cumulativeInterest.setText(perDay.toPlainString() + " (1 day)");
            phpValue.setText("₱" +phpVal.toString());
        }

        if (!interestx.isEmpty() && !amountx.isEmpty() && !numDaysx.isEmpty()){
            BigDecimal finalInterest = new BigDecimal(interestx);
            BigDecimal hundred = new BigDecimal(100);
            BigDecimal year = new BigDecimal(365);
            BigDecimal a = new BigDecimal(amountx);
            BigDecimal i = finalInterest.divide(hundred,15,RoundingMode.DOWN);
            BigDecimal num = new BigDecimal(numDaysx);
            BigDecimal perYear = a.multiply(i);
            BigDecimal perDay = perYear.divide(year,8,RoundingMode.DOWN);
            BigDecimal perNumDays = perDay.multiply(num);
            BigDecimal phpVal = perNumDays.multiply(new BigDecimal(phpx));
            cumulativeInterest.setText(perNumDays.toPlainString() + " (" + String.valueOf(num) + " days)");
            phpValue.setText("₱" +phpVal.toString());
        }


    }



    public void getProfit(String currency) {

        String capitalx = capitalP.getText().toString();
        String bPx = buyP.getText().toString();
        String sPx = sellP.getText().toString();



        if (currency.equals("capitalP")){

            if (capitalP.hasFocus()){

                if (capitalP.getText().toString().equals(".")){

                }else{
                      BigDecimal cP = new BigDecimal(capitalP.getText().toString());
                      BigDecimal php = new BigDecimal(phpR.getText().toString());
                      BigDecimal currentCapital = cP.divide(php,15,RoundingMode.DOWN);
                      capitalD.setText(currentCapital.toPlainString());
                }


            }


        }

        if (currency.equals("capitalD")){

            if (capitalD.hasFocus()){

                if (capitalD.getText().toString().equals(".")){

                }else{
                    BigDecimal cD = new BigDecimal(capitalD.getText().toString());
                       BigDecimal php = new BigDecimal(phpR.getText().toString());
                       BigDecimal currentCapital = cD.multiply(php);
                       capitalP.setText(currentCapital.toPlainString());
                }

            }


        }


        if (currency.equals("buyP")){

            if (buyP.hasFocus()){

                if (buyP.getText().toString().equals(".")){

                }else{
                    BigDecimal bP = new BigDecimal(buyP.getText().toString());
                    BigDecimal php = new BigDecimal(phpR.getText().toString());
                    BigDecimal currentCapital = bP.divide(php,15,RoundingMode.DOWN);

                    buyD.setText(currentCapital.toPlainString());
                }


            }


        }

        if (currency.equals("buyD")){

            if (buyD.hasFocus()){

                if (buyD.getText().toString().equals(".")){

                }else{
                    BigDecimal bD = new BigDecimal(buyD.getText().toString());
                    BigDecimal php = new BigDecimal(phpR.getText().toString());
                    BigDecimal currentCapital = bD.multiply(php);

                    buyP.setText(currentCapital.toPlainString());
                }

            }


        }



        if (currency.equals("sellP")){

            if (sellP.hasFocus()){

                if (sellP.getText().toString().equals(".")){

                }else{
                    BigDecimal sD = new BigDecimal(sellP.getText().toString());
                    BigDecimal php = new BigDecimal(phpR.getText().toString());
                    BigDecimal currentCapital = sD.divide(php,15,RoundingMode.DOWN);

                    sellD.setText(currentCapital.toPlainString());
                }

            }


        }
        if (currency.equals("sellD")){

            if (sellD.hasFocus()){

                if (sellD.getText().toString().equals(".")){

                }else{
                    BigDecimal sP = new BigDecimal(sellD.getText().toString());
                    BigDecimal php = new BigDecimal(phpR.getText().toString());
                    BigDecimal currentCapital = sP.multiply(php);

                    sellP.setText(currentCapital.toPlainString());
                }

            }

        }

        if (!capitalx.isEmpty() && !bPx.isEmpty() && !sPx.isEmpty() ) {

            Double dollarAmount = Double.valueOf(capitalP.getText().toString()) / Double.valueOf(buyP.getText().toString());
            Double net = dollarAmount * Double.valueOf(sellP.getText().toString());
            Double gross = net - Double.valueOf(capitalP.getText().toString());

            Double percent = gross / Double.valueOf(capitalP.getText().toString());
            Double percentFinal = percent * 100;

            totalDollar.setText(dollarAmount.toString()  );
            netProfit.setText("₱" + String.format("%.2f", net));
            grossProfit.setText("₱"+ String.format("%.2f", gross) + " (" + String.format("%.2f", percentFinal) + "%)");

        }


    }


    public void clear(String currency) {


        if (currency.equals("capitalP")) {

            if (capitalP.hasFocus()) {

                capitalD.setText("");
            }


        }

        if (currency.equals("capitalD")) {

            if (capitalD.hasFocus()) {

                capitalP.setText("");
            }


        }

        if (currency.equals("buyP")) {

            if (buyP.hasFocus()) {

                buyD.setText("");
            }


        }

        if (currency.equals("buyD")) {

            if (buyD.hasFocus()) {

                buyP.setText("");
            }


        }


        if (currency.equals("sellP")) {

            if (sellP.hasFocus()) {

                sellD.setText("");
            }


        }
        if (currency.equals("sellD")) {

            if (sellD.hasFocus()) {

                sellP.setText("");

            }

        }


    }
}