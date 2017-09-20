package com.cccis.oa.wechat.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.cccis.oa.wechat.service.OcrService;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ccc on 2017/9/15.
 */
public class BaiduOcrServiceImpl implements OcrService {

    //设置APPID/AK/SK
    public static final String APP_ID = "10082052";
    public static final String API_KEY = "58BYvAoABCE4gTGLxuNwzCsq";
    public static final String SECRET_KEY = "zhtRY6HSPjDkIFNgChvoqE9L2xyV9V0a";
    private static Gson gson = new Gson();
    private AipOcr client;

    public BaiduOcrServiceImpl() {
        // 初始化一个OcrClient
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public String executeVinOcr(InputStream input) throws IOException {
        // 调用通用识别接口
        byte[] imageBytes = toByteArray(input);
        return executeVinOcr(imageBytes);
    }

    public String executeVinOcr(byte[] imageBytes) throws IOException {
        String ocrData = null;
        long s = System.currentTimeMillis();
        JSONObject genRes = client.basicGeneral(imageBytes, new HashMap<String, String>());
        long e = System.currentTimeMillis() - s;
        String words = genRes.get("words_result").toString();
        List<LinkedTreeMap<String, String>> wordMap = gson.fromJson(words, List.class);
        ocrData = wordMap.get(0).get("words").replaceAll("\\s*","");
        System.out.println(ocrData+", take time: " + e);
        return ocrData;
    }

    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
