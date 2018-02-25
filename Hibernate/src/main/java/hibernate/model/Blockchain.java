package hibernate.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Blockchain {
	
	@Id
	@Column(name = "Id")
    private String id;
	
	@Column(name = "Blockname")
    private String blockname;
	
	@Override
    public String toString() {
        return "Person [Id=" + id + "]";
    }
	
}
