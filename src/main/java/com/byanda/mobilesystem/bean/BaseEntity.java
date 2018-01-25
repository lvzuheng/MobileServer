package com.byanda.mobilesystem.bean;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * 
 * @description:我们的基类
 * @date: 2009-6-24
 * @author Peter
 */
public class BaseEntity implements Serializable {
    @Transient
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Transient
    public boolean isSaved() {
        return getId() > 0;
    }

    protected long getId() {
        return -1;
    }
}
