package com.findzach.godsgoodbook.tools;

/**
 * @author: Zach Smith
 * @date: 2/14/2024
 * @time: 10:08 PM
 *
 * Will display details about the current job
 */
public interface BibleScanJob {
    int getTotalProcessed();
    String getJobState();
    void toggleJob(boolean newState);
    boolean isJobRunning();


}
