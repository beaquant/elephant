package com.djt.cbs.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import com.google.gson.Gson;

public class HttpUtils {
    public static final String             HTTP_METHOD_GET  = "GET";
    public static final String             HTTP_METHOD_POST = "POST";
    private static org.apache.log4j.Logger log              = org.apache.log4j.LogManager
                                                                .getLogger(HttpUtils.class);

    private static class HttpRequestJob implements Runnable {
        private IHttpResponseCallback cb           = null;

        private HttpGet               get_request  = null;
        private HttpPost              post_request = null;

        private String                content      = null;

        public HttpRequestJob(HttpGet get_request, HttpPost post_request, IHttpResponseCallback cb) {
            this.get_request = get_request;
            this.post_request = post_request;
            this.cb = cb;
        }

        public String getContent() {
            return content;
        }

        @Override
        public void run() {
            HttpClient client = new DefaultHttpClient();
            BufferedReader in = null;
            HttpResponse response = null;
            try {
                if (get_request != null)
                    response = client.execute(get_request);
                else if (post_request != null)
                    response = client.execute(post_request);

                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
                    "UTF-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                content = sb.toString();
                log.info("[HTTP][RESPONSE]" + content);
            } catch (Exception e) {
                log.error("", e);
                content = e.getMessage();
            } finally {
                if (in != null) {
                    try {
                        in.close();// 最后要关闭BufferedReader  
                    } catch (Exception e) {
                        e.printStackTrace();
                        content = e.getMessage();
                    }
                }

                //System.out.println(response.getStatusLine().getStatusCode() + ":" + content);
                if (cb != null) {
                    cb.response(response.getStatusLine().getStatusCode(), content);
                }
            }
        }
    }

    public static interface IHttpResponseCallback {
        public void response(int http_code, String response);
    }

    public static String sendRequest(String url, Map<String, String> header, String data,
                                     String method, IHttpResponseCallback cb) {

        try {
            HttpRequestJob job = null;
            if (method.equalsIgnoreCase(HTTP_METHOD_GET)) {
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                if (header != null) {
                    for (Map.Entry<String, String> s : header.entrySet()) {
                        Header h = new BasicHeader(s.getKey(), s.getValue());
                        request.addHeader(h);
                    }
                }
                log.info("[HTTP][GET]url:" + request.getURI().toString());
                job = new HttpRequestJob(request, null, cb);
            } else if (method.equalsIgnoreCase(HTTP_METHOD_POST)) {
                HttpPost request = new HttpPost();
                request.setURI(new URI(url));

                if (header != null) {
                    for (Map.Entry<String, String> s : header.entrySet()) {
                        Header h = new BasicHeader(s.getKey(), s.getValue());
                        request.addHeader(h);
                    }
                }

                HttpEntity entity = new StringEntity(data, "utf-8");
                request.setEntity(entity);

                log.info("[HTTP][POST]url:" + request.getURI().toString());
                log.info("[HTTP][POST]data:" + data);
                job = new HttpRequestJob(null, request, cb);
            }

            Thread t = new Thread(job);
            t.start();

            if (cb == null) {
                t.join();
                return job.getContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static String resultBuildJson(Object result, OutputStream os) throws IOException {
        Gson gson = new Gson();
        String s = gson.toJson(result);
        if (os != null)
            os.write(s.getBytes());
        return s;
    }
}
