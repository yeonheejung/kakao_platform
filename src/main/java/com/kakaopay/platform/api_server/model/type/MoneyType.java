package com.kakaopay.platform.api_server.model.type;

import java.util.Objects;
import java.util.function.Function;

public enum MoneyType {

    CASE_MONEY_A(value -> (value * (int) Math.pow(10, 5))),
    CASE_MONEY_B(value -> (value * (int) Math.pow(10, 6))),
    CASE_MONEY_C(value -> (value * (int) Math.pow(10, 7))),
    CASE_MONEY_D(value -> (value * (int) Math.pow(10, 8))),
    CASE_MONEY_E(value -> (value * (int) Math.pow(10, 9))),
    CASE_MONEY_F(value -> (value * (int) Math.pow(10, 10))),
    CASE_MONEY_G(value -> (value * (int) Math.pow(10, 11)));

    private Function<Long, Long> expression;

    MoneyType(Function<Long, Long> expression) {
        this.expression = expression;
    }

    public Long convertMoneyToLong(Long value) {

        return expression.apply(value);
    }

    public static Long findMoneyIntType(String money) {

        MoneyType moneyType = null;

        
        String splitString = null;
        if (money.contains("십만원")) {
            splitString = "십만원";
            moneyType = MoneyType.CASE_MONEY_A;
        }
        if (money.contains("백만원")) {
            splitString = "백만원";
            moneyType = MoneyType.CASE_MONEY_B;
        }
        if (money.contains("천만원")) {
            splitString = "천만원";
            moneyType = MoneyType.CASE_MONEY_C;
        }
        if (money.contains("억원")) {
            splitString = "억원";
            moneyType = MoneyType.CASE_MONEY_D;
        }
        if (money.contains("십억원")) {
            splitString = "십억원";
            moneyType = MoneyType.CASE_MONEY_E;
        }
        if (money.contains("백억원")) {
            splitString = "백억원";
            moneyType = MoneyType.CASE_MONEY_F;
        }
        if (money.contains("천억원")) {
            splitString = "천억원";
            moneyType = MoneyType.CASE_MONEY_G;
        }
        if (Objects.isNull(moneyType)) {
            return null;
        }
        Long value = Long.valueOf(money.split(splitString)[0]);

        return moneyType.convertMoneyToLong(value);
    }

    public static String findMoneyStringType(Long money) {

        String stringMoney = null;
        if (money == null) {
            stringMoney = "추천금액 이내";
            return stringMoney;
        }
        String moneyToString = money.toString();
        
        Long num = Long.valueOf(moneyToString.split("0")[0]);

        int zeroCount = moneyToString.substring(moneyToString.indexOf("0")).length();

        if (zeroCount == 5) {
            stringMoney = num + "십만원 이내";
        } else if (zeroCount == 6) {
            stringMoney = num + "백만원 이내";
        } else if (zeroCount == 7) {
            stringMoney = num + "천만원 이내";
        } else if (zeroCount == 8) {
            stringMoney = num + "억원 이내";
        } else if (zeroCount == 9) {
            stringMoney = num + "십억원 이내";
        } else if (zeroCount == 10) {
            stringMoney = num + "백억원 이내";
        } else if (zeroCount == 11) {
            stringMoney = num + "천억원 이내";
        }
        return stringMoney;
    }
}
