package unit_2;

import javax.swing.*;
public class TrueFalseQuestion extends Question {
	// Constructor for class
	//String question
	TrueFalseQuestion(String question, String answer){
		super(question);
		JPanel buttons = new JPanel();
		addButton(buttons,"TRUE");
		addButton(buttons,"FALSE");
		this.question.add(buttons);
		
		initQuestionDialog();
		
		this.correctAnswer = answer.toUpperCase();
	}

	void addButton(JPanel buttons, String label) {
		JButton button = new JButton(label);
		button.addActionListener(question);
		buttons.add(button);
	}
	

}
