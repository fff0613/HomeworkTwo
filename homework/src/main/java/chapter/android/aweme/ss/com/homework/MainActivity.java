package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_exercises1).setOnClickListener(this);
        findViewById(R.id.btn_exercises2).setOnClickListener(this);
        findViewById(R.id.btn_exercises3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exercises1:
                startActivity(new Intent(this, Exercises1.class));
                Log.d("Mainjava","exercise1");
                break;
            case R.id.btn_exercises2:
                startActivity(new Intent(this, Exercises2.class));
                Log.d("Mainjava","exercise2");
                break;
            case R.id.btn_exercises3:
                startActivity(new Intent(this, Exercises3.class));
                Log.d("Mainjava","exercise3");
                break;
        }
    }
}
