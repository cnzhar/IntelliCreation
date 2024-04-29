package com.intellicreation.article.ai;

import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author za
 */
public class Classify {

    public static void main(String[] args) throws Exception {

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

        // 创建新的实例
        Instance[] trainText = new Instance[15000];
        int trainIndex = 0;
        String spamDirectoryPath = "D:\\Users\\za\\Desktop\\train\\spam\\";
        File spamDirectory = new File(spamDirectoryPath);
        if (spamDirectory.exists() && spamDirectory.isDirectory()) {
            File[] files = spamDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    trainText[trainIndex] = new DenseInstance(2);
                    trainText[trainIndex].setDataset(data);
                    Scanner scanner = new Scanner(file);
                    // 读取文件内容并存储到变量中
                    StringBuilder contentBuilder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        contentBuilder.append(scanner.nextLine());
                    }
                    String fileContent = contentBuilder.toString();
                    scanner.close();
                    trainText[trainIndex].setValue(0, String.valueOf(NlpAnalysis.parse(fileContent).recognition(stopRecognition)));
                    trainText[trainIndex].setValue(1, "spam");
                    data.add(trainText[trainIndex]);
                    trainIndex++;
                }
            } else {
                System.out.println("目录为空或无法读取文件。");
            }
        } else {
            System.out.println("指定的目录不存在或不是一个目录。");
        }
        String nonSpamDirectoryPath = "D:\\Users\\za\\Desktop\\train\\non-spam\\";
        File nonSpamDirectory = new File(nonSpamDirectoryPath);
        if (nonSpamDirectory.exists() && nonSpamDirectory.isDirectory()) {
            File[] files = nonSpamDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    trainText[trainIndex] = new DenseInstance(2);
                    trainText[trainIndex].setDataset(data);
                    Scanner scanner = new Scanner(file);
                    // 读取文件内容并存储到变量中
                    StringBuilder contentBuilder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        contentBuilder.append(scanner.nextLine());
                    }
                    String fileContent = contentBuilder.toString();
                    scanner.close();
                    trainText[trainIndex].setValue(0, String.valueOf(NlpAnalysis.parse(fileContent).recognition(stopRecognition)));
                    trainText[trainIndex].setValue(1, "non-spam");
                    data.add(trainText[trainIndex]);
                    trainIndex++;
                }
            } else {
                System.out.println("目录为空或无法读取文件。");
            }
        } else {
            System.out.println("指定的目录不存在或不是一个目录。");
        }

        // 文本特征提取
        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
//        filter.setTFTransform(true);
//        filter.setIDFTransform(true);
        Instances newData = Filter.useFilter(data, filter);
        System.out.println("属性的数量：" + newData.numAttributes());

        // 构建贝叶斯分类器,训练模型
        NaiveBayes classifier = new NaiveBayes();
        classifier.buildClassifier(newData);

        int count = 0;
        int spamCount = 0;
        int nonspamCount = 0;
        int tempcount = 0;
        for (int i = 0; i < 14853; i++) {
            double predictedClass = classifier.classifyInstance(newData.instance(i));
            String predictedClassLabel = data.classAttribute().value((int) predictedClass);
            if (predictedClassLabel.equals(data.instance(i).stringValue(classAttribute))) {
                count++;
            }
            if ("spam".equals(predictedClassLabel)) {
                spamCount++;
            } else if ("non-spam".equals(predictedClassLabel)) {
                nonspamCount++;
            }
            if ("spam".equals(newData.instance(i).stringValue(classAttribute))) {
                tempcount++;
            }
        }
        System.out.println("correct-train-count:" + count);
        System.out.println("spam-count:" +  spamCount);
        System.out.println("nonspam-count:" +  nonspamCount);
        System.out.println("newData有多少spam" + tempcount);

        // 交叉验证评估分类器性能
        Evaluation eval = new Evaluation(newData);
        eval.crossValidateModel(classifier, newData, 10, new Random(System.currentTimeMillis()));
        System.out.println(eval.toSummaryString("\nResults\n=======", false));

        // 指定保存模型的文件路径
        String modelFilePath = "D:\\Users\\za\\Desktop\\train\\bayes.model";
        // 保存模型
        SerializationHelper.write(modelFilePath, classifier);
    }
}
