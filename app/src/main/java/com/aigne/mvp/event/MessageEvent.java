package com.aigne.mvp.event;

/**
 * 组件间相互通信
 * @author jazzeZhou
 * @date 2018/5/24
 */
public class MessageEvent {

    // 类型
    private int type;
    // 内容
    private Object value;

    public MessageEvent(int type, Object value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
