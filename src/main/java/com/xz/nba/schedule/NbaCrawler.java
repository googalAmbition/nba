package com.xz.nba.schedule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xz.nba.common.dto.NBAJson;
import com.xz.nba.common.enums.NbaEnum.Quarter;
import com.xz.nba.common.util.DateUtils;
import com.xz.nba.common.util.DecodeUtil;
import com.xz.nba.common.util.HttpRequestUtils;
import com.xz.nba.domain.NBAInfo;
import com.xz.nba.service.NbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author ctc
 */
@Component
public class NbaCrawler {
    // private Date

    @Autowired
    private NbaService nbaService;

    public void execute() {
        System.out.println("=============nba==============");
        runCrawler();
    }

    public void runCrawler() {

        String today = DateUtils.getDate();
        today = "2018-12-20";
        String url = "http://matchweb.sports.qq.com/kbs/list?from=NBA_PC&columnId=100000&startTime=" + today
                + "&endTime=" + today;
        String json;
        try {
            json = DecodeUtil.decodeUnicode(HttpRequestUtils.httpGet(url, null, null, 6000, false));
            JSONObject object = JSONObject.parseObject(json);
            JSONArray array = object.getJSONObject("data").getJSONArray(today);
            List<NBAJson> list = array.toJavaList(NBAJson.class);
            // System.out.println(list.size());
            // for (NBAJson nbaJson : list) {
            // System.out.println(nbaJson.getLeftId());
            // }
            handle(list);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 处理比分，生成每节比分
     *
     * @param jsons
     * @return
     */
    private void handle(List<NBAJson> jsons) {
        if (null != jsons) {
            for (NBAJson nbaJson : jsons) {
                NBAInfo db = nbaService.getByCondition(nbaJson.getStartTime(), nbaJson.getLeftId());
                if (db == null) {
                    db = new NBAInfo(nbaJson);
                } else {
                    db.setLeftGoal(nbaJson.getLeftGoal());
                    db.setRightGoal(nbaJson.getRightGoal());
                }
                if (Quarter.第1节.name().equals(nbaJson.getQuarter())) {
                    // System.out.println("第1节" + nbaJson.getQuarter());
                    db.setLeftQuarter1(nbaJson.getLeftGoal());
                    db.setRightQuarter1(nbaJson.getRightGoal());
                    nbaService.saveOrUpdate(db);
                } else if (Quarter.第2节.name().equals(nbaJson.getQuarter())) {
                    // System.out.println("第2节" + nbaJson.getQuarter());
                    db.setLeftQuarter2(nbaJson.getLeftGoal() - db.getLeftQuarter1());
                    db.setRightQuarter2(nbaJson.getRightGoal() - db.getRightQuarter1());
                    nbaService.saveOrUpdate(db);
                } else if (Quarter.第3节.name().equals(nbaJson.getQuarter())) {
                    // System.out.println("第3节" + nbaJson.getQuarter());
                    db.setLeftQuarter3(nbaJson.getLeftGoal() - db.getLeftQuarter1() - db.getLeftQuarter2());
                    db.setRightQuarter3(nbaJson.getRightGoal() - db.getRightQuarter1() - db.getRightQuarter2());
                    nbaService.saveOrUpdate(db);
                } else if (Quarter.第4节.name().equals(nbaJson.getQuarter())) {
                    db.setLeftQuarter4(nbaJson.getLeftGoal() - db.getLeftQuarter1() - db.getLeftQuarter2()
                            - db.getLeftQuarter3());
                    db.setRightQuarter4(nbaJson.getRightGoal() - db.getRightQuarter1() - db.getRightQuarter2()
                            - db.getRightQuarter3());
                    nbaService.saveOrUpdate(db);
                }

            }
        }
    }
}

