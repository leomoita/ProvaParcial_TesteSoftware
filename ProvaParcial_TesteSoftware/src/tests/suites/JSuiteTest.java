package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.classes.JTestConta;
import tests.classes.JTestContasRepositorio;
import tests.classes.JTestContasServico;

// JUnit SuiteTest
@RunWith(Suite.class)

@Suite.SuiteClasses({ JTestConta.class, JTestContasRepositorio.class, JTestContasServico.class })

public class JSuiteTest {
}
