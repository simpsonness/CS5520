package edu.neu.madcourse.numad21su_shanness;



public class ListItems implements ItemClickListener {
    private final int imageSource;
    private final String itemName;
    private final String itemDesc;
    private boolean isChecked;

    //Constructor
    public ListItems(int imageSource, String itemName, String itemDesc,boolean isChecked) {
        this.imageSource = imageSource;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.isChecked = isChecked;
    }

    //Getters for the imageSource, itemName and itemDesc
    public int getImageSource() {
        return imageSource;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getItemName() {
        return itemName + (isChecked ? "(checked)" : "");
    }

    public boolean getStatus() {
        return isChecked;
    }


    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }

    @Override
    public void onCheckBoxClick(int position) {
        isChecked = !isChecked;
    }

}