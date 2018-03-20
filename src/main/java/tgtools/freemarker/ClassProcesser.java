package tgtools.freemarker;

import tgtools.exceptions.APPErrorException;

import java.util.Locale;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 11:24
 */
public class ClassProcesser extends AbstractTempleProcess {
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
        // 设置模板文件的存放目录
        try {
            mConfiguration.setClassForTemplateLoading(this.getClass(),pPath);
        } catch (Exception e) {
            throw new APPErrorException("Freemarker目录设置失败；Path;" + mPath);
        }
    }
    /**
     * 初始化全局设置
     * @param pPath
     * @param pLocale
     * @param pEncoding
     * @throws APPErrorException
     */
    public void init(Class pResourceLoaderClass,String pPath, Locale pLocale, String pEncoding) throws APPErrorException {
        mPath = pPath;
        setEncoding(pEncoding);
        mConfiguration.setEncoding(pLocale, getEncoding());
        mConfiguration.setObjectWrapper(new TableWrapper());
        // 设置模板文件的存放目录
        try {
            mConfiguration.setClassForTemplateLoading(pResourceLoaderClass,pPath);
        } catch (Exception e) {
            throw new APPErrorException("Freemarker目录设置失败；Path;" + mPath);
        }
    }
}
