/*
 * Copyright (c) 2019 Tony Ho. Some rights reserved.
 */

package com.chuntung.plugin.mybatisbuilder.model;

public enum DriverTypeEnum {

    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://${host}:${port}/${schema}?useUnicode=true&useSSL=false&characterEncoding=UTF8", 3306, "/images/MySQL.png"),
    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://${host}:${port}/${schema}", 5432, "/images/PostgreSQL.png"),
    //    Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s", ""),
    Custom("", "jdbc:${vendor}://${host}:${port}/${schema}", 1234, "/images/connection.png");

    private final String driverClass;
    private final String urlPattern;
    private final Integer defaultPort;
    private final String icon;

    DriverTypeEnum(String driverClass, String urlPattern, Integer defaultPort, String icon) {
        this.driverClass = driverClass;
        this.urlPattern = urlPattern;
        this.defaultPort = defaultPort;
        this.icon = icon;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public Integer getDefaultPort() {
        return defaultPort;
    }

    public String getIcon() {
        return icon;
    }
}