package unit_2;

import java.awt.*;
import javax.swing.*;



public abstract class Question {
	static int nQuestions = 0;
	static int nCorrect = 0;
	
	//String question;
	QuestionDialog question;
	String correctAnswer;
	
	Question(String question) {
		this.question = new QuestionDialog();
		this.question.setLayout(new GridLayout(0,1));
		this.question.add(new JLabel(""+question+"",JLabel.CENTER));
		
	}
	
	void initQuestionDialog() {
		this.question.setModal(true);
		this.question.pack();
		this.question.setSize(350, 300);
		this.question.setLocationRelativeTo(null);
	}
	
	String ask() {
		
		question.setVisible(true);
		
		return question.answer;
	}
	// Instance method to checks answers
    void check() {
	      // Record Questions count
	      nQuestions++;
	      	
	      // Call the ask function
	      // Get the answer from the ask function
	      String answer = ask();
	      	
		   
		   // Check for the correct answer
	       // Add count to their correct answers
	       // Return to next question
		   // if incorrect answer, tell them the correct answer.
		   if(answer.equals(correctAnswer)) {
			   JOptionPane.showMessageDialog(null,
						 "<html><span style='font-size:2em; color: green;'>"
						 + "Correct! It's " + correctAnswer);
				// Record correct answers
				nCorrect++;
				return;
		   } else {
				// Tell the User their answer is incorrect
				// Tell the User the right Answer.
		   		JOptionPane.showMessageDialog(null,
							 "<html><span style='font-size:2em;'>"
							 + "<span style='color: red;'>InCorrect!</span>"
							 + " The correct answer is <span style='color: green;'>"
							 + ""+correctAnswer+".</span>"
							 + "");
		    }

    } // End Check Method
    
    // Show results instance method
    static void showResults() {
    	// Finally do a score count of the quiz
		// Number of correct answers
		// Number of questions
		JOptionPane.showMessageDialog(null, 
				"<html><span style='font-size:2em;  color: green;'>"+nCorrect+"</span>"
				+ " correct out of"
				+ " <span style='font-size:2em; color: green;'>"+nQuestions+"</span>"
				+ " questions");
    }
}
