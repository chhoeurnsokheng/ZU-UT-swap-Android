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
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;
import com.zillennium.utswap.models.Subscription.Subscription;
import com.zillennium.utswap.screens.setting.subScriptionScreen.adapter.SubscriptionAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class FilterBottomSheetDialog extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private Spinner customSpinner;
    private MaterialButton btnClear, btnFiler;
    private EditText etStart, etEnd;
    private ArrayList<Subscription> statusList;

    public static FilterBottomSheetDialog newInstance(){
        FilterBottomSheetDialog filterBottomSheetDialog = new FilterBottomSheetDialog();
        filterBottomSheetDialog.setArguments(new Bundle());
        return filterBottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.layout_filter_bottomsheet_fragment,container,false);

        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView();

        statusList = getList();
        SubscriptionAdapter adapter = new SubscriptionAdapter(this.getContext(), statusList);
        if(customSpinner != null){
            customSpinner.setAdapter(adapter);
            customSpinner.setOnItemSelectedListener(this);
        }

        initAction();

        return view;
    }

    public void initView(){
        customSpinner = view.findViewById(R.id.spinner_status);
        btnClear = view.findViewById(R.id.btn_clear);
        btnFiler = view.findViewById(R.id.btn_filter);
        etStart = view.findViewById(R.id.et_start);
        etEnd = view.findViewById(R.id.et_end);
    }

    private ArrayList<Subscription> getList() {
        statusList = new ArrayList<>();

        statusList.add(new Subscription("Balance"));
        statusList.add(new Subscription("Deposit"));
        statusList.add(new Subscription("Transfer"));
        statusList.add(new Subscription("Withdrawal"));

        return statusList;
    }

    public void initAction(){
        btnFiler.setOnClickListener(view1 -> {
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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
