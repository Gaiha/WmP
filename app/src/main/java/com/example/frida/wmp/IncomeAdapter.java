package com.example.frida.wmp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class IncomeAdapter extends ArrayAdapter<Income>{
	private LayoutInflater inflater;

	public IncomeAdapter(Context context, ArrayList<Income>newIncomeList) {
		super(context, R.layout.list_incomes, R.id.Atitle, newIncomeList);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// TODO Auto-generated constructor stub
	}
	
	private void setRow(View view, Income inc, boolean image,
			int textColor, int background) {
		TextView Btitle = (TextView) view.findViewById(R.id.Btitle);
		TextView Bprice = (TextView) view.findViewById(R.id.Bprice);
		TextView Bcategory = (TextView) view.findViewById(R.id.Bcategory);
		Btitle.setText(inc.getTitle());
		Bprice.setText(""+inc.getPrice());
		Bcategory.setText(inc.getCategory());
//		tvDate.setBackgroundColor(background);
//		tvDate.setTextColor(textColor);
//		tvType.setText(tr.getType());
//		tvType.setBackgroundColor(background);
//		tvType.setTextColor(textColor);
//		tvAmount.setBackgroundColor(background);
//		tvAmount.setTextColor(textColor);
//		ivFeeling.setBackgroundColor(background);
//		if (image) {
//			double nbr = Double.parseDouble(tr.getAmount());
//			tvAmount.setText(String.format("%1.2f", nbr));
//			if (nbr >= 1000)
//				ivFeeling.setImageResource(R.drawable.sad);
//			else if (nbr > 100)
//				ivFeeling.setImageResource(R.drawable.ok);
//			else
//				ivFeeling.setImageResource(R.drawable.happy);
//		} else {
//			tvAmount.setText(tr.getAmount());
//			ivFeeling.setImageResource(R.drawable.empty);
		}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.allinc, parent, false);
		Income inc = getItem(position);
		if (position == 0) {
			setRow(view, inc, false, Color.WHITE, Color.DKGRAY);
		} else {
			setRow(view, inc, true, Color.BLACK, Color.CYAN);

		}
		return view;
	}
}


