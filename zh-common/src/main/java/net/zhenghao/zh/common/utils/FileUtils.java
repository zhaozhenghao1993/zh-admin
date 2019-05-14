package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.constant.SystemConstant;

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

    private FileUtils() {}

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
     * @param len
     *            文件长度
     * @param size
     *            限制大小
     * @param unit
     *            限制单位（B,K,M,G）
     * @return
     * 使用
     * // 上传文件格式
     * MultipartFile multipartFile = null;
     * // 文件不大于100M
     * FileUtil.checkFileSize( multipartFile.getSize(),100,"M");
     *
     * // 上传文件格式
     * File file = null;
     * // 文件不大于100M
     * FileUtil.checkFileSize( file.length(),100,"M")
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    /**
     * 根据文件名判断文件类型
     * @param fileName
     * @return 图片：1     文档：2     视频：3    种子：4    音乐：5   其他：6
     */
    public static SystemConstant.FileType fileType(String fileName) {

        if (fileName == null) {
            return null;
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            // 创建图片类型数组
            String img[] = { "bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf" };
            for (int i = 0; i < img.length; i++) {
                if (img[i].equals(fileType)) {
                    return SystemConstant.FileType.IMAGE;
                }
            }

            // 创建文档类型数组
            String document[] = { "txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt" };
            for (int i = 0; i < document.length; i++) {
                if (document[i].equals(fileType)) {
                    return SystemConstant.FileType.DOCUMENT;
                }
            }

            // 创建视频类型数组
            String video[] = { "mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm" };
            for (int i = 0; i < video.length; i++) {
                if (video[i].equals(fileType)) {
                    return SystemConstant.FileType.VIDEO;
                }
            }

            // 创建音乐类型数组
            String music[] = { "mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf" };
            for (int i = 0; i < music.length; i++) {
                if (music[i].equals(fileType)) {
                    return SystemConstant.FileType.MUSIC;
                }
            }

            // 创建种子类型数组
            String seed[] = { "torrent" };
            for (int i = 0; i < seed.length; i++) {
                if (seed[i].equals(fileType)) {
                    return SystemConstant.FileType.SEED;
                }
            }
        }
        return SystemConstant.FileType.OTHER;
    }
}
