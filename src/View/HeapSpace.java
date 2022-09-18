package View;

import HeapDump.HeapCode;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

public class HeapSpace extends Tab{
	private Button heapSpace;
	
	private static HeapCode heap = new HeapCode();
	
	public HeapSpace() {
		setText("HeapSpace");
		heapSpace = new Button("Run");
		heapSpace.setOnAction(e ->
			heap.OutofMemoryPerson()
		);

		setContent(heapSpace);
	}
}
