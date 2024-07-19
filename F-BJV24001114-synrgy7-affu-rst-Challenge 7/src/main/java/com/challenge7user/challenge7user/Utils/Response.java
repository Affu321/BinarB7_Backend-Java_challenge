package com.challenge7user.challenge7user.Utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

    public Map sucsess(Object obj) {
        Map map = new HashMap();
        map.put("data", obj);
        map.put("status", 200);
        map.put("message", "sucsess");
        return map;
    }

    public Map eror(Object obj, Object code) {
        Map map = new HashMap();
        map.put("status", code);
        map.put("message", obj);
        return map;
    }

    public Map Error(Object obj) {
        Map map = new HashMap();
        map.put("status", 404);
        map.put("message", obj);
        return map;
    }

    public Map templateEror(Object obj) {
        Map map = new HashMap();
        map.put("status", 404);
        map.put("message", obj);
        return map;
    }

    public Map templateSuksess(Object obj) {
        Map map = new HashMap();
        map.put("status", 200);
        map.put("message", obj);
        return map;
    }

    public boolean checkNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    public Map templateCRUD(Object obj) {
        Map map = new HashMap();
        map.put("status", obj);
        map.put("message", obj);
        return map;
    }
}
