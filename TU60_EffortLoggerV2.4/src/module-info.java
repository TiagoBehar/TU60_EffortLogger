module TU60_EffortLogger {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application;
}
