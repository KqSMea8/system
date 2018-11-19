package com.system.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static HttpClient httpClient;
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    static {
        List<Header> defaultHeaders = new ArrayList<Header>();
        defaultHeaders.add(new BasicHeader("content-type", "application/json"));
        httpClient = HttpClientBuilder.create()
                .setMaxConnPerRoute(50)
                .setMaxConnTotal(500)
                .setRetryHandler(new StandardHttpRequestRetryHandler(3, true))
                .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(2, 500))
                .setDefaultHeaders(defaultHeaders)
                .build();
    }


    public static String get(String httpUrl, List<NameValuePair> nvps) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(httpUrl);
        if (nvps != null && nvps.size() > 0) {
            String params = (URLEncodedUtils.format(nvps, DEFAULT_CHARSET));
            stringBuilder.append("?" + params);
        }
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        HttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }


    public String doPost(String url, String json) {
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(json, DEFAULT_CHARSET));
            HttpResponse response = httpClient.execute(post);
            result = inputStreamToStr(response.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("url=>{}json=>{}发送Post请求失败，异常=>{}", url, json, e);
        }
        return result;
    }

    private static List<NameValuePair> buildPostParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        List<NameValuePair> results = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            results.add(new BasicNameValuePair(key, value));
        }
        return results;
    }

    private static String inputStreamToStr(InputStream is, String charset) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return new String(buffer.toString().getBytes("ISO-8859-1"), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void postUrl(String url, String message) {
        HttpPost httppost = new HttpPost(url);
        String currentTime = String.valueOf(System.currentTimeMillis()/1000);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        httppost.addHeader("Authorization", "BTBPM17D4A7ABEE164341B879A1E9D44CB674");
        httppost.addHeader("App-Key", "192208F8DB5942DBBCB16ED762B7DCDE");
        httppost.addHeader("APP_SECRET", "65E66CFE173F4F8A864BCA50DB6FC979");
        httppost.addHeader("Timestamp", currentTime);
        httppost.addHeader("Sign-Method", "md5");
        httppost.addHeader("Sign", encryptSign(message, currentTime));
        StringEntity se = new StringEntity(message, "utf-8");
        httppost.setEntity(se);
        InputStream inputStream = null;
        HttpResponse response;
        try {
            response = httpClient.execute(httppost);
            inputStream = response.getEntity().getContent();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes, 0, 1024);
            while (len > 0) {
                output.write(bytes, 0, len);
                len = inputStream.read(bytes, 0, 1024);
            }
            String result = new String(output.toByteArray(), "utf-8");
            logger.info("发送请求完成,result:{}", result);
        } catch (IOException e) {
            logger.error("发送请求失败,异常:{}", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static String encryptSign(String param, String currentTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("65E66CFE173F4F8A864BCA50DB6FC979");
        stringBuilder.append("App-Key");
        stringBuilder.append("192208F8DB5942DBBCB16ED762B7DCDE");
        stringBuilder.append("Authorization");
        stringBuilder.append("BTBPM17D4A7ABEE164341B879A1E9D44CB674");
        stringBuilder.append("param");
        stringBuilder.append(param);
        stringBuilder.append("Sign-Method");
        stringBuilder.append("md5");
        stringBuilder.append("Timestamp");
        stringBuilder.append(currentTime);
        stringBuilder.append("AppSecret");
        String sign = null;
        try {
            byte[] bys = stringBuilder.toString().getBytes("UTF-8");
            byte[] bys1 = MD5Utils.getMD5Bytes(bys);
            sign =  BytesHexStrTranslate.bytesToHexFun1(bys1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }
}
