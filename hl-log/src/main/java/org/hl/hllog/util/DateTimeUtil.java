package org.hl.hllog.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;


public class DateTimeUtil {
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd HH:mm
     **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式：yyyy-MM-dd HH:mm
     **/
    public static final String DF_MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd
     **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 日期格式：yyyy-MM
     **/
    public static final String DF_YYYY_MM = "yyyy-MM";
    /**
     * 日期格式：MM-dd
     **/
    public static final String DF_MM_DD = "MM-dd";
    /**
     * 日期格式：yyyy-MM-dd
     **/
    public static final String DF_YYYY_MM_DD_DOT = "yyyy.MM.dd";
    /**
     * 日期格式：HH:mm:ss
     **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";
    /**
     * 日期格式：HH:mm
     **/
    public static final String DF_HH_MM = "HH:mm";
    /**
     * 日期格式：MM月dd日
     **/
    public static final String DF_MM_YUE_DD = "MM月dd日";
    /**
     * 日期格式：MM月dd HH:mm
     **/
    public static final String DF_MM_DD_HH_MM = "MM月dd日 HH:mm";
    /**
     * 日期格式：yyyy年MM月dd日
     **/
    public static final String DF_YYYYMMDD = "yyyy年MM月dd日";
    /**
     * 日期格式：yyyy年
     **/
    public static final String DF_YYYY_N = "yyyy年";
    /**
     * 日期格式：yyyy年
     **/
    public static final String DF_YYYY = "yyyy";
    /**
     * 日期格式：dd
     **/
    public static final String DF_DD = "dd";
    /**
     * 日期格式：MM月dd日
     **/
    public static final String DF_MM = "MM月";
    /**
     * 日期格式：yyyy年MM月dd日
     **/
    public static final String DF_MMDDHH = "MM月dd日 HH点";

    public final static long second = 1000;// 1秒
    public final static long minute = 60 * 1000;// 1分钟
    public final static long hour = 60 * minute;// 1小时
    public final static long day = 24 * hour;// 1天
    public final static long month = 30 * day;// 1月
    public final static long year = 12 * month;// 1年

    /**
     * Log输出标识
     **/
    private static final String TAG = "DateTimeUtil";

    /**
     * 获取当前时间,单位为秒
     */
    public static long getCurrentDateTimeSeconds() {
        return getCurrentDate().getTime() / 1000;
    }

    /**
     * 获取当前时间,单位为毫秒
     */
    public static long getCurrentDateTimeMills() {
        return getCurrentDate().getTime();
    }

    /**
     * 获取今年年份
     */
    public static String getYearThis() {
        return formatDateTime(getCurrentDate(), DF_YYYY);
    }


    /**
     * 获取间隔时间
     *
     * @param currentDate 参考时间(当前时间)
     * @param otherDate   比较时间 (>参考时间)
     */
    @SuppressWarnings("ConstantConditions")
    public static String getIntervalTime(Date currentDate, Date otherDate) {
        String intervalTime = "";
        if (currentDate == null || otherDate == null) {
            return intervalTime;
        }
        long diff = otherDate.getTime() - currentDate.getTime();
        if (diff > year) {
            intervalTime += (diff / year) + "年";
            diff = diff % year;
        }
        if (diff > month) {
            intervalTime += (diff / month) + "个月";
            diff = diff % month;
        }
        if (diff > day) {
            intervalTime += (diff / day) + "天";
            diff = diff % day;
        }
        if (diff > hour) {
            intervalTime += (diff / hour) + "小时";
            diff = diff % hour;
        }
        if (diff > minute) {
            intervalTime += (diff / minute) + "分钟";
        }
        return intervalTime;
    }

    /**
     * 显示 x天x小时x分钟x秒
     */
    public static String formatTimeFriendly2Sec(long time) {
        time = time - new Date().getTime();
        if (time <= 1000L) {
            return "00:00:00";
        }
        String result = "";
        if (time > day) {
            result += (time / day) + "天";
            time = time % day;
        }
        if (time > hour) {
            result += (time / hour) + "小时";
            time = time % hour;
        }
        if (time > minute) {
            result += (time / minute) + "分钟";
            time = time % minute;
        }
        if (time > second) {
            result += (time / second) + "秒";
        }
        return result;
    }

    /**
     * 显示 x天x小时x分钟
     */
    public static String formatTimeFriendly2Minute(long time) {
        long diffTime = toJavaTS(time) - new Date().getTime();
        if (diffTime <= 1000L) {
            return "00:00:00";
        }
        String result = "";
        if (diffTime > day) {
            result += (diffTime / day) + "天";
            diffTime = diffTime % day;
        }
        if (diffTime > hour) {
            result += (diffTime / hour) + "小时";
            diffTime = diffTime % hour;
        }
        if (diffTime > minute) {
            result += (diffTime / minute) + "分钟";
        }
        return result;
    }

    /**
     * 显示 x天x小时x分钟
     */
    public static String formatTimeFriendly2Minute2(long diffTime) {
        diffTime = diffTime * 1000;
        String result = "";
        if (diffTime > day) {
            result += (diffTime / day) + "天";
            diffTime = diffTime % day;
        }
        if (diffTime > hour) {
            result += (diffTime / hour) + "小时";
            diffTime = diffTime % hour;
        }
        if (diffTime > minute) {
            result += (diffTime / minute) + "分钟";
        }
        return result;
    }

    /**
     * 显示多少天 x
     */
    public static long formatTimeFriendly2Day(long time) {
        long newTime = time - new Date().getTime();
        return newTime / day;
    }

    /**
     * 显示 x天x小时x分钟
     */
    public static String formatTimeFriendly2Min(long time) {
        long newTime = time - new Date().getTime();
        if (newTime < 0) {
            return "已过期";
        }
        String result = "";
        if (newTime > day) {
            result += (newTime / day) + "天";
            newTime = newTime % day;
        }
        if (newTime > hour) {
            result += (newTime / hour) + "小时";
            newTime = newTime % hour;
        }
        if (newTime > minute) {
            result += (newTime / minute) + "分钟";
        }
        if (TextUtils.isEmpty(result)) {
            result = "不足1分钟";
        }
        return result;
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     */
    public static String formatFriendly(Date date) {
        return formatFriendly(new Date().getTime(), date.getTime());
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     */
    public static String formatFriendly(long timeNow, long timeStart) {
        long diff = toJavaTS(timeNow) - toJavaTS(timeStart);
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 友好显示下拉刷新控件记忆时间
     */
    public static String formatFriendlyForRefresh(long time) {
        if (isToday(time)) {
            return "今天 " + formatDateTime(time, DF_HH_MM);
        }
        if ((isThisYear(time))) {
            return "昨天 " + formatDateTime(time, DF_HH_MM);
        }
        return formatDateTime(time, DF_MM_DD_HH_MM);
    }

    public static String formatFriendlyForRefresh(String date) {
        try {
            long time = parseDate(date, DF_YYYY_MM_DD_HH_MM).getTime();
            if (isToday(time)) {
                return "今天 " + formatDateTime(time, DF_HH_MM);
            }
            if ((isYeasterday(time))) {
                return "昨天 " + formatDateTime(time, DF_HH_MM);
            }
            return formatDateTime(time, DF_YYYY_MM_DD_HH_MM);
        } catch (Exception e) {
            return date;
        }
    }

    /**
     * 友好显示下记忆时间
     */
    public static String formatFriendlyMsgTime(long time) {
        time = toJavaTS(time);
        if (isToday(time)) {
            return formatDateTime(time, DF_HH_MM);
        }
        return formatDateTime(time, DF_MM_DD_HH_MM);
    }

    /**
     * 判断是否为今年
     */
    public static boolean isThisYear(long time) {
        Date newDate = new Date(toJavaTS(time));
        String dateTime = formatDateTime(newDate, DF_YYYY_N);
        String nowTime = formatDateTime(new Date(), DF_YYYY_N);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 判断是否为本月
     */
    public static boolean isThisMonth(Date newDate) {
        String dateTime = formatDateTime(newDate, DF_YYYY_MM);
        String nowTime = formatDateTime(new Date(), DF_YYYY_MM);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 判断指定时间是否为当天
     */
    public static boolean isToday(long time) {
        Date newDate = new Date(toJavaTS(time));
        String dateTime = formatDateTime(newDate, DF_YYYY_MM_DD);
        String nowTime = formatDateTime(new Date(), DF_YYYY_MM_DD);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 判断指定时间是否为昨天
     */
    public static boolean isYeasterday(long time) {
        Date newDate = new Date(toJavaTS(time));
        newDate.setDate(newDate.getDate() + 1);
        String dateTime = formatDateTime(newDate, DF_YYYY_MM_DD);
        String nowTime = formatDateTime(new Date(), DF_YYYY_MM_DD);
        return !(TextUtils.isEmpty(dateTime) || TextUtils.isEmpty(nowTime)) && dateTime.equalsIgnoreCase(nowTime);
    }

    /**
     * 是否为最近X天,从昨天开始算起,不算今天
     *
     * @param time 时间戳
     * @param x    最近多少天
     */
    private static boolean isNearlyXDay(long time, int x) {
        time = toJavaTS(time);
        return time < getPastDate(1).getTime() && time >= getPastDate(x).getTime();
    }

    /**
     * 获取过去第几天的日期,0点时间，如：2017-12-03 00:00:00
     */
    public static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return parseDate(formatDateTime(calendar.getTime(), DF_YYYY_MM_DD), DF_YYYY_MM_DD);
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     */
    public static String formatDateTime(long dateL) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
            Date date = new Date(toJavaTS(dateL));
            return sdf.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     */
    public static String formatDateTime(long dateL, String formater) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            return sdf.format(new Date(toJavaTS(dateL)));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     */
    public static String formatDateTime(Date date, String formater) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formater);
            return sdf.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 将日期字符串转成日期
     *
     * @param strDate 字符串日期
     * @return java.util.date日期类型
     */
    public static Date parseDate(String strDate, String formater) {
        Date returnDate = null;
        try {
            returnDate = new SimpleDateFormat(formater).parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    public static String parseDate2(String strDate, String formater) {
        String returnDate = null;
        try {
            returnDate = formatDateTime(new SimpleDateFormat(formater).parse(strDate), formater);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    public static String parseDate3(String strDate) {
        String returnDate = null;
        try {
            returnDate = formatDateTime(new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS).parse(strDate), DF_HH_MM_SS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;
    }


    /**
     * 获取系统当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 验证日期是否比当前日期早
     *
     * @param target1 比较时间1
     * @param target2 比较时间2
     * @return true 则代表target1比target2晚或等于target2，否则比target2早
     */
    public static boolean compareDate(Date target1, Date target2) {
        boolean flag = false;
        try {
            String target1DateTime = DateTimeUtil.formatDateTime(target1, DF_YYYY_MM_DD_HH_MM_SS);
            String target2DateTime = DateTimeUtil.formatDateTime(target2, DF_YYYY_MM_DD_HH_MM_SS);
            if (target1DateTime.compareTo(target2DateTime) <= 0) {
                flag = true;
            }
        } catch (Exception e1) {

        }
        return flag;
    }


    /**
     * 对日期进行增加操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     */
    public static Date addDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }
        return new Date(target.getTime() + (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 对日期进行相减操作
     *
     * @param target 需要进行运算的日期
     * @param hour   小时
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }
        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 获取日期字符串对应的星期
     *
     * @param strDate 日期字符串
     * @return 1-7：对应周日-周六，国外周日为开始，周六为结束
     */
    public static int getDayOfWeekFromStrDate(String strDate, String formater) {
        Date date = parseDate(strDate, formater);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static String getStrDateToStrDate(String strDate, String formater) {
        Date date = parseDate(strDate, formater);
        return formatDateTime(date, formater);
    }

    /**
     * 转换成java时间戳
     */
    public static long toJavaTS(long time) {
        if (String.valueOf(time).length() < 13) {
            return time * 1000;
        }
        return time;
    }

    /**
     * 根据时间戳获取是什么时间范围内<p>
     * 今天：日期和当天相同<p>
     * 最近7天：从今天开始往前的7天之内<p>
     * 最近30天：从今天开始往前的30天之内<p>
     * 更早：除前三种情况<p>
     *
     * @param time 时间戳
     * @return 当前时间范围状态码, 0:今天; 1:最近7天; 2:最近30天; 3:更早;
     */
    public static int roadshowWatchTimeState(long time) {
        time = toJavaTS(time);
        if (isToday(time)) return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        if (time >= calendar.getTimeInMillis()) return 1;
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 30);
        if (time >= calendar.getTimeInMillis()) return 2;
        return 3;
    }

    public static String directListDate(long time) {
        return DateTimeUtil.formatDateTime(time, DF_YYYYMMDD);
    }

    //根据秒数转化为时分秒   00:00:00
    public static String formatTimeFriendly2Sec(int totalSeconds) {
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("00:%02d:%02d", minutes, seconds).toString();
        }
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        return paramWeek == currentWeek;
    }


    // 获取本周开始时间
    public static String getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取本周结束时间
    public static String getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateTimeUtil.parseDate(getBeginDayOfWeek(), DF_YYYY_MM_DD_HH_MM_SS));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    //获取某个日期的开始时间
    public static String getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return formatDateTime(calendar.getTimeInMillis(), DF_YYYY_MM_DD_HH_MM_SS);
    }

    //获取某个日期的结束时间
    public static String getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return formatDateTime(calendar.getTimeInMillis(), DF_YYYY_MM_DD_HH_MM_SS);
    }


    public static String getDaysBetwwen(int days) { //最近几天日期

        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DF_YYYY_MM_DD);
        calendar1.add(Calendar.DATE, -days);
        String three_days_ago = sdf1.format(calendar1.getTime());
        return three_days_ago;
    }

    private static Date getDateAdd(int days) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    /**
     * 计算两个日期相隔多少天
     */
    public static int betweenDays(Calendar beginDate, Calendar endDate) {
        if (beginDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)) {
            return endDate.get(Calendar.DAY_OF_YEAR)
                    - beginDate.get(Calendar.DAY_OF_YEAR);
        } else {
            if (beginDate.getTimeInMillis() < endDate.getTimeInMillis()) {
                int days = beginDate.getActualMaximum(Calendar.DAY_OF_YEAR)
                        - beginDate.get(Calendar.DAY_OF_YEAR)
                        + endDate.get(Calendar.DAY_OF_YEAR);
                for (int i = beginDate.get(Calendar.YEAR) + 1; i < endDate
                        .get(Calendar.YEAR); i++) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, i);
                    days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
                }
                return days;
            } else {
                int days = endDate.getActualMaximum(Calendar.DAY_OF_YEAR)
                        - endDate.get(Calendar.DAY_OF_YEAR)
                        + beginDate.get(Calendar.DAY_OF_YEAR);
                for (int i = endDate.get(Calendar.YEAR) + 1; i < beginDate
                        .get(Calendar.YEAR); i++) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, i);
                    days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
                }
                return days;
            }
        }
    }
}
