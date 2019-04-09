package com.kakaopay.platform.api_server.util;


import com.kakaopay.platform.api_server.model.type.MoneyType;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class StringUtil {


    /**
     * 문자열 공백 제거 후 빈값 체크
     *
     * @param param
     * @return
     */
    public static String removeSpace(String param) {

        if (StringUtils.isEmpty(param)) {
            return null;
        }
        Pattern pattern = Pattern.compile("\\p{Z}");

        param = param.replaceAll(pattern.toString(), "");

        if (StringUtils.isEmpty(param)) {
            return null;
        }
        return param;
    }

    /**
     * 이차보전 최소, 최대값 분리
     * Input : "2.0%~3.5%" / "3.0%"
     * Output : {rateMin: 2.5, rateMax: 3.5} / {rateMin: 3.0, rateMax: 3.0}
     *
     * @param rate
     * @return
     */
    public static Map<String, BigDecimal> convertStringRateToBigDecimal(String rate) {
        Map<String, BigDecimal> rateMap = new HashMap<>();

        try {
            rate = StringUtil.removeSpace(rate);

            String[] rateSplit = rate.split("%");

            // 이차보전 최소
            BigDecimal rateMin = new BigDecimal(rateSplit[0]);
            rateMap.put("rateMin", rateMin);

            // 이차보전 최소, 최대 동일한 경우
            if (!rate.contains("~")) {
                rateMap.put("rateMax", rateMap.get("rateMin"));
                return rateMap;
            }
            // 이차보전 최대
            BigDecimal rateMax = new BigDecimal(rateSplit[1].replaceAll("~", ""));
            rateMap.put("rateMax", rateMax);

            return rateMap;

        } catch (NumberFormatException e) {
            rateMap.put("rateMin", null);
            rateMap.put("rateMax", null);

            return rateMap;
        }
    }

    /**
     * 이차보전 문자 형식으로 포맷
     * Input : (2.0, 3.5)
     * Output : "2.0%~3.5%"
     *
     * @param rateMin
     * @param rateMax
     * @return
     */
    public static String convertRateToString(BigDecimal rateMin, BigDecimal rateMax) {

        if (rateMin == null || rateMax == null) {
            return "대출이자 전액";
        }
        String rate = "";
        System.out.println(rateMin.compareTo(rateMax));
        if (rateMin.compareTo(rateMax) == 0) {
            rate = rateMin + "%";
            return rate;
        }
        rate = rateMin + "%~" + rateMax + "%";

        return rate;
    }

    /**
     * sha512 인코딩
     *
     * @param str
     * @return
     */
    public static String encodingSHA512(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(str.getBytes("utf8"));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

}
