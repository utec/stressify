package edu.utec.tools.smarth_stressor.executor;

import edu.utec.tools.smarth_stressor.base.BaseScriptExecutor;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class SimpleContinuousScriptExecutor implements BaseScriptExecutor {

  public Binding binding;
  public String script;
  public Object output;

  protected void run() {
    GroovyShell shell = new GroovyShell(this.binding);
    output = shell.evaluate(script);
  }

  public void start() {
    run();
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

}
