package net.zhenghao.zh.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 🙃
 * 🙃基于HttpClient连接的封装类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/4/11 14:21
 * HttpClientUtils.java
 */
public class HttpClientUtils {

    public static final String CHARSET = "UTF-8";

    /**
     * 最大线程数
     */
    public static final int THREAD_POOL_SIZE = 5;

    public interface HttpClientDownLoadProgress {
        public void onProgress(int progress);
    }

    private static CloseableHttpClient client;

    //HttpClient相当于一个浏览器，不停的发送get post请求，线程安全，所以运用单例
    private static CloseableHttpClient getHttpClient() {
        if (null == client) {
            //RequestConfig config = RequestConfig.custom()
            //       .setConnectTimeout(5000).setSocketTimeout(3000).build();
            client = HttpClients.createDefault();
        }
        return client;
    }

    private static HttpClientUtils httpClientDownload;

    private static ExecutorService downloadExecutorService;

    private HttpClientUtils() {
        downloadExecutorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    /**
     * 下载文件(GRT)
     * @param url 请求地址
     * @param filePath 请求后文件下载地址(文件路径+文件名)
     */
    public static void download(final String url, final String filePath) {
        downloadExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                httpDownloadFile(url, filePath, null, null);
            }
        });
    }

    /**
     * 下载文件(GRT)
     * @param url 请求地址
     * @param filePath 请求后文件下载地址(文件路径+文件名)
     * @param progress 进度回调
     */
    public static void download(final String url, final String filePath,
                         final HttpClientDownLoadProgress progress) {
        downloadExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                httpDownloadFile(url, filePath, progress, null);
            }
        });
    }

    /**
     * 下载文件(GRT)
     * @param url 请求地址
     * @param filePath 请求后文件下载地址(文件路径+文件名)
     * @param progress 进度回调
     * @param headMap  请求头
     */
    private static void httpDownloadFile(String url, String filePath,
                                  HttpClientDownLoadProgress progress,
                                  Map<String, String> headMap) {
        client = getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            setGetHead(httpGet, headMap);
            CloseableHttpResponse response = client.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                long contentLength = httpEntity.getContentLength();
                InputStream is = httpEntity.getContent();
                // 根据InputStream 下载文件
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int r = 0;
                long totalRead = 0;
                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                    totalRead += r;
                    if (progress != null) {// 回调进度
                        progress.onProgress((int) (totalRead * 100 / contentLength));
                    }
                }
                FileOutputStream fos = new FileOutputStream(filePath);
                output.writeTo(fos);
                output.flush();
                output.close();
                fos.close();
                EntityUtils.consume(httpEntity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * HTTP Get 获取内容
     *
     * @param url 请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @return 返回内容
     */
    public static String sendGet(String url, Map<String, Object> params) throws IOException {
        return sendGet(url, params, null);
    }

    /**
     * HTTP Get 获取内容
     * @param url 请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param headMap 请求头参数
     * @return 返回内容
     * @throws IOException
     */
    public static String sendGet(String url, Map<String, Object> params,
                                 Map<String, String> headMap) throws IOException {

        if(params !=null && !params.isEmpty()){
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
            for (String key :params.keySet()){
                pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            url +="?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs), CHARSET);
        }

        HttpGet httpGet = new HttpGet(url);
        setGetHead(httpGet, headMap);
        client = getHttpClient();
        CloseableHttpResponse response = client.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode != HttpStatus.SC_OK){
            httpGet.abort();
            return "HttpClient,error status code :" + statusCode;
        }
        HttpEntity entity = response.getEntity();
        String result = getResponseString(entity);
        response.close();
        return result;
    }

    /**
     * HTTP Post 获取内容
     * @param url url请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, Map<String, Object> params) throws IOException {
        return sendPost(url, params, null);
    }

    /**
     * HTTP Post 获取内容
     * @param url url请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param headMap 请求头参数
     * @return 页面内容
     * @throws IOException
     */
    public static String sendPost(String url, Map<String, Object> params,
                                  Map<String, String> headMap) throws IOException {

        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        HttpPost httpPost = new HttpPost(url);
        setPostHead(httpPost, headMap);
        if (pairs != null && pairs.size() > 0) {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
        }
        client = getHttpClient();
        CloseableHttpResponse response = client.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            httpPost.abort();
            return "HttpClient,error status code :" + statusCode;
        }
        HttpEntity entity = response.getEntity();
        String result = getResponseString(entity);
        response.close();
        return result;
    }

    /**
     * HTTP Post 传json串 获取内容
     * @param url
     * @param params json串
     * @return
     * @throws Exception
     */
    public static String sendPost(String url, String params) throws Exception {
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        client = getHttpClient();
        try {
            response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            String result = null;
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity);
                return result;
            }
            else{
                return "HttpClient,error status code :" + statusCode;
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置http的HEAD
     *
     * @param httpPost
     * @param headMap
     */
    private static void setPostHead(HttpPost httpPost, Map<String, String> headMap) {
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpPost.addHeader(key, headMap.get(key));
            }
        }
    }

    /**
     * 设置http的HEAD
     *
     * @param httpGet
     * @param headMap
     */
    private static void setGetHead(HttpGet httpGet, Map<String, String> headMap) {
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpGet.addHeader(key, headMap.get(key));
            }
        }
    }

    /**
     * 将返回结果转化为String
     * @param entity
     * @return
     * @throws IOException
     */
    private static String getResponseString(HttpEntity entity) throws IOException {
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        }else{
            return null;
        }
    }
}
