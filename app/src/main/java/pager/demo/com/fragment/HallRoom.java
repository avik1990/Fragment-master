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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user2 on 26-02-2018.
 */

public class HallRoom extends Fragment {

    View view;
    Button firstButton;
    @BindView(R.id.light)
    ImageView light;
    @BindView(R.id.msg)
    TextView txtData;


    @BindView(R.id.parent_layout)
    RelativeLayout parent_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view  = inflater.inflate(R.layout.fragment_forth, container, false);
// get the reference of Button
        ButterKnife.bind(this,view);
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
    @Produce
    public boolean produceEvent() {
        return new Switchboard().isHallRoomswitch();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Subscribe
    public void getResult(Switchboard switchboard) {
        if (switchboard.isHallRoomswitch()){
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


    protected void displayReceivedData(String message)
    {
        txtData.setText("Data received: "+message);
    }
}
