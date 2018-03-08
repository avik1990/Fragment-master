package pager.demo.com.fragment;

/**
 * Created by user2 on 26-02-2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BedRoom extends Fragment {
    @BindView(R.id.light)
    ImageView light;
    @BindView(R.id.parent_layout)
    RelativeLayout parent_layout;
    View view;
    EditText et_text;
    Button secondButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second, container, false);
// get the reference of Button
// perform setOnClickListener on second Button

        ButterKnife.bind(this,view);
// get the reference of Button
// perform setOnClickListener on first Button

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
      //  EventBusInit.getBus().register(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    @Produce
    public String produceEvent() {
        return new Switchboard().isBedRoomswitch()+"";
    }

    @Override
    public void onStop() {
     //   EventBusInit.getBus().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void getResult(Switchboard switchboard) {
        if (switchboard.isBedRoomswitch()){
            light.setImageResource(R.drawable.light_on);
            parent_layout.setBackgroundColor(getResources().getColor(R.color.fragmentBg_light));
            Log.e("TAG===",switchboard.isBedRoomswitch()+"");
        }
        else {
            light.setImageResource(R.drawable.light_off);
            parent_layout.setBackgroundColor(getResources().getColor(R.color.fragment_bg));
            Log.e("TAG==",switchboard.isBathRoomswitch()+"");
        }
    }
}