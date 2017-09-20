package com.cccis.oa.wechat.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ccc on 2017/9/14.
 */
public interface OcrService {

    public String executeVinOcr(InputStream input) throws IOException;
    public String executeVinOcr(byte[] imgBytes) throws IOException;
}
