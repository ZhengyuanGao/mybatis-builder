package com.chuntung.plugin.mybatis.builder.generator.plugins;

import com.chuntung.plugin.mybatis.builder.util.StringUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class SwaggerPlugin extends PluginAdapter {

    private final static FullyQualifiedJavaType ANNOTATION_TYPE = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    private void populateSwaggerAnnotation(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(ANNOTATION_TYPE);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        populateSwaggerAnnotation(topLevelClass);
        return true;
    }


    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        populateSwaggerAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        populateSwaggerAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtil.stringHasValue(remarks)) {
            int index = remarks.indexOf("\r\n");
            if (index > 0) {
                remarks = remarks.substring(0, index);
            }
            field.addAnnotation("@ApiModelProperty(\"" + remarks + "\")");
        }
        return true;
    }
}
