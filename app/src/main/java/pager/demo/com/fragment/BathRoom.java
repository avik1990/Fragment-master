package pager.demo.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user2 on 26-02-2018.
 */

public class BathRoom extends Fragment {

    View view;
    Button firstButton;

    @BindView(R.id.light)
    ImageView light;
    @BindView(R.id.parent_layout)
    RelativeLayout parent_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view  = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this,view);
        this.setRetainInstance(true);
// get the reference of Button
// perform setOnClickListener on first Button

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBusInit.getBus().register(this);
    }

    @Override
    public void onPause() {
        EventBusInit.getBus().unregister(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
    @Produce
    public Switchboard produceEvent() {
        return new Switchboard();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Subscribe
    public void getResult(Switchboard switchboard) {
    if (switchboard.isBathRoomswitch()){
        light.setImageResource(R.drawable.light_on);
        parent_layout.setBackgroundColor(getResources().getColor(R.color.fragmentBg_light));
        Log.e("TAG",switchboard.isBathRoomswitch()+"");
    }
    else {
        light.setImageResource(R.drawable.light_off);
        parent_layout.setBackgroundColor(getResources().getColor(R.color.fragment_bg));
        Log.e("TAG",switchboard.isBathRoomswitch()+"");
    }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
         super.onSaveInstanceState(outState);
        Log.v("TAG", "In frag's on save instance state ");
       //  outState.putSerializable("starttime", startTime);
       }
}
