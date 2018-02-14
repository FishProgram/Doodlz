package com.example.kirill.doodlz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by Kirill on 13.02.2018.
 */

public class ColorDialogFragment extends DialogFragment {

    private SeekBar alphaSeekBar;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;
    private View colorView;
    private int color;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MainActivityFragment fragment = getDoodleFragment();

        if(fragment != null){
            fragment.setDialogOnScreen(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        MainActivityFragment fragment = getDoodleFragment();

        if(fragment != null){
            fragment.setDialogOnScreen(false);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View colorDialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_color, null);
        builder.setView(colorDialogView);

        alphaSeekBar = (SeekBar)(colorDialogView.findViewById(R.id.alphaSeekBar));
        redSeekBar = (SeekBar)(colorDialogView.findViewById(R.id.redSeekBar));
        greenSeekBar = (SeekBar)(colorDialogView.findViewById(R.id.greenSeekBar));
        blueSeekBar = (SeekBar)(colorDialogView.findViewById(R.id.blueSeekBar));
        colorView = (View) (colorDialogView.findViewById(R.id.colorView));

        alphaSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);


        final DoodleView doodleView = getDoodleFragment().getDoodleView();
        color = doodleView.getDrawingColor();
        alphaSeekBar.setProgress(Color.alpha(color));
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
        color = Color.argb(alphaSeekBar.getProgress(),
                redSeekBar.getProgress(),
                greenSeekBar.getProgress(),
                blueSeekBar.getProgress());
        colorView.setBackgroundColor(color);


        builder.setPositiveButton(R.string.button_set_color, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doodleView.setDrawingColor(color);
            }
        });


        return builder.create();
    }

    private  MainActivityFragment getDoodleFragment(){
        return (MainActivityFragment)(getFragmentManager().findFragmentById(R.id.doodleFragment));
    }

    private  final SeekBar.OnSeekBarChangeListener colorChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                color = Color.argb(alphaSeekBar.getProgress(),
                        redSeekBar.getProgress(),
                        greenSeekBar.getProgress(),
                        blueSeekBar.getProgress());
                colorView.setBackgroundColor(color);

            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
