package com.justwayward.booker.ui.activity;
/*this place will be set for what the ListView will show,at this time ,the id is int type this is like the struct in the c language*/
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content,int type)
    {
        this.content=content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
}
