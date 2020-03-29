package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {
    private TextView itemText;
    private TextView title;
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        Intent intent = getIntent();
        int itemIndex = intent.getIntExtra("ItemIndex",-1);
        if(itemIndex != -1){
            itemText = findViewById(R.id.tv_content_info);
            itemText.setText("item " +(itemIndex+1));
        }
        title = findViewById(R.id.tv_with_name);
        title.setText("我与"+intent.getStringExtra("Title")+"的对话");
    }
}
