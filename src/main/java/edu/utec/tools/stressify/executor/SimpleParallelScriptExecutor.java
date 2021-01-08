package edu.utec.tools.stressify.executor;

import java.util.concurrent.CountDownLatch;
import edu.utec.tools.stressify.core.BaseScriptExecutor;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class SimpleParallelScriptExecutor extends Thread implements BaseScriptExecutor {

  private Binding binding;
  private String script;
  private Object output;
  private CountDownLatch countDownLatch;

  @Override
  public void run() {
    GroovyShell shell = new GroovyShell(this.binding);
    output = shell.evaluate(script);
    countDownLatch.countDown();
  }

  public Binding getBinding() {
    return binding;
  }

  public void setBinding(Binding binding) {
    this.binding = binding;
  }

  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

  public Object getOutput() {
    return output;
  }

  public void setOutput(Object output) {
    this.output = output;
  }

  public CountDownLatch getCountDownLatch() {
    return countDownLatch;
  }

  public void setCountDownLatch(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public Object getResponse() {
    // TODO Auto-generated method stub
    return null;
  }

}
