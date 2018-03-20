package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.*;
import tgtools.data.DataRowCollection;
import tgtools.data.DataTable;
import tgtools.freemarker.TableWrapper;

import java.util.Collection;
import java.util.Map;

/**
 * DataTable 类型处理 只要看get方法
 *
 * @author 田径
 */
public class DataTableModel extends StringModel implements
        TemplateCollectionModel {

    //CollectionModel;
    public static final ModelFactory FACTORY = new ModelFactory() {
        @Override
        public TemplateModel create(Object object, ObjectWrapper wrapper) {
            return new DataTableModel(object, (TableWrapper) wrapper);
        }
    };
    private TableWrapper m_wrapper;

    public DataTableModel(Object object, TableWrapper wrapper) {
        super(object, wrapper);
        m_wrapper = wrapper;
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
        return this;

    }


    @SuppressWarnings("rawtypes")
    @Override
    public TemplateModelIterator iterator() {
        if (this.object instanceof DataRowCollection) {
            return new IteratorModel(((Collection) this.object).iterator(),
                    this.wrapper);
        }
        return new IteratorModel(((Collection) ((DataTable) this.object).getRows()).iterator(),
                this.wrapper);
    }


}
