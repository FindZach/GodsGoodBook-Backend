package com.findzach.godsgoodbook.tools.scanner.impl;

import org.springframework.stereotype.Service;

/**
 * @author: Zach Smith
 * @date: 2/15/2024
 * @time: 4:26 PM
 */
@Service
public class NetBibleScanner extends SuperSearchScanner {
    @Override
    protected String getBibleURL() {
        return "https://raw.githubusercontent.com/FindZach/GodsGoodBook-Backend/develop/src/main/resources/static/bible-data/net.json";
    }
}
