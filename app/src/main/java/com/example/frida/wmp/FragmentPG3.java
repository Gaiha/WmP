package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentPG3 extends Fragment {
    private Button c_ok;
    private Button clear;
    private RadioGroup fillCategory;
    private EditText fillTitle;
    private EditText fillPrice;
    private DatabaseController data;
    private RadioButton catInc;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pg3, container, false);
        fillCategory = (RadioGroup) view.findViewById(R.id.radioGroup_pg3);
        fillTitle = (EditText) view.findViewById(R.id.fill_namepg3);
        fillPrice = (EditText) view.findViewById(R.id.eprice_pg3);
        c_ok = (Button) view.findViewById(R.id.confirm_pg3);
        clear = (Button) view.findViewById(R.id.buttonC);

        Button outcomePage = (Button) view.findViewById(R.id.outgoings_pg3);
        Button resultPage = (Button) view.findViewById(R.id.result_pg3);
        Button startPage = (Button) view.findViewById(R.id.start_pg3);

        int selectedId = fillCategory.getCheckedRadioButtonId();
        catInc = (RadioButton) view.findViewById(selectedId);

        // data = new DatabaseController(getActivity());

        MyListener myListener = new MyListener();

        startPage.setOnClickListener(myListener);
        outcomePage.setOnClickListener(myListener);
        resultPage.setOnClickListener(myListener);
        c_ok.setOnClickListener(myListener);
        clear.setOnClickListener(myListener);

        return view;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.start_pg3)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG1(), "pg1");
            else if (v.getId() == R.id.outgoings_pg3)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG2(), "pg2");
            else if (v.getId() == R.id.result_pg3)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG4(), "pg4");
            else if (v.getId() == R.id.buttonC)
                DatabaseController.cleanInc();
            else if (v.getId() == R.id.confirm_pg3) {
                int selectedId = fillCategory.getCheckedRadioButtonId();
                catInc = (RadioButton) getActivity().findViewById(selectedId);

                String title = fillTitle.getText().toString();
                //int price = 0;
                //try {
                    String temp = fillPrice.getText().toString();
                    int price = Integer.parseInt(temp); //kollar hur jag hanterar felinskrivning
                //} catch (Exception e) {
                    // TODO: handle exception
               // }
                String category = catInc.getText().toString();


                // Double.parseDouble(price)

                Income inc = new Income(category, price, title);

                DatabaseController.addIncome(inc);

                Toast.makeText(getActivity(),
                        DatabaseController.getIncome().getTitle(),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),
                        DatabaseController.getIncome().getCategory(),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(
                        getActivity(),
                        Integer.toString(DatabaseController.getIncome()
                                .getPrice()), Toast.LENGTH_SHORT).show();

                // Toast.makeText(getActivity(), (radio1Btn.getText()),
                // Toast.LENGTH_SHORT).show();

            }
        }

    }
}