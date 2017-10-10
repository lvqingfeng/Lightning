package com.lightningfast.uitls;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ListUtils {

    public static <T> List<T> checkDifferent(List<T> list1, List<T> list2) {

        List<T> diff = new ArrayList<T>();
        List<T> maxList = list1;
        List<T> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        Map<T, Integer> map = new HashMap<T, Integer>(maxList.size());
        for (T string : maxList) {
            map.put(string, 1);
        }
        for (T string : minList) {
            if (map.get(string) != null) {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        Set<Map.Entry<T, Integer>> entries = map.entrySet();
        for (Map.Entry<T, Integer> entry : entries) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }

        return diff;

    }

    public static void appendIdList(List<String> idList, StringBuilder sb) {
        for (int i = 0; i < idList.size(); i++) {
            sb.append(idList.get(i));
            if (i + 1 < idList.size()) {
                sb.append(",");
            }
        }
    }

}
