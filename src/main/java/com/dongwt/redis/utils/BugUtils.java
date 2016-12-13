package com.dongwt.redis.utils;

import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dongwt.redis.entity.Area;

public class BugUtils {

    public static String getBody(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.    
            HttpPost httpPost = new HttpPost(url);
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体    
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
            finally {
                response.close();
            }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭连接,释放资源    
            try {
                httpclient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    

    public static Area getUnitId(String urlStr) throws Exception {
        URL url = new URL(urlStr + "_comment");
        Document doc = Jsoup.parse(url, 1200);
        String docStr = doc.toString();
        
        Area area = new Area();
        docStr = docStr.substring(docStr.indexOf("var unitId = ") + 13);
        area.setUnitId(docStr.substring(1, docStr.indexOf(";") - 1));
        
        Elements elements = doc.getElementsByClass("esgrid_left");
        Element  element = elements.get(0);
        Elements aTags = element.getElementsByTag("a");
        String title = aTags.get(0).attr("title");
        
        String preTitle = title.substring(title.indexOf("[") +1 ,title.indexOf("]"));
        String[] preTitleArr = preTitle.split("-");
        area.setQyName(preTitleArr[0]);
        area.setBkName(preTitleArr[1]);
        String suffixTitle = title.substring(title.indexOf("]") + 1);
        area.setXqName(suffixTitle);

        return area;

    }
    
    

}
