package com.xz.nba.webmagic;

import com.xz.nba.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by Administrator on 2018/12/21.
 */
public class NormalTest {
    @Test
    public void test(){
        System.out.println(DateUtils.parseDate(StringUtils.trim(" 2018-12-12 ")));
    }
}
