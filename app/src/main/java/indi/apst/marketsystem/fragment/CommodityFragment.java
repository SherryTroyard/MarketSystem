package indi.apst.marketsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import indi.apst.marketsystem.R;

public class CommodityFragment extends Fragment {

    private Button meatBtn, vegaBtn, fruitBtn;

    public static CommodityFragment newInstance(String param1) {
        CommodityFragment fragment = new CommodityFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public CommodityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commodity, container, false);


    }


}
