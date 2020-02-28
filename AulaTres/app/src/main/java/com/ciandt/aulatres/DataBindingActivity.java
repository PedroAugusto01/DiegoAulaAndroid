package com.ciandt.aulatres;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ciandt.aulatres.databinding.MainBinding;

public class DataBindingActivity extends AppCompatActivity {

    private MainBinding binding;
    private int qtdClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_main);

        binding.setValor("Bem vindo!");
        binding.setQtd(qtdClick);
        binding.setUser(new User());

        binding.editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }



        });

        binding.buttonPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          Toast.makeText(MainActivity.this, "clicou", Toast.LENGTH_LONG).show();
                sendMessage();
                qtdClick += 1;
                binding.setQtd(qtdClick);
            }
        }
        );
    }

private void enableButton(){
        if (binding.editEmail.getText().length() > 0 && binding.editPass.getText().length() > 0){
            binding.buttonPrimary.setEnabled(true);
        }else{
            binding.buttonPrimary.setEnabled(false);
        }
}

private void showDialog(){
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    dialogBuilder.setMessage("Invalid Password or email!");
    dialogBuilder.setTitle("Login error!");
    dialogBuilder.setIcon(R.drawable.ic_alert);
    dialogBuilder.setNegativeButton("OK", null);
    dialogBuilder.setCancelable(true);
    dialogBuilder.show();
}

    private void sendMessage (){
        binding.progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                callAPI();


            }
        }).start();

    }
    private void callbackAPI (){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.progressBar.setVisibility(View.GONE);
                showDialog();
            }
        });
    }
    private void callAPI(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callbackAPI();
    }

}
