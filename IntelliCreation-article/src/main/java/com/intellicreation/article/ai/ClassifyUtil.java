package com.intellicreation.article.ai;

import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.NlpAnalysis;
import weka.classifiers.Classifier;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author za
 */
public class ClassifyUtil {

    public static boolean isSensitive(String str) throws Exception {

        Classifier model = (Classifier) SerializationHelper.read("D:\\Users\\za\\Desktop\\train\\bayes.model");

        // 创建属性
        FastVector attributes = new FastVector();
        Attribute attribute1 = new Attribute("邮件内容", (ArrayList<String>) null);
        FastVector classValues = new FastVector();
        classValues.addElement("spam");
        classValues.addElement("non-spam");
        Attribute classAttribute = new Attribute("类别标签", classValues);
        attributes.addElement(attribute1);
        attributes.addElement(classAttribute);
        // 创建数据集
        Instances data = new Instances("MyDataset", attributes, 0);
        // 设置类别标签
        data.setClassIndex(data.numAttributes() - 1);

        // 分词初始化
        StopRecognition stopRecognition = new StopRecognition();
        // 读取停用词列表文件
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Users\\za\\Desktop\\train\\stopWords.txt"))) {
            String line;
            // 逐行读取停用词并插入到停用词识别器中
            while ((line = br.readLine()) != null) {
                stopRecognition.insertStopWords(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopRecognition.insertStopRegexes(".");
        stopRecognition.insertStopNatures("en");
//        stopRecognition.insertStopNatures("m");

        Instance testText = new DenseInstance(2);
        testText.setValue(attribute1, String.valueOf(NlpAnalysis.parse(str +
                "\n").recognition(stopRecognition)));
        testText.setValue(classAttribute, "non-spam");
        data.add(testText);

        // 文本特征提取
        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
        Instances newData = Filter.useFilter(data, filter);

        double prediction = model.classifyInstance(newData.instance(0));
        System.out.println(newData);
        String predictedClass = data.classAttribute().value((int) prediction);

        if ("spam".equals(predictedClass)) {
            return true;
        } else {
            return false;
        }
    }
}
