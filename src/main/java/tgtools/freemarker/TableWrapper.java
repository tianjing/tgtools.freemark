package tgtools.freemarker;


import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.DataRowModel;
import freemarker.ext.beans.DataTableModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import tgtools.data.DataRow;
import tgtools.data.DataRowCollection;
import tgtools.data.DataTable;

/**
 * 转换器针对DataTable 和自定义类型中DataTable做适配
 */
public class TableWrapper extends BeansWrapper {

    /**
     * 类型适配
     * @param object
     * @return
     * @throws TemplateModelException
     */
    @Override
    public TemplateModel wrap(Object object) throws TemplateModelException {
        if (object instanceof DataRow) {
            return DataRowModel.FACTORY.create(object, this);
        }
        if (object instanceof DataRowCollection) {
            return DataTableModel.FACTORY.create(object, this);
        }
        if (object instanceof DataTable) {
            return DataTableModel.FACTORY.create(object, this);
        }
        TemplateModel res = super.wrap(object);
        return res;
    }

    /**
     *
     * @param model
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object unwrap(TemplateModel model) throws TemplateModelException {
        if (model instanceof DataRowModel) {
            return ((DataRowModel) model).getWrappedObject();
        }
        if (model instanceof DataTableModel) {
            return ((DataTableModel) model).getWrappedObject();
        }
        return super.unwrap(model);
    }


}
