package com.limitium.smarttipper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.limitium.smarttipper.core.calculation.BartenderStrategy;
import com.limitium.smarttipper.core.calculation.BellmanStrategy;
import com.limitium.smarttipper.core.calculation.DeliveryStrategy;
import com.limitium.smarttipper.core.calculation.FixTipStrategy;
import com.limitium.smarttipper.core.calculation.PercentTipStrategy;
import com.limitium.smarttipper.core.Profession;
import com.limitium.smarttipper.core.calculation.TakeoutStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * An activity representing a list of Professions. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProfessionDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProfessionListActivity extends AppCompatActivity {


    public final static List<Profession> PROFESSIONS;

    static {
        PROFESSIONS = Arrays.asList(
                new Profession(R.drawable.ic_local_dining_white_24dp, R.string.waiter, Color.parseColor("#0288D1"), 95, 100, new PercentTipStrategy(17, 18.5f, 20)),
                new Profession(R.drawable.ic_local_pizza_white_24dp, R.string.delivery, Color.parseColor("#0097A7"), 50, 95, new DeliveryStrategy(2, 3, 4)),
                new Profession(R.drawable.ic_store_mall_directory_white_24dp, R.string.takeout, Color.parseColor("#00796B"), 10, 35, new TakeoutStrategy(1, 2, 15)),
                new Profession(R.drawable.ic_local_bar_white_24dp, R.string.bartender, Color.parseColor("#388E3C"), 85, 100, new BartenderStrategy(1, 1, 1, 2, 2, 3, 15, 18, 20)),
                new Profession(R.drawable.ic_local_cafe_white_24dp, R.string.barista, Color.parseColor("#689F38"), 30, 60, new FixTipStrategy(0, 0.5f, 1)),
                new Profession(R.drawable.ic_local_taxi_white_24dp, R.string.driver, Color.parseColor("#AFB42B"), 25, 90, new PercentTipStrategy(10, 16, 18)),
                new Profession(R.drawable.ic_room_service_white_24dp, R.string.valet, Color.parseColor("#FFA000"), 65, 90, new FixTipStrategy(1, 3, 5)),
                new Profession(R.drawable.ic_business_center_white_24dp, R.string.bellman, Color.parseColor("#E64A19"), 65, 85, new BellmanStrategy(3,2,5)),
                new Profession(R.drawable.ic_pan_tool_white_24dp, R.string.doorman, Color.parseColor("#D32F2F"), 15, 95, new FixTipStrategy(35, 75, 300)),
                new Profession(R.drawable.ic_content_cut_white_24dp, R.string.barbershop, Color.parseColor("#C2185B"), 40, 90, new PercentTipStrategy(15, 20, 25)),
                new Profession(R.drawable.ic_colorize_white_24dp, R.string.tattoo, Color.parseColor("#7B1FA2"), 93, 70, new PercentTipStrategy(10, 15, 20)),
                new Profession(R.drawable.ic_airline_seat_flat_white_24dp, R.string.massage, Color.parseColor("#512DA8"), 50, 95, new PercentTipStrategy(15, 18, 20)),
                new Profession(R.drawable.ic_golf_course_white_24dp, R.string.golf, Color.parseColor("#303F9F"), 80, 80, new PercentTipStrategy(30, 45, 60)),
                new Profession(R.drawable.ic_visibility_white_24dp, R.string.stripper, Color.parseColor("#1976D2"), 100, 100, new PercentTipStrategy(15, 18, 20))
        );
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.profession_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.profession_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(PROFESSIONS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Profession> mValues;

        public SimpleItemRecyclerViewAdapter(List<Profession> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profession_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Profession profession = mValues.get(position);
            holder.mItem = profession;

            Bitmap bm = BitmapFactory.decodeResource(getResources(), profession.getIcon());
            holder.mIconView.setImageBitmap(getCircleBitmap(bm, profession.getColor(), getApplicationContext()));

            holder.mContentView.setText(profession.getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ProfessionDetailFragment.ARG_ITEM_ID, String.valueOf(position));
                        ProfessionDetailFragment fragment = new ProfessionDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.profession_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProfessionDetailActivity.class);
                        intent.putExtra(ProfessionDetailFragment.ARG_ITEM_ID, String.valueOf(position));

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mIconView;
            public final TextView mContentView;
            public Profession mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIconView = (ImageView) view.findViewById(R.id.icon);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap, int color, Context ctx) {
        int size = (int) convertDpToPixel(36, ctx);

        final Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        //   paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int offset = (size - bitmap.getHeight()) / 2;
        canvas.drawBitmap(bitmap, offset, offset, paint);

        bitmap.recycle();

        return output;
    }
}
