/*
 * Copyright (c) 2019 Tony Ho. Some rights reserved.
 */

package com.chuntung.plugin.mybatisbuilder.generator;

import org.mybatis.generator.config.ModelType;

public class DefaultParameters {
    public static final String DOMAIN_NAME_PLACEHOLDER = "${domainName}";

    private String targetRuntime = "MyBatis3";
    private ModelType defaultModelType = ModelType.FLAT;
    private String javaFileEncoding = "UTF-8";
    private String mapperNamePattern = "${domainName}Mapper";

    public String getTargetRuntime() {
        return targetRuntime;
    }

    public void setTargetRuntime(String targetRuntime) {
        this.targetRuntime = targetRuntime;
    }

    public ModelType getDefaultModelType() {
        return defaultModelType;
    }

    public void setDefaultModelType(ModelType defaultModelType) {
        this.defaultModelType = defaultModelType;
    }

    public String getJavaFileEncoding() {
        return javaFileEncoding;
    }

    public void setJavaFileEncoding(String javaFileEncoding) {
        this.javaFileEncoding = javaFileEncoding;
    }

    public String getMapperNamePattern() {
        return mapperNamePattern;
    }

    public void setMapperNamePattern(String mapperNamePattern) {
        this.mapperNamePattern = mapperNamePattern;
    }
}
