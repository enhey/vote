package application;

public class List {
	String sdata;
	int ndata;
	public int getNdata() {
		return ndata;
	}


	public void setNdata(int ndata) {
		this.ndata = ndata;
	}


	public List(String sdata) {
		super();
		this.sdata = sdata;
	}
	

	public List() {
		super();
	}


	public String getSdata() {
		return sdata;
	}

	public void setSdata(String sdata) {
		this.sdata = sdata;
	}

	@Override
	public String toString() {
		return sdata;
	}
	

}
