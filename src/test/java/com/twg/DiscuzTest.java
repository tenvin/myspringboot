package com.twg;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by twg on 2017/7/10.
 */
public class DiscuzTest {
    public static void main(String[] args) {
        String loginUrl = "http://www.zhenhao2016.com/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&inajax=1";
        String formhashUrl = "http://www.zhenhao2016.com/plugin.php?id=dsu_paulsign:sign&infloat=yes&handlekey=dsu_paulsign&inajax=1&ajaxtarget=fwin_content_dsu_paulsign";
        String signUrl = "http://www.zhenhao2016.com/plugin.php?id=dsu_paulsign:sign&operation=qiandao&infloat=1&sign_as=1&inajax=1";

        String formhash = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();


        try {
            //1.login
            List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair("username", "xboxer"));
            valuePairs.add(new BasicNameValuePair("quickforward", "yes"));
            valuePairs.add(new BasicNameValuePair("password", "tianwenguo1222"));
            valuePairs.add(new BasicNameValuePair("handlekey", "1s"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);

            HttpPost post = new HttpPost(loginUrl);
            post.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(post);
            //打印登录是否成功信息
            printResponse(httpResponse);

            //2.formhash
            HttpGet get = new HttpGet(formhashUrl);
            HttpResponse response = httpClient.execute(get);
            String content = printResponse(response);
            String regEx = "input type=\\\"hidden\\\" name=\\\"formhash\\\" value=\\\"(.*?)\\\"";

            System.out.println("content"+content);
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(content);

            if(matcher.find()){
                System.out.println("Found value: " + matcher.group(0) );
                System.out.println("Found value: " + matcher.group(1) );
                formhash = matcher.group(1);
            }else {
                System.out.println("NO MATCH");
            }
            //3.sign
            List<NameValuePair> valuePairs2 = new LinkedList<NameValuePair>();
            valuePairs2.add(new BasicNameValuePair("formhash", formhash));
            valuePairs2.add(new BasicNameValuePair("qdxq", "kx"));
            valuePairs2.add(new BasicNameValuePair("qdmode", "1"));
            valuePairs2.add(new BasicNameValuePair("fastreply", "1"));
            valuePairs2.add(new BasicNameValuePair("todaysay", "哈哈哈哈"));
            UrlEncodedFormEntity entity2 = new UrlEncodedFormEntity(valuePairs2, Consts.UTF_8);

            HttpPost post2 = new HttpPost(signUrl);
            post2.setEntity(entity2);
            HttpResponse httpResponse2 = httpClient.execute(post2);
            printResponse(httpResponse2);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String printResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            System.out.println("response length:" + responseString.length());
            System.out.println("response content:"
                    + responseString.replace("\r\n", ""));
            return responseString;
        }
        return null;
    }

}
