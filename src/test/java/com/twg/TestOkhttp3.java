package com.twg;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by twg on 2017/7/10.
 */
public class TestOkhttp3 {

    private  OkHttpClient okHttpClient;
    private String formhash;


    public TestOkhttp3(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public void get1(){
        String url = "http://www.zhenhao2016.com/forum.php";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = this.okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            //System.out.println(content);
            if(content.contains("xboxer")){
                System.out.println("包含");
            }else {
                System.out.println("不包含");
            }
            System.out.println("end get");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(){
        String url = "http://www.zhenhao2016.com/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&inajax=1";
        RequestBody body = new FormBody.Builder()
                .add("username", "xboxer")
                .add("password", "tianwenguo1222")
                .add("quickforward","yes")
                //.add("fastloginfield","username")
                .add("handlekey","1s")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = this.okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            System.out.println("end login!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void formhash(){

        String url = "http://www.zhenhao2016.com/plugin.php?id=dsu_paulsign:sign&infloat=yes&handlekey=dsu_paulsign&inajax=1&ajaxtarget=fwin_content_dsu_paulsign";

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = this.okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            System.out.println(content);

            String regEx = "input type=\\\"hidden\\\" name=\\\"formhash\\\" value=\\\"(.*?)\\\"";

            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(content);

            if(matcher.find()){
                System.out.println("Found value: " + matcher.group(0) );
                System.out.println("Found value: " + matcher.group(1) );
                this.formhash = matcher.group(1);
                System.out.println("end formhash!");
            }else {
                System.out.println("NO MATCH");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sign(){
        String url="http://www.zhenhao2016.com/plugin.php?id=dsu_paulsign:sign&operation=qiandao&infloat=1&sign_as=1&inajax=1";


        RequestBody body = new FormBody.Builder()
                .add("formhash", this.formhash)
                .add("qdxq", "kx")
                .add("qdmode","1")
                .add("fastreply","1")
                .add("todaysay","111111")
                .build();

        Request request = new Request.Builder()
                .url(url)
                //.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                //.header("Content-Type","text/html; charset=utf-8")
                .post(body)
                .build();

        Call call = this.okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String content = response.body().string();
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OkHttpClient okHttpClient;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                cookieStore.put(url.host(), cookies);
                for(Cookie cookie:cookies){
                    System.out.println("add cookies");
                    System.out.println("cookie Name:"+cookie.name());
                    System.out.println("cookie value:"+cookie.value());
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {

                List<Cookie> cookies = cookieStore.get(url.host());
                if(cookies==null){
                    System.out.println("没加载到cookie");

                    return new ArrayList<Cookie>();
                }else{
                    for(Cookie cookie:cookies){
                        System.out.println("get cookies!");
                        System.out.println("cookie Name:"+cookie.name());
                        System.out.println("cookie value:"+cookie.value());
                    }

                    return cookies;
                }
            }
        });
        okHttpClient = builder.build();

        TestOkhttp3 testOkhttp3 = new TestOkhttp3(okHttpClient);
        testOkhttp3.login();

        testOkhttp3.get1();
        testOkhttp3.formhash();
        //testOkhttp3.sign();
    }
}
