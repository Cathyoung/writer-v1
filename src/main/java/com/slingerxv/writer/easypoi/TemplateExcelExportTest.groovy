package com.slingerxv.writer.easypoi

import cn.afterturn.easypoi.excel.ExcelExportUtil
import cn.afterturn.easypoi.excel.entity.TemplateExportParams
import groovy.transform.CompileStatic
import org.apache.poi.ss.usermodel.Workbook

/**
 * Created by chenyong on 2018/9/4
 */
@CompileStatic
class TemplateExcelExportTest {

    public static void main(String[] args) {
        TemplateExportParams params = new TemplateExportParams("excel_template/模板测试.xlsx");

        def list = []
        A a = new A(
                id: "1",
                name: "sad",
                age: "14",
                school: "hah"
        )

        A a1 = new A(
                id: "2",
                name: "sad",
                age: "14",
                school: "hah"
        )
        list << a << a1
        def map = [:]
        map.put("map" , list)
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);

        FileOutputStream fos = new FileOutputStream("/pic/IMG/模板测试.xlsx");
        workbook.write(fos);
        fos.close();
    }

    static String path(){
        return  TemplateExcelExportTest.class.getResource("").getPath()
    }

    static class A {
        String id
        String name
        String age
        String school
        String perfessional
    }
}
