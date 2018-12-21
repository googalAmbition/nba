/**
 * @author luxiangixng
 * @time 2010-6-8
 * @email luxiangxing@jsj.com.cn
 * @tel 13488766519
 */
package com.xz.nba.common.util.pager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述:用于前端展示的分页组件。<p>
 * <p/>
 * 创建日期  2010-1-25 17:01:02<br>
 *
 * @author luxiangxing (luxiangxing@jsj.com.cn) <br>
 * @author $Author: luxiangxing $<br>
 * @version $Revision: 8784 $ $Date: 2010-06-27 15:53:29 +0800 $
 * @see DtssPageBean
 * @since 1.0.0
 */
public class DtssPageBean {
    public static String STYLE_COMMON = "COMMON";
    public static String STYLE_SEARCH = "SEARCH";
    public static String STYLE_SEARCH_METHOD = "SEARCH_METHOD";
    public static String STYLE_SEARCH_METHOD1 = "SEARCH_METHOD1";
    public static String STYLE_SEARCH_METHOD2 = "SEARCH_METHOD2";
    public static String STYLE_TOURISM_METHOD = "STYLE_TOURISM_METHOD";
    public static String STYLE_TUSHUOTIANJIN = "STYLE_TUSHUOTIANJIN";
    //带页码的样式
    public static String STYLE_WITH_PAGENUM = "STYLE_WITH_PAGENUM";
    //项目名
    private String project_name = "nba";

    //	定义页面跳转的路径
    private String actionUrl = "";

    private String param = "";

    private String type;
    //1.定义每页显示的条数 默认为15条
    private long pageSize = 8;
    //2.定义当前页面数
    private long currentPage = 1;
    //3.总的记录数
    private long allRecorders = 0;
    //4.总页数
    private long allPages = 0;
    //5.搜索表示
    private boolean searchFlag = false;

    //要显示页面的内容记录
    private List records;
    //分页条html
    private String pageHtml;

    private String styleType;

    private String method = "dtssJsPage";
    private String method1 = "dtssJsPage1";
    private String method2 = "dtssJsPage2";
    private Map<String, String> paramMap = new HashMap<String, String>();

    public DtssPageBean(long allRecorders, int pageSize, long currentPage) {
        this.allRecorders = allRecorders;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.styleType = STYLE_COMMON;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setMethod1(String method) {
        this.method1 = method;
    }

    public void setMethod2(String method) {
        this.method2 = method;
    }


    public void build() {
        this.allPages = (this.allRecorders + this.pageSize - 1) / pageSize; //总页数
        if (this.currentPage >= this.allPages) this.currentPage = this.allPages;
        buildParam();
        if (this.styleType.equals(STYLE_COMMON)) {
            buildCommonStyle();
        } else if (this.styleType.equals(STYLE_SEARCH)) {
            buildSearchStyle();
        } else if (this.styleType.equals(STYLE_SEARCH_METHOD)) {
            //buildSearchStyleWithJsMethod();
            buildTourismStyle2();
        } else if (this.styleType.equals(STYLE_SEARCH_METHOD1)) {
            //buildSearchStyleWithJsMethod();
            buildTourismStyle21();
        } else if (this.styleType.equals(STYLE_SEARCH_METHOD2)) {
            //buildSearchStyleWithJsMethod();
            buildTourismStyle22();
        } else if (this.styleType.equals(STYLE_TOURISM_METHOD)) {
            buildTourismStyle();
        } else if (this.styleType.equals(STYLE_TUSHUOTIANJIN)) {
            buildToushuotianjinStyle();
        } else if (this.styleType.equals(STYLE_WITH_PAGENUM)) {
            buildStyleWithPagenum();
        } else {
            buildCommonStyle();
        }

        /*if(actionUrl==null || "".equals(actionUrl)){
              this.styleType = 0;
              buildStyle0();
          }else {
              buildStyle1();
          }*/

    }

    private void buildParam() {
        if (paramMap != null) {
            Iterator iter = paramMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = "" + entry.getValue();
                param += key + "=" + val + "&";
            }

        }

    }


    private void buildTourismStyle2() {

        String baseLink = "javascript:" + this.method + "('1')";

        // String lastLink = "javascript:" + this.method + "('" + this.allPages + "')";
        // if (this.allPages <= 1) lastLink = "#";

        StringBuffer sb = new StringBuffer();
        sb.append("<em>&nbsp;共" + this.allRecorders + "条&nbsp;" + this.currentPage + "/" + this.allPages + "页&nbsp;</em>");

        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage - 1) + "')";
            String fis = "javascript:" + this.method + "('1')";
            sb.append("<a href=\"" + fis + "\" class=\"first\"  target=\"_self\" >首页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + "\" class=\"prev\" target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  class=\"first\">首页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"prev\" >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage + 1) + "')";
            String last = "javascript:" + this.method + "('" + this.allPages + "')";
            sb.append("<a href=\"" + baseLink + "\" class=\"next\" target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + last + "\" class=\"last\" target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\" class=\"next\" >下一页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"last\" >尾 页</a>&nbsp;");
        }

        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\" width:31px;_margin-bottom:-5px;line-height:normal!important;box-sizing:content-box;text-align:center;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage()\" style=\"width:50px; height:25px;_margin-bottom:-5px; border:1px #C85B00 solid; background:#FF7400; color:#fff!important; font-size:12px; line-height:14px; cursor:pointer;\"/>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("" + this.method + "(intpage ,\"_self\",\"\");");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildTourismStyle21() {

        String baseLink = "javascript:" + this.method1 + "('1')";

        // String lastLink = "javascript:" + this.method + "('" + this.allPages + "')";
        // if (this.allPages <= 1) lastLink = "#";

        StringBuffer sb = new StringBuffer();
        sb.append("<em>&nbsp;共" + this.allRecorders + "条&nbsp;" + this.currentPage + "/" + this.allPages + "页&nbsp;</em>");

        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method1 + "('" + (this.currentPage - 1) + "')";
            String fis = "javascript:" + this.method1 + "('1')";
            sb.append("<a href=\"" + fis + "\" class=\"first\"  target=\"_self\" >首页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + "\" class=\"prev\" target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  class=\"first\">首页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"prev\" >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            baseLink = "javascript:" + this.method1 + "('" + (this.currentPage + 1) + "')";
            String last = "javascript:" + this.method1 + "('" + this.allPages + "')";
            sb.append("<a href=\"" + baseLink + "\" class=\"next\" target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + last + "\" class=\"last\" target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\" class=\"next\" >下一页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"last\" >尾 页</a>&nbsp;");
        }

        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage1\" value=\"" + this.currentPage + "\" style=\" width:31px;_margin-bottom:-5px;line-height:normal!important;box-sizing:content-box;text-align:center;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage1()\" style=\"width:50px; height:25px;_margin-bottom:-5px; border:1px #C85B00 solid; background:#FF7400; color:#fff!important; font-size:12px; line-height:14px; cursor:pointer;\"/>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage1(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage1\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage1\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("" + this.method1 + "(intpage ,\"_self\",\"\");");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage1();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildTourismStyle22() {

        String baseLink = "javascript:" + this.method2 + "('1')";

        // String lastLink = "javascript:" + this.method + "('" + this.allPages + "')";
        // if (this.allPages <= 1) lastLink = "#";

        StringBuffer sb = new StringBuffer();
        sb.append("<em>&nbsp;共" + this.allRecorders + "条&nbsp;" + this.currentPage + "/" + this.allPages + "页&nbsp;</em>");

        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method2 + "('" + (this.currentPage - 1) + "')";
            String fis = "javascript:" + this.method2 + "('1')";
            sb.append("<a href=\"" + fis + "\" class=\"first\"  target=\"_self\" >首页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + "\" class=\"prev\" target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  class=\"first\">首页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"prev\" >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            baseLink = "javascript:" + this.method2 + "('" + (this.currentPage + 1) + "')";
            String last = "javascript:" + this.method2 + "('" + this.allPages + "')";
            sb.append("<a href=\"" + baseLink + "\" class=\"next\" target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + last + "\" class=\"last\" target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\" class=\"next\" >下一页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"last\" >尾 页</a>&nbsp;");
        }

        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage2\" value=\"" + this.currentPage + "\" style=\" width:31px;_margin-bottom:-5px;line-height:normal!important;box-sizing:content-box;text-align:center;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage2()\" style=\"width:50px; height:25px;_margin-bottom:-5px; border:1px #C85B00 solid; background:#FF7400; color:#fff!important; font-size:12px; line-height:14px; cursor:pointer;\"/>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage2(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage2\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage2\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("" + this.method2 + "(intpage ,\"_self\",\"\");");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage2();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildCommonStyle() {
        String baseLink = this.actionUrl;
        if (param != null && !"".equals(param)) baseLink += "?" + param + "currentPage=";
        else baseLink += "?currentPage=";

        StringBuffer sb = new StringBuffer();
        sb.append("<span>共" + this.allRecorders + "条记录 " + this.currentPage + "/" + this.allPages + "页&nbsp;");

        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage - 1) + "')";
            sb.append("<a href=\"" + baseLink + "1\"  target=\"_self\" >第一页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.currentPage - 1) + "\"  target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  >第一页</a>&nbsp;");
            sb.append("<a href=\"#\"  >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            sb.append("<a href=\"" + baseLink + (this.currentPage + 1) + "\"  target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.allPages) + "\"  target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\"  >下一页</a>&nbsp;");
            sb.append("<a href=\"#\"  >尾 页</a>&nbsp;");
        }

        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\" width:31px; text-align:center;    box-sizing: initial;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage()\" style=\"width:25px; height:17px; border:1px #C85B00 solid; background:#FF7400; color:#fff; font-size:12px; line-height:14px; cursor:pointer;\"/></span>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("\n");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
        sb.append(" if (page == 0) {\n");
        sb.append("alert(\"没有搜索到数据!\");\n");
        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("window.location.href=\"" + baseLink + "\"+intpage ;\n");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    /**
     * 图说天津
     */
    private void buildToushuotianjinStyle() {
        String baseLink = this.actionUrl;
        if (param != null && !"".equals(param)) baseLink += "?" + param + "currentPage=";
        else baseLink += "?currentPage=";

        StringBuffer sb = new StringBuffer();


        if (this.currentPage > 1) {
            //baseLink = "javascript:" + this.method + "('" + (this.currentPage - 1) + "')";
            sb.append("<a href=\"" + baseLink + "1\"  target=\"_self\" >第一页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.currentPage - 1) + "\"  target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  >首页</a>&nbsp;");
            sb.append("<a href=\"#\"  >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            sb.append("<a href=\"" + baseLink + (this.currentPage + 1) + "\"  target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.allPages) + "\"  target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\"  >下一页</a>&nbsp;");
            sb.append("<a href=\"#\"  >尾 页</a>&nbsp;");
        }
        sb.append("<span style=\"display:inline;font-style: normal;	color: #3e3e3e;	background-color: #fff;\">当前" + this.currentPage + "/" + this.allPages + "页&nbsp;&nbsp;&nbsp;&nbsp;共" + this.allRecorders + "条记录 ");
        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\" width:31px; text-align:center;ime-mode:disabled;padding: 1px;border: 1px solid #a2a2a2;\"/> 页&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"button\"  id=\"go\"name=\"\" onclick=\"javascript:gotoPage()\" style=\"width:38px;	height:19px;cursor:pointer;\"/></span>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("\n");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
        sb.append(" if (page == 0) {\n");
        sb.append("alert(\"没有搜索到数据!\");\n");
        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("window.location.href=\"" + baseLink + "\"+intpage ;\n");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();

    }


    private void buildSearchStyleWithJsMethod() {

        String baseLink = "javascript:" + this.method + "('1')";

        String lastLink = "javascript:" + this.method + "('" + this.allPages + "')";
        if (this.allPages <= 1) lastLink = "#";


        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"dtss_Flip_bread_con\">");
        sb.append("<div class=\"dtss_fbc_con_s\">");
        sb.append("<h2>共找到" + this.allRecorders + "条记录</h2>");

        sb.append("<div class=\"dtss_tiaozhuang\">");
        sb.append("<span>跳至第</span><input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\"width:26px; height:25px; border:#CCCCCC solid 1px; text-align:center; line-height:25px; color:#5e5d5d;  display:block; float:left; margin:0px 3px 0px 3px;ime-mode:disabled;\"/><span>页</span>");
//		sb.append("<b class=\"dtss_anniuGO\"	>");
        sb.append("<a href=\"#\" onclick=\"javascript:gotoPage()\"> <img src=\"/" + project_name + "/images/go_14.jpg\" width=\"33\" height=\"25\" border=\"0\" /></a>");
//		sb.append("</b>"	);
        sb.append(" </div>");
        sb.append("</div>");
        sb.append("<div class=\"dtss_flip_SE\">");


        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage - 1) + "')";
            sb.append("<a href=\"" + baseLink + "\"  target=\"_self\">");
            sb.append("<img src=\"/" + project_name + "/images/zuo23.jpg\"  border=\"0\" /></a>");
        } else {
            sb.append("<a href=\"#\">");
            sb.append("<img src=\"/" + project_name + "/images/zuo23.jpg\"  border=\"0\" /></a>");
        }


//        sb.append("<a href=\"#\">第" + this.currentPage + "页</a>");
//        sb.append("<a href=\"" + lastLink + "\" >共" + this.allPages + "页</a>");
        sb.append("第" + this.currentPage + "页 ");
        sb.append(" 共" + this.allPages + "页");

        if (this.currentPage < this.allPages) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage + 1) + "')";
            sb.append("<a href=\"" + baseLink + "\"  target=\"_self\"><img src=\"/" + project_name + "/images/you25.jpg\"  border=\"0\" class=\"dtss_f14\" /></a>");
        } else {
            sb.append("<a href=\"#\"><img src=\"/" + project_name + "/images/you25.jpg\"  border=\"0\" class=\"dtss_f14\" /></a>");
        }

        sb.append("</div>");
        sb.append("</div>");


        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("" + this.method + "(intpage ,\"_self\",\"\");");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildSearchStyle() {
//	  sb.append("<a href=\"javascript:"+this.method+"('1')\"  >第一页</a>");


        String baseLink = this.actionUrl;
        if (param != null && !"".equals(param)) baseLink += "?" + param + "currentPage=";
        else baseLink += "?currentPage=";

        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"dtss_Flip_bread_con\">");
        sb.append("<div class=\"dtss_fbc_con_s\">");
        sb.append("<h2>共找到" + this.allRecorders + "条记录</h2>");

        sb.append("<div class=\"dtss_tiaozhuang\">");
        sb.append("<span>跳至第</span><input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\"width:26px; height:25px; border:#CCCCCC solid 1px; text-align:center; line-height:25px; color:#5e5d5d;  display:block; float:left; margin:0px 3px 0px 3px;ime-mode:disabled;\"/><span>页</span>");
//		sb.append("<b class=\"dtss_anniuGO\"	>");
        sb.append("<a href=\"#\" onclick=\"javascript:gotoPage()\"> <img src=\"/" + project_name + "/images/go_14.jpg\" width=\"33\" height=\"25\" border=\"0\" /></a>");
//		sb.append("</b>"	);
        sb.append(" </div>");
        sb.append("</div>");
        sb.append("<div class=\"dtss_flip_SE\">");


        if (this.currentPage > 1) {
            sb.append("<a href=\"" + baseLink + (this.currentPage - 1) + "\"  target=\"_self\">");
            sb.append("<img src=\"/" + project_name + "/images/zuo23.jpg\"  border=\"0\" /></a>");
        } else {
            sb.append("<a href=\"#\">");
            sb.append("<img src=\"/" + project_name + "/images/zuo23.jpg\"  border=\"0\" /></a>");
        }


        sb.append("<a href=\"#\">第" + this.currentPage + "页</a>");
        sb.append("<a href=\"#\"  target=\"_self\" >共" + this.allPages + "页</a>");


        if (this.currentPage < this.allPages) {
            sb.append("<a href=\"" + baseLink + (this.currentPage + 1) + "\"  target=\"_self\"><img src=\"/" + project_name + "/images/you25.jpg\"  border=\"0\" class=\"dtss_f14\" /></a>");
        } else {
            sb.append("<a href=\"#\"><img src=\"/" + project_name + "/images/you25.jpg\"  border=\"0\" class=\"dtss_f14\" /></a>");
        }

        sb.append("</div>");
        sb.append("</div>");


        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("window.location.href=\"" + baseLink + "\"+intpage ;");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildTourismStyle() {
        String baseLink = this.actionUrl;
        if (param != null && !"".equals(param)) {
            baseLink += "?" + param + "currentPage=";
        } else {
            baseLink += "?currentPage=";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<em>&nbsp;共" + this.allRecorders + "条&nbsp;" + this.currentPage + "/" + this.allPages + "页&nbsp;</em>");

        if (this.currentPage > 1) {
            sb.append("<a href=\"" + baseLink + "1\"&type=" + type + " class=\"first\"  target=\"_self\" >首页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.currentPage - 1) + "&type=" + type + "\" class=\"prev\" target=\"_self\">上一页</a>&nbsp;");

        } else {
            sb.append("<a href=\"#\"  class=\"first\">首页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"prev\" >上一页</a>&nbsp;");
        }
        if (this.currentPage < this.allPages) {
            sb.append("<a href=\"" + baseLink + (this.currentPage + 1) + "&type=" + type + "\" class=\"next\" target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + (this.allPages) + "&type=" + type + "\" class=\"last\" target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\" class=\"next\" >下一页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"last\" >尾 页</a>&nbsp;");
        }

        sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\" width:31px; text-align:center;    box-sizing: initial;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage()\" style=\"width:50px; height:25px; border:1px #C85B00 solid; background:#FF7400; color:#fff; font-size:12px; line-height:14px; cursor:pointer;\"/>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("\n");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
        sb.append(" if (page == 0) {\n");
        sb.append("alert(\"没有搜索到数据!\");\n");
        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("window.location.href=\"" + baseLink + "\"+intpage ;\n");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    private void buildStyleWithPagenum() {

        String baseLink = "javascript:" + this.method + "('1')";

        // String lastLink = "javascript:" + this.method + "('" + this.allPages + "')";
        // if (this.allPages <= 1) lastLink = "#";

        StringBuffer sb = new StringBuffer();
        //sb.append("<em>&nbsp;共" + this.allRecorders + "条&nbsp;"  + this.currentPage + "/" + this.allPages + "页&nbsp;</em>" );

        if (this.currentPage > 1) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage - 1) + "')";
            String fis = "javascript:" + this.method + "('1')";
            sb.append("<a href=\"" + fis + "\" class=\"first\"  target=\"_self\" >首页</a>&nbsp;");
            sb.append("<a href=\"" + baseLink + "\" class=\"prev\" target=\"_self\">上一页</a>&nbsp;");

            if (this.allPages == 2) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
            } else if (this.allPages == 3) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
            } else if (this.allPages == 4) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
            } else if (this.allPages == 5) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 4) + "')" + "\" class=\"pn\" >" + (this.currentPage + 4) + "</a>&nbsp;");
            } else if (this.allPages > 5) {

                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 4) + "')" + "\" class=\"pn\" >" + (this.currentPage + 4) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 5) + "')" + "\" class=\"pn\" >" + "···" + "</a>&nbsp;");
            }


        } else {
            sb.append("<a href=\"#\"  class=\"first\">首页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"prev\" >上一页</a>&nbsp;");

            if (this.allPages == 1) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn pnc\" >" + this.currentPage + "</a>&nbsp;");
            } else if (this.allPages == 2) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn  pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
            } else if (this.allPages == 3) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn  pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
            } else if (this.allPages == 4) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn  pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
            } else if (this.allPages == 5) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn  pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 4) + "')" + "\" class=\"pn\" >" + (this.currentPage + 4) + "</a>&nbsp;");
            } else if (this.allPages > 5) {
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage) + "')" + "\" class=\"pn  pnc\" >" + this.currentPage + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 1) + "')" + "\" class=\"pn\" >" + (this.currentPage + 1) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 2) + "')" + "\" class=\"pn\" >" + (this.currentPage + 2) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 3) + "')" + "\" class=\"pn\" >" + (this.currentPage + 3) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 4) + "')" + "\" class=\"pn\" >" + (this.currentPage + 4) + "</a>&nbsp;");
                sb.append("<a href=\"" + "javascript:" + this.method + "('" + (this.currentPage + 5) + "')" + "\" class=\"pn\" >" + "···" + "</a>&nbsp;");
            }

        }
        if (this.currentPage < this.allPages) {
            baseLink = "javascript:" + this.method + "('" + (this.currentPage + 1) + "')";
            String last = "javascript:" + this.method + "('" + this.allPages + "')";
            sb.append("<a href=\"" + baseLink + "\" class=\"next\" target=\"_self\" >下一页</a>&nbsp;");
            sb.append("<a href=\"" + last + "\" class=\"last\" target=\"_self\">尾  页</a>&nbsp;");
        } else {
            sb.append("<a href=\"#\" class=\"next\" >下一页</a>&nbsp;");
            sb.append("<a href=\"#\" class=\"last\" >尾 页</a>&nbsp;");
        }

        //sb.append(" 第 <input type=\"text\" name=\"toPage\" id=\"toPage\" value=\"" + this.currentPage + "\" style=\" width:31px;_margin-bottom:-5px; text-align:center;ime-mode:disabled;\"/> 页 <input type=\"button\" value=\"GO\" name=\"\" onclick=\"javascript:gotoPage()\" style=\"width:50px; height:25px;_margin-bottom:-5px; border:1px #C85B00 solid; background:#FF7400; color:#fff; font-size:12px; line-height:14px; cursor:pointer;\"/>");

        //生成js部分
        sb.append("");
        sb.append("<script>\n");
        sb.append("");
        sb.append("function gotoPage(){\n");
        sb.append("var re = /\\D/g;\n");
        sb.append("var page = document.getElementById(\"toPage\").value;\n");
//        sb.append(" if (page == 0) {\n");
//        sb.append("alert(\"没有搜索到数据!\");\n");
//        sb.append("return;}\n");
        sb.append("var r = page.match(re);\n");
        sb.append("\n");
        sb.append(" if (r != null) {\n");
        sb.append("alert(\"页码必须为数字!\");\n");
        sb.append(" document.getElementById(\"toPage\").focus();\n");
        sb.append("return;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("if(page == null || page == \"\" ){\n");
        sb.append(" page = " + this.currentPage + ";\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("var intpage = parseInt(page);\n");
        sb.append("if (intpage < 1) {\n");
        sb.append(" intpage = 1;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append(" if (intpage > " + this.allPages + ") {\n");
        sb.append(" intpage =  " + this.allPages + " ;\n");
        sb.append("}\n");
        sb.append("\n");
        sb.append("" + this.method + "(intpage ,\"_self\",\"\");");
        sb.append("}\n");
        sb.append("function checkEnter() {\n");
        sb.append("if (window.event.keyCode == 13) {\n"); //回车
        sb.append("gotoPage();\n");
        sb.append("}\n");
        sb.append("}\n");
        sb.append("</script>");
        this.pageHtml = sb.toString();
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getAllRecorders() {
        return allRecorders;
    }

    public void setAllRecorders(long allRecorders) {
        this.allRecorders = allRecorders;
    }

    public long getAllPages() {
        return allPages;
    }

    public void setAllPages(long allPages) {
        this.allPages = allPages;
    }


    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }


    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }


    public void setAllRecorders(int allRecorders) {
        this.allRecorders = allRecorders;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }

    public boolean isSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(boolean searchFlag) {
        this.searchFlag = searchFlag;
    }


    public String getMethod() {
        return method;
    }

    public String getMethod1() {
        return method1;
    }

    public String getMethod2() {
        return method2;
    }

    public String getParam() {
        return param;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParam(String key, String val) {
        if (key != null && !"".equals(key) && val != null && !"".equals(val)) {
            paramMap.put(key, val);
        }
    }

    public void setParams(Object condition, String conditionName) {
        Field[] fields = condition.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String key = conditionName == null ? field.getName() : conditionName + "." + field.getName();
                if (field.get(condition) != null) {
                    paramMap.put(key, "" + field.get(condition));
                }
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            }
        }
    }
}


//		int allRecorders = 100;	//查询结果总数 
//		int pageSize     = 10; 		//每页显示数
//		
//		/**模拟获取当页数据过程**/
//		PageTestDates dates = new PageTestDates();
//		dates.setKeyword(keyword);
//		studentInfos = dates.getStudents(currentPage, pageSize);
//		
//		/**模拟输入分页数据**/
//		String actionUrl = "/page/studentInfo.action";
//		DtssPageBean pageBean = new DtssPageBean( allRecorders, pageSize, currentPage);
//		pageBean.setActionUrl(actionUrl);
//		pageBean.setParam("keyword", keyword); //传递搜索参数 无参数
//		pageBean.build();
//		
//		/**往界面传递数据**/
//		ActionContext ctx = ActionContext.getContext();        
//	    HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);        
//	    request.setAttribute("studentInfos", studentInfos);
//	    request.setAttribute("pageBean", pageBean);


/*
//搜索条件部分 可以传递多搜索条件 参数自行控制
<form action="/page/studentInfo.action" method="post">
   输入查询关键字：<input type="text" name="keyword" id="keyword" value="${keyword}" >
   <input type="submit" value="查询">
</form>
//列表部分
<c:forEach  items="${studentInfos}" var="studentInfo" varStatus="num">

   <tr class="odd">
   <td>${studentInfo.name}</td>
   <td>${studentInfo.age}</td>
   <td>${studentInfo.mark}</td></tr>

</c:forEach>
//分页条
<div id="page">${pageBean.pageHtml}</div>
*/
