package edu.utec.tools.fiveminutestressor.main;

import javax.swing.JFrame;
import edu.utec.tools.fiveminutestressor.mode.SimpleCommandLineStressor;
import edu.utec.tools.fiveminutestressor.ui.MainView;

public class EntrypointStressor {

  public static void main(String[] args) throws Exception {

    if (args.length == 0) {
      System.err.println(
              "This tool needs at least one initial argument app_mode_graphic or app_mode_cmd");
      System.exit(1);
    }

    String appMode = args[0];

    if (appMode == null || !(appMode.contentEquals("app_mode_graphic")
            || appMode.contentEquals("app_mode_cmd"))) {
      System.err.println(
              "This tool needs at least one initial argument app_mode_graphic or app_mode_cmd. Current value:"
                      + appMode);
      System.exit(1);
    }

    if (appMode.contentEquals("app_mode_graphic")) {

      try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                .getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
          }
        }
      } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(MainView.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(MainView.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(MainView.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(MainView.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
      }
      // </editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
          MainView mainView = new MainView();
          mainView.setVisible(true);
          mainView.setExtendedState(mainView.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
      });

    } else if (appMode.contentEquals("app_mode_cmd")) {
      String[] argsForCmd = new String[args.length];

      for (int a = 1; a < args.length; a++) {
        argsForCmd[a - 1] = args[a];
      }

      SimpleCommandLineStressor commandLineStressor = new SimpleCommandLineStressor();
      commandLineStressor.perform(argsForCmd);

    }

  }
}
