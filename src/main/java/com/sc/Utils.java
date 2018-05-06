package com.sc;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xyqin on 2017/4/7.
 */
public final class Utils {

    private static final ExecutorService THREAD_EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final RandomStringGenerator ALPHABETIC_GENERATOR = new RandomStringGenerator.Builder()
            .withinRange('A', 'z')
            .filteredBy(CharacterPredicates.LETTERS)
            .build();

    private static final RandomStringGenerator NUMERIC_GENERATOR = new RandomStringGenerator.Builder()
            .withinRange('0', '9')
            .filteredBy(CharacterPredicates.DIGITS)
            .build();

    private static final RandomStringGenerator ALPHANUMERIC_GENERATOR = new RandomStringGenerator.Builder()
            .withinRange('0', 'z')
            .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
            .build();

    private static final RandomStringGenerator ASCII_GENERATOR = new RandomStringGenerator.Builder().withinRange(33, 126).build();

    /**
     * 金额转换成字符串
     *
     * @param money
     * @return
     */
    public static String amountToString(BigDecimal money) {
        return money.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 根据字母生成随机字符串
     *
     * @param count
     * @return
     */
    public static String randomAlphabetic(int count) {
        return ALPHABETIC_GENERATOR.generate(count);
    }

    /**
     * 根据数字生成随机字符串
     *
     * @param count
     * @return
     */
    public static String randomNumeric(int count) {
        return NUMERIC_GENERATOR.generate(count);
    }

    /**
     * 根据字母和数字生成随机字符串
     *
     * @param count
     * @return
     */
    public static String randomAlphanumeric(int count) {
        return ALPHANUMERIC_GENERATOR.generate(count);
    }

    /**
     * 根据ASCII字符生成随机字符串
     *
     * @param count
     * @return
     */
    public static String randomAscii(int count) {
        return ASCII_GENERATOR.generate(count);
    }

}
