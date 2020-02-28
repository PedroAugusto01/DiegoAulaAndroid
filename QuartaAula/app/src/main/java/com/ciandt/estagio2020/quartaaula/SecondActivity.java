package com.ciandt.estagio2020.quartaaula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ciandt.estagio2020.quartaaula.databinding.ActivityMainBinding;
import com.ciandt.estagio2020.quartaaula.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        String strTitle = this.getIntent().getStringExtra("TEXT_TITLE");
        String strLong = this.getIntent().getStringExtra("TEXT_LONG");
        binding.textView.setText(strTitle);
        binding.editText.setText(strLong);
    }

    public void saveClick(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("DATA_RESULT", binding.editText.getText().toString());
        setResult(this.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        saveClick(null);
    }
}
