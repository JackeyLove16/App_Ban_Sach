package com.example.app_ban_sach.model.EventBus;

import com.example.app_ban_sach.model.sachcu;

public class EventSuaXoa {
    sachcu sachcu;

    public EventSuaXoa(com.example.app_ban_sach.model.sachcu sachcu) {
        this.sachcu = sachcu;
    }

    public com.example.app_ban_sach.model.sachcu getSachcu() {
        return sachcu;
    }

    public void setSachcu(com.example.app_ban_sach.model.sachcu sachcu) {
        this.sachcu = sachcu;
    }
}
