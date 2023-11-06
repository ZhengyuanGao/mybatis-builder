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

    private final static FullyQualifiedJavaType ANNOTATION_TYPE_1 = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");
    private final static FullyQualifiedJavaType ANNOTATION_TYPE_2 = new FullyQualifiedJavaType("com.uxq.common.annotations.ApiPropertyReference");

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    private void populateSwaggerAnnotation(TopLevelClass topLevelClass) {

        for (Field field : topLevelClass.getFields()) {
            for (String annotation : field.getAnnotations()) {
                if (annotation != null) {
                    if (annotation.startsWith("@ApiModelProperty")) {
                        topLevelClass.addImportedType(ANNOTATION_TYPE_1);
                    } else if (annotation.startsWith("@ApiPropertyReference")) {
                        topLevelClass.addImportedType(ANNOTATION_TYPE_2);
                    }
                }
            }
        }


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
            String enumClass = null;
            String remarkValue = remarks;
            int indexSee = remarks.indexOf("@see");
            if (indexSee > 0) {
                enumClass = remarks.substring(indexSee + 5);

            }
            int indexReturn = remarks.indexOf("\r\n");
            if (indexReturn > 0) {
                remarkValue = remarks.substring(0, indexReturn);
            }

            if (enumClass != null) {
                field.addAnnotation("@ApiPropertyReference(value = \"" + remarkValue + "\", referenceClazz = " + enumClass + ".class)");
            } else {
                field.addAnnotation("@ApiModelProperty(\"" + remarkValue + "\")");
            }
        }
        return true;
    }
}
