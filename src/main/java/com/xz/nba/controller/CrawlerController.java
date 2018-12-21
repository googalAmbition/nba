package com.xz.nba.controller;

import com.xz.nba.common.util.DateUtils;
import com.xz.nba.domain.NBAInfo;
import com.xz.nba.service.NbaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/21.
 */
@Controller

public class CrawlerController {
    @Autowired
    private NbaService nbaService;

    @RequestMapping("/crawler")
    public void crawler() {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>()) {
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:");
            }

            protected void afterExecute(Thread t, Runnable r) {
                System.out.println("执行完成:");
            }

            protected void terminated() {
                System.out.println("线程池退出!");
            }
        };
        for (int i = 0; i < 42980; i++) {
            String url = "http://www.stat-nba.com/game/" + i + ".html";
            Task task = new Task();
            task.url = url;
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    private static ThreadLocal<StatDataCrawler> threadLocal = ThreadLocal.withInitial(() -> new StatDataCrawler());

    private class Task implements Runnable {

        private String url;

        @Override
        public void run() {

            try {
                System.out.println(System.currentTimeMillis() + "Thread ID:" + Thread.currentThread().getId());
                StatDataCrawler crawler = threadLocal.get();
                crawler.excute(url);
                String dateString = crawler.getDateString();
                List<String> goals = crawler.getGoals();
                List<String> teams = crawler.getTeams();
                NBAInfo info = new NBAInfo();
                info.setStartTime(DateUtils.parseDate(StringUtils.trim(dateString)));
                info.setLeftName(teams.get(0));
                info.setRightName(teams.get(1));
                int quater = goals.size();
                if (quater == 8) {
                    for (int i = 0; i < quater; i++) {
                        if (i < quater / 2) {
                            info.setLeftQuarter1(Integer.parseInt(goals.get(0)));
                            info.setLeftQuarter2(Integer.parseInt(goals.get(1)));
                            info.setLeftQuarter3(Integer.parseInt(goals.get(2)));
                            info.setLeftQuarter4(Integer.parseInt(goals.get(3)));
                        } else {
                            info.setRightQuarter1(Integer.parseInt(goals.get(4)));
                            info.setRightQuarter2(Integer.parseInt(goals.get(5)));
                            info.setRightQuarter3(Integer.parseInt(goals.get(6)));
                            info.setRightQuarter4(Integer.parseInt(goals.get(7)));
                        }
                    }
                } else if (quater == 10) {
                    for (int i = 0; i < quater; i++) {
                        if (i < quater / 2) {
                            info.setLeftQuarter1(Integer.parseInt(goals.get(0)));
                            info.setLeftQuarter2(Integer.parseInt(goals.get(1)));
                            info.setLeftQuarter3(Integer.parseInt(goals.get(2)));
                            info.setLeftQuarter4(Integer.parseInt(goals.get(3)));
                            info.setLeftQuarter5(Integer.parseInt(goals.get(4)));
                        } else {
                            info.setRightQuarter1(Integer.parseInt(goals.get(5)));
                            info.setRightQuarter2(Integer.parseInt(goals.get(6)));
                            info.setRightQuarter3(Integer.parseInt(goals.get(7)));
                            info.setRightQuarter4(Integer.parseInt(goals.get(8)));
                            info.setRightQuarter5(Integer.parseInt(goals.get(9)));
                        }
                    }

                } else if (quater == 12) {
                    for (int i = 0; i < quater; i++) {
                        if (i < quater / 2) {
                            info.setLeftQuarter1(Integer.parseInt(goals.get(0)));
                            info.setLeftQuarter2(Integer.parseInt(goals.get(1)));
                            info.setLeftQuarter3(Integer.parseInt(goals.get(2)));
                            info.setLeftQuarter4(Integer.parseInt(goals.get(3)));
                            info.setLeftQuarter5(Integer.parseInt(goals.get(4)));
                            info.setLeftQuarter6(Integer.parseInt(goals.get(5)));
                        } else {
                            info.setRightQuarter1(Integer.parseInt(goals.get(6)));
                            info.setRightQuarter2(Integer.parseInt(goals.get(7)));
                            info.setRightQuarter3(Integer.parseInt(goals.get(8)));
                            info.setRightQuarter4(Integer.parseInt(goals.get(9)));
                            info.setRightQuarter5(Integer.parseInt(goals.get(10)));
                            info.setRightQuarter6(Integer.parseInt(goals.get(11)));
                        }
                    }
                }
                nbaService.save(info);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
