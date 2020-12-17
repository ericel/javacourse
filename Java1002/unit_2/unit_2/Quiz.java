package unit_2;


    public class Quiz {
	    	
	 
		public static void main(String[] args) {
		
			// Multiple Choice Questions
			MultipleChoiceQuestion question1 = new MultipleChoiceQuestion(
					"What is the name of the Stoic Roman emperor?",
					"Seneca The Younger",
					"Marcus Aurelius",
					"Epictetus",
					"Zeno The Citium",
					"Donald J. Trump",
					"b"
					);
			
			MultipleChoiceQuestion question2 = new MultipleChoiceQuestion(
					"What is the coolest programming language?",
					"Java",
					"C, C#, C++",
					"JavaScript",
					"PHP",
					"Python",
					"e"
					);
			MultipleChoiceQuestion question3 = new MultipleChoiceQuestion(
					"What's the fastest web framework?",
					"Laravel PHP",
					"Django Python",
					"ReactJs JavaScript",
					"AngularJs JavaScript",
					"Flutter Dart",
					"b"
					);
			
		   MultipleChoiceQuestion question4 = new MultipleChoiceQuestion(
					"Which of the following is not a natural language?",
					"English",
					"French",
					"German",
					"Java",
					"Spanish",
					"d"
					);
		   
		   MultipleChoiceQuestion question5 = new MultipleChoiceQuestion(
					"Why is it possible to run Java on all platforms?",
					"Because it's Java",
					"Bytecode makes it platform independent",
					"Java prgrammers are smarter",
					"I don't know",
					"None of the above",
					"b"
					);
			   
			
				// True or False Questions
				Question question6   = new TrueFalseQuestion("You are learning Java?", "True");
				
				Question question7   = new TrueFalseQuestion("Java is a natural language?", "False");
				Question question8   = new TrueFalseQuestion("Bytecode makes Java platform independent?", "True");
				Question question9   = new TrueFalseQuestion("SUPER is a Java special Variable?", "True");
				Question question10  = new TrueFalseQuestion("The variable THIS in Java is not equivalent to self in python?", "False");
				
				/*
				 Question question6 = new TrueFalseQuestion("You are learning Java?", "True");
				 question6.check();
				 question6.showResults();
				
				*/
				// Array of questions
				Object[] questions = {question1, question2, question3, question4, question5, question6, question7, question8, question9, question10};

				// Check an object of the questions
				// with the check method
				int i = 0;
				while(i < questions.length) {
			        // We call our check method here
					((Question) questions[i]).check();
					i++;
				}
				
				Question.showResults();
				

		} // End Main Method
		
		

}