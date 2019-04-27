package indi.apst.marketsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import indi.apst.marketsystem.R;

public class CommodityFragment extends Fragment implements View.OnClickListener {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_commodity, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        MeatFragment meatFragment = new MeatFragment();
        ft.replace(R.id.ftb, meatFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        meatBtn = layout.findViewById(R.id.meat_btn);
        meatBtn.setOnClickListener(this);
        vegaBtn = layout.findViewById(R.id.vegetable_btn);
        vegaBtn.setOnClickListener(this);
        fruitBtn = layout.findViewById(R.id.fruit_btn);
        fruitBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return layout;

    }

    @Override
    public void onClick(View view){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.meat_btn:
                transaction.replace(R.id.ftb,new MeatFragment());
                break;
            case R.id.vegetable_btn:
                transaction.replace(R.id.ftb,new VegetableFragment());
                break;
            case R.id.fruit_btn:
                transaction.replace(R.id.ftb,new FruitFragment());
                break;
            default:
                break;
            }
            transaction.commit();
        }

    }



