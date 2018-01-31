package sgm.maksubswipeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import sgm.maksubswipeview.adapter.CardAdapter;
import sgm.maksubswipeview.flingswitch.SwipeFlingAdapterView;
import sgm.maksubswipeview.modul.CardObject;

public class MainActivity extends AppCompatActivity implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {

    SwipeFlingAdapterView mSwipeView;
    private ArrayList<CardObject> card = new ArrayList<>();
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeView = findViewById(R.id.swipe_view);
        mSwipeView.setIsNeedSwipe(true);
        mSwipeView.setFlingListener(this);
        mSwipeView.setOnItemClickListener(this);
        mAdapter = new CardAdapter(MainActivity.this, card);
        mSwipeView.setAdapter(mAdapter);
        loadData();
        mAdapter.setListener(new CardAdapter.onActionSendRequest() {
            @Override
            public void onSend(int position) {

            }
        });
    }

    private void loadData() {
        CardObject user;
        for (int i = 0; i < 5; i++) {
            user = new CardObject("ID : " + i, "Url");
            card.add(user);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {

    }

    @Override
    public void removeFirstObjectInAdapter() {
        mAdapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {

    }

    @Override
    public void onRightCardExit(Object dataObject) {

    }

    @Override
    public void onDeleteCard(Object dataObject) {
        CardObject user = (CardObject) dataObject;
        Log.e("Email", "values : " + user.getId());
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {

    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }
}
