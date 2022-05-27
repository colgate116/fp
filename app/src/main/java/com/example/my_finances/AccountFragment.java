package com.example.my_finances;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountFragment extends Fragment {
    ArrayList<Card> cardList;
    RecyclerView recyclerView;
    recycleAdapter.RecyclerViewClickListener listener;
    Button plus, minus;
    OpenHelper openHelper;
    SQLiteDatabase database;
    Boolean filterY=false;
    ImageButton filter, add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        openHelper=new OpenHelper(getContext());
        database=openHelper.getWritableDatabase();

        recyclerView = rootView.findViewById(R.id.cardList);
        plus = rootView.findViewById(R.id.plusBILLS);
        minus = rootView.findViewById(R.id.minusBILLS);
        add =rootView.findViewById(R.id.add);
        filter=rootView.findViewById(R.id.filterButton);
        plus.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Add_income_activity.class);
            startActivity(intent);
        });

        minus.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), add_activity.class);
            startActivity(intent);
        });


        filter.setOnClickListener(v -> {
            if(!filterY){
                filterY=true;
                filter.setImageResource(R.drawable.ic_arrow_up);
                filterUp();
            }
            else {
                filterY=false;
                filter.setImageResource(R.drawable.ic_arrow_down);
                filterDown();
            }
setAdapter();
        });

        cardList = new ArrayList<>();
        setCardInfo();

        add.setOnClickListener(v -> addCard());
        setAdapter();
        return rootView;
    }

    private void filterDown() {
//todo
    }

    private void filterUp() {
//todo
    }

    private void setAdapter() {
        setOnClickListener();
        recycleAdapter adapter = new recycleAdapter(cardList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

//todo убрать двойной клик
    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(AccountFragment.this.getContext(), Card_inside_settings.class);
            intent.putExtra("card_give_tittle", cardList.get(position).getTittle());
            intent.putExtra("card_give_score", cardList.get(position).getScore());
            intent.putExtra("card_give_annotation", cardList.get(position).getAnnotation());
            AccountFragment.this.startActivity(intent);
            getActivity().finish();
        };

    }

    public void setCardInfo() {
        Cursor cursor=database.query(OpenHelper.TABLE_NAME,
                new String[]{OpenHelper.COLUMN_NAME,
                        OpenHelper.COLUMN_SCORE,
                        OpenHelper.COLUMN_ANNOTATION,
                        OpenHelper.COLUMN_FAVOURITE
                },null,null,null,null,null);
        cursor.move(0);
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_NAME));
            @SuppressLint("Range") double score=cursor.getDouble(cursor.getColumnIndex(OpenHelper.COLUMN_SCORE));
            @SuppressLint("Range") String annotation=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_ANNOTATION));
            @SuppressLint("Range") int fav=cursor.getInt(cursor.getColumnIndex(OpenHelper.COLUMN_FAVOURITE));
            boolean favourite;
            favourite= fav == 1;
            cardList.add(new Card(name,annotation,favourite,score));
        }
        cursor.close();
    }

    public void addCard() {
        final EditText card_tittle, card_annotation, card_score;
        Button button;

        final Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_card);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        button=dialog.findViewById(R.id.dialog_save);
        card_tittle=dialog.findViewById(R.id.ET_name_of_card);
        card_annotation=dialog.findViewById(R.id.descr_dialog);
        card_score=dialog.findViewById(R.id.ET_dialog_score);

        button.setOnClickListener(v -> {
            String tittle = card_tittle.getText().toString();
            String score = card_score.getText().toString();
            if(score.equals(""))
                score=null;
            String annotation = card_annotation.getText().toString();

            for (int i = 0; i < cardList.size(); i++) {
                if(tittle.equals(cardList.get(i).getTittle()))
                    tittle="";
            }

            if (!tittle.equals("")&&score!=null) {
                double sc= Double.parseDouble(score);
                cardList.add(new Card(tittle, annotation, false, sc));
                recycleAdapter adapter = new recycleAdapter(cardList, listener);
                adapter.notifyItemInserted(cardList.size() - 1);
                recyclerView.setAdapter(adapter);
                ContentValues values = new ContentValues();
                values.put(OpenHelper.COLUMN_NAME, cardList.get(cardList.size() - 1).getTittle());
                if (!cardList.get(cardList.size() - 1).isFavourite()) {
                    values.put(OpenHelper.COLUMN_FAVOURITE, 0);
                } else
                    values.put(OpenHelper.COLUMN_FAVOURITE, 1);
                values.put(OpenHelper.COLUMN_SCORE, cardList.get(cardList.size() - 1).getScore());
                values.put(OpenHelper.COLUMN_ANNOTATION, cardList.get(cardList.size() - 1).getAnnotation());
                database.insert(OpenHelper.TABLE_NAME, null, values);
                dialog.cancel();
            }
        });
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
    }



    @Override
    public void onResume() {
        super.onResume();
        database = openHelper.getWritableDatabase();
        setAdapter();
    }

    @Override
    public void onStop() {
        super.onStop();

        double score;
        boolean fav;
        String annotation, tittle;
        int fav_num;

        for (int i = 0; i < cardList.size(); i++) {
            score=cardList.get(i).getScore();
            fav=cardList.get(i).isFavourite();
            annotation=cardList.get(i).getAnnotation();
            tittle=cardList.get(i).getTittle();
            if (fav)
                fav_num=1;
            else
                fav_num=0;
            String sqlQuery="Update "+OpenHelper.TABLE_NAME+" set "+OpenHelper.COLUMN_NAME+"=\""+tittle+"\", "+
                    OpenHelper.COLUMN_FAVOURITE+"="+fav_num+
                    ", "+OpenHelper.COLUMN_SCORE+"="+score+
                    ", "+OpenHelper.COLUMN_ANNOTATION+"=\""+annotation+"\" where _id="+(i+1)+
                    ";";
            database.execSQL(sqlQuery);
        }
        database.close();
    }
}
