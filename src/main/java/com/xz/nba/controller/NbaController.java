package com.xz.nba.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xz.nba.common.util.pager.DtssPageBean;
import com.xz.nba.condition.NbaCondition;
import com.xz.nba.domain.NBAInfo;
import com.xz.nba.service.NbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ctc
 */
@Controller
public class NbaController {

    @Autowired
    private NbaService nbaService;

    @RequestMapping("/list")
    public String list(NbaCondition condition, Model model) {

        IPage<NBAInfo> page = nbaService.listByConditionPage(condition);
        model.addAttribute("test", "hello world");
        DtssPageBean pageBean = new DtssPageBean(page.getTotal(), 15,
                condition.getCurrentPage());
        pageBean.setParam("name", condition.getName());
        pageBean.setStyleType(DtssPageBean.STYLE_TOURISM_METHOD);
        pageBean.build();
        model.addAttribute("page", pageBean);
        model.addAttribute("list",page.getRecords());
        model.addAttribute("condition",condition);
        return "index";
    }
}

