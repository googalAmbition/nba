package com.xz.nba.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.nba.condition.NbaCondition;
import com.xz.nba.dao.NbaDao;
import com.xz.nba.domain.NBAInfo;
import com.xz.nba.service.NbaService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ctc
 */
@Service
public class NbaServiceImpl extends ServiceImpl<NbaDao, NBAInfo> implements NbaService {
    @Override
    public NBAInfo getByCondition(Date startTime, String leftId) {
        QueryWrapper<NBAInfo> query = new QueryWrapper<>();
        query.eq("date", startTime).eq("left_id", leftId);
        return baseMapper.selectOne(query);
    }

    @Override
    public IPage<NBAInfo> listByConditionPage(NbaCondition condition) {
        Page<NBAInfo> page = new Page<>(condition.getCurrentPage(), 15);
        IPage<NBAInfo> p = baseMapper.selectPage(page, condition.wrapper());
        return p;
    }
}

