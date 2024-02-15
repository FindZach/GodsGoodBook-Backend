package com.findzach.godsgoodbook.tools.scanner;

import com.findzach.godsgoodbook.tools.BibleScanJob;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 11:15 PM
 */
public abstract class BibleScanner implements BibleScanJob {

    private int totalRecordsProcessed = 0;
    private String bibleState = "Loading...";
    private boolean executing = false;

    @Override
    public int getTotalProcessed() {
        return this.totalRecordsProcessed;
    }

    @Override
    public String getJobState() {
        return this.bibleState;
    }

    @Override
    public void toggleJob(boolean newState) {
        this.executing = newState;
    }

    @Override
    public boolean isJobRunning() {
        return executing;
    }
}
