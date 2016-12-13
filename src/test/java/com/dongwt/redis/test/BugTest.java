package com.dongwt.redis.test;

import java.io.FileWriter;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.entity.Area;
import com.dongwt.redis.utils.BugUtils;

public class BugTest {

    private String preUrl = "http://www.fangjiadp.com";

    @Test
    public void getUrlList() throws Exception {
        

        FileWriter fw = new FileWriter("C:\\data\\result.txt");
        
        for (int i = 1; i < 884; i++) {
            System.out.println("==>"+i);
            URL url = new URL("http://www.fangjiadp.com/shanghai/esf/advanced/search?pg=" + i + "&lt=1");
            Document doc = Jsoup.parse(url, 1200);
            Element content = doc.getElementById("esfunitlist");
            Elements links = content.getElementsByTag("a");
            for (Element link : links) {
                System.out.println("-------------------------------------------");
                
                //获取link
                String linkHref = preUrl + link.attr("href");
                String[] arr = linkHref.split("/");
                //获取unitId
                Area result = BugUtils.getUnitId(linkHref);
                String unitId = result.getUnitId();
                String body = BugUtils.getBody(
                        "http://www.fangjiadp.com/unit/comment/"+unitId+"/1?unitIntId=" + arr[arr.length - 1]);
                Area area = JSONObject.parseObject(body,Area.class);
                for(int j=2; j<= area.getTotalPage(); j++){
                    body = BugUtils.getBody(
                            "http://www.fangjiadp.com//unit/comment/"+unitId+"/"+j+"?unitIntId=" + arr[arr.length - 1]);
                    Area  tempArea = JSONObject.parseObject(body,Area.class);
                    area.getUnitComments().addAll(tempArea.getUnitComments());
                }
                
                Elements emTags = link.getElementsByTag("em");
                result.setAddress(emTags.get(0).text());
                result.setUnitComments(area.getUnitComments());
                StringBuffer sb = new StringBuffer();
                sb.append(result.getQyName()+"\t"+result.getBkName()+"\t"
                            +result.getXqName()+"\t"+result.getAddress()+"\t");
                
                for(int x=0;x<result.getUnitComments().size();x++){
                    sb.append(result.getUnitComments().get(x).getCommentContent()+"\t");
                }
                sb.append("\r\n");
                fw.write(sb.toString());
                
//                System.out.println(sb.toString());
            }
            
        }
        fw.flush();
        fw.close();
    }
    

}
