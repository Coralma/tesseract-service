package com.cccis.oa.wechat.action;

import java.io.IOException;

import com.alibaba.fastjson.util.Base64;
import com.cccis.oa.wechat.service.OcrService;
import com.cccis.oa.wechat.service.impl.BaiduOcrServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by CCC on 2016/10/25.
 */
@Controller
@RequestMapping("/ocr")
public class OcrAction {

    private OcrService ocrService = new BaiduOcrServiceImpl();

    public OcrAction() {
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public @ResponseBody
    String hello() {
        return "Hello OCR ";
    }

    @RequestMapping(value = "/vin", method = RequestMethod.POST,headers = "content-type=multipart/form-data")
    public @ResponseBody
    String vinOcr(@RequestParam("file") MultipartFile file) throws IOException {
        String vin = ocrService.executeVinOcr(file.getInputStream());
        return vin;
    }

    @RequestMapping(value = "/vin/base64", method = RequestMethod.POST)
    public @ResponseBody
    String vinOcrBase64(@RequestParam String base64File) throws IOException {
        byte[] imgBytes = Base64.decodeFast(base64File);
        String vin = ocrService.executeVinOcr(imgBytes);
        return vin;
    }
}