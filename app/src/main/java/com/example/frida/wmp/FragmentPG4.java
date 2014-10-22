package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import java.util.ArrayList;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

//dom expenses som var inlagda funkade och fick id nr. Men nu l�gger den inte in nya
//expenses h�r, det �r det soom ska fixas

public class FragmentPG4 extends Fragment {
    private Button test;
    private TextView totExp;
    private TextView totInc;
    private TextView totRes;
    private TextView totBud;
    private TextView firstN;
    private TextView lastN;
    private ListView table;
    private ListView infoExp; // Hur visar jag detta?
    private ListView infoInc;
    private ScrollView scrollExp;
    private ExpensesAdapter adapter;
    private Bitmap picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.pg4, container, false);


        // Button test = (Button) view.findViewById(R.id.result_btn);
        infoExp = (ListView) view.findViewById(R.id.listExpenses);
        Log.i("PG4 onCreate",(infoExp==null)? "null" : infoExp.toString());
        infoInc = (ListView) view.findViewById(R.id.listIncomes);
        Log.i("PG4 onCreate",(infoExp==null)? "null" : infoExp.toString());

        totExp = (TextView) view.findViewById(R.id.totalExpenses);
        totInc = (TextView) view.findViewById(R.id.totalIncome);
        totRes = (TextView) view.findViewById(R.id.totalResult);
        totBud = (TextView) view.findViewById(R.id.resPlanBudget);
        firstN = (TextView) view.findViewById(R.id.firstName);
        lastN = (TextView) view.findViewById(R.id.lastName);

//		scrollExp = (ScrollView) view.findViewById(R.id.scrollExp);


        MyListener myListener = new MyListener();
        infoExp.setOnItemClickListener(myListener);



        // infoExp = (TextView) view.findViewById(R.id.textView13);

        // test:-------------------------------
        // tf_test = (TextView) view.findViewById(R.id.message_person_pg4);
        // tf_test.setText("");
        // ------------------------------------------
        //
        // MyListener myListener = new MyListener();
        // test.setOnClickListener(myListener);

        return view;

    }

    // public int Result {
    // int totinc;
    // int totex;
    // }

    // public void setTESTText(int totinc, int totexp) {
    // tf_test.setText("" + (totinc.getPrice() - exp.getPrice()));
    // Toast.makeText(getActivity(), (tf_test.getText()), Toast.LENGTH_SHORT)
    // .show();

    /*
     * (non-Javadoc)
     *
     * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DatabaseController.init(getActivity());
        ArrayList<Expense> list = DatabaseController.getAllExpenses();
        ExpensesAdapter expAdapter = new ExpensesAdapter(getActivity(),list);
        for(Expense expense : list) {
            Log.i("PG4 - onActivityCreated",expense.toString());
        }

        ArrayList<Income> list1 = DatabaseController.getAllIncomes();
        IncomeAdapter incAdapter = new IncomeAdapter(getActivity(),list1);
        for(Income income : list1) {
            Log.i("PG4 - onActivityCreated",income.toString());
        }

        Log.i("PG4 - onActivityCreated",(infoExp==null)?"null":infoExp.toString());
        Expense exp = new Expense(null, 0, null, null); //
        //(title, price, category, BitMapToString(picture));// price
        //image = null;
        infoExp.setAdapter(expAdapter);
        infoInc.setAdapter(incAdapter);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        prefs.getAll();
        totBud.setText(String.valueOf(prefs.getAll()));
        // User u = new User(firstname, lastname);
//		totBud.setText(String.valueOf(FragmentPG1.())); frag1 shared preferences??
        totExp.setText(String.valueOf(DatabaseController.calcExpense()));
        totInc.setText(String.valueOf(DatabaseController.calcIncome()));
        totRes.setText(String.valueOf(DatabaseController.calcIncome()
                - (DatabaseController.calcExpense())));
        firstN.setText(String.valueOf(DatabaseController.getUser()
                .getFirstName()));
        lastN.setText(String
                .valueOf(DatabaseController.getUser().getLastName()));
        Log.i("pg4", "totexp: " + totExp.getText().toString() + " totInc: "
                + totInc.getText().toString());

    }

    private class MyListener implements OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                long arg3) {
//			H�r vill jag att bilden ska komma upp om man klickar p� listan
            adapter.notifyDataSetChanged();


            Toast.makeText(getActivity(),
                    DatabaseController.getIncome().getCategory(),
                    Toast.LENGTH_SHORT).show();
            // TODO Auto-generated method stub

        }
    }

    public void explLoaded(ArrayList<Expense> List) {
        infoExp.setAdapter(new ExpensesAdapter(getActivity(),
                List));
    }

    public void inclLoaded(ArrayList<Income> newIncomeList) {
        infoInc.setAdapter(new IncomeAdapter(getActivity(),
                newIncomeList));
    }
}

