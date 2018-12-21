package com.xz.nba.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xz.nba.condition.NbaCondition;
import com.xz.nba.domain.NBAInfo;

import java.util.Date;
import java.util.List;

/**
 * @author ctc
 */
public interface NbaService extends IService<NBAInfo> {
    NBAInfo getByCondition(Date startTime, String leftId);

    IPage<NBAInfo> listByConditionPage(NbaCondition condition);
}
