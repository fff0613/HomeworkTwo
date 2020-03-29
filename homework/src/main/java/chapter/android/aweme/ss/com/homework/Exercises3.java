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
public class Exercises3 extends AppCompatActivity implements ExercisesThreeAdapter.ListItemClickListener {
    private ExercisesThreeAdapter mAdapter;
    private RecyclerView mNumbersListView;
    private static final int NUM_LIST_ITEMS = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
//        设置页面布局
        mNumbersListView = findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        mNumbersListView.setHasFixedSize(true);

        try {
            InputStream assetInput = getAssets().open("data.xml");
            List<Message> messages = PullParser.pull2xml(assetInput);
            for (Message message : messages) {
                mAdapter = new ExercisesThreeAdapter(NUM_LIST_ITEMS, this,messages);
                mNumbersListView.setAdapter(mAdapter);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(int clickedItemIndex,String title) {
        Log.d("exercise2", "onListItemClick: ");
        Intent intent = new Intent(this,ChatActivity.class);
        intent.putExtra("ItemIndex",clickedItemIndex);
        intent.putExtra("Title",title);
        startActivity(intent);
    }
}


class ExercisesThreeAdapter extends RecyclerView.Adapter<ExercisesThreeAdapter.ExercisesThreeViewHolder>{
    private static final String TAG = "ExercisesThreeAdapter";

    private int mNumberItems;
    private final ListItemClickListener mOnClickListener;
    private  List<Message> messages;


    public ExercisesThreeAdapter(int numListItems, ListItemClickListener listener, List<Message> message) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        messages = message;
    }

    public ExercisesThreeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        ExercisesThreeViewHolder viewHolder = new ExercisesThreeViewHolder(view);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull ExercisesThreeViewHolder viewHolder, int position){
        Log.d(TAG, "onBindViewHolder: #" + position);
        viewHolder.bind(position);
    }

    public int getItemCount() {
        return mNumberItems;
    }

    class ExercisesThreeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView title;
        private final TextView description;
        private final ImageView imageItemView;
        private final TextView time;
        private final ImageView officialImageView;

        public ExercisesThreeViewHolder(@NonNull View itemView){
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            imageItemView = (ImageView) itemView.findViewById(R.id.iv_avatar);
            time = (TextView)itemView.findViewById(R.id.tv_time);
            officialImageView = itemView.findViewById(R.id.robot_notice);

            itemView.setOnClickListener(this);
        }

        public void bind(int position){
            title.setText(messages.get(position).getTitle());
            description.setText(messages.get(position).getDescription());
            time.setText(messages.get(position).getTime());

            if(messages.get(position).getIcon().equals("TYPE_ROBOT"))
                imageItemView.setImageResource(R.drawable.session_robot);
            if(messages.get(position).getIcon().equals("TYPE_GAME"))
                imageItemView.setImageResource(R.drawable.icon_micro_game_comment);
            if(messages.get(position).getIcon().equals("TYPE_SYSTEM"))
                imageItemView.setImageResource(R.drawable.session_system_notice);
            if(messages.get(position).getIcon().equals("TYPE_STRANGER"))
                imageItemView.setImageResource(R.drawable.session_stranger);
            if(messages.get(position).getIcon().equals("TYPE_USER"))
                imageItemView.setImageResource(R.drawable.icon_girl);

            if(messages.get(position).isOfficial())
                officialImageView.setVisibility(View.VISIBLE);
            else
                officialImageView.setVisibility(View.INVISIBLE);
        }
        public void onClick(View v){
            int clickedPosition = getAdapterPosition();
            String title = messages.get(clickedPosition).getTitle();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition,title);
            }
        }
    }
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex,String title);
    }
}
