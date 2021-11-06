package com.castroantonio.reservas.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//anotação do JUnit para informar que deve usar Cucumber
@RunWith(Cucumber.class)

// anotação Cucumber para informar onde (em que pacote) estão as features
@CucumberOptions(features = "classpath:features")
public class ReservasCucumberRunner {

}