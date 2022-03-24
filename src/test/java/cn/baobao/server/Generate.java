package cn.baobao.server;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author Iris 2022/2/28
 */
public class Generate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入需要自动导出代码的表名，多表名用英文,分隔>>>");
        String[] tables = in.next().split(",");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/managementdb?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Iris") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.baobao.server") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
