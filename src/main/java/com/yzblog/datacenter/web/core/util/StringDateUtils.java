package com.yzblog.datacenter.web.core.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串、日期处理工具类
 */
public class StringDateUtils {

    /**
     * 年月日转日期
     *
     * @return Date 当前日期和时间，例：2018年1月1号字符串转2018-01-01日期
     */
    public static String ymdToDate(String ymd)
    {

        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        ymd = ymd.replace("年","-").replace("月","-").replace("日","");
        /*SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd"); //加上时间
        Date date= null;
        try {
            date = sDateFormat.parse(ymd);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return ymd;
    }

    /**
     * 获得当前日期和时间
     *
     * @return String 当前日期和时间，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDateTime()
    {
        SimpleDateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return nowDate.format(new Date());

    }

    /**
     * 获得当前日期和时间
     *@param format
     * @return String 当前日期和时间
     */
    public static String getCurDateTime(String format)
    {
        SimpleDateFormat nowDate = new SimpleDateFormat(format);
        return nowDate.format(new Date());

    }

    //参数为null，则返回空字符串
    public static String nullToString(Object obj)
    {
        if(null==obj){
            return "";
        }else{
            return obj.toString();
        }

    }

    //参数为null，则返回0
    public static String nullMathToString(Object obj)
    {
        if(null==obj){
            return "0";
        }else{
            return obj.toString();
        }

    }

    /**
     * 计算2个日期之间相差多少分钟
     * @param fromStr
     * @param toStr
     * @return int
     */
    public static long dayCompareMinuteDate(Date fromStr,Date toStr){
        long between=(toStr.getTime()-fromStr.getTime())/1000;//除以1000是为了转换成秒
        return between/60;
    }

    /**
     * 计算2个日期之间相差多少分钟
     * @param fromStr
     * @param toStr
     * @return int
     */
    public static long dayCompareMinuteString(String fromStr,String toStr,String format){
        SimpleDateFormat dfs = new SimpleDateFormat(format);
        long min = 0;
        try {
            Date begin=dfs.parse(fromStr);
            Date end = dfs.parse(toStr);
            long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
            min=between/60;
        } catch (ParseException e) {

        }
        return min;
    }


    /**
     * 计算2个日期之间的差值 与传入的年分比较  小于返回true 大于返回false
     * @param fromStr
     * @param toStr
     * @return
     */
    public static boolean dayComparePrecise(String fromStr,String toStr,String year){
        if(StringUtils.isBlank(fromStr)){
            return false;
        }
        String[] arr = fromStr.split("-");
        String format =null;
        if(arr.length==2){
            format =  "yyyy-MM";
        }else if(arr.length==3){
            format =  "yyyy-MM-dd";
        }else{
            return  false;
        }
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        //将字符串形式的时间转化为Date类型的时间
        Date fromDate= null;
        Date toDate = null;
        try {
            fromDate =sdf.parse(fromStr);
            toDate = sdf.parse(toStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
//        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.YEAR, Integer.parseInt(year));
        fromDate = calendar.getTime();

        if(fromDate.getTime()-toDate.getTime()>0)
            return true;
        else
            return false;
    }


    /**
     * 两个字符串日期相隔天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(String date1,String date2)
    {
        SimpleDateFormat sdf1 = new SimpleDateFormat(judgeDate(date1));
        SimpleDateFormat sdf2 = new SimpleDateFormat(judgeDate(date2));
        Date fromDate= null;
        Date toDate = null;
        try {
            fromDate =sdf1.parse(date1);
            toDate = sdf2.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result;
        if(fromDate.getTime()>toDate.getTime()){
            result = fromDate.getTime()-toDate.getTime();
        }else{
            result = toDate.getTime()-fromDate.getTime();
        }
        int days = (int) ((result) / (1000*3600*24));
        return days;
    }

    /**
     * 两个日期相隔天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        long result;
        if(date1.getTime()>date2.getTime()){
            result = date1.getTime()-date2.getTime();
        }else{
            result = date2.getTime()-date1.getTime();
        }
        int days = (int) ((result) / (1000*3600*24));
        return days;
    }

    /**
     * 判断两个日期相差多少月
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(String date1,String date2)
    {
        SimpleDateFormat sdf1 = new SimpleDateFormat(judgeDate(date1));
        SimpleDateFormat sdf2 = new SimpleDateFormat(judgeDate(date2));
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf1.parse(date1));
            aft.setTime(sdf2.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 比较两个字符串日期的大小
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDateVal(String date1,String date2)
    {
        SimpleDateFormat sdf1=new SimpleDateFormat(judgeDate(date1));
        SimpleDateFormat sdf2=new SimpleDateFormat(judgeDate(date2));
        Date fromDate= null;
        Date toDate = null;
        try {
            fromDate =sdf1.parse(date1);
            toDate = sdf2.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result;
        if(fromDate.getTime()>=toDate.getTime()){
            return true;
        }
        return false;
    }

    /**
     * 字符串转日期
     * @param date 日期字符串
     * @return
     */
    public static Date stringToData(String date)
    {
        String[] arr = date.split("-");
        String format =null;
        if(arr.length==2){
            format =  "yyyy-MM";
        }else if(arr.length==3){
            format =  "yyyy-MM-dd";
        }else{
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date fromDate= null;
        try {
            fromDate = sdf.parse(date);
        } catch (ParseException e) {

        }
        return fromDate;
    }

    /**
     * 日期转指定格式字符串
     * @param date 日期
     * @param format 日期格式
     * @return String
     */
    public static String dateToString(Date date,String format)
    {
        SimpleDateFormat f=new SimpleDateFormat(format);
        return  f.format(date);
    }

    /**
     * 比较两个日期的大小date1<date2 return true;
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDateVal(Date date1,Date date2)
    {
        if (date1.before(date2)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 几年后
     * @param date 日期
     * @param year 年数
     * @return
     */
    public static Date yearLater(Date date,int year)
    {
        Calendar canlandar = Calendar.getInstance();
        canlandar.setTime(date);
        canlandar.add(canlandar.YEAR,year);
        return canlandar.getTime();
    }

    /**
    * 判断日期是否符合yyyy-MM-dd格式
    * @return
    */
    public static boolean isFormat(String dateStr) {
        boolean b = false;

        String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(dateStr);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            System.out.println("格式错误");
            b = false;
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(dateStr);
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }


    /**
     *  参数日期与当前日期比较属于几年内
     * @param date 日期
     * @return
     */
    public static int yearContain(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int k = 0;
        for (int i=0; i<6; i++){
            cal.add(Calendar.YEAR, +1);
            Date year = cal.getTime();
            k = i;
            if (new Date().compareTo(year)<0){
                break;
            }
        }
        return k;
    }

    //判断字符串日期的格式
    public static String judgeDate(String date)
    {
        String[] arr = date.split("-");
        String format =null;
        if(arr.length==2){
            format =  "yyyy-MM";
        }else if(arr.length==3){
            format =  "yyyy-MM-dd";
        }
        return format;
    }

    //比较三个数的大小
    public static String compareTree(String one,String two,String three)
    {
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {

                        // 降序排序
                        return Integer.parseInt(obj1)>=Integer.parseInt(obj2)? 1:-1;
                    }
                });
        map.put(one,"a");
        map.put(two, "b");
        map.put(three, "c");
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        String next="";
        while (iter.hasNext()) {
            next = iter.next();
        }
        if(next.equals(one)){
            return "a";
        }else if(next.equals(two)){
            return "b";
        }
        return "c";
    }

    //比较四个数的大小
    public static String compareFour(String one,String two,String three,String four)
    {
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {

                        // 降序排序
                        return Integer.parseInt(obj1)>=Integer.parseInt(obj2)? 1:-1;
                    }
                });
        map.put(one,"a");
        map.put(two, "b");
        map.put(three, "c");
        map.put(four, "d");
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        String next="";
        while (iter.hasNext()) {
            next = iter.next();
        }
        if(next.equals(one)){
            return "a";
        }else if(next.equals(two)){
            return "b";
        }else if(next.equals(three)){
            return "c";
        }
        return "d";
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }
    /**
     * 当前日期的下个月一号
     */
    public static Date nextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

}
