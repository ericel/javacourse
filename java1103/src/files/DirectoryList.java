package files;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * This program lists the files in a directory specified by the user. The user
 * 
 * is asked to type in a directory name. If the name entered by the user is not
 * 
 * a directory, a message is printed and the program ends.
 * 
 * @author ericel123
 * 
 */

public class DirectoryList {

	public static void main(String[] args) {
		//System.out.print(System.getProperty("user.dir"));
		
		// Variable to hold input directory path.
		String path;

		// File object referring to the directory.
		File directory;

		// Array of files in the directory.
		File[] files;

		// For reading a line of input from the user.
		Scanner scanner;

		// scanner reads from standard input.
		scanner = new Scanner(System.in);

		System.out.print("Enter a directory path name: ");

		path = scanner.nextLine().trim();

		directory = new File(path);

		// Check if input path is a directory
		// Give output if not a directory or directory not found.
		if (!directory.isDirectory()) {

			if (directory.exists() == false)
				// Directory path doesn't exist
				System.out.println("That directory doesn't exist!");

			else
				// Path entered is not a directory
				System.out.println("That's not a directory");

		} else {
			// list directory files
			// Print out files in a directory
			files = directory.listFiles();

			System.out.println("Files in directory \"" + directory + "\":\n");

			// Call our displayFiles function here to display the directory files
			displayFiles(files);

		}

		scanner.close(); // We should close all resources after utilization.

	} // end main()

	/**
	 * 
	 * Recursive method for displaying nested files and directories.
	 * 
	 * This method is recursive.
	 * 
	 * @param files
	 * 
	 *              - All files and directories, part of a directory.
	 * 
	 */

	public static void displayFiles(File[] files) {
		// Loop through the files array
		for (File file : files) {
			// If it's not a directory, print out file name
			if (!file.isDirectory()) {

				System.out.println(" " + file.getName());

			}

			// If is directory, print out directory name and files in directory files
			// For each directory found, we do a recursive call inside the directory to
			// print out files and
			// possible any other directory inside and so on...
			// until no other directory is found.
			if (file.isDirectory()) {

				System.out.println("\nFiles in directory \"" + file.getAbsolutePath() + "\":\n");

				displayFiles(file.listFiles());

			}

		}

	}

} // end class DirectoryList