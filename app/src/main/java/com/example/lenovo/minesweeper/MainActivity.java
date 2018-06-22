package com.example.lenovo.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout rootLayout;
    int size=4;
    public ArrayList<LinearLayout>rows;
    public Minesweeper[][]board;
    public static int isMine=-1;
    public static int isNumber=1;  //Changes made in java file
    public static int isEmptyNumber=0; //Empty Number means empty button
    public static boolean isFirstClick=true;
    public static int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout=findViewById(R.id.rootLayout);
        setUpBoard();
    }
    public void setUpBoard()
    {
        rows=new ArrayList<>();
        board=new Minesweeper[size][size];
        rootLayout.removeAllViews();
        for(int i=0;i<size;i++)
        {
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rootLayout.addView(linearLayout);
            rows.add(linearLayout);
        }
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                Minesweeper buttonSmall=new Minesweeper(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                buttonSmall.setLayoutParams(layoutParams);
                buttonSmall.setOnClickListener(this);
                LinearLayout row=rows.get(i);
                row.addView(buttonSmall);
                board[i][j]=buttonSmall;
            }
        }
    }
    public int getRowIndex()
    {
        int selected=new Random().nextInt(size);
        return selected;
    }
    public int getColumnIndex()
    {
        int selected=new Random().nextInt(size);
        return selected;
    }
    public void setUpMines()
    {
        while(counter<8)
        {
            int i=getRowIndex();
            int j=getColumnIndex();
            Minesweeper button=board[i][j];
            if(button.isButtonNotSelected()==0)
            {
                button.setButton(-1);
                counter++;
            }
        }
        boolean a=isMineAlloted();
    }
    boolean isMineAlloted()
    {
        if(counter==8)
        {
            eval();
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View view)
    {
        Minesweeper button=(Minesweeper) view ;
        if(isFirstClick)
        {
            setUpMines();
            isFirstClick=false;
        }
    }
    public void eval() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Minesweeper mineButton = board[i][j];
                if (mineButton.getCategory()== -1) {
                    ArrayList<Integer> rowIndex = new ArrayList<>();
                    ArrayList<Integer> colIndex = new ArrayList<>();
                    if ((i - 1) > -1) {
                        rowIndex.add(i - 1);
                    }
                    rowIndex.add(i);
                    if ((i + 1) < size) {
                        rowIndex.add(i + 1);
                    }
                    if ((j - 1) > -1) {
                        colIndex.add(j - 1);
                    }
                    colIndex.add(j);
                    if ((j + 1) < size) {
                        colIndex.add(j + 1);
                    }
                    for(int s=0;s<rowIndex.size();s++)
                    {
                        for(int k=0;k<colIndex.size();k++)
                        {
                            Minesweeper otherThanMineButton=board[s][k];
                            if(otherThanMineButton.getCategory()!=-1) {
                              int num=otherThanMineButton.getCategory();
                                otherThanMineButton.setButton(num+1);
                            }

                        }
                    }
                }
            }
        }
    }
}
