package com.example.admin.zgapplication.base;

import java.util.Set;

/**
 * Created by fushuang on 2017/12/28.
 */

public class EventRegionSelect {
    private Set<Integer> rent_way_set;
    private Set<Integer> house_type_set;
    private int leftProgress;
    private int rightProgress;

    public EventRegionSelect(Set<Integer> rent_way_set, Set<Integer> house_type_set, int leftProgress, int rightProgress) {
        this.rent_way_set = rent_way_set;
        this.house_type_set = house_type_set;
        this.leftProgress = leftProgress;
        this.rightProgress = rightProgress;
    }

    public Set<Integer> getRent_way_set() {
        return rent_way_set;
    }

    public void setRent_way_set(Set<Integer> rent_way_set) {
        this.rent_way_set = rent_way_set;
    }

    public Set<Integer> getHouse_type_set() {
        return house_type_set;
    }

    public void setHouse_type_set(Set<Integer> house_type_set) {
        this.house_type_set = house_type_set;
    }

    public int getLeftProgress() {
        return leftProgress;
    }

    public void setLeftProgress(int leftProgress) {
        this.leftProgress = leftProgress;
    }

    public int getRightProgress() {
        return rightProgress;
    }

    public void setRightProgress(int rightProgress) {
        this.rightProgress = rightProgress;
    }
}
