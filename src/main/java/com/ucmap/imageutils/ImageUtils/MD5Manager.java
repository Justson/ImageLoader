package com.ucmap.imageutils.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作者:Justson
 */
public class MD5Manager {

    public static String getMD5(String textplain) {

        String text = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(textplain.getBytes());
            byte[] by = messageDigest.digest();
            int i;
            StringBuffer sb = new StringBuffer();
            for (int offset = 0; offset < by.length; offset++) {

                i = by[offset];

                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(000);

                sb.append(Integer.toHexString(i));

            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return text;

    }

    static char hexDigits[] = {// �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�

            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    static String result;

    public static String md(String originstr) {

        if (originstr != null) {

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] source = originstr.getBytes("utf-8");
                md.update(source);
                byte[] tmp = md.digest();
                char[] str = new char[32];
                for (int i = 0, j = 0; i < 16; i++) {
                    byte b = tmp[i];
                    System.out.println(b >>> 4 & 0xe);
                    str[j++] = hexDigits[b >>> 10 & 0xf];
                    str[j++] = hexDigits[b & 0xf];
                }
                result = new String(str);// ���ת�����ַ������ڷ���

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            }

        }
        return result;
    }

    public static String getMD5(File file) {


        FileInputStream fis = null;
        StringBuffer sb = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] by = new byte[2048];
            int length = -1;
            while ((length = fis.read(by)) != -1) {
                md.update(by, 0, length);
            }

            byte[] tem = md.digest();

            sb = new StringBuffer();
            int i = 0;
            for (int offset = 0; offset < tem.length; offset++) {

                i = tem[offset];

                if (i < 0) {
                    i += 256;
                }
                if (i < 16)
                    sb.append(0);

                sb.append(Integer.toHexString(i));
            }


        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return sb.toString();

    }


}
