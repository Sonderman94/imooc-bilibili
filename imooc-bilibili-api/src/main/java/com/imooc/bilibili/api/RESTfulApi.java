package com.imooc.bilibili.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RESTfulApi {

    private final Map<Integer, Map<String, Object>> dataMap;

    public RESTfulApi(){
        dataMap = new HashMap<>();
        for(int i = 0 ; i < 3 ; i++){
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name" + i);
            dataMap.put(i, data);
        }
    }

    @GetMapping("/objects/{id}")
    public Map<String, Object> getData(@PathVariable Integer id){
        return dataMap.get(id);
    }

    @DeleteMapping("/objects/{id}")
    public String delete(@PathVariable Integer id){
        dataMap.remove(id);
        return "delete success";
    }

     @PostMapping("/objects")
    public String postData(@RequestBody Map<String, Object> data){
        Integer[] idArray = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(idArray);
        int nextId = idArray[idArray.length - 1] + 1;
        return "post success";
    }

    @PutMapping("objects")
    public String putData(@RequestBody Map<String, Object> data){
        Integer id = Integer.valueOf(String.valueOf(data.get("id")));
        Map<String, Object> containeData = dataMap.get(id);
        if (containeData == null){
            Integer[] idArray = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(idArray);
            int nextId = idArray[idArray.length - 1] + 1;
            dataMap.put(nextId, data);
        }else{
            dataMap.put(id, data);
        }
        return "put success";
    }

}

