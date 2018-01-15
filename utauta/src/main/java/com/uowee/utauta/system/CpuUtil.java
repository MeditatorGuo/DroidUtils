package com.uowee.utauta.system;

import com.uowee.utauta.assist.Check;
import com.uowee.utauta.file.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * Created by GuoWee on 2018/1/12.
 */

public final class CpuUtil {

    private static final String CPU_INFO_PATH = "/proc/cpuinfo";

    private static final String CPU_FREQ_NULL = "N/A";
    private static final String CMD_CAT = "/system/bin/cat";
    private static final String CPU_FREQ_CUR_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private static final String CPU_FREQ_MAX_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    private static final String CPU_FREQ_MIN_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";

    private static String CPU_NAME;
    private static int CPU_CORES = 0;
    private static long CPU_MAX_FREQENCY = 0;
    private static long CPU_MIN_FREQENCY = 0;

    private CpuUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * get CPU Info
     *
     * @return
     */
    public static String getCpuInfo() {
        String info = FileUtil.readFile(CPU_INFO_PATH);
        return info;
    }

    /**
     * get Processor Counts
     *
     * @return
     */
    public static int getProcessorsCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * get Core Numbers
     *
     * @return
     */
    public static int getCoresNumbers() {
        if (CPU_CORES != 0) {
            return CPU_CORES;
        }
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]+", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        File dir = new File("/sys/devices/system/cpu/");
        File[] files = dir.listFiles(new CpuFilter());
        CPU_CORES = files.length;
        if (CPU_CORES < 1) {
            CPU_CORES = Runtime.getRuntime().availableProcessors();
        }
        if (CPU_CORES < 1) {
            CPU_CORES = 1;
        }

        return CPU_CORES;
    }

    /**
     * get CPU Name
     *
     * @return
     */
    public static String getCpuName() {
        if (!Check.isEmpty(CPU_NAME)) {
            return CPU_NAME;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CPU_INFO_PATH), 8192);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            String[] array = line.split(":\\s+", 2);
            if (array.length > 1) {
                CPU_NAME = array[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CPU_NAME;
    }

    /**
     * Get current CPU freqency.
     */
    public static long getCurrentFreqency() {
        try {
            return Long.parseLong(FileUtil.readFile(CPU_FREQ_CUR_PATH).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get maximum CPU freqency
     */
    public static long getMaxFreqency() {
        if (CPU_MAX_FREQENCY > 0) {
            return CPU_MAX_FREQENCY;
        }
        try {
            CPU_MAX_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MAX_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MAX_FREQENCY;
    }

    /**
     * Get minimum frenqency.
     */
    public static long getMinFreqency() {
        if (CPU_MIN_FREQENCY > 0) {
            return CPU_MIN_FREQENCY;
        }
        try {
            CPU_MIN_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MIN_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MIN_FREQENCY;
    }

    /**
     * Get command output string.
     */
    public static String getCMDOutputString(String[] args) {
        try {
            ProcessBuilder cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] re = new byte[64];
            int len;
            while ((len = in.read(re)) != -1) {
                sb.append(new String(re, 0, len));
            }
            in.close();
            process.destroy();
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
