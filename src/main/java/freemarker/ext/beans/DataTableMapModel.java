package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import tgtools.data.DataRowCollection;
import tgtools.data.DataTable;

import java.util.Map;

/**
 * Map 中 DataTable 类型处理 只要看get方法
 *
 * @author 田径
 */
public class DataTableMapModel extends MapModel implements TemplateMethodModelEx {
    static final ModelFactory FACTORY = new ModelFactory() {
        @SuppressWarnings("rawtypes")
        @Override
        public TemplateModel create(Object object, ObjectWrapper wrapper) {
            return new DataTableMapModel((Map) object, (BeansWrapper) wrapper);
        }
    };


    @SuppressWarnings("rawtypes")
    public DataTableMapModel(Map map, BeansWrapper wrapper) {
        super(map, wrapper);
    }


    @SuppressWarnings("rawtypes")
    @Override
    public TemplateModel get(String key) throws TemplateModelException {
        if (((Map) this.object).get(key) instanceof DataRowCollection) {
            return DataTableModel.FACTORY.create(((Map) this.object).get(key), this.wrapper);
        }
        if (((Map) this.object).get(key) instanceof DataTable) {
            return DataTableModel.FACTORY.create((((Map) this.object).get(key)), this.wrapper);
        }
        return super.get(key);
    }
}