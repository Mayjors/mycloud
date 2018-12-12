package com.example.util.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成二维码
 * @author yuanjie
 * @date 2018/9/18 18:25
 */
public class CreateQrcode {

    public void getQrcode(String url, HttpServletResponse resp) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = resp.getOutputStream();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bm = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToStream(bm, "png", stream);
        } catch (WriterException e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    public static void getQrcode() {
        QRCode qrCode = new QRCode();
    }

}
