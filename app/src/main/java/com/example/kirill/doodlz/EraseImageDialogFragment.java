package com.example.kirill.doodlz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Kirill on 13.02.2018.
 */

public class EraseImageDialogFragment extends DialogFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivityFragment fragment = getDoodleFragment();
        if (fragment != null){
            fragment.setDialogOnScreen(true);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.message_erase);

        builder.setPositiveButton(R.string.button_erase, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getDoodleFragment().getDoodleView().clear();
            }
        });

        builder.setNegativeButton(android.R.string.cancel,null);


        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivityFragment fragment = getDoodleFragment();
        if (fragment != null){
            fragment.setDialogOnScreen(false);
        }
    }

    private  MainActivityFragment getDoodleFragment(){
        return (MainActivityFragment)(getFragmentManager().findFragmentById(R.id.doodleFragment));
    }

}
