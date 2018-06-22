package com.example.lenovo.minesweeper;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class Minesweeper extends AppCompatButton {

    private int category=MainActivity.isEmptyNumber;
    private int buttonNotSelected=0;
    private int rowindex;
    private int columnindex;

    public Minesweeper(Context context) {
        super(context);
    }
    public void setButton(int category)
    {
        this.category=category;
        if(category==MainActivity.isMine)
        {
            setText("-1");
            buttonNotSelected=1;
        }
        else
        {
            setText(category);
        }
        setEnabled(false);
    }
    public int getCategory()
    {
        return category;
    }
    public int isButtonNotSelected()
    {
        return buttonNotSelected;
    }
    public int rowIndexret()
    {
        return rowindex;
    }
    public int columnIndexret()
    {
        return columnindex;
    }



}
