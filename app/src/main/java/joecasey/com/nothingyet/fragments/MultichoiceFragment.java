package joecasey.com.nothingyet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import joecasey.com.nothingyet.R;
import joecasey.com.nothingyet.managers.MultichoiceManager;
import joecasey.com.nothingyet.models.Multichoice;
import joecasey.com.nothingyet.utils.AndroidUtils;
import joecasey.com.nothingyet.utils.Logger;
import joecasey.com.nothingyet.views.ProgressMeterLayout;
import joecasey.com.nothingyet.views.RoundedTextLayout;

/**
 * Created by Joe F on 2/17/2015.
 */
public class MultichoiceFragment extends Fragment {

    private View mRootView;
    private String mType;

    private static final List<Multichoice> MULTICHOICE_LIST;
    static {
        MULTICHOICE_LIST = new ArrayList<>();
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"aa", "sa", "ni", "ke"}, "aa"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "aa", "ba", "ke"}, "ba"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ni", "ca"}, "ca"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"da", "sa", "ni", "ke"}, "da"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ea", "ni", "ke"}, "ea"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "fa", "ke"}, "fa"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ni", "ga"}, "ga"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ha", "sa", "ni", "ke"}, "ha"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ia", "ni", "ke"}, "ia"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ja", "ke"}, "ja"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ni", "ke"}, "ka"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ma", "ni", "ke"}, "ma"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "na", "ke"}, "na"));

        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"aa1", "sa", "ni", "ke"}, "aa1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "aa", "ba1", "ke"}, "ba1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ni", "ca1"}, "ca1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"da1", "sa", "ni", "ke"}, "da1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ea1", "ni", "ke"}, "ea1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "fa1", "ke"}, "fa1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ni", "ga1"}, "ga1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ha1", "sa", "ni", "ke"}, "ha1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ia1", "ni", "ke"}, "ia1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "ja1", "ke"}, "ja1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka1", "sa", "ni", "ke"}, "ka1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "ma1", "ni", "ke"}, "ma1"));
        MULTICHOICE_LIST.add(new Multichoice(R.drawable.abc_ab_share_pack_holo_dark,
                new String[] {"ka", "sa", "na1", "ke"}, "na1"));

    }

    public static MultichoiceFragment newInstance(String type) {
        MultichoiceFragment multichoiceFragment = new MultichoiceFragment();
        multichoiceFragment.setType(type);
        return multichoiceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(container.getContext()).inflate(R.layout.multichoice_fragment, container, false);
        initView();
        return mRootView;
    }

    private void populatePlaceholderProgresses() {
        Random random = new Random();
        for (int i = 0; i < MULTICHOICE_LIST.size(); i++) {
            Multichoice multichoice = MULTICHOICE_LIST.get(i);
            MultichoiceManager.setMultichoiceStatus(getActivity(), mType, multichoice.getAnswer(), random.nextInt(3));
        }
    }

    public void setType(String type) {
        mType = type;
    }

    private void initView() {
        populatePlaceholderProgresses();
        setCurrentItem(MULTICHOICE_LIST.get(0));
    }

    private void setCurrentItem(final Multichoice multichoice) {
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = MultichoiceManager.Status.INCOMPLETE;
                switch (v.getId()) {
                    case R.id.choice0:
                        status = multichoice.getChoices().get(0).equals(multichoice.getAnswer())
                                ? MultichoiceManager.Status.DONE : MultichoiceManager.Status.INCOMPLETE;
                        break;
                    case R.id.choice1:
                        status = multichoice.getChoices().get(1).equals(multichoice.getAnswer())
                                ? MultichoiceManager.Status.DONE : MultichoiceManager.Status.INCOMPLETE;
                        break;
                    case R.id.choice2:
                        status = multichoice.getChoices().get(2).equals(multichoice.getAnswer())
                                ? MultichoiceManager.Status.DONE : MultichoiceManager.Status.INCOMPLETE;
                        break;
                    case R.id.choice3:
                        status = multichoice.getChoices().get(3).equals(multichoice.getAnswer())
                                ? MultichoiceManager.Status.DONE : MultichoiceManager.Status.INCOMPLETE;
                        break;
                }
                MultichoiceManager.setMultichoiceStatus(getActivity(), mType, multichoice.getAnswer(), status);
                int currentMultichoiceIndex = MULTICHOICE_LIST.indexOf(multichoice);
                currentMultichoiceIndex = currentMultichoiceIndex == MULTICHOICE_LIST.size() - 1
                        ? 0 : currentMultichoiceIndex + 1;
                setCurrentItem(MULTICHOICE_LIST.get(currentMultichoiceIndex));
            }
        };

        ((RoundedTextLayout)mRootView.findViewById(R.id.choice0)).setText(multichoice.getChoices().get(0));
        ((RoundedTextLayout)mRootView.findViewById(R.id.choice1)).setText(multichoice.getChoices().get(1));
        ((RoundedTextLayout)mRootView.findViewById(R.id.choice2)).setText(multichoice.getChoices().get(2));
        ((RoundedTextLayout)mRootView.findViewById(R.id.choice3)).setText(multichoice.getChoices().get(3));

        mRootView.findViewById(R.id.choice0).setOnClickListener(onClickListener);
        mRootView.findViewById(R.id.choice1).setOnClickListener(onClickListener);
        mRootView.findViewById(R.id.choice2).setOnClickListener(onClickListener);
        mRootView.findViewById(R.id.choice3).setOnClickListener(onClickListener);

        ((ProgressMeterLayout)mRootView.findViewById(R.id.progress_meter)).setMultichoices(mType, MULTICHOICE_LIST);
        ((ProgressMeterLayout)mRootView.findViewById(R.id.progress_meter)).setOnItemSelectedListener(new ProgressMeterLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position, Multichoice multichoice) {
                setCurrentItem(multichoice);
            }
        });

        ((ImageView)mRootView.findViewById(R.id.symbol)).setImageResource(multichoice.getResId());
    }
 }
