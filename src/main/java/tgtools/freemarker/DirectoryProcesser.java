package tgtools.freemarker;

import tgtools.exceptions.APPErrorException;

import java.io.File;
import java.util.Locale;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:49
 */
public class DirectoryProcesser extends AbstractTempleProcess{

    /**
     * 初始化全局设置
     * @param pPath
     * @param pLocale
     * @param pEncoding
     * @throws APPErrorException
     */
    @Override
    public void init(String pPath, Locale pLocale, String pEncoding) throws APPErrorException {
        mPath = pPath;
        setEncoding(pEncoding);
        mConfiguration.setEncoding(pLocale, getEncoding());
        mConfiguration.setObjectWrapper(new TableWrapper());
        // 设置模板文件的存放目录
        try {
            mConfiguration.setDirectoryForTemplateLoading(new File(mPath));
        } catch (Exception e) {
            throw new APPErrorException("Freemarker目录设置失败；Path;" + mPath);
        }
    }
}
