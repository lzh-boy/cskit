package com.cskit.utils.commonutils;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Micro
 * @Title: Http异步请求
 * @Package com.cskit.utils.commonutils
 * @Description: null
 * @date 2018/6/26 20:01
 */
public class AsyncHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(AsyncHttpUtil.class);
    private static final ThreadLocal<Exception> exception = new ThreadLocal<>();
    private static PoolingNHttpClientConnectionManager connMgr;
    private static ConnectingIOReactor ioReactor;
    private static int MAX_TIMEOUT = 30000;
    private static int MAX_POOL_COUNT = 100;
    private static CloseableHttpAsyncClient httpClient;

    static {
        try {
            AsyncHttpUtil.MAX_TIMEOUT = 120000;
            RegistryBuilder<SchemeIOSessionStrategy> registryBuilder = RegistryBuilder.create();
            Registry<SchemeIOSessionStrategy> sessionStrategyRegistry =
                    registryBuilder.register("http", NoopIOSessionStrategy.INSTANCE)
                            .register("https", new SSLIOSessionStrategy(AsyncHttpUtil.createIgnoreVerifySSL())).build();
            IOReactorConfig config = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors())
                    .setConnectTimeout(AsyncHttpUtil.MAX_TIMEOUT).setSoTimeout(AsyncHttpUtil.MAX_TIMEOUT).build();
            AsyncHttpUtil.ioReactor = new DefaultConnectingIOReactor(config);
            AsyncHttpUtil.connMgr =
                    new PoolingNHttpClientConnectionManager(AsyncHttpUtil.ioReactor, null, sessionStrategyRegistry, new SystemDefaultDnsResolver());
            AsyncHttpUtil.connMgr.setMaxTotal(AsyncHttpUtil.MAX_POOL_COUNT);
            AsyncHttpUtil.connMgr.setDefaultMaxPerRoute(AsyncHttpUtil.connMgr.getMaxTotal());
            AsyncHttpUtil.httpClient = HttpAsyncClients.custom().setConnectionManager(AsyncHttpUtil.connMgr).build();

            System.setProperty("jsse.enableSNIExtension", "false");
        } catch (IOReactorException e) {
            AsyncHttpUtil.logger.error("ERROR::", e);
        }
    }

    public static Exception hasException() {
        return AsyncHttpUtil.exception.get();
    }

    public static String doGet(String url, String data) {
        return AsyncHttpUtil.doGet(url, new HashMap<>(), null, null);
    }

    public static String doGet(String url, Map<String, Object> params) {
        return AsyncHttpUtil.doGet(url, params, null, null);
    }

    public static String doGet(String url, FutureCallback<HttpResponse> futureCallback) {
        return AsyncHttpUtil.doGet(url, new HashMap<>(), futureCallback, null);
    }

    public static String doGet(String url, FutureCallback<HttpResponse> futureCallback,
                               Map<String, String> headerParams) {
        return AsyncHttpUtil.doGet(url, new HashMap<>(), futureCallback, headerParams);
    }

    public static String doGet(String url, Map<String, Object> params, FutureCallback<HttpResponse> futureCallback,
                               Map<String, String> headerParams) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl = apiUrl + param;
        String result = null;
        try {
            if (!AsyncHttpUtil.httpClient.isRunning()) {
                AsyncHttpUtil.httpClient.start();
            }
            HttpGet httpGet = new HttpGet(apiUrl);

            AsyncHttpUtil.handleHeader(headerParams, httpGet);
            Future<HttpResponse> future = AsyncHttpUtil.httpClient.execute(httpGet, futureCallback);
            if (futureCallback == null) {
                HttpResponse response = future.get(AsyncHttpUtil.MAX_TIMEOUT, TimeUnit.MILLISECONDS);

                int statusCode = response.getStatusLine().getStatusCode();
                AsyncHttpUtil.logger.info("====================statusCode:{}", Integer.valueOf(statusCode));
                if (statusCode != 200) {
                    AsyncHttpUtil.exception.set(new Exception("reqest error, status code:" + statusCode));
                    return null;
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    result = IOUtils.toString(instream, "UTF-8");
                }
            }
        } catch (Exception e) {
            AsyncHttpUtil.logger.error("ERROR::", e);
            AsyncHttpUtil.exception.set(e);
        }
        return result;
    }

    public static String doPost(String apiUrl, String jsonStr) {
        return AsyncHttpUtil.doPost(apiUrl, null, jsonStr, null, null, null);
    }

    public static String doPost(String apiUrl, String jsonStr, Map<String, Object> params) {
        return AsyncHttpUtil.doPost(apiUrl, null, jsonStr, params, null, null);
    }

    public static String doPost(String apiUrl, String jsonStr, FutureCallback<HttpResponse> futureCallback) {
        return AsyncHttpUtil.doPost(apiUrl, null, jsonStr, null, futureCallback, null);
    }

    public static String doPost(String apiUrl, String jsonStr, Map<String, Object> params,
                                FutureCallback<HttpResponse> futureCallback) {
        return AsyncHttpUtil.doPost(apiUrl, null, jsonStr, params, futureCallback, null);
    }

    public static String doPost(String apiUrl, String jsonStr, FutureCallback<HttpResponse> futureCallback,
                                Map<String, String> headerParams) {
        return AsyncHttpUtil.doPost(apiUrl, null, jsonStr, null, futureCallback, headerParams);
    }

    public static String doPost(String apiUrl, Map<String, Object> urlParam) {
        return AsyncHttpUtil.doPost(apiUrl, urlParam, null, null, null, null);
    }

    public static String doPost(String apiUrl, Map<String, Object> urlParam, String jsonStr, Map<String, Object> params,
                                FutureCallback<HttpResponse> futureCallback, Map<String, String> headerParams) {

        StringBuffer param = new StringBuffer();
        int i = 0;
        if (urlParam != null) {
            for (String key : urlParam.keySet()) {
                if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(key).append("=").append(urlParam.get(key));
                i++;
            }
        }

        apiUrl = apiUrl + param;

        String httpStr = null;
        try {
            if (!AsyncHttpUtil.httpClient.isRunning()) {
                AsyncHttpUtil.httpClient.start();
            }
            HttpPost httpPost = new HttpPost(apiUrl);
            if ((params != null) && (params.size() > 0)) {
                List<NameValuePair> pairList = new ArrayList<>(params.size());
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            }

            if (StringUtils.isNotBlank(jsonStr)) {
                StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
            }

            AsyncHttpUtil.handleHeader(headerParams, httpPost);

            Future<HttpResponse> future = AsyncHttpUtil.httpClient.execute(httpPost, futureCallback);
            if (futureCallback == null) {
                HttpResponse response = future.get(AsyncHttpUtil.MAX_TIMEOUT, TimeUnit.MILLISECONDS);
                try {
                    int statusCode = response.getStatusLine().getStatusCode();
                    AsyncHttpUtil.logger.info("====================statusCode:{}", Integer.valueOf(statusCode));
                    if (statusCode != 200) {
                        AsyncHttpUtil.exception.set(new Exception("reqest error, status code:" + statusCode));
                        return null;
                    }
                    HttpEntity entity = response.getEntity();
                    httpStr = EntityUtils.toString(entity, "UTF-8");
                } finally {
                    EntityUtils.consume(response.getEntity());
                }
                EntityUtils.consume(response.getEntity());
            }
        } catch (Exception e) {
            AsyncHttpUtil.logger.error("ERROR::", e);
            AsyncHttpUtil.exception.set(e);
        }
        return httpStr;
    }

    public static SSLContext createIgnoreVerifySSL() {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSLv3");

            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {}

                @Override
                public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
                        throws CertificateException {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sc.init(null, new TrustManager[] {trustManager}, null);
        } catch (Exception e) {
            AsyncHttpUtil.logger.error("ERROR::", e);
        }
        return sc;
    }

    private static void handleHeader(Map<String, String> headerParams, HttpRequestBase request) {
        if ((headerParams != null) && (headerParams.size() > 0)) {
            Iterator<String> it = headerParams.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                request.addHeader(key, headerParams.get(key));
            }
        }
    }

}
