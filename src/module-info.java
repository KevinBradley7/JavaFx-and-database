module StudentRecords {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}