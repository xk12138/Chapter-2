package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.tips.ChatRoomActivity;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        recyclerView = findViewById(R.id.rv_list);
        List<Message> messages = null;
        try {
            InputStream stream = getAssets().open("data.xml");
            messages = PullParser.pull2xml(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(Exercises3.this));
        recyclerView.setAdapter(new MyRecyclerAdapter(Exercises3.this, messages));
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter {
        private String TAG = "MyRecyclerAdapter";

        private Context context;
        private List<Message> messages;
        public MyRecyclerAdapter(Context context, List<Message> messages) {
            this.context = context;
            this.messages = messages;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Message message = messages.get(i);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.im_list_item, viewGroup, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            Message message = messages.get(i);
            if(viewHolder instanceof  MyViewHolder) {
                MyViewHolder myViewHolder = (MyViewHolder)viewHolder;
                myViewHolder.title.setText(message.getTitle());
                myViewHolder.time.setText(message.getTime());
                myViewHolder.description.setText(message.getDescription());
                switch(message.getIcon()) {
                    case "TYPE_ROBOT":
                        myViewHolder.avatar.setImageResource(R.drawable.session_robot);
                        break;
                    case "TYPE_USER":
                        myViewHolder.avatar.setImageResource(R.drawable.icon_girl);
                        break;
                    case "TYPE_SYSTEM":
                        myViewHolder.avatar.setImageResource(R.drawable.session_system_notice);
                        break;
                    case "Type_STRANGER":
                        myViewHolder.avatar.setImageResource(R.drawable.session_stranger);
                        break;
                    case "TYPE_GAME":
                        myViewHolder.avatar.setImageResource(R.drawable.icon_micro_game_comment);
                        break;
                }
                if(message.isOfficial()) {
                    myViewHolder.notice.setVisibility((View.VISIBLE));
                } else {
                    myViewHolder.notice.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView title, description, time;
            public CircleImageView avatar;
            public ImageView notice;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.tv_title);
                description = view.findViewById(R.id.tv_description);
                time = view.findViewById(R.id.tv_time);
                avatar = view.findViewById(R.id.iv_avatar);
                notice = view.findViewById(R.id.robot_notice);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Log.d(TAG, "Position:" + position + " is clicked.");
                Intent intent = new Intent(context, ChatRoomActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        }
    }


}
