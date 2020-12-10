package com.babbangona.hyperlogger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static android.content.Context.ACTIVITY_SERVICE;

public class RunTimeMemoryParameters {

    private final String nativeAllocatedMemory;
    private final String vmHeapSizeMemory;
    private final String vmAllocatedMemory;
    private final String totalAllocatedMemory;
    private final String nativeHeap;
    private final String availableMemoryInMB;
    //private final String CPULoadComposer;
    private final String ramUtilization;
    private final String memoryUsage;

    public RunTimeMemoryParameters() {
        Runtime rt = Runtime.getRuntime();
        long vmAlloc =  rt.totalMemory() - rt.freeMemory();
        long nativeAlloc = Debug.getNativeHeapAllocatedSize();
        this.nativeAllocatedMemory = formatMemoryText(nativeAlloc);
        this.vmHeapSizeMemory = formatMemoryText(rt.totalMemory());
        this.vmAllocatedMemory = formatMemoryText(vmAlloc);
        this.totalAllocatedMemory = formatMemoryText(nativeAlloc+vmAlloc);
        this.nativeHeap = formatMemoryText(Debug.getNativeHeapSize());

        final long usedMemInMB = (vmAlloc) / 1048576L;
        final long maxHeapSizeInMB = rt.maxMemory() / 1048576L;
        final long availHeapSizeInMB = maxHeapSizeInMB - usedMemInMB;
        this.availableMemoryInMB = memoryText(availHeapSizeInMB);
        this.ramUtilization = getRamUsagePercentage(usedMemInMB, maxHeapSizeInMB);
        this.memoryUsage = memoryText(usedMemInMB);

        //this.CPULoadComposer = getInfo();
    }

    /*public String getCPULoadComposer() {
        return CPULoadComposer;
    }*/

    public String getAvailableMemoryInMB() {
        return availableMemoryInMB;
    }

    public String getNativeAllocatedMemory() {
        return nativeAllocatedMemory;
    }

    public String getVmHeapSizeMemory() {
        return vmHeapSizeMemory;
    }

    public String getVmAllocatedMemory() {
        return vmAllocatedMemory;
    }

    public String getTotalAllocatedMemory() {
        return totalAllocatedMemory;
    }

    public String getNativeHeap() {
        return nativeHeap;
    }

    public String getRamUtilization() {
        return ramUtilization;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    private String formatMemoryText(long memory) {
        float memoryInMB = memory * 1f / 1024 / 1024;
        return memoryInMB + "MB";
    }

    private String memoryText(long memory) {
        return memory + "MB";
    }

    private String getRamUsagePercentage(long usedMemInMB, long maxHeapSizeInMB){
        long division = usedMemInMB/maxHeapSizeInMB;
        return Math.round((division*100)/100.00) + "%";
    }

    @NotNull
    @Override
    public String toString() {
        return "RunTimeMemoryParameters{" +
                "nativeAllocatedMemory='" + nativeAllocatedMemory + '\'' +
                ", vmHeapSizeMemory='" + vmHeapSizeMemory + '\'' +
                ", vmAllocatedMemory='" + vmAllocatedMemory + '\'' +
                ", totalAllocatedMemory='" + totalAllocatedMemory + '\'' +
                ", nativeHeap='" + nativeHeap + '\'' +
                ", availableMemoryInMB='" + availableMemoryInMB + '\'' +
                ", ramUtilization='" + ramUtilization + '\'' +
                ", memoryUsage='" + memoryUsage + '\'' +
                '}';
    }

    //The methods below were proposed to be used to get CPU Load
    private String getCPULoads(){
        ArrayList<String> logs = new ArrayList<>();
        for (int i = 0; i < calcCpuCoreCount(); i++) {
            logs.add(takeCurrentCpuFreq(i) +"\n");
        }
        return logs.toString();
    }

    private static int readIntegerFile(String filePath) {

        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath)), 1000);
            final String line = reader.readLine();
            reader.close();

            return Integer.parseInt(line);
        } catch (Exception e) {
            return 0;
        }
    }

    private static int takeCurrentCpuFreq(int coreIndex) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq");
    }

    private static int calcCpuCoreCount() {

        int sLastCpuCoreCount = 0;

        if (sLastCpuCoreCount >= 1) {
            return sLastCpuCoreCount;
        }

        try {
            // Get directory containing CPU info
            final File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            final File[] files = dir.listFiles(pathname -> {
                //Check if filename is "cpu", followed by a single digit number
                return Pattern.matches("cpu[0-9]", pathname.getName());
            });

            // Return the number of cores (virtual CPU devices)
            sLastCpuCoreCount = files.length;

        } catch(Exception e) {
            sLastCpuCoreCount = Runtime.getRuntime().availableProcessors();
        }

        return sLastCpuCoreCount;
    }

    private String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("abi: ").append(Build.CPU_ABI).append("\n");
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader(new File("/proc/cpuinfo")));
                String aLine;
                while ((aLine = br.readLine()) != null) {
                    sb.append(aLine).append("\n");
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String getCPUInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("abi: ").append(Build.CPU_ABI).append("\n");
        if (new File("/proc/uptime").exists()) {
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader(new File("/proc/uptime")));
                String aLine;
                while ((aLine = br.readLine()) != null) {
                    sb.append(aLine).append("\n");
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    void getRAMLoad(Context context){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        double availableMegs = (float)mi.availMem / 0x100000L;

        //Percentage can be calculated for API 16+
        double percentAvail = mi.availMem / (double)mi.totalMem * 100.0;
    }
}
