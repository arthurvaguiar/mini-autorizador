package com.vrautorizador.miniautorizador;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "caminho.para.os.steps")
public class RunCucumberTests {
}
