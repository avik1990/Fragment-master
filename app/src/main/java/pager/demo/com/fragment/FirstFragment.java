package pager.demo.com.fragment;

/**
 * Created by user2 on 26-02-2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.ghyeok.stickyswitch.widget.StickySwitch;

import static android.content.ContentValues.TAG;

public class FirstFragment extends Fragment {

    @BindView(R.id.bath_switch)
    StickySwitch bathSwitch;

    @BindView(R.id.bed_switch)
    StickySwitch bedSwitch;

    @BindView(R.id.et_txt)
    EditText et_text;

    @BindView(R.id.send)
    Button send;

    @BindView(R.id.hall_switch)
    StickySwitch hallSwitch;

    String Bed, Bath, Hall;

    View view;
    Button firstButton;

    SendMessage SM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);
        //SWITCH INITIALIZE
        ButterKnife.bind(this, view);

        bathSwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Log.d(TAG, "Now Selected : " + direction.name() + ", Current Text : " + text);
                if (text.equalsIgnoreCase("on")) {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setBathRoomswitch(true);
                    EventBusInit.getBus().post(new Switchboard());
                } else {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setBathRoomswitch(false);
                    EventBusInit.getBus().post(new Switchboard());
                }
                Bath = text;
            }
        });
        hallSwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Log.d(TAG, "Now Selected : " + direction.name() + ", Current Text : " + text);
                if (text.equalsIgnoreCase("on")) {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setHallRoomswitch(true);
                    EventBusInit.getBus().post(new Switchboard());
                } else {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setHallRoomswitch(false);
                    EventBusInit.getBus().post(new Switchboard());
                }
                Hall = text;
            }
        });

        bedSwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Log.d(TAG, "Now Selected : " + direction.name() + ", Current Text : " + text);
                if (text.equalsIgnoreCase("on")) {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setBedRoomswitch(true);
                    EventBusInit.getBus().post(new Switchboard());
                } else {
                    Switchboard switchboard = new Switchboard();
                    switchboard.setBedRoomswitch(false);
                    EventBusInit.getBus().post(new Switchboard());
                }
                Bed = text;
            }

        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.viewPager.setCurrentItem(3);
                SM.sendData(et_text.getText().toString().trim());


            }
        });

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
        //  EventBusInit.getBus().register(this);

    }

    @Override
    public void onStop() {
        //  EventBusInit.getBus().unregister(this);
        super.onStop();
    }


    //Otto EventBus for getiing value....
    @Subscribe
    public void getMessage(Switchboard switchboard) {
        Log.e("TAG", switchboard.isBathRoomswitch() + "");
        if (switchboard.isBedRoomswitch()) {
            bedSwitch.setDirection(StickySwitch.Direction.RIGHT);
        }
        if (switchboard.isHallRoomswitch()) {
            hallSwitch.setDirection(StickySwitch.Direction.RIGHT);
        }
        if (switchboard.isBathRoomswitch()) {
            bathSwitch.setDirection(StickySwitch.Direction.RIGHT);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("bed", Bed);
        outState.putSerializable("bath", Bath);
        outState.putSerializable("hall", Hall);
    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

}