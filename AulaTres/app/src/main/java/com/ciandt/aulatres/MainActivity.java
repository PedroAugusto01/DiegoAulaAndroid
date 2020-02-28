package com.ciandt.aulatres;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button button;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editEmail);
        pass = findViewById(R.id.editPass);
        button = findViewById(R.id.buttonPrimary);
        progress = findViewById(R.id.progressBar);

        pass.addTextChangedListener(new TextWatcher() {
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


        email.addTextChangedListener(new TextWatcher() {
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

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
//          Toast.makeText(MainActivity.this, "clicou", Toast.LENGTH_LONG).show();
                                          sendMessage();
                                      }
                                  }
        );
    }

    private void enableButton() {
        if (email.getText().length() > 0 && pass.getText().length() > 0) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Invalid Password or email!");
        dialogBuilder.setTitle("Login error!");
        dialogBuilder.setIcon(R.drawable.ic_alert);
        dialogBuilder.setNegativeButton("CANCEL", null);
        dialogBuilder.setCancelable(true);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToNextScreen();
            }
        });
        dialogBuilder.show();

    }



    private void sendMessage() {
        progress.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                callAPI();


            }
        }).start();

    }

    private void callbackAPI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                showDialog();
            }
        });
    }

    private void callAPI() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callbackAPI();
    }


private void goToNextScreen(){
    Intent intent = new Intent(this, DataBindingActivity.class);
    startActivity(intent);
}
}
