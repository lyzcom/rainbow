package com.lyz.rainbow.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lyz.rainbow.R;
import com.lyz.rainbow.data.bean.Essay;
import com.lyz.rainbow.presenter.EssayPresenter;
import com.lyz.rainbow.view.EssayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ring on 17/7/18.
 */

public class EssayActivity extends AppCompatActivity implements EssayView {

    private List<Essay> datas = new ArrayList<>();

    private EssayAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);
        initView();
    }

    private void initView() {
        EssayPresenter essayPresenter = new EssayPresenter(this);
        essayPresenter.LoadEssays(); //开始加载

        ListView listView = (ListView) findViewById(R.id.listview);

        mAdapter = new EssayAdapter(this, R.layout.essay_item, datas);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void showEssay(List<Essay> list) {
        datas.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    private class EssayAdapter extends ArrayAdapter<Essay> {

        private Context mContext;
        private LayoutInflater inflater;

        EssayAdapter(@NonNull Context context, @LayoutRes int resource, List<Essay> arrayList) {
            super(context, resource, arrayList);
            mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.essay_item, parent, false);
            }

            Essay essay = getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.tv_content);
            assert essay != null;
            textView.setText(essay.getTitle());

            return convertView;
        }
    }
}
