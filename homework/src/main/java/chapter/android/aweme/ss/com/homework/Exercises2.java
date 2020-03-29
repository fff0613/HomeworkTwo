package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity{
    private TextView tView;
    private ViewGroup relativeLayoutVGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);

        tView = findViewById(R.id.tv_center);
        relativeLayoutVGroup = findViewById(R.id.vgroup_re);
        tView.setText(getAllChildViewCount(relativeLayoutVGroup)+"");
}

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        if(view == null)
            return 0;

        int subCount,viewCount = 0;
        View node = view;
        LinkedList<View> views = new LinkedList<View>();

        if(node instanceof ViewGroup)
            views.add(node);
        else
            viewCount = 1;

        while(!views.isEmpty())
        {
            node = views.removeFirst();
            subCount = ((ViewGroup) node).getChildCount();

            for(int i = 0;i < subCount;i++)
                if(((ViewGroup) node).getChildAt(i) instanceof ViewGroup)
                    views.add(((ViewGroup) node).getChildAt(i));
                else
                    viewCount++;
        }

        return viewCount;
    }
}
