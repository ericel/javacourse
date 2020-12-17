public class Sample {

	private int number;
	private String color;

	public Sample(int number) {
		this.number = number;
		this.color = "blue";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Sample other = (Sample) obj;
		if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
			return false;
		}
		return true;
	}

}
