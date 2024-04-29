package com.intellicreation.article.ai;

import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import weka.core.DenseInstance;

import java.io.*;
import java.util.*;

public class WordFrequency {

    public static void wordFrequency() throws IOException {
        Map<String, Integer> map = new HashMap<>();

        String directoryPath = "D:\\Users\\za\\Desktop\\train\\spam\\";
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    String article = getString(file.getPath());
                    String result = ToAnalysis.parse(article).toStringWithOutNature();
                    String[] words = result.split(",");

                    for(String word: words) {
                        String str = word.trim();
                        // 过滤空白字符
                        if ("".equals(str)) {
                            continue;
                        }
                        // 过滤一些高频率的符号
                        else if (str.matches("[）|（|.|，|。|+|-|“|”|：|？|\\s]")) {
                            continue;
                        }
                        // 此处过滤长度为1的str
                        else if (str.length() < 2) {
                            continue;
                        }

                        if (!map.containsKey(word)) {
                            map.put(word, 1);
                        } else {
                            int n = map.get(word);
                            map.put(word, ++n);
                        }
                    }
                }
            } else {
                System.out.println("目录为空或无法读取文件。");
            }
        } else {
            System.out.println("指定的目录不存在或不是一个目录。");
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        Map.Entry<String, Integer> entry;
        while ((entry = getMax(map)) != null){
            list.add(entry);
        }

        System.out.println(Arrays.toString(list.toArray()));

    }

    public static Map.Entry<String, Integer> getMax(Map<String, Integer> map){
        if (map.size() == 0){
            return null;
        }
        Map.Entry<String, Integer> maxEntry = null;
        boolean flag = false;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            if (!flag){
                maxEntry = entry;
                flag = true;
            }
            if (entry.getValue() > maxEntry.getValue()){
                maxEntry = entry;
            }
        }
        map.remove(maxEntry.getKey());
        return maxEntry;
    }

    public static String getString(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder strBuilder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null){
            strBuilder.append(line);
        }
        reader.close();
        inputStream.close();
        return strBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        WordFrequency.wordFrequency();
    }
}
