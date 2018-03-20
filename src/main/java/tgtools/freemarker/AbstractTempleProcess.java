package tgtools.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import java.io.*;
import java.util.Locale;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 11:25
 */
public abstract class AbstractTempleProcess {
    protected static final String ENCODING_DEFAULT = "UTF-8";
    protected String mPath;
    protected Configuration mConfiguration;
    protected String mEncoding = ENCODING_DEFAULT;
    protected String mOutputEncoding = ENCODING_DEFAULT;

    public AbstractTempleProcess() {
        mConfiguration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    }

    public String getEncoding() {
        return mEncoding;
    }

    public void setEncoding(String pEncoding) {
        mEncoding = StringUtil.isNullOrEmpty(pEncoding) ? ENCODING_DEFAULT : pEncoding;
    }

    public String getOutputEncoding() {
        return mOutputEncoding;
    }

    public void setOutputEncoding(String pOutputEncoding) {
        mEncoding = StringUtil.isNullOrEmpty(pOutputEncoding) ? ENCODING_DEFAULT : pOutputEncoding;
    }
    /**
     * 初始化全局设置
     * @param pPath
     * @throws APPErrorException
     */
    public void init(String pPath) throws APPErrorException {
        init(pPath, Locale.SIMPLIFIED_CHINESE, getEncoding());
    }
    /**
     * 初始化全局设置
     * @param pPath 模板路径
     * @param pEncoding
     * @throws APPErrorException
     */
    public void init(String pPath, String pEncoding) throws APPErrorException {
        init(pPath, Locale.SIMPLIFIED_CHINESE, pEncoding);
    }
    /**
     * 初始化全局设置
     * @param pPath
     * @param pLocale
     * @param pEncoding
     * @throws APPErrorException
     */
   abstract void init(String pPath, Locale pLocale, String pEncoding) throws APPErrorException;



    /**
     * 处理模板内容
     *
     * @param pFileName
     * @param pData
     *
     * @return
     *
     * @throws APPErrorException
     */
    public String process(Object pData, String pFileName) throws APPErrorException {
        return process(pData, pFileName, null, null, null, true, false);
    }

    /**
     * 处理模板内容
     */
    public String process(Object pData, String pFileName, Locale pLocale, Object pCustomLookupCondition,
                          String pEncoding, boolean pParseAsFTL, boolean pIgnoreMissing) throws APPErrorException {
        java.io.ByteArrayOutputStream dsa = new ByteArrayOutputStream();
        try {
            Template t = mConfiguration.getTemplate(pFileName, pLocale, pCustomLookupCondition, pEncoding, pParseAsFTL, pIgnoreMissing);
            Writer out = new BufferedWriter(new OutputStreamWriter(dsa));
            t.process(pData, out, new TableWrapper());
            out.flush();
            if (ENCODING_DEFAULT.equals(getOutputEncoding())) {
                return dsa.toString();
            } else {
                return changeEncoding(dsa.toString(), getOutputEncoding());
            }

        } catch (Exception e) {
            throw new APPErrorException("模板转换失败；原因：" + e.getMessage(), e);
        } finally {
            try {
                dsa.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 将utf8 编码 转换为指定的编码
     *
     * @param pData
     * @param pEncoding
     *
     * @return
     */
    private String changeEncoding(String pData, String pEncoding) {
        byte[] data = pData.getBytes();
        boolean issame = true;
        try {
            byte[] bom = new String(StringUtil.UTF8Bom, pEncoding).getBytes();
            for (int i = 0; i < bom.length; i++) {
                if (bom[i] != data[i]) {
                    issame = false;
                    break;
                }
            }
            if (issame) {
                return new String(data, bom.length, data.length - bom.length);
            }

        } catch (UnsupportedEncodingException e) {
            new APPErrorException("字符转换出错", e);
        }
        return pData;
    }
}
