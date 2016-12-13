package com.lee.recycview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeeAdapter adapter;

    TextView handTv;
    TextView footTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handTv.setText("这是头布局");
                footTv.setText("这是footer");
                //adapter.notifyDataSetChanged();
            }
        });

        init();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.bottom=20;
//            }
//
//            @Override
//            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//
//                Canvas cc=new Canvas();
//
//                Bitmap bitmap=Bitmap.createBitmap(new int[]{R.color.colorAccent},100,100, Bitmap.Config.ARGB_4444);
//                cc.setBitmap(bitmap);
//
//                super.onDrawOver(cc, parent, state);
//
//            }
//        });

        recyclerView.setAdapter(adapter);

    }

    private void init() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("测试" + i);
        }
        adapter = new LeeAdapter(list, this);

        View handView = View.inflate(this, R.layout.hand_view, null);
        View footView = View.inflate(this, R.layout.hand_view, null);
        handTv = (TextView) handView.findViewById(R.id.hand_tv);
        footTv = (TextView) footView.findViewById(R.id.hand_tv);

        adapter.setHanfView(handView);
        adapter.setFootView(footView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
