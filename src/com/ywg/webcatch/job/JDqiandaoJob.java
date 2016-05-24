package com.ywg.webcatch.job;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JDqiandaoJob {
	@Scheduled(cron = "0 0 1 * * ?")
	public void qiandao() throws IOException {
		System.out.println("进入测试");
		Response rep = Jsoup.connect("http://vip.jd.com/")
				.header("Host", "vip.jd.com").userAgent("Mozilla")
				.cookie("auth", "token").timeout(3000).ignoreContentType(true).method(Method.GET)
				.execute();
		
		System.err.println(rep.cookies());
	}
}
