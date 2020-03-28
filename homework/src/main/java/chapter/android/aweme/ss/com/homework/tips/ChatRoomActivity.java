package chapter.android.aweme.ss.com.homework.tips;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

public class ChatRoomActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        TextView position = findViewById(R.id.tv_content_info);
        Bundle bundle = getIntent().getExtras();
        position.setText("Position: " + bundle.getInt("position"));
    }
}
