package com.xz.nba.condition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xz.nba.domain.NBAInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ctc
 */
public class NbaCondition {
    private QueryWrapper<NBAInfo> wrapper = new QueryWrapper<>();
    private long currentPage = 1;

    private String name;

    public QueryWrapper<NBAInfo> wrapper() {
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("left_name", name).or().eq("right_name", name);
        }
        return wrapper;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

