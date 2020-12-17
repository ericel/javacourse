
public class Test {
	public static void main(String[] args) {
		Blog blog = new Blog("Blog title 1", "this is the blog description");

		
		if(!blog.checkIfAnyIsNull()) {
			System.out.println(blog.title);
			System.out.println(blog.description);
		}
	}
}

class Blog {
	// Blog Fields
    public String title;
    public String description;
    
    // Blog Class constructor
    Blog(String title, String description){
    	this.title       = title;
    	this.description = description;
    }

    
	Boolean checkIfAnyIsNull() {
		if(title == null || description == null)
			return true;
		return false;
	}
}
