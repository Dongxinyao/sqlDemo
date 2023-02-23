package com.example.sqldemo.utils;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 李超超
 * @ClassName StringUtil
 * @Description TODO
 * @date 2017年12月1日
 */
public class StringUtil {

    /**
     * @return
     * @Method: trimAll
     * @Description: TODO去掉空格 换行
     */
    public static String trimAll(String content) {
        return content.trim().replaceAll("\r\n", "").replaceAll("\n", "");
    }

    /**
     * @param o
     * @return
     * @Method: isEmpty
     * @Description: TODO 对象是否为空
     */
    public static Boolean isEmpty(Object o) {
        String str = String.valueOf(o);

        return StringUtils.isEmpty(str) || "null".equals(str);

    }

    public static boolean equalsAll(List<String> strings, String s) {
        if (strings == null || strings.size() == 0 || isEmpty(s))
            return false;

        boolean res = strings.get(0).equals(s);
        if (res) return true;

        for (int i = 1; i < strings.size(); i++)
            res = res || strings.get(i).equals(s);
        return res;
    }

    /**
     * 判断字符串不为空
     *
     * @param
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        boolean isNotEmpty = false;
        String str = "";
        if (object != null) {
            str = object.toString();
        }
        if (!str.trim().equals("") && !str.trim().equalsIgnoreCase("NULL")) {
            isNotEmpty = true;
        }
        return isNotEmpty;
    }

    /**
     * @param o
     * @return
     * @Method: validateStr
     * @Description: TODO 空对象返回空串反之直接返回
     */
    public static String validateStr(Object o) {
        if (isEmpty(o)) {
            return "";
        } else {
            return String.valueOf(o);
        }
    }

    /**
     * @param o
     * @param str
     * @return
     * @Method: validateStr
     * @Description: TODO 空对象返回空串反之直接返回
     */
    public static String validateStr(Object o, String str) {
        if (isEmpty(o)) {
            if (isEmpty(str)) {
                return "";
            } else {
                return str;
            }
        } else {
            return String.valueOf(o);
        }
    }

    /**
     * 税号分组
     *
     * @param nsrsbh
     * @return
     */
    public static String shfz(String nsrsbh) {
        nsrsbh = nsrsbh.replaceAll("[a-zA-Z]", "");
        long a = 0;
        if (!nsrsbh.equals("")) {
            a = Long.valueOf(nsrsbh);
        }
        int b = (int) (a % 10);
        return String.valueOf(b);
    }

    /**
     * 获取表名
     *
     * @param ca_ip
     * @param nsrsbh
     * @return
     */
    public static String getTableName(String ca_ip, String nsrsbh) {
        String tableName = "tb_fpzb";
        String ip = ca_ip.replaceAll("\\.", "_");
        String shfz = shfz(nsrsbh);
        return tableName + "_" + ip + "_" + shfz;
    }

    /***
     *@Author zhoushaozhi
     *@Description 获取对象
     *@Date 2021/6/29 15:39
     *@param o
     *@Return String
     */
    public static String getNullObject(Object o) {
        return o == null ? "" : o.toString().trim();
    }

    public static String parseString(String[] param) {
        StringBuffer s = new StringBuffer();
        if (param == null) return s.toString();
        for (String p : param) {
            s.append(p);
            s.append(",");
        }
        return s.toString();
    }

    public static String parseString(Object[] param) {
        StringBuffer s = new StringBuffer();
        if (param == null) return s.toString();
        for (Object p : param) {
            s.append(p);
            s.append(",");
        }
        return s.toString();
    }

    public static boolean oneMoreIsEmpty(String[] strings) {
        boolean res = false;
        for (String s : strings)
            res = res || StringUtils.isEmpty(s);
        return res;
    }
}
