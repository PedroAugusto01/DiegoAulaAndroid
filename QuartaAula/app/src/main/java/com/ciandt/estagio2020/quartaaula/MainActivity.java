package com.ciandt.estagio2020.quartaaula;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.ciandt.estagio2020.quartaaula.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final int RESULT_DATA_SCREEN = 100120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding != null) {
            loadData();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public void goToNextScreen(View v) {
        String strTitle = binding.editText.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("TEXT_TITLE", strTitle);
        intent.putExtra("TEXT_LONG", binding.textViewResult.getText().toString());
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strResult = data.getStringExtra("DATA_RESULT");
        binding.textViewResult.setText(strResult);
    }

    private void saveData() {
            SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("SWITCH", binding.switch1.isChecked());
            if (binding.switch1.isChecked()) {
                editor.putString("TITLE", binding.editText.getText().toString());
                editor.putString("MESSAGE", binding.textViewResult.getText().toString());
            }
            editor.apply();

    }

    private void loadData() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPref.getBoolean("SWITCH", false);
        String title = sharedPref.getString("TITLE", "");
        String message = sharedPref.getString("MESSAGE", "");
        binding.switch1.setChecked(isChecked);
        binding.editText.setText(title);
        if (binding.textViewResult.getText().toString().isEmpty()) {
            binding.textViewResult.setText(message);
        }

    }
}
