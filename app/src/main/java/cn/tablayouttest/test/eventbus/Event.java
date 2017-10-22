package cn.tablayouttest.test.eventbus;

/**
 * Created by yu on 2017/10/20.
 */

public class Event {
    private String mMsg;
    public Event(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
