package com.learn.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.core.entity.HeroTheme;
import com.learn.core.repository.HeroThemeRepository;

@RestController
@RequestMapping("/api/heroTheme")
public class HeroThemeController {
    @Autowired
    private HeroThemeRepository repository;

    @PostMapping("/save")
    public void save(@RequestBody List<HeroTheme> heroThemes) {
        for(HeroTheme heroTheme : heroThemes) {
            System.out.println(heroTheme.getId()+" "+heroTheme.getParentId()+" "+heroTheme.getName()+" "+heroTheme.getColor());
            repository.save(heroTheme);
        }
    }

    @GetMapping("/solve")
    public List<HashMap<String, HashMap<String, String>>> solve() {
        HashMap<String, HashMap<String, String>> hm = new HashMap<>();
        // hm.put("name", "Warrior");
        HashMap<String, String> sub = new HashMap<>();
        sub.put("name", "Fighter");
        hm.put("subclasses", sub);
        List<HashMap<String, HashMap<String, String>>> result = new ArrayList<>();
        // List<HeroTheme> heroThemes = repository.findAll();
        result.add(hm);
        return result;
    }

    @GetMapping("/solve-with-jacksonapi")
    public List<Object> solveWithJacksonApi() throws Exception {
        List<HeroTheme> heroThemes = repository.findAll();
        List<HeroTheme> test = new ArrayList<>();
        List<JSONObject> finalAnswer = new ArrayList<JSONObject>();
        JSONArray jsonArray = new JSONArray();
        test.add(heroThemes.get(0));
        test.add(heroThemes.get(1));
        // String res = "<Name>HasanPrakash</Name>";
        JSONObject jsonObject = new JSONObject();
        JSONObject inner = new JSONObject();
        inner.put("name", "Assassin");
        jsonObject.put("name", inner);
        System.out.println(jsonObject);
        finalAnswer.add(jsonObject);
        jsonArray.put(jsonObject);
        return jsonArray.toList(); 
    }

    @GetMapping("/task1")
    public List<Object> solveTask1() throws Exception {
        JSONArray ansArray = new JSONArray();
        List<HeroTheme> heroThemes = repository.findAll();
        for(int i=0;i<heroThemes.size();i++) {
            int parentId = heroThemes.get(i).getParentId();
            Optional<HeroTheme> parentRecord = repository.findById(parentId);
            if(parentRecord.isPresent()) {
                Stack<HeroTheme> stack = new Stack<HeroTheme>();
                stack.push(heroThemes.get(i));
                while(parentRecord.isPresent()) {
                    stack.push(parentRecord.get());
                    parentRecord = repository.findById(parentRecord.get().getParentId());
                }
                String parentName = stack.pop().getName();
                JSONArray jsonTranversalArray = ansArray;
                while(!stack.isEmpty()) {
                    String theName = stack.pop().getName();
                    System.out.println(theName);

                    if(!stack.isEmpty()) {
                        int index = getHeroThemeObject(jsonTranversalArray, parentName);
                        jsonTranversalArray = jsonTranversalArray.getJSONObject(index).getJSONArray("Sub Classes");
                    }
                    else {
                        int index = getHeroThemeObject(jsonTranversalArray, parentName);
                        JSONObject tempObject = jsonTranversalArray.getJSONObject(index);
                        JSONArray subClasses;
                        if(!tempObject.has("Sub Classes")) {
                            subClasses = new JSONArray();
                        }
                        else {
                            subClasses = (JSONArray) tempObject.get("Sub Classes");
                        }
                        JSONObject subRecordItem = new JSONObject();
                        subRecordItem.put("Name", theName);
                        subClasses.put(subRecordItem);
                        tempObject.put("Sub Classes", subClasses);
                        // jsonTranversalArray.put(index, tempObject); // no need
                    }
                    parentName = theName;
                }
                System.out.println();
            }
            else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Name", heroThemes.get(i).getName());
                ansArray.put(jsonObject);
            }
        }
        return ansArray.toList();
    }

    private int getHeroThemeObject(JSONArray arr, String name) {
        for(int i=0;i<arr.length();i++) {
            JSONObject temp = (JSONObject)arr.get(i);
            if(temp.get("Name").equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
