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
import com.limitium.smarttipper.core.TipCalculator;
import com.limitium.smarttipper.core.tips.PercentTipStrategy;

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

    /**
     * The dummy content this fragment is presenting.
     */
    String greeds[];
    private Profession profession;
    private TextView seekBarValue;
    private TextView totalPerPerson;
    private Integer persons = 1;
    private GreedMode greed = GreedMode.AVERAGE;
    private TipCalculator tipCalculator = new TipCalculator();
    private Float total = 0f;

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

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(getString(profession.getName()));
            }

            ImageView professionIcon = (ImageView) activity.findViewById(R.id.ficon);
            if (professionIcon != null) {
                Bitmap bm = BitmapFactory.decodeResource(getResources(), profession.getIcon());
                professionIcon.setImageBitmap(ProfessionListActivity.getCircleBitmap(bm, profession.getColor(), getActivity().getApplicationContext()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profession_detail, container, false);

        totalPerPerson = (TextView) rootView.findViewById(R.id.per_person_total);
        setUpGreedBar(rootView);
        setUpPersons(rootView);
        setUpTotal(rootView);

        recalculate();
        return rootView;
    }


    private void setUpTotal(View rootView) {
        final EditText orderTotal = (EditText) rootView.findViewById(R.id.total_order);
        orderTotal.setText(total.toString());
        orderTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                total = s.length() == 0 ? 0 : Float.valueOf(s.toString());
                if (total < 0) {
                    total = 0f;
                    orderTotal.setText(total.toString());
                }
                recalculate();
            }
        });
    }

    private void setUpPersons(View rootView) {
        final EditText orderPersons = (EditText) rootView.findViewById(R.id.order_persons);
        orderPersons.setText(persons.toString());
        orderPersons.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                persons = s.length() == 0 ? 1 : Integer.valueOf(s.toString());
                if (persons < 1) {
                    persons = 1;
                    orderPersons.setText(persons.toString());
                }
                recalculate();
            }
        });
    }


    private void setUpGreedBar(View rootView) {
        SeekBar seekBar = (SeekBar) rootView.findViewById(R.id.greed_mode);
        seekBar.setProgress(greed.ordinal());
        seekBar.setMax(2);
        seekBarValue = (TextView) rootView.findViewById(R.id.greed_level);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateGreed(seekBar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
        updateGreed(seekBar);
    }

    public void updateGreed(SeekBar seekBar) {
        seekBarValue.setText(getString(R.string.tips_level) + " " + greeds[seekBar.getProgress()]);
        greed = GreedMode.values()[seekBar.getProgress()];
        recalculate();
    }

    public void recalculate() {
        tipCalculator.setTipProvider(profession);
        tipCalculator.setGreed(greed);
        tipCalculator.setPersons(persons);
        PercentTipStrategy tipStrategy = (PercentTipStrategy) profession.getTipStrategy();
        tipStrategy.setOrderSumm(total);
        totalPerPerson.setText(String.format("%.2f", tipCalculator.getTip()));
    }
}
