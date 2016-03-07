package com.limitium.smarttipper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.limitium.smarttipper.core.GreedMode;
import com.limitium.smarttipper.core.Profession;

import java.util.Observable;
import java.util.Observer;

/**
 * A fragment representing a single Profession detail screen.
 * This fragment is either contained in a {@link ProfessionListActivity}
 * in two-pane mode (on tablets) or a {@link ProfessionDetailActivity}
 * on handsets.
 */
public class ProfessionDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    String greeds[];
    private Profession profession;
    private TextView seekBarValue;
    private TextView totalPerPerson;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfessionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            greeds = new String[]{
                    getString(R.string.greed_low), getString(R.string.greed_average), getString(R.string.greed_high)
            };
            profession = ProfessionListActivity.PROFESSIONS.get(Integer.parseInt((String) getArguments().get(ARG_ITEM_ID)));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getString(profession.getName()));
        }

        ImageView professionIcon = (ImageView) activity.findViewById(R.id.ficon);
        if (professionIcon != null) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), profession.getIcon());
            professionIcon.setImageBitmap(ProfessionListActivity.getCircleBitmap(bm, profession.getColor(), getActivity().getApplicationContext()));
        }

        profession.getTipStrategy().deleteObservers();

        View rootView = inflater.inflate(R.layout.profession_detail, container, false);

        setUpGreedBar(rootView);
        setUpPersons(rootView);

        profession.getTipStrategy().getInflater().inflateAndSetUp((ViewGroup) rootView.findViewById(R.id.tip_special), profession.getTipStrategy());

        totalPerPerson = (TextView) rootView.findViewById(R.id.per_person_total);

        profession.getTipStrategy().addCalculateObserver(new Observer() {
            @Override
            public void update(Observable observable, Object data) {
                totalPerPerson.setText(String.format("%.2f", (float) data));
            }
        });
        profession.getTipStrategy().recalculate();
        return rootView;
    }

    private void setUpPersons(View rootView) {
        final EditText orderPersons = (EditText) rootView.findViewById(R.id.order_persons);
        orderPersons.setText(String.valueOf(profession.getTipStrategy().getPersons()));
        orderPersons.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int persons = s.length() == 0 ? 1 : Integer.valueOf(s.toString());
                if (persons < 1) {
                    persons = 1;
                    orderPersons.setText(String.valueOf(persons));
                }
                profession.getTipStrategy().setPersons(persons);
            }
        });
    }

    private void setUpGreedBar(View rootView) {
        SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.greed_mode);
        seekBar.setProgress(profession.getTipStrategy().getGreed().ordinal() * 100);
        seekBar.setMax(200);
        seekBarValue = (TextView) rootView.findViewById(R.id.greed_level);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    setGreedMode(seekBar);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int position = setGreedMode(seekBar);
                seekBar.setProgress(position * 100);
            }

        });
        setGreedMode(seekBar);
    }

    private int setGreedMode(SeekBar seekBar) {
        float v = seekBar.getProgress() / 100f;
        int position = Math.round(v);
        seekBarValue.setText(getString(R.string.tips_level) + " " + greeds[position]);
        profession.getTipStrategy().setGreed(GreedMode.values()[position]);
        return position;
    }
}
