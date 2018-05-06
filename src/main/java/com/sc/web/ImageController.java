package com.sc.web;

 import com.sc.config.GlobalConfig;
 import com.sc.file.ApiEntity;
        import com.sc.file.FileController;
        import com.sc.utils.DeEnCode;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.apache.commons.lang3.StringUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.ServletOutputStream;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.Part;
        import java.io.File;
        import java.io.IOException;
        import java.util.Calendar;
        import java.util.UUID;

/**
 * Created by xyqin on 16/5/25.
 */
@Api(tags = "图片", description = "图片API")
@RestController
@RequestMapping("/images")
public class ImageController extends FileController {

    private static final String[] SUPPORTED_IMAGE_EXTENSIONS = {"jpg", "jpeg", "png"};

    private static final int MAX_IMAGE_SIZE_KB = 5000;
    private static final int MAX_IMAGE_FILE_NAME = 100;

    @Override
    protected String[] getSupportedExtensions() {
        return SUPPORTED_IMAGE_EXTENSIONS;
    }

    @Override
    protected int getMaxSize() {
        return MAX_IMAGE_SIZE_KB;
    }

    @Autowired
    private GlobalConfig config;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiEntity<String> uploadImage(@RequestParam("file") Part file) throws IOException {
        String imgid = UUID.randomUUID().toString();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        String rexfn = c.getTimeInMillis() + "_" + DeEnCode.encode(UUID.randomUUID().toString());
        String storepath = config.storepath + "/image/" + year + "/" + month + "/" + c.getTimeInMillis() % 10 + "/" + rexfn + ".";
        return super.upload(file, storepath);
    }

    @ApiOperation(value = "图片", notes = "图片")
    @RequestMapping(value = "/get/{filename}", method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
        response.setContentType("image/jpeg");
        ServletOutputStream ops = response.getOutputStream();

        String[] sn = StringUtils.split(filename, "_");

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(sn[0]));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;

        String storepath = config.storepath + "/image/" + year + "/" + month + "/" + c.getTimeInMillis() % 10 + "/" + filename + ".jpg";
        File file = new File(storepath);
        response.setContentLengthLong(file.length());
        super.download(ops, storepath);
    }

}
