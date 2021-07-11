package jp.co.teruuu.ec_modeling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.teruuu.ec_modeling.infla.data.dao")
public class BootMain {
  public static void main(String[] args) {
    SpringApplication.run(BootMain.class, args);
  }
}
