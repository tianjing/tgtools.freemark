package tgtools.freemarker;

import org.junit.Assert;
import org.junit.Test;
import tgtools.data.DataRow;
import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 11:51
 */
public class ClassProcesserTest {
    @Test
    public void processTest() throws APPErrorException {
        String encoding = "UTF-8";
        String filepath = "/tgtools/freemarker/";
        String filename = "test.ftl";


        DataTable dt = new DataTable();
        dt.appendColumn("ID");
        dt.appendColumn("NAME");
        dt.appendColumn("SEX");
        DataRow row1 = dt.appendRow();
        row1.setValue("ID", "1");
        row1.setValue("NAME", "tg1");
        row1.setValue("SEX", "男");

        DataRow row2 = dt.appendRow();
        row2.setValue("ID", "2");
        row2.setValue("NAME", "tg2");
        row2.setValue("SEX", "女");

        List<DirectoryProcesserTest.A> list = new ArrayList<DirectoryProcesserTest.A>();
        DirectoryProcesserTest.A a = new DirectoryProcesserTest.A();
        a.setDt(dt);
        list.add(a);

        Map<String, DirectoryProcesserTest.B> dd = new HashMap<String, DirectoryProcesserTest.B>();
        dd.put("114", new DirectoryProcesserTest.B("222", "333"));
        dd.put("115", new DirectoryProcesserTest.B("444", "555"));

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("message", "fda");
        data.put("table", dt);
        data.put("list1", list);
        data.put("marketMap", dd);


        ClassProcesser proces = new ClassProcesser();

        proces.init(filepath);
        proces.process(data, filename);
        Assert.assertTrue(true);
    }
}