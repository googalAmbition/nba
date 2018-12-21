package com.xz.nba.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class StatDataCrawler implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);



    private List<String> teams = new ArrayList<>(2);
    private String dateString;
    private List<String> goals = new ArrayList<>();


    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
        //球队
        teams = page.getHtml().xpath("//div[@class='teamDiv]/div/a[@style='font-size:14px;']/text()").all();
//        page.addTargetRequests(urls);
        //比分
        goals = page.getHtml().xpath("//td[@class='number']/text()").all();
        //比赛日期
        dateString = page.getHtml()
                .xpath("//div[@style='float: left;margin-top: 25px;margin-left: 10px;font-size: 16px;font-weight: bold;color: #009CFF']/text()").get();
        System.out.println(dateString);


        for (String team : teams) {
            System.out.println(team);
        }
        for (String goal : goals) {
            System.out.println(goal);
        }
        System.out.println("=====================");

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void excute(){
        Spider.create(new StatDataCrawler()).addUrl("http://www.stat-nba.com/game/42979.html").run();
    }

    public static void main(String[] args) {
        Spider.create(new StatDataCrawler()).addUrl("http://www.stat-nba.com/game/42979.html").run();
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public List<String> getGoals() {
        return goals;
    }

    public void setGoals(List<String> goals) {
        this.goals = goals;
    }
}

