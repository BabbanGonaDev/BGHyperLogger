package com.babbangona.hyperlogger;

import android.os.Debug;

import org.jetbrains.annotations.NotNull;

public class RunTimeMemoryParameters {

    private final String nativeAllocatedMemory;
    private final String vmHeapSizeMemory;
    private final String vmAllocatedMemory;
    private final String totalAllocatedMemory;
    private final String nativeHeap;

    public RunTimeMemoryParameters() {
        Runtime rt = Runtime.getRuntime();
        long vmAlloc =  rt.totalMemory() - rt.freeMemory();
        long nativeAlloc = Debug.getNativeHeapAllocatedSize();
        this.nativeAllocatedMemory = formatMemoryText(nativeAlloc);
        this.vmHeapSizeMemory = formatMemoryText(rt.totalMemory());
        this.vmAllocatedMemory = formatMemoryText(vmAlloc);
        this.totalAllocatedMemory = formatMemoryText(nativeAlloc+vmAlloc);
        this.nativeHeap = formatMemoryText(Debug.getNativeHeapSize());
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

    private String formatMemoryText(long memory) {
        float memoryInMB = memory * 1f / 1024 / 1024;
        return memoryInMB + "MB";
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
                '}';
    }
}
