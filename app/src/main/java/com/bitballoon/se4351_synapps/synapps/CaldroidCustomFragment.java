package com.bitballoon.se4351_synapps.synapps;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

public class CaldroidCustomFragment extends CaldroidFragment {

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        return new CaldroidCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

}
