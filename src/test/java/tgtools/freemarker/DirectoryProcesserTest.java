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
 * @date 11:38
 */
public class DirectoryProcesserTest {
    @Test
    public void processTest() {
        String encoding = "UTF-8";
        String filepath = "C:\\Works\\DQ\\javademos\\tgtools.freemarker\\src\\test\\resources";
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

        List<A> list = new ArrayList<A>();
        A a = new A();
        a.setDt(dt);
        list.add(a);

        Map<String, B> dd = new HashMap<String, B>();
        dd.put("114", new B("222", "333"));
        dd.put("115", new B("444", "555"));

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("message", "fda");
        data.put("table", dt);
        data.put("list1", list);
        data.put("marketMap", dd);
        DirectoryProcesser dirproces = new DirectoryProcesser();
        try {
            dirproces.init(filepath);
            dirproces.process(data, filename);
            Assert.assertTrue(true);
        } catch (APPErrorException e) {
            Assert.fail();
        }
    }


    public static class A {
        private DataTable dt;

        public DataTable getDt() {
            return dt;
        }

        public void setDt(DataTable dt) {
            this.dt = dt;
        }


    }

    public static class B {
        private String id;
        private String name;

        public B(String p_id, String p_name) {
            setId(p_id);
            setName(p_name);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}