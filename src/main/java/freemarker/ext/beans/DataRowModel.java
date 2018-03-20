package freemarker.ext.beans;


import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import tgtools.data.DataRow;

import java.util.Map;

/**
 * DataRow 类型处理 只要看get方法
 * @author 田径
 */
public class DataRowModel extends StringModel {


    public static final ModelFactory FACTORY = new ModelFactory() {
        @Override
        public TemplateModel create(Object object, ObjectWrapper wrapper) {
            return new DataRowModel(object, (BeansWrapper) wrapper);
        }
    };
    private BeansWrapper m_wrapper;
    private Object m_map;

    public DataRowModel(Object object, BeansWrapper wrapper) {
        super(object, wrapper);
        m_wrapper = wrapper;
        m_map = object;
    }

    @Override
    protected TemplateModel wrap(Object obj) throws TemplateModelException {
        return this.m_wrapper.getOuterIdentity().wrap(obj);
    }

    @Override
    protected Object unwrap(TemplateModel model) throws TemplateModelException {
        return this.m_wrapper.unwrap(model);
    }


    @Override
    public TemplateModel get(String key) throws TemplateModelException {
        Class clazz = this.object.getClass();
        Map<Object, Object> map = this.wrapper.getClassIntrospector().get(clazz);
        if (map.containsKey(key)) {
            return super.get(key);
        }

        DataRow row = (DataRow) m_map;
        Object obj = row.getValue(key);
        return new StringModel(obj, this.m_wrapper);
    }


}
