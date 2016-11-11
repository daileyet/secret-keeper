package com.openthinks.secretkeeper.client.model;

import javafx.stage.Stage;

public class TransferData {
	private Stage currentStage;

	public TransferData setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
		return this;
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public static TransferData build(Stage primaryStage) {
		TransferData data = new TransferData();
		return data.setCurrentStage(primaryStage);
	}
}
