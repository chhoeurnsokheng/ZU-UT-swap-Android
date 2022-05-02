package com.zillennium.utswap.screens.setting.MyWallet.dialog;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class ExportTransactionBottomSheetDialog extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private EditText etStart, etEnd;
    private MaterialButton btnClear, btnExport;
    private Spinner spinner;
    private final String[] strPeriod = {"Select Time","Today", "Yesterday", "This week", "Last week", "This Month", "Last Month"};

    public static ExportTransactionBottomSheetDialog newInstance(){
        ExportTransactionBottomSheetDialog exportTransactionBottomSheetDialog = new ExportTransactionBottomSheetDialog();
        exportTransactionBottomSheetDialog.setArguments(new Bundle());
        return exportTransactionBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.layout_export_transaction_bottomsheet_fragment,container,false);

        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item,strPeriod);
        aa.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        initAction();

        return view;
    }

    public void initView(){
        etStart = view.findViewById(R.id.et_start);
        etEnd = view.findViewById(R.id.et_end);
        btnClear = view.findViewById(R.id.btn_clear);
        btnExport = view.findViewById(R.id.btn_export);
        spinner = view.findViewById(R.id.spinner_period);
    }

    public void initAction(){
        btnExport.setOnClickListener(view1 -> {
            dismiss();
        });

        btnClear.setOnClickListener(view1 -> {
            dismiss();
        });

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date_start = (datePicker, year, month, day) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONDAY, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            String Format = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format, Locale.US);

            etStart.setText(simpleDateFormat.format(calendar.getTime()));

        };

        DatePickerDialog.OnDateSetListener date_end = (datePicker, year, month, day) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONDAY, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            String Format = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Format, Locale.US);

            etEnd.setText(simpleDateFormat.format(calendar.getTime()));

        };

        etStart.setOnClickListener(view -> new DatePickerDialog(this.getContext(), date_start, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        etEnd.setOnClickListener(view -> new DatePickerDialog(this.getContext(), date_end, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        if(spinner.getSelectedItem().toString().equals("Today"))
        {
//            etStart.setText(Utils.getTodayDate());
//            etEnd.setText(Utils.getTodayDate());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this.getContext(),strPeriod[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
