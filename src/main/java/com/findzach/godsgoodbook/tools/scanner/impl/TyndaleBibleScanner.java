package com.findzach.godsgoodbook.tools.scanner.impl;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 4:22 PM
 */
public class TyndaleBibleScanner extends SuperSearchScanner {
    @Override
    protected String getBibleURL() {
        return "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/tyndale.json";
    }
}
