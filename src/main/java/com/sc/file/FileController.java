package com.sc.file;

        import com.google.common.base.Joiner;
        import com.sc.utils.ImageHelper;
        import org.apache.commons.io.FilenameUtils;

        import javax.servlet.http.Part;
        import java.io.*;
        import java.math.BigInteger;

/**
 * 文件上传下载控制
 *
 */
public abstract class FileController {
    protected abstract String[] getSupportedExtensions();

    protected abstract int getMaxSize();

    /**
     * 上传文件
     * @param file
     * @param storepath
     */
    protected ApiEntity upload(Part file, String storepath) throws IOException {
        int fileSizeInKB = BigInteger.valueOf(file.getSize()).divide(BigInteger.valueOf(1024)).intValue();
        String filename = file.getSubmittedFileName();
        storepath += FilenameUtils.getExtension(filename);
        if (!FilenameUtils.isExtension(filename, getSupportedExtensions())) {
            return new ApiEntity<>(AdminErrorStatus.STATUS_1502.getCode(),
                    String.format(AdminErrorStatus.STATUS_1502.getMessage(), Joiner.on("，").join(getSupportedExtensions())));
        }

        if (fileSizeInKB > getMaxSize()) {
            return new ApiEntity<>(AdminErrorStatus.STATUS_1503.getCode(), AdminErrorStatus.STATUS_1503.getMessage());
        }

        File storeFile = new File(storepath);
        if (!storeFile.getParentFile().exists()) {
            storeFile.getParentFile().mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(storeFile);
        org.apache.commons.io.IOUtils.copy(file.getInputStream(),fos);
        fos.flush();
        fos.close();

        try {
            ImageHelper.zoomImage(storepath, storepath, 686, 326);
        }catch(Exception e){

        }

        return new ApiEntity<>(storeFile.getName());
    }

    /**
     * 下载文件
     * @param outputStream
     * @param storepath
     * @throws IOException
     */
    protected void download(OutputStream outputStream, String storepath) throws IOException {
        FileInputStream is = null;
        try {
            is = new FileInputStream(storepath);
            byte[] b = new byte[1024];
            while (is.read(b)>0) {
                outputStream.write(b,0,b.length);
                b = new byte[1024];
                outputStream.flush();
            }
        } finally {
            if (is!=null) {
                is.close();
            }
            if (outputStream!=null) {
                outputStream.close();
            }
        }
    }
}
