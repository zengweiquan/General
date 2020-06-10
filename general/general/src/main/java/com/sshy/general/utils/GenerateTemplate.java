package com.sshy.general.utils;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GenerateTemplate {
    public static void main(String[] args) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(false);                   //文件覆盖
        gc.setOpen(false);                          //打开文件目录
        gc.setActiveRecord(true);                   // 不需要ActiveRecord特性的请改为false  ActiveRecord是plus的面向对象查询功能  实体类可以面向查询
        gc.setEnableCache(true);                    // XML 二级缓存
        gc.setBaseColumnList(true);                 // XML 生成sql片段
        gc.setAuthor("曾伟权");
        gc.setServiceName("%sService");             //去掉前缀I
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("general");
        dsc.setPassword("general");
        dsc.setUrl("jdbc:mysql://192.168.2.201:3306/general?useUnicode=true&characterEncoding=utf-8");

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setInclude("sys_users");                     //要生成的表
        strategy.setNaming(NamingStrategy.underline_to_camel);//不用下划线转驼峰命名

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.sshy.general");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setEntity("entity");


        //整合配置
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setStrategy(strategy);
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
        System.out.println("生成完毕");
    }
}
