package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ParseException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentPG1 extends Fragment {
    private String franDatum, tillDatum;
    private EditText fillName;
    private static EditText fillBudget;
    private Button clearBudget;
    private Button savePg1;

    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pg1, container, false);
        DatabaseController.init(getActivity());

        Button outgoingsBtn = (Button) view.findViewById(R.id.outgoing_pg1);
        Button incomesBtn = (Button) view.findViewById(R.id.incomes_pg1);
        Button fran = (Button) view.findViewById(R.id.btnFran_pg1);
        Button till = (Button) view.findViewById(R.id.btnTill_pg1);
        fillName = (EditText) view.findViewById(R.id.fill_name_pg1);
        fillBudget = (EditText) view.findViewById(R.id.fill_budget_pg1);
        savePg1 = (Button) view.findViewById(R.id.confirm_ok_pg1);
        clearBudget = (Button) view.findViewById(R.id.clearBud);

        MyListener myListener = new MyListener();

        outgoingsBtn.setOnClickListener(myListener);
        incomesBtn.setOnClickListener(myListener);
        fran.setOnClickListener(myListener);
        till.setOnClickListener(myListener);
        savePg1.setOnClickListener(myListener);
        clearBudget.setOnClickListener(myListener);

        return view;
    }

    private void getDate(Button button) { // en metod som skapar en kalender och
        // en datepickerdialog som f�r in en
        // datelistener och button som
        // parameter
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                new DateListener(button), year, month, day);
        dialog.show();
    }
    //Skapar en button som �r en datelistener
    //Anv�nda denna button till att spara valt datum?
    private class DateListener implements OnDateSetListener {

        private Button button;

        public DateListener(Button button) {
            this.button = button;
        }

        @Override
        public void onDateSet(DatePicker picker, int year, int month, int day) {
            String datum = year + "/" + (month + 1) + "/" + day;
            if (button.getId() == R.id.btnFran_pg1)
                franDatum = datum;
            else
                tillDatum = datum;
            button.setText(datum);
        }
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.outgoing_pg1)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG2(), "pg2");
            else if (v.getId() == R.id.incomes_pg1)
                ((MainActivity) getActivity())
                        .changeFragments(new FragmentPG3(), "pg3");
            else if (v.getId() == R.id.btnFran_pg1) {
                getDate((Button) v);
            } else if (v.getId() == R.id.btnTill_pg1) {// lagra detta valet och
                // uppdatera i
                // databasen??
                getDate((Button) v);
            } else if (v.getId() == R.id.confirm_ok_pg1) {
                String name = fillName.getText().toString();
                String[] newName = name.split(" ");

                DatabaseController.addUser(new User(newName[0], newName[1]));

                prefs = PreferenceManager
                        .getDefaultSharedPreferences(getActivity());//gör så man kan mata in 1 namn

                Editor edit = prefs.edit();

                String budget = fillBudget.getText().toString();

                edit.putString("", budget);

                edit.commit();

                Toast.makeText(getActivity(),
                        DatabaseController.getUser().getFirstName(),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),
                        DatabaseController.getUser().getLastName(),
                        Toast.LENGTH_SHORT).show();

            } else if (v.getId() == R.id.clearBud) {
                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(getActivity());
                Editor edit = prefs.edit();
                prefs.getAll();
                edit.clear();
                edit.commit();


            }

        }

    }

}
