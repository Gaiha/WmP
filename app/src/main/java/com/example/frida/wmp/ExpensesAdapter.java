package com.example.frida.wmp; /**
 * Created by Frida on 2014-09-24.
 */
        import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.util.List;
        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.preference.PreferenceManager;
        import android.util.Base64;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Adapter;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

public class ExpensesAdapter extends BaseAdapter {

    private Activity context;
    private List<Expense> list;
    private int selectedPosition = -1;

    public ExpensesAdapter(Activity context, List<Expense> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getId();
    }

    @Override
    public View getView(int pos, View view, ViewGroup root) {
        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(R.layout.allexp, null);

        if (pos == selectedPosition) {
            view.setBackgroundColor(0xFFFF0000);
        }

        TextView Atitle = (TextView) view.findViewById(R.id.Atitle);
        TextView Aprice = (TextView) view.findViewById(R.id.Aprice);
        TextView Acategory = (TextView) view.findViewById(R.id.Acategory);
        ImageView Aimage = (ImageView) view.findViewById(R.id.Aimage);
        TextView id = (TextView) view.findViewById(R.id.Aid);

        Expense exp = list.get(pos);//kommentarer

        id.setText(exp.getId() + ". ");
        Aprice.setText(exp.getPrice() + "");
        Acategory.setText(exp.getCategory());
        Atitle.setText(exp.getTitle());

        Aimage.setImageBitmap(BitmapFactory.decodeFile(exp.getImage()));
        //Aimage.setImageBitmap(StringToBitMap(exp.getImage()));
        //stringToBitmap("filelocation"));
        //sharedpreferences get all Aimageset image konvertera sträng


        // if(exp.getImage() != null){

        // Aimage.setImageBitmap(stringToBitmap(exp.getImage()));
        //System.out.println(exp.getImage());
        // System.out.println("BILD FINNS!!!!!!!!!!!!!!!!");
        return view;
    }

//		if (str != null && !str.matches("")) {
//			Log.i("test", (str == null ? "str null":"str ok"));
//			Bitmap bmp = BitmapFactory.decodeFile(str);
////			byte[] b = getBytes(str);
//			Log.i("test", (bmp == null ? "bmp null":"bmp ok"));
//			Aimage.setImageBitmap(bmp);
//		}




    //}

    // private byte[] getBytes(String str) {
    // String[] arr = str.split(",");
    // byte[] b = new byte[arr.length];
    //
    // for (int i = 0; i < arr.length; i++) {
    // b[i] = Byte.parseByte(arr[i]);
    // }
    // return b;
    // }
    //
     private Bitmap getBitmap(byte[] b) {
     ByteArrayInputStream imageStream = new ByteArrayInputStream(b);
     Bitmap image = BitmapFactory.decodeStream(imageStream);
     return image;
     }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public String BitMapToString(Bitmap bitmap) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
     bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
     byte[] b = baos.toByteArray();
    String temp = Base64.encodeToString(b, Base64.DEFAULT);
     return temp;
     }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }


}
