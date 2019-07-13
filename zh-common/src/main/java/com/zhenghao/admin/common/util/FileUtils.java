package com.zhenghao.admin.common.util;

import com.zhenghao.admin.common.constant.SystemConstant;

/**
 * 🙃
 * 🙃 文件工具类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 23:43
 * FileUtils.java
 */

public class FileUtils {

    private FileUtils() {
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

    /**
     * 判断文件大小
     *
     * @param len  文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return true 为满足大小，小于上限
     * false 为不满足大小，大于上限
     * 使用
     * // 上传文件格式
     * MultipartFile multipartFile = null;
     * // 文件不大于100M
     * FileUtil.checkFileSize( multipartFile.getSize(),100,"M");
     * <p>
     * // 上传文件格式
     * File file = null;
     * // 文件不大于100M
     * FileUtil.checkFileSize( file.length(),100,"M")
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        // long len = file.length();
        double fileSize = 0;
        boolean bool = true;
        if ("B".equalsIgnoreCase(unit)) {
            fileSize = (double) len;
        } else if ("K".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1024;
        } else if ("M".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1048576;
        } else if ("G".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            bool = false;
        }
        return bool;
    }

    /**
     * 根据文件名判断文件类型
     *
     * @param fileName
     * @return 图片：1     文档：2     视频：3    种子：4    音乐：5   其他：6
     */
    public static SystemConstant.FileType fileType(String fileName) {

        if (fileName == null) {
            return null;
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).toLowerCase();
            // 创建图片类型数组
            String[] imgs = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
            for (String img : imgs) {
                if (img.equals(fileType)) {
                    return SystemConstant.FileType.IMAGE;
                }
            }

            // 创建文档类型数组
            String[] documents = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
            for (String document : documents) {
                if (document.equals(fileType)) {
                    return SystemConstant.FileType.DOCUMENT;
                }
            }

            // 创建视频类型数组
            String[] videos = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
            for (String video : videos) {
                if (video.equals(fileType)) {
                    return SystemConstant.FileType.VIDEO;
                }
            }

            // 创建音乐类型数组
            String[] musics = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf"};
            for (String music : musics) {
                if (music.equals(fileType)) {
                    return SystemConstant.FileType.MUSIC;
                }
            }

            // 创建种子类型数组
            String[] seeds = {"torrent"};
            for (String seed : seeds) {
                if (seed.equals(fileType)) {
                    return SystemConstant.FileType.SEED;
                }
            }
        }
        return SystemConstant.FileType.OTHER;
    }
}
